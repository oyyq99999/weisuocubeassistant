package scramble;

import java.util.Random;

public class pyScramble extends Scramble {
	private static int[] perm = new int[6];
	private static int[] flip = new int[6];
	private static int[] twst = new int[4];
	private static int[] fact = { 1, 1, 2/2, 6/2, 24/2, 120/2, 720/2 };
	private static int[][] permmv = new int[360][4];
	private static int[][] twstmv = new int[2592][4];
	private static int[] permprun = new int[360];
	private static int[] twstprun = new int[2592];
	private static String[] move2str = { "U  ", "U' ", "L  ", "L' ", "R  ", "R' ", "B  ", "B' "};
	private static boolean inited = false;
	private int[] sol = new int[12];

	public pyScramble(byte length) {
		this.length = length;
	}

    public static void main(String[] args) {
        for (int i=0; i<1000; i++)
        System.out.println((new pyScramble((byte)0)).scramble());
    }

	private static int getpermmv(int idx, int move) {
		int val = 0x543210;
		int parity = 0;
		for (int i=0; i<5; i++) {
			int p = fact[5-i];
			int v = idx / p;
			idx -= v*p;
			parity ^= v;
			v <<= 2;
			perm[i] = (val >> v) & 0xf;
			int m = (1 << v) - 1;
			val = (val & m) + ((val >> 4) & ~m);
		}
		if ((parity & 1) == 0) {
			perm[5] = val;
		} else {
			perm[5] = perm[4];
			perm[4] = val;
		}
        int t;
		if (move == 0) {
			t = perm[0];
			perm[0] = perm[3];
			perm[3] = perm[1];
			perm[1] = t;
		} else if (move == 1) {
			t = perm[1];
			perm[1] = perm[5];
			perm[5] = perm[2];
			perm[2] = t;
		} else if (move == 2) {
			t = perm[0];
			perm[0] = perm[2];
			perm[2] = perm[4];
			perm[4] = t;
		} else if (move == 3) {
			t = perm[3];
			perm[3] = perm[4];
			perm[4] = perm[5];
			perm[5] = t;		
		}
		val = 0x543210;
		for (int i=0; i<4; i++) {
			int v = perm[i] << 2;
			idx *= 6-i;
			idx += (val >> v) & 0xf;
			val -= 0x111110L << v;
		}
		return idx;	
	}

	private static int gettwstmv(int idx, int move) {
	    for (int i=0; i<4; i++) {
	        twst[i] = idx % 3;
	        idx /= 3;
	    }
	    flip[5] = 0;
	    for (int i=0; i<5; i++) {
	        flip[5] ^= flip[i] = idx & 1;
	        idx >>= 1;
	    }
	
        twst[move] = (twst[move] + 1) % 3;
		int t;
		if (move == 0) {
			t = flip[0];
			flip[0] = flip[3];
			flip[3] = flip[1] ^ 1;
			flip[1] = t ^ 1;
		} else if (move == 1) {
			t = flip[1];
			flip[1] = flip[5];
			flip[5] = flip[2] ^ 1;
			flip[2] = t ^ 1;
		} else if (move == 2) {
			t = flip[0];
			flip[0] = flip[2] ^ 1;
			flip[2] = flip[4] ^ 1;
			flip[4] = t;
		} else if (move == 3) {
			t = flip[3];
			flip[3] = flip[4] ^ 1;
			flip[4] = flip[5] ^ 1;
			flip[5] = t;
		}
		
		for (int i=4; i>=0; i--) {
		    idx = idx << 1 | flip[i];
		}
		for (int i=3; i>=0; i--) {
		    idx = idx * 3 + twst[i];
		}
		return idx;
	}

	private static void init() {
		if (inited) {
			return;
		}
		for (int i = 0; i < 360; i++) {
			permprun[i] = -1;
			for (int j = 0; j < 4; j++) {
				permmv[i][j] = getpermmv(i, j);
			}
		}
		for (int i = 0; i < 2592; i++) {
			twstprun[i] = -1;
			for (int j = 0; j < 4; j++) {
				twstmv[i][j] = gettwstmv(i, j);
			}
		}
		twstprun[0] = permprun[0] = 0;
		for (int l = 0; l < 5; l++) {
			for (int p = 0; p < 360; p++) {
				if (permprun[p] == l) {
					for (int m = 0; m < 4; m++) {
						int q = p;
						for (int c = 0; c < 2; c++) {
							q = permmv[q][m];
							if (permprun[q] == -1) {
								permprun[q] = l + 1;
							}
						}
					}
				}
			}
		}
		twstprun[0] = 0;
		for (int l = 0; l < 7; l++) {
			for (int p = 0; p < 2592; p++) {
				if (twstprun[p] == l) {
					for (int m = 0; m < 4; m++) {
						int q = p;
						for (int c = 0; c < 2; c++) {
							q = twstmv[q][m];
							if (twstprun[q] == -1) {
								twstprun[q] = l + 1;
							}
						}
					}
				}
			}
		}
		inited = true;
	}

	private boolean search(int d, int q, int t, int l, int lm) {
		if (l == 0) {
			if (q == 0 && t == 0) {
				return true;
			}
		} else {
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
		}
		return false;
	}

	public String scramble() {
		init();
		Random gen = new Random();
		int perm = gen.nextInt(360);
		int twst = gen.nextInt(2592);
		int depth;
		for (depth = 0; depth < 12; depth++) {
			if (search(0, perm, twst, depth, -1)) {
				break;
			}
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < depth; i++) {
			sb.append(move2str[sol[i]]);
		}
		for (int i=0; i < 4; i++) {
		    int t = gen.nextInt(3);
		    if (t != 0) {
		        sb.append("lrbu".charAt(i));
		        sb.append(" '".charAt(t-1));
		        sb.append(' ');
		    }
		}
		return sb.toString();
	}
}
