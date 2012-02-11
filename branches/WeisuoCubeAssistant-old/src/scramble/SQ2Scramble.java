package scramble;

import java.util.Random;

public class SQ2Scramble extends Scramble {

	public SQ2Scramble() {
		this.length = 30;
	}

	public SQ2Scramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int x, y;
			do {
				x = rand.nextInt(12) - 5;
				y = (i < 2) ? (rand.nextInt(12) - 5) : (rand.nextInt(6));
			} while (x == 0 && y == 0);
			sb.append("(");
			sb.append(x >= 0 ? (" " + Integer.toString(x)) : Integer
					.toString(x));
			sb.append(",");
			sb.append(y >= 0 ? (" " + Integer.toString(y)) : Integer
					.toString(y));
			sb.append(") ");
			if (i % 5 == 4)
				sb.append("\n");
			sequence[i] = (byte) (x + (y + 12) % 12);
		}
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "SQ2";
	}

}
