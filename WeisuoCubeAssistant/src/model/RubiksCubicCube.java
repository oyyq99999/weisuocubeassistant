package model;

import java.util.Random;

import util.MathUtil;

public class RubiksCubicCube {
	public static final byte FL = 0;
	public static final byte BL = 1;
	public static final byte BR = 2;
	public static final byte FR = 3;
	public static final byte UF = 4;
	public static final byte UL = 5;
	public static final byte UB = 6;
	public static final byte UR = 7;
	public static final byte DF = 8;
	public static final byte DL = 9;
	public static final byte DB = 10;
	public static final byte DR = 11;
	public static final byte UFL = 0;
	public static final byte ULB = 1;
	public static final byte UBR = 2;
	public static final byte URF = 3;
	public static final byte DLF = 4;
	public static final byte DBL = 5;
	public static final byte DRB = 6;
	public static final byte DFR = 7;
	public static final byte FIRST_EDGE = FL;
	public static final byte LAST_EDGE = DR;
	public static final byte FIRST_MIDDLESLICE_EDGE = FL;
	public static final byte LAST_MIDDLESLICE_EDGE = FR;
	public static final byte FIRST_NONMIDDLESLICE_EDGE = UF;
	public static final byte LAST_NONMIDDLESLICE_EDGE = DR;
	public static final byte FIRST_CORNER = UFL;
	public static final byte LAST_CORNER = DFR;
	public static final byte EDGE_COUNT = LAST_EDGE + 1;
	public static final byte CORNER_COUNT = LAST_CORNER + 1;
	public static final byte MIDDLESLICE_EDGE_COUNT = LAST_MIDDLESLICE_EDGE
			- FIRST_MIDDLESLICE_EDGE + 1;
	public static final byte NONMIDDLESLICE_EDGE_COUNT = LAST_NONMIDDLESLICE_EDGE
			- FIRST_NONMIDDLESLICE_EDGE + 1;
	private static final byte[][] moveTable = {
			{ UFL, ULB, UBR, URF, UF, UL, UB, UR, // Permutations
					0, 0, 0, 0, 0, 0, 0, 0 // Orientations
			},// U
			{ DLF, DFR, DRB, DBL, DF, DR, DB, DL,// Permutations
					0, 0, 0, 0, 0, 0, 0, 0 // Orientations
			},// D
			{ UFL, DLF, DBL, ULB, FL, DL, BL, UL,// Permutations
					2, 1, 2, 1, 0, 0, 0, 0 // Orientations
			},// L
			{ UBR, DRB, DFR, URF, BR, DR, FR, UR,// Permutations
					2, 1, 2, 1, 0, 0, 0, 0 // Orientations
			},// R
			{ UFL, URF, DFR, DLF, FL, UF, FR, DF,// Permutations
					1, 2, 1, 2, 1, 1, 1, 1 // Orientations
			},// F
			{ ULB, DBL, DRB, UBR, BL, DB, BR, UB,// Permutations
					2, 1, 2, 1, 1, 1, 1, 1 // Orientations
			} };// B
	public byte[] edgePermutations = new byte[EDGE_COUNT];
	public byte[] cornerPermutations = new byte[CORNER_COUNT];
	public byte[] edgeOrientations = new byte[EDGE_COUNT];
	public byte[] cornerOrientations = new byte[CORNER_COUNT];

	public RubiksCubicCube() {
		reset();
	}

	public void reset() {
		// TODO Auto-generated method stub
		for (byte i = FIRST_EDGE; i <= LAST_EDGE; i++) {
			edgePermutations[i] = i;
			edgeOrientations[i] = 0;
		}
		for (byte i = FIRST_CORNER; i <= LAST_CORNER; i++) {
			cornerPermutations[i] = i;
			cornerOrientations[i] = 0;
		}
	}

	public short getChooseCode(byte[] chooseState) {
		boolean[] edgesChoosed = new boolean[EDGE_COUNT];
		for (int i = FIRST_EDGE; i <= LAST_EDGE; i++) {
			edgesChoosed[i] = false;
			if (chooseState[i] >= FIRST_MIDDLESLICE_EDGE
					&& chooseState[i] <= LAST_MIDDLESLICE_EDGE)
				edgesChoosed[i] = true;
		}
		int remaining = MIDDLESLICE_EDGE_COUNT;
		short choose = 0;
		byte edge;
		for (edge = 0; remaining > 0; edge++) {
			if (edgesChoosed[edge]) {
				remaining--;
			} else {
				choose += MathUtil.Cnk(EDGE_COUNT - 1 - edge, remaining - 1);
			}
		}
		return choose;
	}

	public byte[] getChooseState(short choose) {
		byte[] edges = new byte[EDGE_COUNT];
		for (int i = FIRST_EDGE; i <= LAST_EDGE; i++) {
			edges[i] = -1;
		}
		byte edge;
		int digit = 0;
		for (edge = 0; edge < MIDDLESLICE_EDGE_COUNT; edge++) {
			while (true) {
				// combinations begin with digit
				int combinations = MathUtil.Cnk(EDGE_COUNT - 1 - digit,
						MIDDLESLICE_EDGE_COUNT - 1 - edge);
				if (choose >= combinations) {
					digit++;
					choose -= combinations;
				} else
					break;
			}
			edges[digit++] = (byte) (FIRST_MIDDLESLICE_EDGE + edge);
		}
		return edges;
	}

	public short getEdgeOrientationCode(byte[] edgeOrient) {
		short n = 0;
		for (int i = 0; i < EDGE_COUNT - 2; i++) {
			n += edgeOrient[i];
			n <<= 1;// n *= 2;
		}
		n += edgeOrient[EDGE_COUNT - 2];
		return n;
	}

	public byte[] getEdgeOrientationState(short edgeOrientCode) {
		byte[] edgeOrient = new byte[EDGE_COUNT];
		int total = 0;
		for (int i = EDGE_COUNT - 2; i >= 0; i--) {
			edgeOrient[i] = (byte) (edgeOrientCode % 2);
			total += edgeOrient[i];
			edgeOrientCode >>= 1;// edgeOrientCode /= 2;
		}
		edgeOrient[EDGE_COUNT - 1] = (byte) ((2 - total % 2) % 2);
		return edgeOrient;
	}

	public short getCornerOrientationCode(byte[] cornerOrient) {
		short n = 0;
		for (int i = 0; i < CORNER_COUNT - 2; i++) {
			n += cornerOrient[i];
			n *= 3;
		}
		n += cornerOrient[CORNER_COUNT - 2];
		return n;
	}

	public byte[] getCornerOrientationState(short cornerOrientCode) {
		byte[] cornerOrient = new byte[CORNER_COUNT];
		int total = 0;
		for (int i = CORNER_COUNT - 2; i >= 0; i--) {
			cornerOrient[i] = (byte) (cornerOrientCode % 3);
			total += cornerOrient[i];
			cornerOrientCode /= 3;
		}
		cornerOrient[CORNER_COUNT - 1] = (byte) ((3 - total % 3) % 3);
		return cornerOrient;
	}

	public int getUdEdgePermutationCode(byte[] permutation) {
		int code = 0;
		int nonMiddleSliceEdgeCount = 0;
		for (int i = LAST_EDGE; i >= FIRST_EDGE; i--) {
			if (permutation[i] < FIRST_NONMIDDLESLICE_EDGE
					|| permutation[i] > LAST_NONMIDDLESLICE_EDGE)
				continue;
			nonMiddleSliceEdgeCount++;
			int count = 0;
			for (int j = FIRST_EDGE; j < i; j++) {
				if (permutation[j] < FIRST_NONMIDDLESLICE_EDGE
						|| permutation[j] > LAST_NONMIDDLESLICE_EDGE)
					continue;
				if (permutation[j] > permutation[i])
					count++;
			}
			code = code
					* (NONMIDDLESLICE_EDGE_COUNT - nonMiddleSliceEdgeCount + 1)
					+ count;
		}
		return code;
	}

	public byte[] getUdEdgePermutationState(int permutationCode, short choose) {

		byte[] udEdgePermutation = new byte[EDGE_COUNT];
		byte[] chooseState = getChooseState(choose);
		int j = 1;
		for (byte i = FIRST_MIDDLESLICE_EDGE; i <= LAST_MIDDLESLICE_EDGE; i++) {
			udEdgePermutation[i] = -1;
		}
		for (byte i = FIRST_NONMIDDLESLICE_EDGE; i <= LAST_NONMIDDLESLICE_EDGE; i++) {
			if (i > FIRST_NONMIDDLESLICE_EDGE)
				j *= (i - FIRST_NONMIDDLESLICE_EDGE);
			udEdgePermutation[i] = (byte) (i - FIRST_NONMIDDLESLICE_EDGE);
		}
		for (byte i = LAST_NONMIDDLESLICE_EDGE; i > FIRST_NONMIDDLESLICE_EDGE; i--) {
			int x = permutationCode / j;
			byte tmp = udEdgePermutation[i - x];
			for (int k = i - x; k < i; k++) {
				udEdgePermutation[k] = udEdgePermutation[k + 1];
			}
			udEdgePermutation[i] = tmp;
			permutationCode -= x * j;
			j /= (i - FIRST_NONMIDDLESLICE_EDGE);
		}
		j = FIRST_NONMIDDLESLICE_EDGE;
		for (int i = 0; i < EDGE_COUNT; i++) {
			if (chooseState[i] < 0) {
				byte t = udEdgePermutation[i];
				udEdgePermutation[i] = udEdgePermutation[j];
				udEdgePermutation[j++] = t;
			}
		}
		for (int i = 0; i < EDGE_COUNT; i++) {
			if (udEdgePermutation[i] >= 0)
				udEdgePermutation[i] += MIDDLESLICE_EDGE_COUNT;
		}
		return udEdgePermutation;
	}

	public byte getESliceEdgePermutationCode(byte[] permutation) {
		byte code = 0;
		int middleSliceEdgeCount = 0;
		for (int i = LAST_EDGE; i >= FIRST_EDGE; i--) {
			if (permutation[i] < FIRST_MIDDLESLICE_EDGE
					|| permutation[i] > LAST_MIDDLESLICE_EDGE)
				continue;
			middleSliceEdgeCount++;
			int count = 0;
			for (int j = FIRST_EDGE; j < i; j++) {
				if (permutation[j] < FIRST_MIDDLESLICE_EDGE
						|| permutation[j] > LAST_MIDDLESLICE_EDGE)
					continue;
				if (permutation[j] > permutation[i])
					count++;
			}
			code = (byte) (code
					* (MIDDLESLICE_EDGE_COUNT - middleSliceEdgeCount + 1) + count);
		}
		return code;
	}

	public byte[] getESliceEdgePermutationState(byte permutationCode,
			short choose) {
		byte[] eSliceEdgePermutation = new byte[EDGE_COUNT];
		byte[] chooseState = getChooseState(choose);
		int j = 1;
		for (byte i = FIRST_NONMIDDLESLICE_EDGE; i <= LAST_NONMIDDLESLICE_EDGE; i++)
			eSliceEdgePermutation[i] = -1;
		for (byte i = FIRST_MIDDLESLICE_EDGE; i <= LAST_MIDDLESLICE_EDGE; i++) {
			if (i > FIRST_MIDDLESLICE_EDGE)
				j *= (i - FIRST_MIDDLESLICE_EDGE);
			eSliceEdgePermutation[i] = (byte) (i - FIRST_MIDDLESLICE_EDGE);
		}
		for (byte i = LAST_MIDDLESLICE_EDGE; i > FIRST_MIDDLESLICE_EDGE; i--) {
			int x = permutationCode / j;
			byte tmp = eSliceEdgePermutation[i - x];
			for (int k = i - x; k < i; k++) {
				eSliceEdgePermutation[k] = eSliceEdgePermutation[k + 1];
			}
			eSliceEdgePermutation[i] = tmp;
			permutationCode -= x * j;
			j /= (i - FIRST_MIDDLESLICE_EDGE);
		}
		j = LAST_MIDDLESLICE_EDGE;
		for (int i = EDGE_COUNT - 1; i >= 0; i--) {
			if (chooseState[i] >= 0) {
				byte t = eSliceEdgePermutation[i];
				eSliceEdgePermutation[i] = eSliceEdgePermutation[j];
				eSliceEdgePermutation[j--] = t;
			}
		}
		return eSliceEdgePermutation;
	}

	public int getCornerPermutationCode(byte[] permutation) {
		int code = 0;
		for (int i = LAST_CORNER; i >= FIRST_CORNER; i--) {
			int count = 0;
			for (int j = FIRST_CORNER; j < i; j++) {
				if (permutation[j] > permutation[i])
					count++;
			}
			code = code * (i - FIRST_CORNER + 1) + count;
		}
		return code;
	}

	public byte[] getCornerPermutationState(int permutationCode) {
		byte[] cornerPermutation = new byte[CORNER_COUNT];
		int j = 1;
		for (byte i = FIRST_CORNER; i <= LAST_CORNER; i++) {
			if (i > FIRST_CORNER)
				j *= (i - FIRST_CORNER);
			cornerPermutation[i] = (byte) (i - FIRST_CORNER);
		}
		for (byte i = LAST_CORNER; i > FIRST_CORNER; i--) {
			int x = permutationCode / j;
			byte tmp = cornerPermutation[i - x];
			for (int k = i - x; k < i; k++) {
				cornerPermutation[k] = cornerPermutation[k + 1];
			}
			cornerPermutation[i] = tmp;
			permutationCode -= x * j;
			j /= (i - FIRST_CORNER);
		}
		return cornerPermutation;
	}

	public void doMove(byte axis, byte turns) {
		for (int i = 0; i < turns; i++) {
			byte tmp = cornerPermutations[moveTable[axis][0]];
			cornerPermutations[moveTable[axis][0]] = cornerPermutations[moveTable[axis][3]];
			cornerPermutations[moveTable[axis][3]] = cornerPermutations[moveTable[axis][2]];
			cornerPermutations[moveTable[axis][2]] = cornerPermutations[moveTable[axis][1]];
			cornerPermutations[moveTable[axis][1]] = tmp;

			tmp = cornerOrientations[moveTable[axis][0]];
			cornerOrientations[moveTable[axis][0]] = (byte) ((cornerOrientations[moveTable[axis][3]] + moveTable[axis][11]) % 3);
			cornerOrientations[moveTable[axis][3]] = (byte) ((cornerOrientations[moveTable[axis][2]] + moveTable[axis][10]) % 3);
			cornerOrientations[moveTable[axis][2]] = (byte) ((cornerOrientations[moveTable[axis][1]] + moveTable[axis][9]) % 3);
			cornerOrientations[moveTable[axis][1]] = (byte) ((tmp + moveTable[axis][8]) % 3);

			tmp = edgePermutations[moveTable[axis][4]];
			edgePermutations[moveTable[axis][4]] = edgePermutations[moveTable[axis][7]];
			edgePermutations[moveTable[axis][7]] = edgePermutations[moveTable[axis][6]];
			edgePermutations[moveTable[axis][6]] = edgePermutations[moveTable[axis][5]];
			edgePermutations[moveTable[axis][5]] = tmp;

			tmp = edgeOrientations[moveTable[axis][4]];
			edgeOrientations[moveTable[axis][4]] = (byte) ((edgeOrientations[moveTable[axis][7]] + moveTable[axis][15]) % 2);
			edgeOrientations[moveTable[axis][7]] = (byte) ((edgeOrientations[moveTable[axis][6]] + moveTable[axis][14]) % 2);
			edgeOrientations[moveTable[axis][6]] = (byte) ((edgeOrientations[moveTable[axis][5]] + moveTable[axis][13]) % 2);
			edgeOrientations[moveTable[axis][5]] = (byte) ((tmp + moveTable[axis][12]) % 2);
		}
	}

	public static String translateMoveToName(int axis, int turns) {
		String name = new String();
		name = name + "UDLRFB".charAt(axis);
		name = name + " 2'".charAt(turns - 1);
		return name.trim();
	}

	public static int translateNameToAxis(String name) {
		return "UDLRFB".indexOf(name.charAt(0));
	}

	public static int translateNameToTurns(String name) {
		return " 2'".indexOf((name + " ").charAt(1)) + 1;
	}

	public void randomState() {
		Random rand = new Random();
		byte[] cornerPermutation;
		byte[] edgeOrientation;
		byte[] cornerOrientation;
		byte[] edgePermutationState;
		while (true) {
			int udEdgePermutationCode = rand.nextInt(40320);
			byte eSliceEdgePermutationCode = (byte) rand.nextInt(24);
			short chooseCode = (short) rand.nextInt(495);
			int cornerPermutationCode = rand.nextInt(40320);
			short edgeOrientationCode = (short) rand.nextInt(2048);
			short cornerOrientationCode = (short) rand.nextInt(2187);
			byte[] udEdgePermutationState = getUdEdgePermutationState(
					udEdgePermutationCode, chooseCode);
			byte[] eSliceEdgePermutationState = getESliceEdgePermutationState(
					eSliceEdgePermutationCode, chooseCode);
			cornerPermutation = getCornerPermutationState(cornerPermutationCode);
			edgeOrientation = getEdgeOrientationState(edgeOrientationCode);
			cornerOrientation = getCornerOrientationState(cornerOrientationCode);
			edgePermutationState = new byte[EDGE_COUNT];
			for (int i = FIRST_EDGE; i <= LAST_EDGE; i++) {
				edgePermutationState[i] = udEdgePermutationState[i] > eSliceEdgePermutationState[i] ? udEdgePermutationState[i]
						: eSliceEdgePermutationState[i];
			}
			int parity = 0;
			for (int i = FIRST_EDGE; i < LAST_EDGE; i++) {
				for (int j = i + 1; j <= LAST_EDGE; j++) {
					if (edgePermutationState[i] > edgePermutationState[j])
						parity++;
				}
			}
			for (int i = FIRST_CORNER; i < LAST_CORNER; i++) {
				for (int j = i + 1; j <= LAST_CORNER; j++) {
					if (cornerPermutation[i] > cornerPermutation[j])
						parity++;
				}
			}
			if (parity % 2 == 0) {
				// System.out.println("choose = " + chooseCode + " "
				// + getChooseCode(edgePermutationState));
				// System.out.println("udperm = " + udEdgePermutationCode + " "
				// + getUdEdgePermutationCode(edgePermutationState));
				// System.out.println("eperm = " + eSliceEdgePermutationCode +
				// " "
				// + getESliceEdgePermutationCode(edgePermutationState));
				break;
			}
		}
		this.edgeOrientations = edgeOrientation;
		this.edgePermutations = edgePermutationState;
		this.cornerOrientations = cornerOrientation;
		this.cornerPermutations = cornerPermutation;
	}

	public static void main(String args[]) {
		RubiksCubicCube cube = new RubiksCubicCube();
		cube.randomState();
		// byte[] perm = { 11, 1, 5, 6, 2, 4, 0, 8, 9, 10, 3, 7 };
		// int code = cube.getChooseCode(chooseState);
		// System.out.println(code);
		// chooseState = cube.getChooseState((short) code);
		// byte[] perm = { 0, 1, 5, 4, 11, 10, 9, 8, 7, 6, 3, 2 };
		// byte[] perm = { 3, 2, 1, 0, 5, 7, 6, 4 };
		// byte code;
		// short choose;
		// System.out.println(code = cube.getESliceEdgePermutationCode(perm));
		// choose = cube.getChooseCode(perm);
		// long start = System.currentTimeMillis();
		// perm = cube.getESliceEdgePermutationState(code, choose);
		// System.out.println(System.currentTimeMillis() - start);
		// cube.doMove((byte) 0, (byte) 1, false);
		// cube.doMove((byte) 4, (byte) 1, false);
		// for (int i = 0; i < 12; i++) {
		// System.out.print(perm[i] + " ");
		// }
		// for (int i = 0; i < 8; i++) {
		// System.out.print(cube.udEdgePermutations[i] + " ");
		// }
		// System.out.println();
		// for (int i = 0; i < 12; i++) {
		// System.out.print(cube.edgeOrientations[i] + " ");
		// }
		// System.out.println();
		// System.out.println(cube.getChooseCode());
	}
}
