package draw;

import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class DrawFloppy {

	private byte[] sequence;
	private byte[][][] facelet;

	private final int[] color = new int[] { 0xffffff, 0xffff00, 0xff0000,
			0xff9000, 0x00ff00, 0x0000ff };

	public DrawFloppy(byte[] scrambleSequence) {
		sequence = new byte[scrambleSequence.length];
		for (int i = 0; i < scrambleSequence.length; i++)
			sequence[i] = scrambleSequence[i];
		scramble();
	}

	private void swap(int f1, int r1, int c1, int f2, int r2, int c2) {
		byte temp = facelet[f1][r1][c1];
		facelet[f1][r1][c1] = facelet[f2][r2][c2];
		facelet[f2][r2][c2] = temp;
	}

	private void scramble() {
		facelet = new byte[][][] { { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } },
				{ { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, { { 2, 2, 2 } },
				{ { 3, 3, 3 } }, { { 4, 4, 4 } }, { { 5, 5, 5 } } };
		for (int i = 0; i < sequence.length; i++) {
			if (sequence[i] == 0) {
				for (int j = 0; j < 3; j++) {
					swap(0, j, 2, 1, j, 2);
				}
				swap(2, 0, 0, 2, 0, 2);
				swap(4, 0, 2, 5, 0, 0);
			} else if (sequence[i] == 1) {
				for (int j = 0; j < 3; j++) {
					swap(0, 2, j, 1, 0, 2 - j);
				}
				swap(4, 0, 0, 4, 0, 2);
				swap(2, 0, 0, 3, 0, 2);
			} else if (sequence[i] == 2) {
				for (int j = 0; j < 3; j++) {
					swap(0, j, 0, 1, j, 0);
				}
				swap(3, 0, 0, 3, 0, 2);
				swap(4, 0, 0, 5, 0, 2);
			} else if (sequence[i] == 3) {
				for (int j = 0; j < 3; j++) {
					swap(0, 0, j, 1, 2, 2 - j);
				}
				swap(5, 0, 0, 5, 0, 2);
				swap(2, 0, 2, 3, 0, 0);
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
		int length = GlobalData.width / 10;
		int x = (GlobalData.width - 8 * length) / 2;
		int y = (GlobalData.height - 5 * length) / 2;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				fillSquare(g, x + length * (1 + i), y + length * (1 + j),
						length, color[facelet[0][j][i]]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				fillSquare(g, x + length * (5 + i), y + length * (1 + j),
						length, color[facelet[1][2 - j][2 - i]]);
			}
		}
		for (int i = 0; i < 3; i++) {
			fillSquare(g, x + length * 4, y + length * (1 + i), length,
					color[facelet[2][0][2 - i]]);
		}
		for (int i = 0; i < 3; i++) {
			fillSquare(g, x, y + length * (1 + i), length,
					color[facelet[3][0][i]]);
		}
		for (int i = 0; i < 3; i++) {
			fillSquare(g, x + length * (1 + i), y + length * 4, length,
					color[facelet[4][0][i]]);
		}
		for (int i = 0; i < 3; i++) {
			fillSquare(g, x + length * (1 + i), y, length,
					color[facelet[5][0][2 - i]]);
		}
	}

}
