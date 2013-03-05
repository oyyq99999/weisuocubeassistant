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
		sequence = new byte[length];
		int move = 0;
		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		int lastAxis = -1;
		int[] shiftMove = new int[size - 1];
		for (int i = 0; i < size - 1; i++)
			shiftMove[i] = -1;
		for (int i = 0; i < length; i++) {
			int axis, shift, count;
			do {
				axis = rand.nextInt(3);
				shift = rand.nextInt(size - 1);
				count = rand.nextInt(3);
			} while (axis == lastAxis && shiftMove[shift] != -1);
			if (shift < size / 2) {
				sequence[move++] = (byte) ((size / 2 - shift - 1) * 18 + axis * 3 + count);
			} else {
				sequence[move++] = (byte) ((size - shift - 2) * 18 + 9 + axis * 3 + count);
			}
			if (axis == lastAxis) {
				shiftMove[shift] = count;
			} else {
				for (int j = 0; j < size - 1; j++) {
					if (shiftMove[j] != -1) {
						sb.append(getMoveName(lastAxis, j, shiftMove[j]));
					}
					shiftMove[j] = -1;
				}
				shiftMove[shift] = count;
				lastAxis = axis;
			}
		}
		for (int i = 0; i < size - 1; i++) {
			if (shiftMove[i] != -1) {
				sb.append(getMoveName(lastAxis, i, shiftMove[i]));
			}
		}
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}

	private String getMoveName(int axis, int shift, int count) {
		StringBuffer sb = new StringBuffer();
		if (shift < size / 2) {
			if (shift < size / 2 - 2) {
				sb.append(size / 2 - shift);
			}
			sb.append("URF".charAt(axis));
			if (shift != size / 2 - 1) {
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
			if (shift < size - 3) {
				sb.append(size - shift - 1);
			}
			sb.append("DLB".charAt(axis));
			if (shift != size - 2) {
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
		sb.append(' ');
		return sb.toString();
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Cube" + Integer.toString(size);
	}
}
