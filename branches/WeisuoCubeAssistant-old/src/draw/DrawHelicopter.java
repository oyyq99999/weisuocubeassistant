package draw;

import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class DrawHelicopter {

	private byte[] sequence;
	private byte[][][] facelet = new byte[6][2][4];

	private int[] color = new int[] { 0xffffff, 0xffff00, 0xff0000, 0xff9000,
			0x00ff00, 0x0000ff };

	public DrawHelicopter(byte[] scrambleSequence) {
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
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 4; k++)
					facelet[i][j][k] = (byte) i;
		for (int i = 0; i < sequence.length; i++) {
			switch (sequence[i]) {
			case 0:
				swap(0, 0, 1, 2, 0, 0);
				swap(0, 0, 2, 2, 0, 1);
				swap(0, 1, 1, 2, 1, 0);
				swap(0, 1, 2, 2, 1, 1);
				swap(4, 0, 1, 5, 0, 0);
				break;
			case 1:
				swap(0, 0, 0, 3, 0, 1);
				swap(0, 0, 3, 3, 0, 0);
				swap(0, 1, 0, 3, 1, 1);
				swap(0, 1, 3, 3, 1, 0);
				swap(4, 0, 0, 5, 0, 1);
				break;
			case 2:
				swap(1, 0, 1, 2, 0, 2);
				swap(1, 0, 2, 2, 0, 3);
				swap(1, 1, 1, 2, 1, 2);
				swap(1, 1, 2, 2, 1, 3);
				swap(4, 0, 2, 5, 0, 3);
				break;
			case 3:
				swap(1, 0, 0, 3, 0, 3);
				swap(1, 0, 3, 3, 0, 2);
				swap(1, 1, 0, 3, 1, 3);
				swap(1, 1, 3, 3, 1, 2);
				swap(4, 0, 3, 5, 0, 2);
				break;
			case 4:
				swap(0, 0, 2, 4, 0, 0);
				swap(0, 0, 3, 4, 0, 1);
				swap(0, 1, 2, 4, 1, 0);
				swap(0, 1, 3, 4, 1, 1);
				swap(2, 0, 0, 3, 0, 1);
				break;
			case 5:
				swap(0, 0, 0, 5, 0, 0);
				swap(0, 0, 1, 5, 0, 1);
				swap(0, 1, 0, 5, 1, 0);
				swap(0, 1, 1, 5, 1, 1);
				swap(2, 0, 1, 3, 0, 0);
				break;
			case 6:
				swap(1, 0, 0, 4, 0, 2);
				swap(1, 0, 1, 4, 0, 3);
				swap(1, 1, 0, 4, 1, 2);
				swap(1, 1, 1, 4, 1, 3);
				swap(2, 0, 3, 3, 0, 2);
				break;
			case 7:
				swap(1, 0, 2, 5, 0, 2);
				swap(1, 0, 3, 5, 0, 3);
				swap(1, 1, 2, 5, 1, 2);
				swap(1, 1, 3, 5, 1, 3);
				swap(2, 0, 2, 3, 0, 3);
				break;
			case 8:
				swap(2, 0, 0, 4, 0, 2);
				swap(2, 0, 3, 4, 0, 1);
				swap(2, 1, 0, 4, 1, 2);
				swap(2, 1, 3, 4, 1, 1);
				swap(0, 0, 2, 1, 0, 1);
				break;
			case 9:
				swap(3, 0, 1, 4, 0, 3);
				swap(3, 0, 2, 4, 0, 0);
				swap(3, 1, 1, 4, 1, 3);
				swap(3, 1, 2, 4, 1, 0);
				swap(0, 0, 3, 1, 0, 0);
				break;
			case 10:
				swap(2, 0, 1, 5, 0, 3);
				swap(2, 0, 2, 5, 0, 0);
				swap(2, 1, 1, 5, 1, 3);
				swap(2, 1, 2, 5, 1, 0);
				swap(0, 0, 1, 1, 0, 2);
				break;
			case 11:
				swap(3, 0, 0, 5, 0, 2);
				swap(3, 0, 3, 5, 0, 1);
				swap(3, 1, 0, 5, 1, 2);
				swap(3, 1, 3, 5, 1, 1);
				swap(0, 0, 0, 1, 0, 3);
				break;
			}
		}
	}

	private void fillTri(Graphics g, int x1, int y1, int x2, int y2, int x3,
			int y3, int color) {
		g.setColor(color);
		g.fillTriangle(x1, y1, x2, y2, x3, y3);
		g.setColor(0x000000);
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x1, y1, x3, y3);
		g.drawLine(x2, y2, x3, y3);
	}

	public void paint(Graphics g) {
		int length = GlobalData.width / 10;
		int x = (GlobalData.width - 8 * length) / 2;
		int y = (GlobalData.height - 6 * length) / 2;
		int[][] location = new int[][] { { 3, 1 }, { 3, 5 }, { 5, 3 },
				{ 1, 3 }, { 3, 3 }, { 7, 3 } };
		for (int i = 0; i < 6; i++) {
			fillTri(g, x + length * (location[i][0] - 1), y + length
					* (location[i][1] - 1), x + length * location[i][0], y
					+ length * (location[i][1] - 1), x + length
					* (location[i][0] - 1), y + length * location[i][1],
					color[facelet[i][0][0]]);
			fillTri(g, x + length * (location[i][0] + 1), y + length
					* (location[i][1] - 1), x + length * location[i][0], y
					+ length * (location[i][1] - 1), x + length
					* (location[i][0] + 1), y + length * location[i][1],
					color[facelet[i][0][1]]);
			fillTri(g, x + length * (location[i][0] + 1), y + length
					* (location[i][1] + 1), x + length * (location[i][0] + 1),
					y + length * location[i][1], x + length * location[i][0], y
							+ length * (location[i][1] + 1),
					color[facelet[i][0][2]]);
			fillTri(g, x + length * (location[i][0] - 1), y + length
					* (location[i][1] + 1), x + length * (location[i][0] - 1),
					y + length * location[i][1], x + length * location[i][0], y
							+ length * (location[i][1] + 1),
					color[facelet[i][0][3]]);
			fillTri(g, x + length * location[i][0],
					y + length * location[i][1], x + length * location[i][0], y
							+ length * (location[i][1] - 1), x + length
							* (location[i][0] - 1),
					y + length * location[i][1], color[facelet[i][1][0]]);
			fillTri(g, x + length * location[i][0],
					y + length * location[i][1], x + length * location[i][0], y
							+ length * (location[i][1] - 1), x + length
							* (location[i][0] + 1),
					y + length * location[i][1], color[facelet[i][1][1]]);
			fillTri(g, x + length * location[i][0],
					y + length * location[i][1], x + length
							* (location[i][0] + 1),
					y + length * location[i][1], x + length * location[i][0], y
							+ length * (location[i][1] + 1),
					color[facelet[i][1][2]]);
			fillTri(g, x + length * location[i][0],
					y + length * location[i][1], x + length
							* (location[i][0] - 1),
					y + length * location[i][1], x + length * location[i][0], y
							+ length * (location[i][1] + 1),
					color[facelet[i][1][3]]);
		}
	}

}
