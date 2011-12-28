package scramble;

import java.util.Random;

public class CubeScramble extends Scramble {

	private int size = 0;

	public CubeScramble(int size, int length) {
		this.size = size;
		this.length = length;
	}

	public CubeScramble(int size) {
		this.size = size;
		if (size <= 3)
			length = 25;
		else
			length = 20 * (size - 2);
	}

	public CubeScramble() {
		this(3, 25);
	}

	public String scramble() {
		int lastAxis = -1;
		StringBuffer sequence = new StringBuffer();
		int moved = 0;
		Random rand = new Random();
		for (int i = 0; i < length; i += moved) {
			moved = 0;
			int axis = rand.nextInt(3);
			while (axis == lastAxis) {
				axis = rand.nextInt(3);
			}
			int[] shiftMoved = new int[size - 1];
			for (int j = 0; j < shiftMoved.length; j++)
				shiftMoved[j] = 0;
			do {
				int shift = rand.nextInt(size - 1);
				if (shiftMoved[shift] == 1)
					continue;
				int count = rand.nextInt(3);
				sequence.append(getMoveName(size, axis, shift, count));
				moved++;
				shiftMoved[shift] = 1;
			} while (rand.nextInt(2) != 0 && moved + i < length
					&& moved < shiftMoved.length);
			lastAxis = axis;
		}
		this.scrambleSequence = sequence.toString().trim();
		return scrambleSequence;
	}

	private String getMoveName(int size, int axis, int shift, int count) {
		StringBuffer sb = new StringBuffer();
		if (shift < size / 2) {
			if (size > 5 && shift > 0) {
				sb.append(shift + 1);
			}
			switch (axis) {
			case 0:
				sb.append("R");
				break;
			case 1:
				sb.append("U");
				break;
			case 2:
				sb.append("F");
				break;
			}
			if (size > 3 && size < 6 && shift > 0) {
				sb.append("w");
			}
			switch (count) {
			case 0:
				break;
			case 1:
				sb.append("2");
				break;
			case 2:
				sb.append("'");
				break;
			}
		} else {
			shift -= size / 2;
			if (size > 5 && shift > 0) {
				sb.append(shift + 1);
			}
			switch (axis) {
			case 0:
				sb.append("L");
				break;
			case 1:
				sb.append("D");
				break;
			case 2:
				sb.append("B");
				break;
			}
			if (size > 3 && size < 6 && shift > 0) {
				sb.append("w");
			}
			switch (count) {
			case 0:
				break;
			case 1:
				sb.append("2");
				break;
			case 2:
				sb.append("'");
				break;
			}
		}
		sb.append(" ");
		return sb.toString();
	}
}
