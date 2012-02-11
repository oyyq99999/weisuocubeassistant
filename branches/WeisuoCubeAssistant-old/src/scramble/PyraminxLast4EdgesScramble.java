package scramble;

import java.util.Random;

public class PyraminxLast4EdgesScramble extends PyraminxScramble {

	public PyraminxLast4EdgesScramble() {
		super((byte) 0);
	}

	private static final byte[][] perm_4 = new byte[][] { { 0, 1, 2, 3 },
			{ 0, 2, 3, 1 }, { 0, 3, 1, 2 }, { 1, 0, 3, 2 }, { 1, 2, 0, 3 },
			{ 1, 3, 2, 0 }, { 2, 0, 1, 3 }, { 2, 1, 3, 0 }, { 2, 3, 0, 1 },
			{ 3, 0, 2, 1 }, { 3, 1, 0, 2 }, { 3, 2, 1, 0 } };

	private static final byte[][] index = new byte[][] { { 0, 1, 2, 3 },
			{ 0, 1, 2, 4 }, { 0, 1, 2, 5 }, { 0, 1, 3, 4 }, { 0, 1, 3, 5 },
			{ 0, 2, 3, 4 }, { 0, 2, 4, 5 }, { 0, 3, 4, 5 }, { 1, 2, 3, 5 },
			{ 1, 2, 4, 5 }, { 1, 3, 4, 5 }, { 2, 3, 4, 5 } };

	private static final byte[] ori = new byte[] { 0, 3, 5, 6, 9, 10, 12, 15 };

	public String scramble() {
		Random rand = new Random();
		int code = rand.nextInt(12);
		int dir = rand.nextInt(12);
		int o = rand.nextInt(8);
		int[] perm = new int[] { 0, 1, 2, 3, 4, 5 };
		int[] flip = new int[] { 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < 4; i++) {
			perm[index[dir][i]] = index[dir][perm_4[code][i]];
			flip[index[dir][i]] = (ori[o] >>> i) & 1;
		}

		int val = 0x543210;
		int idx = 0;
		for (int i = 0; i < 4; i++) {
			int v = perm[i] << 2;
			idx *= 6 - i;
			idx += (val >> v) & 0xf;
			val -= 0x111110L << v;
		}
		int permutation = idx;
		int orientation = flip[0] | (flip[1] << 1) | (flip[2] << 2)
				| (flip[3] << 3) | (flip[4] << 4);
		int depth;
		for (depth = 0; depth < 12; depth++) {
			if (search(0, permutation, orientation * 81, depth, -1)) {
				break;
			}
		}
		sequence = new byte[depth];
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < depth; i++) {
			sb.append(move2str[sol[i]]);
			sequence[i] = (byte) (sol[i] + 8);
		}
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}
}
