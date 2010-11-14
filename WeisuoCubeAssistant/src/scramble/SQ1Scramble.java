package scramble;

import java.util.Random;

public class SQ1Scramble extends Scramble {

	private int[] top = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	private int[] bottom = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0 };

	public SQ1Scramble(int length) {
		this.length = length;
	}

	public SQ1Scramble() {
		this.length = 40;
	}

	private void reset() {
		for (int i = 0; i < top.length; i++) {
			top[i] = i;
			bottom[i] = (i + 1) % 12;
		}
	}

	public String scramble() {
		reset();
		Random rand = new Random();
		int moved = 0;
		StringBuffer sequence = new StringBuffer();
		int twists = 0;
		for (int i = 0; i < length; i += moved) {
			moved = 0;
			int x;
			int y;
			do {
				x = rand.nextInt(12) - 5;
				y = twists >= 2 ? rand.nextInt(7) : rand.nextInt(12) - 5;
			} while ((x == 0 && y == 0 && i != 0)
					|| (check(x, y) == false)
					|| (i + (x != 0 ? 1 : 0) + (y != 0 ? 1 : 0) + 1 > length + 1));
			sequence.append("(");
			sequence.append(x);
			sequence.append(", ");
			sequence.append(y);
			sequence.append(") ");
			move(x, y);
			moved += (x != 0 ? 1 : 0);
			moved += (y != 0 ? 1 : 0);
			moved++;
			twists++;
			if (i + moved > length) {
				sequence.append("(0, 0)");
			}
		}
		scrambleSequence = sequence.toString().trim();
		return scrambleSequence;
	}

	private void move(int x, int y) {
		for (int i = 0; i < (x + 12) % 12; i++) {
			int tmp = top[0];
			for (int j = 0; j < 11; j++) {
				top[j] = top[j + 1];
			}
			top[11] = tmp;
		}
		for (int i = 0; i < (y + 12) % 12; i++) {
			int tmp = bottom[0];
			for (int j = 0; j < 11; j++) {
				bottom[j] = bottom[j + 1];
			}
			bottom[11] = tmp;
		}
		for (int i = 0; i < 6; i++) {
			int tmp = top[i];
			top[i] = bottom[i];
			bottom[i] = tmp;
		}
	}

	private boolean check(int x, int y) {
		if (top[(x + 12) % 12] % 3 != 2 && top[(x + 12 + 6) % 12] % 3 != 2
				&& bottom[(y + 12) % 12] % 3 != 2
				&& bottom[(y + 12 + 6) % 12] % 3 != 2)
			return true;
		else
			return false;
	}
	
	public static void main(String args[]) {
		System.out.println(new SQ1Scramble().scramble());
	}
}
