package scramble;

import java.util.Random;

public abstract class Scramble {

	protected int length = 0;
	protected String scrambleSequence = null;
	protected byte[] sequence;

	public abstract String scramble();

	public abstract byte[] getSequence();

	public abstract String getName();

	protected String generatorScramble(String[][][] generator, byte[][][] seq,
			int startMove) {
		int move = startMove;
		int[][] index = new int[100][3];
		int totalMoves = 0;
		for (int i = 0; i < generator.length; i++) {
			for (int j = 0; j < generator[i][0].length; j++) {
				for (int k = 0; k < generator[i][1].length; k++) {
					int t = totalMoves + j * generator[i][1].length + k;
					index[t][0] = i;
					index[t][1] = j;
					index[t][2] = k;
				}
			}
			totalMoves += generator[i][0].length * generator[i][1].length;
		}

		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		int lastAxis = -1;
		boolean[] shiftMoved = null;
		for (int i = 0; i < length; i++) {
			int axis, shift, count;
			do {
				int code = rand.nextInt(totalMoves);
				axis = index[code][0];
				shift = index[code][1];
				count = index[code][2];
			} while (axis == lastAxis && shiftMoved[shift]);
			sb.append(generator[axis][0][shift]);
			sb.append(generator[axis][1][count]);
			sb.append(' ');
			sequence[move++] = (byte) (seq[axis][0][shift] + seq[axis][1][count]);
			if (axis == lastAxis) {
				shiftMoved[shift] = true;
			} else {
				shiftMoved = new boolean[generator[axis][0].length];
				for (int j = 0; j < generator[axis][0].length; j++)
					shiftMoved[j] = false;
				shiftMoved[shift] = true;
				lastAxis = axis;
			}
		}
		return sb.toString().trim();
	}
}
