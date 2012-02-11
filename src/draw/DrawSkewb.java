package draw;

import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class DrawSkewb {

	private byte[] sequence;
	private byte[][] facelet = new byte[][] { { 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1 }, { 2, 2, 2, 2, 2 }, { 3, 3, 3, 3, 3 },
			{ 4, 4, 4, 4, 4 }, { 5, 5, 5, 5, 5 } };

	private int[] color = new int[] { 0xffffff, 0xffff00, 0xff0000, 0xff9000,
			0x00ff00, 0x0000ff };

	public DrawSkewb(byte[] scrambleSequence) {
		sequence = new byte[scrambleSequence.length];
		for (int i = 0; i < scrambleSequence.length; i++)
			sequence[i] = scrambleSequence[i];
		scramble();
	}

	private void cycle(int f1, int r1, int f2, int r2, int f3, int r3, int turn) {
		byte temp = facelet[f1][r1];
		if (turn == 0) {
			facelet[f1][r1] = facelet[f3][r3];
			facelet[f3][r3] = facelet[f2][r2];
			facelet[f2][r2] = temp;
		} else {
			facelet[f1][r1] = facelet[f2][r2];
			facelet[f2][r2] = facelet[f3][r3];
			facelet[f3][r3] = temp;
		}
	}

	private void scramble() {
		for (int i = 0; i < sequence.length; i++) {
			int turn = sequence[i] & 1;
			switch (sequence[i] >>> 1) {
			case 0:
				cycle(0, 0, 2, 0, 4, 0, turn);
				cycle(0, 2, 2, 3, 4, 1, turn);
				cycle(0, 3, 2, 2, 4, 4, turn);
				cycle(0, 4, 2, 1, 4, 2, turn);
				cycle(1, 2, 3, 2, 5, 1, turn);
				break;
			case 1:
				cycle(0, 0, 3, 0, 5, 0, turn);
				cycle(0, 1, 3, 1, 5, 2, turn);
				cycle(0, 2, 3, 2, 5, 4, turn);
				cycle(0, 3, 3, 3, 5, 1, turn);
				cycle(1, 3, 2, 2, 4, 1, turn);
				break;
			case 2:
				cycle(1, 0, 3, 0, 4, 0, turn);
				cycle(1, 1, 3, 4, 4, 3, turn);
				cycle(1, 2, 3, 3, 4, 1, turn);
				cycle(1, 3, 3, 2, 4, 4, turn);
				cycle(0, 3, 2, 3, 5, 4, turn);
				break;
			case 3:
				cycle(1, 0, 2, 0, 5, 0, turn);
				cycle(1, 2, 2, 2, 5, 4, turn);
				cycle(1, 3, 2, 3, 5, 1, turn);
				cycle(1, 4, 2, 4, 5, 3, turn);
				cycle(0, 2, 3, 3, 4, 4, turn);
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

	private void fillSquare(Graphics g, int x, int y, int length, int color) {
		g.setColor(color);
		g.fillTriangle(x, y - length, x - length, y, x + length, y);
		g.fillTriangle(x, y + length, x - length, y, x + length, y);
		g.setColor(0x000000);
		g.drawLine(x, y - length, x + length, y);
		g.drawLine(x + length, y, x, y + length);
		g.drawLine(x, y + length, x - length, y);
		g.drawLine(x - length, y, x, y - length);
	}

	public void paint(Graphics g) {
		int length = GlobalData.width / 10;
		int x = (GlobalData.width - 8 * length) / 2;
		int y = (GlobalData.height - 6 * length) / 2;
		int[][] location = new int[][] { { 3, 1 }, { 3, 5 }, { 5, 3 },
				{ 1, 3 }, { 3, 3 }, { 7, 3 } };
		for (int i = 0; i < 6; i++) {
			fillSquare(g, x + length * location[i][0], y + length
					* location[i][1], length, color[facelet[i][0]]);
			fillTri(g, x + length * (location[i][0] - 1), y + length
					* (location[i][1] - 1), x + length * location[i][0], y
					+ length * (location[i][1] - 1), x + length
					* (location[i][0] - 1), y + length * location[i][1],
					color[facelet[i][1]]);
			fillTri(g, x + length * (location[i][0] + 1), y + length
					* (location[i][1] - 1), x + length * location[i][0], y
					+ length * (location[i][1] - 1), x + length
					* (location[i][0] + 1), y + length * location[i][1],
					color[facelet[i][2]]);
			fillTri(g, x + length * (location[i][0] - 1), y + length
					* (location[i][1] + 1), x + length * (location[i][0] - 1),
					y + length * location[i][1], x + length * location[i][0], y
							+ length * (location[i][1] + 1),
					color[facelet[i][3]]);
			fillTri(g, x + length * (location[i][0] + 1), y + length
					* (location[i][1] + 1), x + length * (location[i][0] + 1),
					y + length * location[i][1], x + length * location[i][0], y
							+ length * (location[i][1] + 1),
					color[facelet[i][4]]);
		}
	}

}
