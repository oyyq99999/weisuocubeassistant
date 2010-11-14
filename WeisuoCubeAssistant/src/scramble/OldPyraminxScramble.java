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
		StringBuffer sequence = new StringBuffer();
		int lastAxis = -1;
		Random rand = new Random();
		int tipCount = 0;
		for (int i = 0; i < 4; i++) {
			int count = rand.nextInt(3);
			if (count != 0) {
				switch (i) {
				case 0:
					sequence.append("u");
					break;
				case 1:
					sequence.append("r");
					break;
				case 2:
					sequence.append("l");
					break;
				case 3:
					sequence.append("b");
					break;
				}
				if (count == 2)
					sequence.append("'");
				sequence.append(" ");
				tipCount++;
			}
		}
		for (int i = 0; i < length - tipCount; i++) {
			int axis;
			do {
				axis = rand.nextInt(4);
			} while (axis == lastAxis);
			switch (axis) {
			case 0:
				sequence.append("U");
				break;
			case 1:
				sequence.append("R");
				break;
			case 2:
				sequence.append("L");
				break;
			case 3:
				sequence.append("B");
				break;
			}
			int count = rand.nextInt(2);
			if (count == 1)
				sequence.append("'");
			sequence.append(" ");
			lastAxis = axis;
		}
		scrambleSequence = sequence.toString().trim();
		return scrambleSequence;
	}
}
