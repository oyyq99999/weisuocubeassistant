package scramble;

import java.util.Random;

public class OldPyraminxScramble extends Scramble {

	public OldPyraminxScramble() {
		this.length = 25;
	}

	public OldPyraminxScramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		int tipCount = 0;
		for (int i = 0; i < 4; i++) {
			int count = rand.nextInt(3);
			if (count != 0) {
				sb.append("ulrb".charAt(i));
				sequence[tipCount] = (byte) (2 * count);
				if (count == 2) {
					sb.append("'");
					sequence[tipCount] = (byte) (2 * count + 1);
				}
				sb.append(' ');
				tipCount++;
			}
		}
		length -= tipCount;
		String[][][] generator = new String[][][] { { { "U" }, { "", "'" } },
				{ { "L" }, { "", "'" } }, { { "R" }, { "", "'" } },
				{ { "B" }, { "", "'" } } };
		byte[][][] seq = new byte[][][] { { { 8 }, { 0, 1 } },
				{ { 10 }, { 0, 1 } }, { { 12 }, { 0, 1 } },
				{ { 14 }, { 0, 1 } } };
		sb.append(generatorScramble(generator, seq, tipCount));
		length += tipCount;
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Pyraminx";
	}
}
