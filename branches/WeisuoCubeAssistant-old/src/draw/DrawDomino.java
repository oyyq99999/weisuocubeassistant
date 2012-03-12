package draw;

import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class DrawDomino {

	private byte[] sequence;
	private byte[][][] facelet;
	private int[] color = ColourScheme.cube;

	public DrawDomino(byte[] scrambleSequence) {
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

	private void swap(int f1, int r1, int c1, int f2, int r2, int c2) {
		byte temp = facelet[f1][r1][c1];
		facelet[f1][r1][c1] = facelet[f2][r2][c2];
		facelet[f2][r2][c2] = temp;
	}

	private void scramble() {
		facelet = new byte[][][] { { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } },
				{ { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } },
				{ { 2, 2, 2 }, { 2, 2, 2 } }, { { 3, 3, 3 }, { 3, 3, 3 } },
				{ { 4, 4, 4 }, { 4, 4, 4 } }, { { 5, 5, 5 }, { 5, 5, 5 } } };
		for (int i = 0; i < sequence.length; i++) {
			if (sequence[i] < 3) {
				cycle(0, 0, 0, 0, 0, 2, 0, 2, 2, 0, 2, 0, sequence[i] + 1);
				cycle(0, 0, 1, 0, 1, 2, 0, 2, 1, 0, 1, 0, sequence[i] + 1);
				cycle(2, 0, 0, 4, 0, 0, 3, 0, 0, 5, 0, 0, sequence[i] + 1);
				cycle(2, 0, 1, 4, 0, 1, 3, 0, 1, 5, 0, 1, sequence[i] + 1);
				cycle(2, 0, 2, 4, 0, 2, 3, 0, 2, 5, 0, 2, sequence[i] + 1);
			} else if (sequence[i] == 3) {
				for (int j = 0; j < 3; j++) {
					swap(2, 0, j, 2, 1, 2 - j);
					swap(0, j, 2, 1, j, 2);
				}
				swap(4, 0, 2, 5, 1, 0);
				swap(4, 1, 2, 5, 0, 0);
			} else if (sequence[i] == 4) {
				for (int j = 0; j < 3; j++) {
					swap(4, 0, j, 4, 1, 2 - j);
					swap(0, 2, j, 1, 0, 2 - j);
				}
				swap(2, 0, 0, 3, 1, 2);
				swap(2, 1, 0, 3, 0, 2);
			} else if (sequence[i] == 5) {
				for (int j = 0; j < 3; j++) {
					swap(3, 0, j, 3, 1, 2 - j);
					swap(0, j, 0, 1, j, 0);
				}
				swap(4, 0, 0, 5, 1, 2);
				swap(4, 1, 0, 5, 0, 2);
			} else if (sequence[i] == 6) {
				for (int j = 0; j < 3; j++) {
					swap(5, 0, j, 5, 1, 2 - j);
					swap(0, 0, j, 1, 2, 2 - j);
				}
				swap(2, 0, 2, 3, 1, 0);
				swap(2, 1, 2, 3, 0, 0);
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
		int length = GlobalData.width / 12;
		int x = (GlobalData.width - 10 * length) / 2;
		int y = (GlobalData.height - 7 * length) / 2;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				fillSquare(g, x + length * (2 + i), y + length * (2 + j),
						length, color[facelet[0][j][i]]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				fillSquare(g, x + length * (7 + i), y + length * (2 + j),
						length, color[facelet[1][2 - j][2 - i]]);
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				fillSquare(g, x + length * (5 + i), y + length * (2 + j),
						length, color[facelet[2][i][2 - j]]);
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				fillSquare(g, x + length * i, y + length * (2 + j), length,
						color[facelet[3][1 - i][j]]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				fillSquare(g, x + length * (2 + i), y + length * (5 + j),
						length, color[facelet[4][j][i]]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				fillSquare(g, x + length * (2 + i), y + length * j, length,
						color[facelet[5][1 - j][2 - i]]);
			}
		}
	}
}
