package scramble;

import java.util.Random;

public class MegaminxScramble extends Scramble {

	private int lines;
	private int lineLength;

	public MegaminxScramble() {
		this.lines = 7;
		this.lineLength = 10;
		this.length = lines * (lineLength + 1);
	}

	public MegaminxScramble(int lines, int lineLength) {
		this.lines = lines;
		this.lineLength = (lineLength + 1) / 2 * 2;// 保证是偶数
		this.length = lines * (lineLength + 1);
	}

	public String scramble() {
		StringBuffer sequence = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < lines; i++) {
			int direction = rand.nextInt(2);
			for (int j = 0; j < lineLength; j++) {
				if (j % 2 == 0) {
					sequence.append("R");
				} else
					sequence.append("D");
				direction = rand.nextInt(2);
				if (direction == 0) {
					sequence.append("++");
				} else
					sequence.append("--");
				sequence.append(" ");
			}
			if (direction == 0) {
				sequence.append("U\n");
			} else
				sequence.append("U'\n");
		}
		scrambleSequence = sequence.toString().trim();
		return scrambleSequence;
	}
}
