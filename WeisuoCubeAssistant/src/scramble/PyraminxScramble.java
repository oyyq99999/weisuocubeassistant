package scramble;

import java.util.Random;

import util.EnDeCoderPyra;

public class PyraminxScramble extends Scramble {

	// length表示最短从length步开始搜索
	// DF, DL, DR, FL, FR, RL
	// U, L, R, B
	private int[][] state = { { 0, 1, 2, 3, 4, 5 }, // 棱位置
			{ 0, 0, 0, 0, 0, 0 },// 棱方向
			{ 0, 0, 0, 0, -1, -1 } };// 大角方向
	// u, l, r, b
	private int[] tips = { 0, 0, 0, 0 };
	private char[] permCost = new char[720];
	private char[] orientCost = new char[81 * 32];
	private EnDeCoderPyra coder = new EnDeCoderPyra();
	private int moves[][] = new int[2][100];

	public PyraminxScramble() {
		this(0);
	}

	public PyraminxScramble(int minLength) {
		this.length = minLength;
		initCostTable();
	}

	private void initCostTable() {
		// TODO Auto-generated method stub
		reset();
		for (int i = 0; i < 81 * 32; i++) {
			orientCost[i] = 255;
			if (i < 720)
				permCost[i] = 255;
		}
		int permCount = 0;
		int orientCount = 0;
		for (char depth = 0; permCount < 360 || orientCount < 81 * 32; depth++) {
			if (depth == 0) {
				permCost[0] = 0;
				orientCost[0] = 0;
				permCount++;
				orientCount++;
//				 System.out.println("Depth " + (int) depth + " initialized "
//				 + permCount + " perms " + orientCount + " orients");
				continue;
			} else {
				for (int i = 0; i < 81 * 32; i++) {
					if (orientCost[i] == depth - 1) {
						int orient = i;
						int code = orient;
						int[][] codeState = decode(code);
						int axis = -1;
						int turns = 0;
						for (axis = 0; axis < 4; axis++) {
							for (turns = 1; turns < 3; turns++) {
								for (int j = 0; j < 6; j++) {
									this.state[0][j] = codeState[0][j];
									this.state[1][j] = codeState[1][j];
									this.state[2][j] = codeState[2][j];
								}
								doMove(axis, turns);
								int newOrient = encode(state) % (81 * 32);
								if (orientCost[newOrient] == 255) {
									orientCost[newOrient] = depth;
									orientCount++;
								}
							}
						}
					}
					if (i < 720) {
						if (permCost[i] == depth - 1) {
							int perm = i;
							int code = perm * 81 * 32;
							int[][] codeState = decode(code);
							int axis = -1;
							int turns = 0;
							for (axis = 0; axis < 4; axis++) {
								for (turns = 1; turns < 3; turns++) {
									for (int j = 0; j < 6; j++) {
										this.state[0][j] = codeState[0][j];
										this.state[1][j] = codeState[1][j];
										this.state[2][j] = codeState[2][j];
									}
									doMove(axis, turns);
									int newPerm = encode(state) / 81 / 32;
									if (permCost[newPerm] == 255) {
										permCost[newPerm] = depth;
										permCount++;
									}
								}
							}
						}
					}
				}
			}
			// System.out.println("Depth " + (int) depth + " initialized "
			// + permCount + " perms " + orientCount + " orients");
		}
	}

	private int encode(int[][] state2) {
		// TODO Auto-generated method stub
		return coder.encode(state);
	}

	private int[][] decode(int code) {
		// TODO Auto-generated method stub
		return coder.decode(code);
	}

	private void doMove(int axis, int turns) {
		// TODO Auto-generated method stub
		switch (axis) {
		case 0:
			moveU(turns);
			break;
		case 1:
			moveL(turns);
			break;
		case 2:
			moveR(turns);
			break;
		case 3:
			moveB(turns);
			break;
		}
	}

	private void moveU(int turns) {
		// TODO Auto-generated method stub
		for (int i = 0; i < turns; i++) {
			int t = state[0][3];
			state[0][3] = state[0][4];
			state[0][4] = state[0][5];
			state[0][5] = t;

			t = state[1][3];
			state[1][3] = (state[1][4] + 1) % 2;
			state[1][4] = state[1][5];
			state[1][5] = (t + 1) % 2;

			state[2][0]++;
			state[2][0] %= 3;
		}
	}

	private void moveL(int turns) {
		// TODO Auto-generated method stub
		for (int i = 0; i < turns; i++) {
			int t = state[0][3];
			state[0][3] = state[0][1];
			state[0][1] = state[0][0];
			state[0][0] = t;

			t = state[1][3];
			state[1][3] = (state[1][1] + 1) % 2;
			state[1][1] = (state[1][0] + 1) % 2;
			state[1][0] = t;

			state[2][1]++;
			state[2][1] %= 3;
		}
	}

	private void moveR(int turns) {
		// TODO Auto-generated method stub
		for (int i = 0; i < turns; i++) {
			int t = state[0][4];
			state[0][4] = state[0][0];
			state[0][0] = state[0][2];
			state[0][2] = t;

			t = state[1][4];
			state[1][4] = state[1][0];
			state[1][0] = (state[1][2] + 1) % 2;
			state[1][2] = (t + 1) % 2;

			state[2][2]++;
			state[2][2] %= 3;
		}
	}

	private void moveB(int turns) {
		// TODO Auto-generated method stub
		for (int i = 0; i < turns; i++) {
			int t = state[0][5];
			state[0][5] = state[0][2];
			state[0][2] = state[0][1];
			state[0][1] = t;

			t = state[1][5];
			state[1][5] = state[1][2];
			state[1][2] = (state[1][1] + 1) % 2;
			state[1][1] = (t + 1) % 2;

			state[2][3]++;
			state[2][3] %= 3;
		}
	}

	private void reset() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 6; i++) {
			state[0][i] = i;
			state[1][i] = 0;
			state[2][i] = 0;
		}
		state[2][4] = state[2][5] = -1;
		for (int i = 0; i < 4; i++)
			tips[i] = 0;
		for (int i = 0; i < moves[0].length; i++) {
			moves[0][i] = -1;
			moves[1][i] = 0;
		}
	}

	private void randomState() {
		Random rand = new Random();
		int code;
		do {
			code = rand.nextInt(720);
			int[][] state = decode(code * 81 * 32);
			int total = 0;
			for (int i = 0; i < 6; i++) {
				for (int j = i + 1; j < 6; j++) {
					if (state[0][i] > state[0][j])
						total++;
				}
			}
			if (total % 2 == 0)
				break;
		} while (true);
		code *= 81 * 32;
		code += rand.nextInt(81) * 32 + rand.nextInt(32);
		this.state = decode(code);
		randomTips();
		for (int i = 0; i < 6; i++) {
			// System.out.print(state[0][i] + " ");
		}
		// System.out.println();
		for (int i = 0; i < 6; i++) {
			// System.out.print(state[1][i] + " ");
		}
		// System.out.println();
		for (int i = 0; i < 4; i++) {
			// System.out.print(state[2][i] + " ");
		}
		// System.out.println();
		for (int i = 0; i < 4; i++) {
			// System.out.print(tips[i] + " ");
		}
		// System.out.println();
	}

	private boolean solve(int stateCode, int moveCount, int lastAxis, int depth) {
		if (moveCount == 0) {
			if (stateCode == 0)
				return true;
		} else {
			if (permCost[stateCode / 81 / 32] > moveCount
					|| orientCost[stateCode % (81 * 32)] > moveCount)
				return false;
			else {
				int[][] stateNow = decode(stateCode);
				int axis = -1;
				int turns = 0;
				for (axis = 0; axis < 4; axis++) {
					if (axis != lastAxis) {
						for (turns = 1; turns < 3; turns++) {
							for (int i = 0; i < 6; i++) {
								this.state[0][i] = stateNow[0][i];
								this.state[1][i] = stateNow[1][i];
								this.state[2][i] = stateNow[2][i];
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
		reset();
		randomState();
		int code = encode(this.state);
		int i = 0;
		do {
			for (i = length; i < moves[0].length + 1; i++) {
				for (int j = 0; j < moves[0].length; j++) {
					moves[0][j] = -1;
					moves[1][j] = 0;
				}
				if (solve(code, i, -1, 0)) {
					inverseSolution();
					break;
				}
			}
		} while (i == moves[0].length);
		this.scrambleSequence = convertToNames();
		return this.scrambleSequence;
	}

	private String convertToNames() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			switch (tips[i]) {
			case 0:
				break;
			case 1:
				sb.append("ulrb".charAt(i));
				sb.append(" ");
				break;
			case 2:
				sb.append("ulrb".charAt(i));
				sb.append("' ");
				break;
			}
		}
		for (int i = 0; i < moves[0].length; i++) {
			if (moves[0][i] == -1)
				break;
			switch (moves[0][i]) {
			case 0:
				sb.append("U");
				break;
			case 1:
				sb.append("L");
				break;
			case 2:
				sb.append("R");
				break;
			case 3:
				sb.append("B");
				break;
			}
			switch (moves[1][i]) {
			case 1:
				sb.append(" ");
				break;
			case 2:
				sb.append("' ");
				break;
			}
		}
		return sb.toString().trim();
	}

	private void randomTips() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		for (int i = 0; i < 4; i++) {
			tips[i] = rand.nextInt(3);
		}
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

			t = 3 - moves[1][i];
			moves[1][i] = 3 - moves[1][j];
			moves[1][j] = t;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PyraminxScramble scr = new PyraminxScramble();
		long start = System.currentTimeMillis();
		// for (int i = 0; i < 100; i++)
		System.out.println(scr.scramble());
		System.out.println(System.currentTimeMillis() - start);
	}

}
