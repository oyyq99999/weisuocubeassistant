package scramble;

import java.util.Random;

public class SQ1Scramble extends Scramble {

	private boolean bandaged;

	private int[] top = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	private int[] bottom = { 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 12 };

	public SQ1Scramble() {
		this.length = 40;
	}

	public SQ1Scramble(boolean bandaged) {
		this.length = 40;
		this.bandaged = bandaged;
	}

	public SQ1Scramble(int length) {
		this.length = length;
	}

	public SQ1Scramble(boolean bandaged, int length) {
		this.length = length;
		this.bandaged = bandaged;
	}

	private void reset() {
		for (int i = 0; i < top.length; i++) {
			top[i] = i;
			bottom[i] = (i + 1) % 12 + 12;
		}
	}

	public String scramble() {
		byte[] seq = new byte[40];
		int move = 0;
		reset();
		Random rand = new Random();
		int moved = 0;
		StringBuffer sb = new StringBuffer();
		int twists = 0;
		for (int i = 0; i < length; i += moved) {
			moved = 0;
			int x;
			int y;
			do {
				x = rand.nextInt(12) - 5;
				// 将第二次twist之后的底层转动限制在0到5之间，WCA打乱是这样的，不知道为什么
				y = bandaged ? 0 : (twists >= 2 ? rand.nextInt(6) : rand
						.nextInt(12) - 5);
			} while ((x == 0 && y == 0 && i != 0)
					|| !check(x, y)
					|| (i + (x != 0 ? 1 : 0) + (y != 0 ? 1 : 0) + 1 > length + 1));
			sb.append("(");
			sb.append(x >= 0 ? (" " + Integer.toString(x)) : Integer
					.toString(x));
			sb.append(",");
			sb.append(y >= 0 ? (" " + Integer.toString(y)) : Integer
					.toString(y));
			sb.append(") ");
			move(x, y);
			moved += (x != 0 ? 1 : 0);
			moved += (y != 0 ? 1 : 0);
			moved++;
			seq[move++] = (byte) (12 * x + (y + 12) % 12);
			if ((twists++) % 5 == 4)
				sb.append("\n");
			if (i + moved > length) {
				sb.append("( 0, 0)");
				move(0, 0);
				seq[move++] = (byte) 0;
			}
		}
		sequence = new byte[move];
		for (int i = 0; i < move; i++) {
			sequence[i] = seq[i];
		}
		scrambleSequence = sb.toString().trim();
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

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "SQ1";
	}

}
