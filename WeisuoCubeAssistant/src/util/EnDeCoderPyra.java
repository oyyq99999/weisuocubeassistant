package util;

public class EnDeCoderPyra {

	public int encode(int[][] state) {
		int permutation = perm(state[0]);
		int bigOrientation = bigOrient(state[2]);
		int edgeOrientation = edgeOrient(state[1]);
		return (permutation * 81 + bigOrientation) * 32 + edgeOrientation;
	}

	private int edgeOrient(int[] state) {
		// TODO Auto-generated method stub
		int n = 0;
		for (int i = 0; i < 4; i++) {
			n += state[i];
			n *= 2;
		}
		n += state[4];
		return n;
	}

	private int bigOrient(int[] state) {
		// TODO Auto-generated method stub
		int n = 0;
		for (int i = 0; i < 3; i++) {
			n += state[i];
			n *= 3;
		}
		n += state[3];
		return n;
	}

	private int perm(int[] state) {
		// TODO Auto-generated method stub
		int q = 0;
		int a;
		int b;
		int c;
		for (a = 0; a < 6; a++) {
			b = 0;
			for (c = 0; c < 6; c++) {
				if (state[c] == a)
					break;
				if (state[c] > a)
					b++;
			}
			q = q * (6 - a) + b;
		}
		return q;
	}

	public int[][] decode(int code) {
		int edgeOrientation = code % 32;
		int bigOrientation = (code / 32) % 81;
		int permutation = code / 32 / 81;
		int state[][] = new int[3][6];
		state[0] = dePerm(permutation);
		state[2] = deBigOrient(bigOrientation);
		state[1] = deEdgeOrient(edgeOrientation);
		return state;
	}

	private int[] deEdgeOrient(int edgeOrientation) {
		// TODO Auto-generated method stub
		int[] edgeOrient = new int[6];
		int total = 0;
		for (int i = 4; i >= 0; i--) {
			edgeOrient[i] = edgeOrientation % 2;
			total += edgeOrient[i];
			edgeOrientation /= 2;
		}
		edgeOrient[5] = (2 - total % 2) % 2;
		return edgeOrient;
	}

	private int[] deBigOrient(int bigOrientation) {
		// TODO Auto-generated method stub
		int[] bigOrient = new int[6];
		bigOrient[4] = bigOrient[5] = -1;
		for (int i = 3; i >= 0; i--) {
			bigOrient[i] = bigOrientation % 3;
			bigOrientation /= 3;
		}
		return bigOrient;
	}

	private int[] dePerm(int permutation) {
		// TODO Auto-generated method stub
		int a;
		int b;
		int c;
		int q;
		int[] state = new int[6];
		int[] tmp = new int[7];
		q = permutation;
		for (a = 1; a <= 6; a++) {
			b = q % a;
			q = q / a;
			for (c = a - 1; c >= b; c--)
				tmp[c + 1] = tmp[c];
			tmp[b] = 6 - a;
		}
		for (int i = 0; i < 6; i++) {
			state[i] = tmp[i];
		}
		return state;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnDeCoderPyra coder = new EnDeCoderPyra();
		int[][] state = { { 1, 5, 2, 3, 4, 0 }, { 1, 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, -1, -1 } };
		int code;
		System.out.println(code = coder.encode(state));
		int[][] s = coder.decode(code);
		for (int i = 0; i < 6; i++) {
			System.out.print(s[0][i] + " ");
		}
		System.out.println();
		for (int i = 0; i < 6; i++) {
			System.out.print(s[1][i] + " ");
		}
		System.out.println();
		for (int i = 0; i < 6; i++) {
			System.out.print(s[2][i] + " ");
		}
		System.out.println();
	}

}
