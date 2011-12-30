package scramble;

import java.util.Random;

public abstract class Scramble {
	protected int length = 0;
	protected String scrambleSequence = null;

	public abstract String scramble();
	
	protected String generatorScramble(String[][][] generator) {
		StringBuffer sequence = new StringBuffer();
		Random rand = new Random();
		int lastAxis = -1;
		boolean[] shiftMoved = null;
		for (int i = 0; i < length; i++) {
			int axis, shift, count;
			do {
				axis = rand.nextInt(generator.length);
				shift = rand.nextInt(generator[axis][0].length);
				count = rand.nextInt(generator[axis][1].length);
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
