package util;

import javax.microedition.lcdui.Form;

import model.RubiksCubicCube;

public class TwoPhaseSearch {

	private static final int NOT_SOLVED = -1;
	private static final int PHASE1_SOLVED = 1;
	private static final int PHASE2_SOLVED = 2;
	private static final int TOTAL_SOLVED = 0;
	private static final int NOT_SOLVABLE = -2;
	private static TwoPhaseSearch searcher = null;
	private static PruningTable pruningTable = null;
	private static MoveTable moveTable = null;
	private byte phase1Moves[][] = new byte[2][20];
	private byte phase2Moves[][] = new byte[2][20];
	private RubiksCubicCube scrambled = new RubiksCubicCube();

	private TwoPhaseSearch() {
		moveTable = MoveTable.getInstance();
		pruningTable = PruningTable.getInstance();
	}

	private TwoPhaseSearch(Form prepare) {
		moveTable = MoveTable.getInstance(prepare);
		pruningTable = PruningTable.getInstance(prepare);
	}

	public static TwoPhaseSearch getInstance() {
		if (searcher == null)
			searcher = new TwoPhaseSearch();
		return searcher;
	}

	public static TwoPhaseSearch getInstance(Form prepare) {
		// TODO Auto-generated method stub
		if (searcher == null)
			searcher = new TwoPhaseSearch(prepare);
		return searcher;
	}

	private int search1(short choose, short flip, short twist, int moveCount,
			int lastAxis, int lastAxis2, int depth, int minLength, int maxLength) {
		if (moveCount == 0) {
			if (choose == 0 && flip == 0 && twist == 0)
				return PHASE1_SOLVED;
		} else {
			if (pruningTable.getChooseFlipPruningTableValue(choose, flip) > moveCount
					|| pruningTable.getChooseTwistPruningTableValue(choose,
							twist) > moveCount
					|| pruningTable.getFlipTwistPruningTableValue(flip, twist) > moveCount) {
				return NOT_SOLVABLE;
			} else {
				byte axis = -1;
				byte turns = 0;
				for (axis = 0; axis < 6; axis++) {
					if (lastAxis == -1
							|| (lastAxis2 == -1 && axis != lastAxis)
							|| (lastAxis / 2 != lastAxis2 / 2 && axis != lastAxis)
							|| (lastAxis / 2 == lastAxis2 / 2 && axis / 2 != lastAxis / 2)) {
						for (turns = 1; turns <= 3; turns++) {
							phase1Moves[0][depth] = axis;
							phase1Moves[1][depth] = turns;
							int result = -3;
							if ((result = search1(
									moveTable.getChooseMoveTableValue(choose,
											axis, turns),
									moveTable.getFlipMoveTableValue(flip, axis,
											turns),
									moveTable.getTwistMoveTableValue(twist,
											axis, turns), moveCount - 1, axis,
									lastAxis, depth + 1, minLength, maxLength)) == PHASE1_SOLVED) {
								RubiksCubicCube cube = new RubiksCubicCube();
								for (int i = RubiksCubicCube.FIRST_EDGE; i <= RubiksCubicCube.LAST_EDGE; i++) {
									cube.edgeOrientations[i] = scrambled.edgeOrientations[i];
									cube.edgePermutations[i] = scrambled.edgePermutations[i];
								}
								for (int i = RubiksCubicCube.FIRST_CORNER; i <= RubiksCubicCube.LAST_CORNER; i++) {
									cube.cornerOrientations[i] = scrambled.cornerOrientations[i];
									cube.cornerPermutations[i] = scrambled.cornerPermutations[i];
								}
								for (int i = 0; i <= depth; i++) {
									cube.doMove(phase1Moves[0][i],
											phase1Moves[1][i]);
								}
								int udEdge = cube
										.getUdEdgePermutationCode(cube.edgePermutations);
								byte eSliceEdge = cube
										.getESliceEdgePermutationCode(cube.edgePermutations);
								int corner = cube
										.getCornerPermutationCode(cube.cornerPermutations);
								if (solve2(
										udEdge,
										eSliceEdge,
										corner,
										minLength - depth - 1,
										maxLength - depth - 1,
										phase1Moves[0][depth],
										depth > 0
												&& (phase1Moves[1][depth] / 2 == phase1Moves[1][depth - 1] / 2)) == PHASE2_SOLVED) {
									return PHASE2_SOLVED;
								}
							} else if (result == PHASE2_SOLVED)
								return result;
						}
					}
				}
			}
		}
		return NOT_SOLVED;
	}

	private int search2(int udEdge, byte eSliceEdge, int corner, int moveCount,
			int lastAxis, int lastAxis2, int depth, int phase1Axis,
			boolean parallel) {
		if (moveCount == 0) {
			if (udEdge == 0 && eSliceEdge == 0 && corner == 0)
				return PHASE2_SOLVED;
		} else {
			if (pruningTable.getUdESlicePruningTableValue(udEdge, eSliceEdge) > moveCount
					|| pruningTable.getCornerESlicePruningTableValue(corner,
							eSliceEdge) > moveCount) {
				return NOT_SOLVABLE;
			} else {
				byte axis = -1;
				byte turns = 0;
				for (axis = 0; axis < 6; axis++) {
					if (lastAxis == -1
							|| (lastAxis2 == -1 && axis != lastAxis)
							|| (lastAxis / 2 != lastAxis2 / 2 && axis != lastAxis)
							|| (lastAxis / 2 == lastAxis2 / 2 && axis / 2 != lastAxis / 2)) {
						// 第二阶段第一步和第一阶段最后一步(两步)不能平行
						if (depth == 0) {
							if (parallel) {
								if (axis / 2 == phase1Axis / 2)
									continue;
							} else {
								if (axis == phase1Axis)
									continue;
							}
						}
						for (turns = 1; turns <= 3; turns++) {
							if (axis != 0 && axis != 1 && turns != 2)
								continue;
							phase2Moves[0][depth] = axis;
							phase2Moves[1][depth] = turns;
							if (search2(moveTable.getUdEdgeMoveTableValue(
									udEdge, axis, turns),
									moveTable.getESliceEdgeMoveTableValue(
											eSliceEdge, axis, turns),
									moveTable.getCornerMoveTableValue(corner,
											axis, turns), moveCount - 1, axis,
									lastAxis, depth + 1, phase1Axis, parallel) == PHASE2_SOLVED) {
								return PHASE2_SOLVED;
							}
						}
					}
				}
			}
		}
		return NOT_SOLVED;
	}

	private int solve1(short choose, short flip, short twist, int minLength,
			int maxLength) {
		for (int i = minLength; i <= maxLength && i < phase1Moves[0].length + 1; i++) {
			for (int j = 0; j < phase1Moves[0].length; j++) {
				phase1Moves[0][j] = -1;
				phase1Moves[1][j] = 0;
			}
			for (int j = 0; j < phase2Moves[0].length; j++) {
				phase2Moves[0][j] = -1;
				phase2Moves[1][j] = 0;
			}
			int result;
			if ((result = search1(choose, flip, twist, i, -1, -1, 0, minLength,
					maxLength)) == PHASE2_SOLVED) {
				return TOTAL_SOLVED;
			} else if (result == PHASE1_SOLVED) {
				RubiksCubicCube cube = new RubiksCubicCube();
				for (int k = RubiksCubicCube.FIRST_EDGE; k <= RubiksCubicCube.LAST_EDGE; k++) {
					cube.edgeOrientations[k] = scrambled.edgeOrientations[k];
					cube.edgePermutations[k] = scrambled.edgePermutations[k];
				}
				for (int k = RubiksCubicCube.FIRST_CORNER; k <= RubiksCubicCube.LAST_CORNER; k++) {
					cube.cornerOrientations[k] = scrambled.cornerOrientations[k];
					cube.cornerPermutations[k] = scrambled.cornerPermutations[k];
				}
				int udEdge = cube
						.getUdEdgePermutationCode(cube.edgePermutations);
				byte eSliceEdge = cube
						.getESliceEdgePermutationCode(cube.edgePermutations);
				int corner = cube
						.getCornerPermutationCode(cube.cornerPermutations);
				if (solve2(udEdge, eSliceEdge, corner, minLength, maxLength,
						-1, false) == PHASE2_SOLVED) {
					return TOTAL_SOLVED;
				}
			}
		}
		return NOT_SOLVED;
	}

	private int solve2(int udEdge, byte eSliceEdge, int corner, int minLength2,
			int maxLength2, int phase1Axis, boolean parallel) {
		for (int i = minLength2; i <= maxLength2
				&& i < phase2Moves[0].length + 1; i++) {
			for (int j = 0; j < phase2Moves[0].length; j++) {
				phase2Moves[0][j] = -1;
				phase2Moves[1][j] = 0;
			}
			if (search2(udEdge, eSliceEdge, corner, i, -1, -1, 0, phase1Axis,
					parallel) == PHASE2_SOLVED) {
				return PHASE2_SOLVED;
			}
		}
		return NOT_SOLVED;
	}

	public String scramble(int maxLength) {
		scrambled.randomState();
		short choose = scrambled.getChooseCode(scrambled.edgePermutations);
		short flip = scrambled
				.getEdgeOrientationCode(scrambled.edgeOrientations);
		short twist = scrambled
				.getCornerOrientationCode(scrambled.cornerOrientations);
		if (solve1(choose, flip, twist, 0, maxLength) == TOTAL_SOLVED)
			return convertToNames(true);
		else
			return "NOT_SOLVED";
	}

	public String solve(RubiksCubicCube cube, int minLength, int maxLength) {
		scrambled = cube;
		short choose = scrambled.getChooseCode(scrambled.edgePermutations);
		short flip = scrambled
				.getEdgeOrientationCode(scrambled.edgeOrientations);
		short twist = scrambled
				.getCornerOrientationCode(scrambled.cornerOrientations);
		if (solve1(choose, flip, twist, minLength, maxLength) == TOTAL_SOLVED)
			return convertToNames(false);
		else
			return "NOT_SOLVED";
	}

	private String convertToNames(boolean generator) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (generator) {
			int i;
			for (i = phase2Moves[0].length - 1; i >= 0 && phase2Moves[0][i] < 0; i--)
				;
			while (i >= 0) {
				sb.append(RubiksCubicCube.translateMoveToName(
						phase2Moves[0][i], 4 - phase2Moves[1][i]) + " ");
				i--;
			}
			// sb.append(". ");
			for (i = phase1Moves[0].length - 1; i >= 0 && phase1Moves[0][i] < 0; i--)
				;
			while (i >= 0) {
				sb.append(RubiksCubicCube.translateMoveToName(
						phase1Moves[0][i], 4 - phase1Moves[1][i]) + " ");
				i--;
			}
		} else {
			for (int i = 0; i < phase1Moves[0].length && phase1Moves[0][i] >= 0; i++) {
				sb.append(RubiksCubicCube.translateMoveToName(
						phase1Moves[0][i], phase1Moves[1][i]) + " ");
			}
			// sb.append(". ");
			for (int i = 0; i < phase2Moves[0].length && phase2Moves[0][i] >= 0; i++) {
				sb.append(RubiksCubicCube.translateMoveToName(
						phase2Moves[0][i], phase2Moves[1][i]) + " ");
			}
		}
		return sb.toString().trim();
	}

	public static void main(String args[]) {
		System.out.println(TwoPhaseSearch.getInstance().scramble(20));
		System.out.println();
	}

}
