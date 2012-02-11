package scramble;

import java.util.Random;

public class GigaminxScramble extends Scramble {

	private int lines;
	private int lineLength;

	public GigaminxScramble() {
		this.lines = 30;
		this.lineLength = 10;
		this.length = lines * (lineLength + 1);
	}

	public GigaminxScramble(int lines, int lineLength) {
		this.lines = lines;
		this.lineLength = (lineLength + 1) & (-2);// 保证是偶数
		this.length = lines * (lineLength + 1);
	}

	public String scramble() {
		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < lineLength; j++) {
				sb.append("RD".charAt(j & 1));
				if (rand.nextInt(2) == 1) {
					sb.append('w');
				}
				switch (rand.nextInt(4)) {
				case 0:
					sb.append('+');
					break;
				case 1:
					sb.append("++");
					break;
				case 2:
					sb.append("--");
					break;
				case 3:
					sb.append('-');
					break;
				}
				sb.append(' ');
			}
			sb.append('y');
			switch (rand.nextInt(4)) {
			case 0:
				break;
			case 1:
				sb.append('2');
				break;
			case 2:
				sb.append("2'");
				break;
			case 3:
				sb.append("'");
				break;
			}
			sb.append('\n');
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
