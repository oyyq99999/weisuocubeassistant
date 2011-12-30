package scramble;

import java.util.Random;

public abstract class Scramble {

	protected int length = 0;
	protected String scrambleSequence = null;

	public abstract String scramble();

	protected String generatorScramble(String[][][] generator) {
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

		StringBuffer sequence = new StringBuffer();
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
			sequence.append(generator[axis][0][shift]);
			sequence.append(generator[axis][1][count]);
			sequence.append(' ');
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
		return sequence.toString().trim();
	}
}
