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
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int x, y;
			do {
				x = rand.nextInt(12) - 5;
				y = (i < 2) ? (rand.nextInt(12) - 5) : (rand.nextInt(6));
			} while (x == 0 && y == 0);
			sb.append("(");
			sb.append(x);
			sb.append(", ");
			sb.append(y);
			sb.append(") ");
		}
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}
}
