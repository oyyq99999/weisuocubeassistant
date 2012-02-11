package scramble;

import java.util.Random;

public class PyraminxCrystalScramble extends Scramble {

	private int lines;
	private int lineLength;

	public PyraminxCrystalScramble() {
		this.lines = 7;
		this.lineLength = 10;
		this.length = lines * (lineLength + 1);
	}

	public PyraminxCrystalScramble(int lines, int lineLength) {
		this.lines = lines;
		this.lineLength = (lineLength + 1) & (-2);// 保证是偶数
		this.length = lines * (lineLength + 1);
	}

	public String scramble() {
		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < lines; i++) {
			int direction = rand.nextInt(2);
			for (int j = 0; j < lineLength; j++) {
				sb.append("RD".charAt(j & 1));
				direction = rand.nextInt(2);
				if (direction == 0) {
					sb.append("++");
				} else
					sb.append("--");
				sb.append(' ');
			}
			if (direction == 0) {
				sb.append("U\n");
			} else
				sb.append("U'\n");
		}
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return null;
	}

}
