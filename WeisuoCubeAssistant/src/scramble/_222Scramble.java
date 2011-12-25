package scramble;

import java.util.Random;

public class _222Scramble extends Scramble {
    private static int[] perm = new int[7];
    private static int[] fact = {1, 1, 2, 6, 24, 120, 720};
    private static int[][] permmv = new int[5040][3];
    private static int[][] twstmv = new int[729][3];
    private static int[] permprun = new int[5040];
    private static int[] twstprun = new int[729];
    private static String[] move2str = {"U  ", "U2 ", "U' ", "R  ", "R2 ", "R' ", "F  ", "F2 ", "F' "};
    private static boolean inited = false;

    private int[] sol = new int[12];
    
    public _222Scramble(byte length) {
    	super.length = length;
    }
    
    private static int getpermmv(int idx, int move) {
        int val = 0x6543210;
        int t;
		for (int i=0; i<6; i++) {
			int p = fact[6-i];
			int v = idx / p;
			idx -= v*p;
			v <<= 2;
			perm[i] = (val >> v) & 0x0f;
			int m = (1 << v) - 1;
			val = (val & m) + ((val >> 4) & ~m);
		}
		perm[6] = val;
		if (move == 0) {
		    t = perm[0];perm[0] = perm[1];perm[1] = perm[3];perm[3] = perm[2];perm[2] = t;
		} else if (move == 1) {
		    t = perm[0];perm[0] = perm[4];perm[4] = perm[5];perm[5] = perm[1];perm[1] = t;
		} else if (move == 2) {
		    t = perm[0];perm[0] = perm[2];perm[2] = perm[6];perm[6] = perm[4];perm[4] = t;
		}
		idx = 0;
		val = 0x6543210;
		for (int i=0; i<6; i++) {
			int v = perm[i] << 2;
			idx = (7 - i) * idx + ((val >> v) & 0x0f);
			val -= 0x1111110 << v;
		}
		return idx;	
    }
    
    private static int gettwstmv(int idx, int move) {
        perm[6] = 18;
        for (int i=0; i<6; i++) {
            perm[6] -= perm[i] = idx % 3;
            idx /= 3;
        }
        perm[6] %= 3;
        int t;
		if (move == 0) {
		    t = perm[0];perm[0] = perm[1];perm[1] = perm[3];perm[3] = perm[2];perm[2] = t;
		} else if (move == 1) {
		    t = perm[0];perm[0] = perm[4]+2;perm[4] = perm[5]+1;perm[5] = perm[1]+2;perm[1] = t+1;
		} else if (move == 2) {
		    t = perm[0];perm[0] = perm[2]+1;perm[2] = perm[6]+2;perm[6] = perm[4]+1;perm[4] = t+2;
		}
		for (int i=5; i>=0; i--) {
		    idx = idx * 3 + perm[i] % 3;
		}
		return idx;
    }
    
    private static void init() {
        if (inited) {
            return;
        }
        for (int i=0; i<729; i++) {
            twstprun[i] = -1;
            for (int j=0; j<3; j++) {
                twstmv[i][j] = gettwstmv(i, j);
            }
        }
        for (int i=0; i<5040; i++) {
            permprun[i] = -1;
            for (int j=0; j<3; j++) {
                permmv[i][j] = getpermmv(i, j);
            }
        }
        twstprun[0] = permprun[0] = 0;
        for (int l=0; l<7; l++) {
            for (int p=0; p<5040; p++) {
                if (permprun[p] == l) {
                    for (int m=0; m<3; m++) {
                        int q = p;
                        for (int c=0; c<3; c++) {
                            q = permmv[q][m];
                            if (permprun[q] == -1) {
                                permprun[q] = l+1;
                            }
                        }
                    }
                }
            }
        }
        for (int l=0; l<6; l++) {
            for (int p=0; p<729; p++) {
                if (twstprun[p] == l) {
                    for (int m=0; m<3; m++) {
                        int q = p;
                        for (int c=0; c<3; c++) {
                            q = twstmv[q][m];
                            if (twstprun[q] == -1) {
                                twstprun[q] = l+1;
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
            for (int m=0; m<3; m++) {
                if (m != lm) {
                    int p = q;
                    int s = t;
                    for (int a=0; a<3; a++) {
                        p = permmv[p][m];
                        s = twstmv[s][m];
                        sol[d] = m * 3 + a;
                        if (search(d+1, p, s, l-1, m)) {
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
        int perm = gen.nextInt(5040);
        int twst = gen.nextInt(729);
        int depth;
        for (depth=0; depth<12; depth++) {
            if (search(0, perm, twst, depth, -1)) {
                break;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<depth; i++) {
            sb.append(move2str[sol[i]]);
        }
        return sb.toString();
    }

}
