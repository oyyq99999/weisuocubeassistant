package draw;

import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class DrawCube {

	private int size;
	private byte[] sequence;
	private byte[][][] facelet;

	private final int[] color = new int[] { 0xffffff, 0xffff00, 0xff0000,
			0xff9000, 0x00ff00, 0x0000ff };

	public DrawCube(int size, byte[] scrambleSequence) {
		this.size = size;
		sequence = new byte[scrambleSequence.length];
		for (int i = 0; i < scrambleSequence.length; i++)
			sequence[i] = scrambleSequence[i];
		scramble();
	}

	private void cycle(int f1, int r1, int c1, int f2, int r2, int c2, int f3,
			int r3, int c3, int f4, int r4, int c4, int turn) {
		byte temp;
		for (int i = 0; i < turn; i++) {
			temp = facelet[f1][r1][c1];
			facelet[f1][r1][c1] = facelet[f4][r4][c4];
			facelet[f4][r4][c4] = facelet[f3][r3][c3];
			facelet[f3][r3][c3] = facelet[f2][r2][c2];
			facelet[f2][r2][c2] = temp;
		}
	}

	private static byte[] twistFace = new byte[] { 0, 2, 4, 1, 3, 5 };

	private void twist(int twst) {
		int shift = twst / 18 + 1;
		int face = (twst % 18) / 3;
		int turn = twst % 3 + 1;
		for (int j = 0; j < size / 2; j++) {
			for (int k = j; k < size - 1 - j; k++) {
				cycle(twistFace[face], j, k, twistFace[face], k, size - 1 - j,
						twistFace[face], size - 1 - j, size - 1 - k,
						twistFace[face], size - 1 - k, j, turn);
			}
		}
		switch (face) {
		case 0:
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < shift; k++) {
					cycle(2, k, j, 4, k, j, 3, k, j, 5, k, j, turn);
				}
			}
			break;
		case 1:
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < shift; k++) {
					cycle(0, j, size - 1 - k, 5, size - 1 - j, k, 1, j, size
							- 1 - k, 4, j, size - 1 - k, turn);
				}
			}
			break;
		case 2:
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < shift; k++) {
					cycle(0, size - 1 - k, j, 2, j, k, 1, k, size - 1 - j, 3,
							size - 1 - j, size - 1 - k, turn);
				}
			}
			break;
		case 3:
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < shift; k++) {
					cycle(2, size - 1 - k, j, 5, size - 1 - k, j, 3, size - 1
							- k, j, 4, size - 1 - k, j, turn);
				}
			}
			break;
		case 4:
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < shift; k++) {
					cycle(0, j, k, 4, j, k, 1, j, k, 5, size - 1 - j, size - 1
							- k, turn);
				}
			}
			break;
		case 5:
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < shift; k++) {
					cycle(0, k, j, 3, size - 1 - j, k, 1, size - 1 - k, size
							- 1 - j, 2, j, size - 1 - k, turn);
				}
			}
			break;
		}
	}

	private void scramble() {
		facelet = new byte[6][size][size];
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < size; j++)
				for (int k = 0; k < size; k++)
					facelet[i][j][k] = (byte) i;
		for (int i = 0; i < sequence.length; i++) {
			if (sequence[i] >= 0) {
				twist(sequence[i]);
			} else {
				twist(20 - sequence[i]);
				twist(6 + sequence[i]);
			}
		}
	}

	private void fillSquare(Graphics g, int x, int y, int length, int color) {
		g.setColor(color);
		g.fillRect(x, y, length, length);
		g.setColor(0x000000);
		g.drawLine(x, y, x + length, y);
		g.drawLine(x, y, x, y + length);
		g.drawLine(x, y + length, x + length, y + length);
		g.drawLine(x + length, y, x + length, y + length);
	}

	public void paint(Graphics g) {
		int length = GlobalData.width / (5 * size);
		int x = (GlobalData.width - 4 * size * length) / 2;
		int y = (GlobalData.height - 3 * size * length) / 2;
		int[][] location = new int[][] { { 1, 0 }, { 1, 2 }, { 2, 1 },
				{ 0, 1 }, { 1, 1 }, { 3, 1 } };
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					fillSquare(g, x + length * (size * location[i][0] + k), y
							+ length * (size * location[i][1] + j), length,
							color[facelet[i][j][k]]);
				}
			}
		}
	}
}
