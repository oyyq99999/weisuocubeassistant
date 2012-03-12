package draw;

import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class Draw334 {

	private byte[] sequence;
	private byte[][][] facelet;
	private int[] color = ColourScheme.cube;

	public Draw334(byte[] scrambleSequence) {
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
		facelet = new byte[6][4][3];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					facelet[i][j][k] = (byte) i;
				}
			}
		}
		for (int i = 0; i < sequence.length; i++) {
			if (sequence[i] < 6) {
				cycle(0, 0, 0, 0, 0, 2, 0, 2, 2, 0, 2, 0, sequence[i] % 3 + 1);
				cycle(0, 0, 1, 0, 1, 2, 0, 2, 1, 0, 1, 0, sequence[i] % 3 + 1);
				for (int j = 0; j < 3; j++) {
					cycle(2, 0, j, 4, 0, j, 3, 0, j, 5, 0, j,
							sequence[i] % 3 + 1);
				}
				if (sequence[i] >= 3) {
					for (int j = 0; j < 3; j++) {
						cycle(2, 1, j, 4, 1, j, 3, 1, j, 5, 1, j,
								sequence[i] - 2);
					}
				}
			} else if (sequence[i] < 9) {
				cycle(1, 0, 0, 1, 0, 2, 1, 2, 2, 1, 2, 0, sequence[i] - 5);
				cycle(1, 0, 1, 1, 1, 2, 1, 2, 1, 1, 1, 0, sequence[i] - 5);
				for (int j = 0; j < 3; j++) {
					cycle(2, 3, j, 5, 3, j, 3, 3, j, 4, 3, j,
							sequence[i] % 3 + 1);
				}
			} else if (sequence[i] == 9) {
				for (int j = 0; j < 3; j++) {
					swap(2, 0, j, 2, 3, 2 - j);
					swap(2, 1, j, 2, 2, 2 - j);
					swap(0, j, 2, 1, j, 2);
				}
				for (int j = 0; j < 4; j++) {
					swap(4, j, 2, 5, 3 - j, 0);
				}
			} else if (sequence[i] == 10) {
				for (int j = 0; j < 3; j++) {
					swap(4, 0, j, 4, 3, 2 - j);
					swap(4, 1, j, 4, 2, 2 - j);
					swap(0, 2, j, 1, 0, 2 - j);
				}
				for (int j = 0; j < 4; j++) {
					swap(2, j, 0, 3, 3 - j, 2);
				}
			} else if (sequence[i] == 11) {
				for (int j = 0; j < 3; j++) {
					swap(3, 0, j, 3, 3, 2 - j);
					swap(3, 1, j, 3, 2, 2 - j);
					swap(0, j, 0, 1, j, 0);
				}
				for (int j = 0; j < 4; j++) {
					swap(4, j, 0, 5, 3 - j, 2);
				}
			} else if (sequence[i] == 12) {
				for (int j = 0; j < 3; j++) {
					swap(5, 0, j, 5, 3, 2 - j);
					swap(5, 1, j, 5, 2, 2 - j);
					swap(0, 0, j, 1, 2, 2 - j);
				}
				for (int j = 0; j < 4; j++) {
					swap(2, j, 2, 3, 3 - j, 0);
				}
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
		int length = GlobalData.width / 18;
		int x = (GlobalData.width - 12 * length) / 2;
		int y = (GlobalData.height - 10 * length) / 2;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				fillSquare(g, x + length * (3 + i), y + length * j, length,
						color[facelet[0][j][i]]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				fillSquare(g, x + length * (3 + i), y + length * (7 + j),
						length, color[facelet[1][j][i]]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				fillSquare(g, x + length * (6 + i), y + length * (3 + j),
						length, color[facelet[2][j][i]]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				fillSquare(g, x + length * i, y + length * (3 + j), length,
						color[facelet[3][j][i]]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				fillSquare(g, x + length * (3 + i), y + length * (3 + j),
						length, color[facelet[4][j][i]]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				fillSquare(g, x + length * (9 + i), y + length * (3 + j),
						length, color[facelet[5][j][i]]);
			}
		}
	}

}
