package scramble;

import java.util.Random;

public class FloppyScramble extends Scramble {

	public FloppyScramble() {
		this.length = 15;
	}

	public FloppyScramble(int length) {
		this.length = length;
	}

	private static final byte[][] permmv = new byte[][] {
			{ 1, 0, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, 13, 12, 15, 14, 17, 16, 19,
					18, 21, 20, 23, 22 },
			{ 2, 4, 0, 5, 1, 3, 8, 10, 6, 11, 7, 9, 14, 16, 12, 17, 13, 15, 20,
					22, 18, 23, 19, 21 },
			{ 6, 7, 12, 13, 18, 19, 0, 1, 14, 15, 20, 21, 2, 3, 8, 9, 22, 23,
					4, 5, 10, 11, 16, 17 },
			{ 21, 15, 23, 9, 17, 11, 19, 13, 22, 3, 16, 5, 18, 7, 20, 1, 10, 4,
					12, 6, 14, 0, 8, 2 } };

	private static final byte[][] orimv = new byte[][] {
			{ 1, 0, 3, 2, 5, 4, 7, 6 }, { 2, 3, 0, 1, 6, 7, 4, 5 },
			{ 4, 5, 6, 7, 0, 1, 2, 3 }, { 0, 1, 2, 3, 4, 5, 6, 7 } };

	private static final byte[] permMinMove = new byte[] { 0, 1, 1, 2, 2, 3, 1,
			2, 2, 3, 3, 2, 2, 3, 3, 2, 4, 3, 3, 2, 2, 1, 3, 2 };

	private static final byte[] oriMinMove = new byte[] { 0, 1, 1, 2, 1, 2, 2,
			3 };

	public String scramble() {
		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		byte perm, ori;
		do {
			perm = (byte) rand.nextInt(24);
			ori = (byte) rand.nextInt(8);
		} while (perm == 0 && ori == 0);
		byte[] move = new byte[8];
		byte minMove = (byte) (permMinMove[perm] >= oriMinMove[ori] ? permMinMove[perm]
				: (oriMinMove[ori] + ((permMinMove[perm] ^ oriMinMove[ori]) & 1)));
		byte moves = minMove;
		label: for (moves = minMove;; moves += 2) {
			for (int i = 0; i < (1 << (moves << 1)); i++) {
				byte p = perm, o = ori;
				for (int j = 0; j < moves; j++) {
					move[j] = (byte) ((i >>> (j << 1)) & 3);
					p = permmv[move[j]][p];
					o = orimv[move[j]][o];
				}
				if (p == 0 && o == 0) {
					break label;
				}
			}
		}
		sequence = new byte[moves];
		for (int i = 0; i < moves; i++) {
			sb.append("RFLB".charAt(move[i]));
			sb.append("2 ");
			sequence[i] = move[i];
		}
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Floppy";
	}

}
