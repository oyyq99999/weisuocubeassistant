package scramble;

import java.util.Random;

public class SkewbScramble extends Scramble {

	private static int[] centerperm = new int[6];
	private static int[] twst = new int[4];
	private static int[] fixedtwst = new int[4];
	private static final int[] fact = { 1, 1, 2 / 2, 6 / 2, 24 / 2, 120 / 2,
			720 / 2 };
	private static char[][] permmv = new char[4320][4];
	private static char[][] twstmv = new char[2187][4];
	private static byte[] permprun = new byte[4320];
	private static byte[] twstprun = new byte[2187];

	private static final String[] move2str = { "R ", "R' ", "L ", "L' ", "D ",
			"D' ", "B ", "B' " };
	private int[] sol = new int[12];

	private static boolean inited = false;

	private static SkewbScramble instance = null;

	private SkewbScramble(byte length) {
		this.length = length;
		init();
	}

	public static SkewbScramble getInstance(byte length) {
		if (instance == null) {
			instance = new SkewbScramble(length);
		}
		instance.length = length;
		return instance;
	}

	private static final byte[][] cornerpermmv = new byte[][] {
			{ 6, 5, 10, 1 }, { 9, 7, 4, 2 }, { 3, 11, 8, 0 }, { 10, 1, 6, 5 },
			{ 0, 8, 11, 3 }, { 7, 9, 2, 4 }, { 4, 2, 9, 7 }, { 11, 3, 0, 8 },
			{ 1, 10, 5, 6 }, { 8, 0, 3, 11 }, { 2, 4, 7, 9 }, { 5, 6, 1, 10 } };

	private static final byte[] ori = new byte[] { 0, 1, 2, 0, 2, 1, 1, 2, 0,
			2, 1, 0 };

	private static int getpermmv(int idx, int move) {
		int centerindex = idx / 12;
		int cornerindex = idx % 12;
		int val = 0x543210;
		int parity = 0;
		for (int i = 0; i < 5; i++) {
			int p = fact[5 - i];
			int v = centerindex / p;
			centerindex -= v * p;
			parity ^= v;
			v <<= 2;
			centerperm[i] = (val >> v) & 0xf;
			int m = (1 << v) - 1;
			val = (val & m) + ((val >> 4) & ~m);
		}
		if ((parity & 1) == 0) {
			centerperm[5] = val;
		} else {
			centerperm[5] = centerperm[4];
			centerperm[4] = val;
		}
		int t;
		if (move == 0) {
			t = centerperm[0];
			centerperm[0] = centerperm[1];
			centerperm[1] = centerperm[3];
			centerperm[3] = t;
		} else if (move == 1) {
			t = centerperm[0];
			centerperm[0] = centerperm[4];
			centerperm[4] = centerperm[2];
			centerperm[2] = t;
		} else if (move == 2) {
			t = centerperm[1];
			centerperm[1] = centerperm[2];
			centerperm[2] = centerperm[5];
			centerperm[5] = t;
		} else if (move == 3) {
			t = centerperm[3];
			centerperm[3] = centerperm[5];
			centerperm[5] = centerperm[4];
			centerperm[4] = t;
		}
		val = 0x543210;
		for (int i = 0; i < 4; i++) {
			int v = centerperm[i] << 2;
			centerindex *= 6 - i;
			centerindex += (val >> v) & 0xf;
			val -= 0x111110L << v;
		}
		return centerindex * 12 + cornerpermmv[cornerindex][move];
	}

	private static int gettwstmv(int idx, int move) {
		for (int i = 0; i < 4; i++) {
			fixedtwst[i] = idx % 3;
			idx /= 3;
		}
		for (int i = 0; i < 3; i++) {
			twst[i] = idx % 3;
			idx /= 3;
		}
		twst[3] = (6 - twst[0] - twst[1] - twst[2]) % 3;
		fixedtwst[move] = (fixedtwst[move] + 1) % 3;
		int t;
		switch (move) {
		case 0:
			t = twst[0];
			twst[0] = twst[2] + 2;
			twst[2] = twst[1] + 2;
			twst[1] = t + 2;
			break;
		case 1:
			t = twst[0];
			twst[0] = twst[1] + 2;
			twst[1] = twst[3] + 2;
			twst[3] = t + 2;
			break;
		case 2:
			t = twst[0];
			twst[0] = twst[3] + 2;
			twst[3] = twst[2] + 2;
			twst[2] = t + 2;
			break;
		case 3:
			t = twst[1];
			twst[1] = twst[2] + 2;
			twst[2] = twst[3] + 2;
			twst[3] = t + 2;
			break;
		}
		for (int i = 2; i >= 0; i--) {
			idx = idx * 3 + twst[i] % 3;
		}
		for (int i = 3; i >= 0; i--) {
			idx = idx * 3 + fixedtwst[i];
		}
		return idx;
	}

	private static void init() {
		if (inited) {
			return;
		}
		for (int i = 0; i < 4320; i++) {
			permprun[i] = -1;
			for (int j = 0; j < 4; j++) {
				permmv[i][j] = (char) getpermmv(i, j);
			}
		}
		for (int i = 0; i < 2187; i++) {
			twstprun[i] = -1;
			for (int j = 0; j < 4; j++) {
				twstmv[i][j] = (char) gettwstmv(i, j);
			}
		}
		permprun[0] = 0;
		for (int l = 0; l < 6; l++) {
			for (int p = 0; p < 4320; p++) {
				if (permprun[p] == l) {
					for (int m = 0; m < 4; m++) {
						int q = p;
						for (int c = 0; c < 2; c++) {
							q = permmv[q][m];
							if (permprun[q] == -1) {
								permprun[q] = (byte) (l + 1);
							}
						}
					}
				}
			}
		}
		twstprun[0] = 0;
		for (int l = 0; l < 6; l++) {
			for (int p = 0; p < 2187; p++) {
				if (twstprun[p] == l) {
					for (int m = 0; m < 4; m++) {
						int q = p;
						for (int c = 0; c < 2; c++) {
							q = twstmv[q][m];
							if (twstprun[q] == -1) {
								twstprun[q] = (byte) (l + 1);
							}
						}
					}
				}
			}
		}
		inited = true;
	}

	protected boolean search(int d, int q, int t, int l, int lm) {
		if (l == 0) {
			return (q == 0 && t == 0);
		}
		if (permprun[q] > l || twstprun[t] > l) {
			return false;
		}
		for (int m = 0; m < 4; m++) {
			if (m != lm) {
				int p = q;
				int s = t;
				for (int a = 0; a < 2; a++) {
					p = permmv[p][m];
					s = twstmv[s][m];
					sol[d] = m * 2 + a;
					if (search(d + 1, p, s, l - 1, m)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String scramble() {
		Random gen = new Random();
		int perm;
		int twst = gen.nextInt(2592);
		do {
			perm = gen.nextInt(4320);
			twst = gen.nextInt(2187);
			int idx = twst;
			for (int i = 0; i < 4; i++) {
				fixedtwst[i] = idx % 3;
				idx /= 3;
			}
		} while (ori[perm % 12] != (fixedtwst[0] + fixedtwst[1] + fixedtwst[2] + fixedtwst[3]) % 3);
		int depth;
		for (depth = length; depth < 12; depth++) {
			if (search(0, perm, twst, depth, -1)) {
				break;
			}
		}
		StringBuffer sb = new StringBuffer();
		sequence = new byte[depth];
		for (int i = 0; i < depth; i++) {
			sb.append(move2str[sol[i]]);
			sequence[i] = (byte) sol[i];
		}
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Skewb";
	}
}
