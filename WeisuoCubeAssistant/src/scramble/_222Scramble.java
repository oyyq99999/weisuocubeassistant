package scramble;

import java.util.Random;

import javax.microedition.lcdui.Form;

import util.EnDeCoder222;

public class _222Scramble extends Scramble {

	// length表示最短从length步开始搜索
	// UFL, URF, UBR, ULB, DLF, DFR, DRB, DBL
	private int[][] state = { { 0, 1, 2, 3, 4, 5, 6, 7 },// 位置
			{ 0, 0, 0, 0, 0, 0, 0, 0 } };// 方向
	private char[] permCost = new char[5040];
	private char[] orientCost = new char[729];
	private EnDeCoder222 coder = new EnDeCoder222();
	private int moves[][] = new int[2][100];
	private Form prepare = null;

	public _222Scramble() {
		this(0);
	}

	public _222Scramble(int minLength) {
		this.length = minLength;
		initCostTable();
	}

	public _222Scramble(int minLength, Form prepare) {
		this.prepare = prepare;
		this.length = minLength;
		initCostTable();
	}

	private void initCostTable() {
		reset();
		for (int i = 0; i < 5040; i++) {
			permCost[i] = 255;
			if (i < 729)
				orientCost[i] = 255;
		}
		int permCount = 0;
		int orientCount = 0;
		for (char depth = 0; permCount < 5040 || orientCount < 729; depth++) {
			if (depth == 0) {
				permCost[0] = 0;
				orientCost[0] = 0;
				permCount++;
				orientCount++;
				// System.out.println("Depth " + (int) depth + " initialized "
				// + permCount + " perms " + orientCount + " orients");
				if (prepare != null) {
					prepare.deleteAll();
					prepare.append("Depth: " + (int) depth + "\n" + permCount
							+ "/5040\n" + orientCount + "/729\n");
				}
				continue;
			} else {
				for (int i = 0; i < 5040; i++) {
					if (permCost[i] == depth - 1) {
						int perm = i;
						int code = perm * 729;
						int[][] codeState = decode(code);
						int axis = -1;
						int turns = 0;
						for (axis = 0; axis < 3; axis++) {
							for (turns = 1; turns < 4; turns++) {
								for (int j = 0; j < 8; j++) {
									this.state[0][j] = codeState[0][j];
									this.state[1][j] = codeState[1][j];
								}
								doMove(axis, turns);
								int newPerm = encode(state) / 729;
								if (permCost[newPerm] == 255) {
									permCost[newPerm] = depth;
									permCount++;
								}
							}
						}
					}
					if (i < 729) {
						if (orientCost[i] == depth - 1) {
							int orient = i;
							int code = orient;
							int[][] codeState = decode(code);
							int axis = -1;
							int turns = 0;
							for (axis = 0; axis < 3; axis++) {
								for (turns = 1; turns < 4; turns++) {
									for (int j = 0; j < 8; j++) {
										this.state[0][j] = codeState[0][j];
										this.state[1][j] = codeState[1][j];
									}
									doMove(axis, turns);
									int newOrient = encode(state) % 729;
									if (orientCost[newOrient] == 255) {
										orientCost[newOrient] = depth;
										orientCount++;
									}
								}
							}
						}
					}
				}
			}
			// System.out.println("Depth " + (int) depth + " initialized "
			// + permCount + " perms " + orientCount + " orients");
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Depth: " + (int) depth + "\n" + permCount
						+ "/5040\n" + orientCount + "/729");
			}
		}
	}

	private void doMove(int axis, int count) {
		switch (axis) {
		case 0:
			moveU(count);
			break;
		case 1:
			moveR(count);
			break;
		case 2:
			moveF(count);
			break;
		}
	}

	private int encode(int[][] state) {
		return coder.encode(state);
	}

	private int[][] decode(int code) {
		return coder.decode(code);
	}

	private void reset() {
		for (int i = 0; i < 8; i++) {
			state[0][i] = i;
			state[1][i] = 0;
		}
		for (int i = 0; i < moves[0].length; i++) {
			moves[0][i] = -1;
			moves[1][i] = 0;
		}
	}

	private void moveU(int n) {
		for (int i = 0; i < n; i++) {
			int t = state[0][0];
			state[0][0] = state[0][1];
			state[0][1] = state[0][2];
			state[0][2] = state[0][3];
			state[0][3] = t;

			t = state[1][0];
			state[1][0] = state[1][1];
			state[1][1] = state[1][2];
			state[1][2] = state[1][3];
			state[1][3] = t;
		}
	}

	private void moveR(int n) {
		for (int i = 0; i < n; i++) {
			int t = state[0][1];
			state[0][1] = state[0][5];
			state[0][5] = state[0][6];
			state[0][6] = state[0][2];
			state[0][2] = t;

			t = state[1][1];
			state[1][1] = (state[1][5] + 2) % 3;
			state[1][5] = (state[1][6] + 1) % 3;
			state[1][6] = (state[1][2] + 2) % 3;
			state[1][2] = (t + 1) % 3;
		}
	}

	private void moveF(int n) {
		for (int i = 0; i < n; i++) {
			int t = state[0][0];
			state[0][0] = state[0][4];
			state[0][4] = state[0][5];
			state[0][5] = state[0][1];
			state[0][1] = t;

			t = state[1][0];
			state[1][0] = (state[1][4] + 2) % 3;
			state[1][4] = (state[1][5] + 1) % 3;
			state[1][5] = (state[1][1] + 2) % 3;
			state[1][1] = (t + 1) % 3;
		}
	}

	private void randomState() {
		Random rand = new Random();
		int code;
		do {
			code = rand.nextInt(5040) * 729 + rand.nextInt(729);
		} while ((code % 729) % 3 != 0);
		this.state = decode(code);
	}

	private boolean solve(int stateCode, int moveCount, int lastAxis, int depth) {
		// System.out.println("Solving Depth " + depth);
		if (moveCount == 0) {
			if (stateCode == 0)
				return true;
		} else {
			if (permCost[stateCode / 729] > moveCount
					|| orientCost[stateCode % 729] > moveCount)
				return false;
			else {
				int[][] stateNow = decode(stateCode);
				int axis = -1;
				int turns = 0;
				for (axis = 0; axis < 3; axis++) {
					if (axis != lastAxis) {
						for (turns = 1; turns < 4; turns++) {
							for (int i = 0; i < 8; i++) {
								this.state[0][i] = stateNow[0][i];
								this.state[1][i] = stateNow[1][i];
							}
							moves[0][depth] = axis;
							moves[1][depth] = turns;
							doMove(axis, turns);
							if (solve(encode(this.state), moveCount - 1, axis,
									depth + 1))
								return true;
						}
					}
				}
			}
		}
		return false;
	}

	public String scramble() {
		// TODO Auto-generated method stub
		// for (int i = 0; i < 8; i++) {
		// System.out.print(state[0][i] + " ");
		// }
		// System.out.println();
		// for (int i = 0; i < 8; i++) {
		// System.out.print(state[1][i] + " ");
		// }
		// System.out.println();
		int i = 0;
		do {
			reset();
			randomState();
			int code = encode(this.state);
			// long start = System.currentTimeMillis();
			for (i = length; i < moves[0].length + 1; i++) {
				for (int j = 0; j < moves[0].length; j++) {
					moves[0][j] = -1;
					moves[1][j] = 0;
				}
				// System.out.println("Solve at Length " + i);
				if (solve(code, i, -1, 0)) {
					// System.out.println(System.currentTimeMillis() - start);
					inverseSolution();
					break;
				}
			}
		} while (i == moves[0].length + 1);
		this.scrambleSequence = convertToNames();
		// for (i = 0; i < 11; i++) {
		// if (moves[0][i] == -1)
		// break;
		// System.out.print(moves[0][i] + " ");
		// }
		// System.out.println();
		// for (i = 0; i < 11; i++) {
		// if (moves[1][i] == 0)
		// break;
		// System.out.print(moves[1][i] + " ");
		// }
		// System.out.println();
		return this.scrambleSequence;
	}

	private void inverseSolution() {
		// TODO Auto-generated method stub
		int endIndex;
		for (endIndex = moves[0].length - 1; endIndex >= 0; endIndex--) {
			if (moves[0][endIndex] >= 0)
				break;
		}
		int i;
		int j;
		for (i = 0, j = endIndex; i <= j; i++, j--) {
			int t = moves[0][i];
			moves[0][i] = moves[0][j];
			moves[0][j] = t;

			t = 4 - moves[1][i];
			moves[1][i] = 4 - moves[1][j];
			moves[1][j] = t;
		}
	}

	private String convertToNames() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < moves[0].length; i++) {
			if (moves[0][i] == -1)
				break;
			switch (moves[0][i]) {
			case 0:
				sb.append("U");
				break;
			case 1:
				sb.append("R");
				break;
			case 2:
				sb.append("F");
			}
			switch (moves[1][i]) {
			case 1:
				sb.append(" ");
				break;
			case 2:
				sb.append("2 ");
				break;
			case 3:
				sb.append("' ");
				break;
			}
		}
		return sb.toString().trim();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_222Scramble scr = new _222Scramble();
		long start = System.currentTimeMillis();
		// for (int code = 0; code < 100000; code++) {
		// if ((code % 729) % 3 != 0)
		// continue;
		// for (int i = 0; i < 12; i++) {
		// for (int j = 0; j < 11; j++) {
		// scr.moves[0][j] = -1;
		// scr.moves[1][j] = 0;
		// }
		// // System.out.println("Solve at Length " + i);
		// if (scr.solve(code, i, -1, 0)) {
		// // System.out.println(System.currentTimeMillis() - start);
		// break;
		// }
		// }
		// }
		System.out.println(scr.scramble());
		System.out.println((System.currentTimeMillis() - start));
	}
}
