package draw;

import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class DrawMegaminx {

	private byte[] sequence;
	private byte[][] facelet = new byte[12][10];
	private byte[] center = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	private int[] color = ColourScheme.megaminx;

	public DrawMegaminx(byte[] scrambleSequence) {
		sequence = new byte[scrambleSequence.length];
		for (int i = 0; i < scrambleSequence.length; i++)
			sequence[i] = scrambleSequence[i];
		scramble();
	}

	private void cycle(int c1, int c2, int c3, int c4, int c5, int turn) {
		byte temp;
		for (int i = 0; i < turn; i++) {
			temp = center[c1];
			center[c1] = center[c5];
			center[c5] = center[c4];
			center[c4] = center[c3];
			center[c3] = center[c2];
			center[c2] = temp;
		}
	}

	private void cycle(int f1, int r1, int f2, int r2, int f3, int r3, int f4,
			int r4, int f5, int r5, int turn) {
		byte temp;
		for (int i = 0; i < turn; i++) {
			temp = facelet[f1][r1];
			facelet[f1][r1] = facelet[f5][r5];
			facelet[f5][r5] = facelet[f4][r4];
			facelet[f4][r4] = facelet[f3][r3];
			facelet[f3][r3] = facelet[f2][r2];
			facelet[f2][r2] = temp;
		}
	}

	private void scramble() {
		for (int i = 0; i < 12; i++)
			for (int j = 0; j < 10; j++)
				facelet[i][j] = (byte) i;
		for (int i = 0; i < sequence.length; i++) {
			int turn = (sequence[i] & 3) + 1;
			switch (sequence[i] >>> 2) {
			case 0:
				cycle(0, 0, 0, 2, 0, 4, 0, 6, 0, 8, turn);
				cycle(0, 1, 0, 3, 0, 5, 0, 7, 0, 9, turn);
				for (int j = 4; j <= 6; j++)
					cycle(2, j, 4, j, 6, j, 8, j, 10, j, turn);
				break;
			case 1:
				cycle(10, 0, 10, 2, 10, 4, 10, 6, 10, 8, turn);
				cycle(10, 1, 10, 3, 10, 5, 10, 7, 10, 9, turn);
				for (int j = 0; j < 3; j++)
					cycle(0, j + 2, 8, j + 2, 5, (j + 8) % 10, 7, j, 2, j + 6,
							turn);
				break;
			case 2:
				cycle(5, 0, 5, 2, 5, 4, 5, 6, 5, 8, turn);
				cycle(5, 1, 5, 3, 5, 5, 5, 7, 5, 9, turn);
				cycle(0, 6, 11, 9, 2, turn);
				cycle(1, 7, 10, 8, 3, turn);
				for (int j = 0; j < 7; j++)
					cycle(0, (j + 9) % 10, 6, (j + 9) % 10, 11, (j + 3) % 10,
							9, (j + 1) % 10, 2, (j + 5) % 10, turn);
				for (int j = 0; j < 10; j++)
					cycle(1, j, 7, j, 10, (j + 6) % 10, 8, (j + 8) % 10, 3,
							(j + 4) % 10, turn);
				break;
			case 3:
				cycle(1, 0, 1, 2, 1, 4, 1, 6, 1, 8, turn);
				cycle(1, 1, 1, 3, 1, 5, 1, 7, 1, 9, turn);
				cycle(2, 10, 8, 6, 4, turn);
				cycle(3, 11, 9, 7, 5, turn);
				for (int j = 0; j < 7; j++)
					cycle(2, (j + 7) % 10, 10, (j + 7) % 10, 8, (j + 7) % 10,
							6, (j + 7) % 10, 4, (j + 7) % 10, turn);
				for (int j = 0; j < 10; j++)
					cycle(3, j, 11, j, 9, j, 7, j, 5, j, turn);
				break;
			}
		}
	}

	private final double[] sin18 = { 0, (Math.sqrt(5) - 1) / 4,
			Math.sqrt(10 - 2 * Math.sqrt(5)) / 4, (Math.sqrt(5) + 1) / 4,
			Math.sqrt(10 + 2 * Math.sqrt(5)) / 4 };

	private final double phi = (Math.sqrt(5) - 1) / 2;

	private void fillQuad(Graphics g, int x1, int y1, int x2, int y2, int x3,
			int y3, int x4, int y4, int color) {
		g.setColor(color);
		g.fillTriangle(x1, y1, x2, y2, x3, y3);
		g.fillTriangle(x1, y1, x3, y3, x4, y4);
		g.setColor(0x000000);
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x2, y2, x3, y3);
		g.drawLine(x3, y3, x4, y4);
		g.drawLine(x4, y4, x1, y1);
	}

	private void fillPent(Graphics g, int x1, int y1, int x2, int y2, int x3,
			int y3, int x4, int y4, int x5, int y5, int color) {
		g.setColor(color);
		g.fillTriangle(x1, y1, x2, y2, x3, y3);
		g.fillTriangle(x1, y1, x3, y3, x4, y4);
		g.fillTriangle(x1, y1, x4, y4, x5, y5);
		g.setColor(0x000000);
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x2, y2, x3, y3);
		g.drawLine(x3, y3, x4, y4);
		g.drawLine(x4, y4, x5, y5);
		g.drawLine(x5, y5, x1, y1);
	}

	public void paint(Graphics g) {
		double length = GlobalData.width / 9.0;
		int x1 = GlobalData.width / 4;
		int x2 = GlobalData.width / 4 * 3;
		int y = GlobalData.height / 2;
		double[][][] vertex = new double[6][5][2];
		vertex[0][0][0] = 0;
		vertex[0][0][1] = -1 / (2 * sin18[2]);
		vertex[0][1][0] = sin18[3];
		vertex[0][1][1] = -sin18[1] * sin18[3] / sin18[4];
		vertex[0][2][0] = 0.5;
		vertex[0][2][1] = sin18[3] / (2 * sin18[2]);
		vertex[0][3][0] = -vertex[0][2][0];
		vertex[0][3][1] = vertex[0][2][1];
		vertex[0][4][0] = -vertex[0][1][0];
		vertex[0][4][1] = vertex[0][1][1];
		for (int i = 0; i < 5; i++) {
			vertex[i + 1][0][0] = vertex[0][(i + 2) % 5][0]
					+ vertex[0][(i + 3) % 5][0] - vertex[0][i][0];
			vertex[i + 1][0][1] = vertex[0][(i + 2) % 5][1]
					+ vertex[0][(i + 3) % 5][1] - vertex[0][i][1];
			vertex[i + 1][1][0] = vertex[0][(i + 2) % 5][0]
					+ vertex[0][(i + 3) % 5][0] - vertex[0][(i + 1) % 5][0];
			vertex[i + 1][1][1] = vertex[0][(i + 2) % 5][1]
					+ vertex[0][(i + 3) % 5][1] - vertex[0][(i + 1) % 5][1];
			vertex[i + 1][2][0] = vertex[0][(i + 3) % 5][0];
			vertex[i + 1][2][1] = vertex[0][(i + 3) % 5][1];
			vertex[i + 1][3][0] = vertex[0][(i + 2) % 5][0];
			vertex[i + 1][3][1] = vertex[0][(i + 2) % 5][1];
			vertex[i + 1][4][0] = vertex[0][(i + 2) % 5][0]
					+ vertex[0][(i + 3) % 5][0] - vertex[0][(i + 4) % 5][0];
			vertex[i + 1][4][1] = vertex[0][(i + 2) % 5][1]
					+ vertex[0][(i + 3) % 5][1] - vertex[0][(i + 4) % 5][1];
		}

		for (int i = 0; i < 6; i++) {
			fillPent(
					g,
					x1
							+ (int) (length * ((2 * phi - 1) * vertex[i][0][0] + (1 - phi)
									* (vertex[i][1][0] + vertex[i][4][0]))),
					y
							+ (int) (length * ((2 * phi - 1) * vertex[i][0][1] + (1 - phi)
									* (vertex[i][1][1] + vertex[i][4][1]))),
					x1
							+ (int) (length * ((2 * phi - 1) * vertex[i][1][0] + (1 - phi)
									* (vertex[i][2][0] + vertex[i][0][0]))),
					y
							+ (int) (length * ((2 * phi - 1) * vertex[i][1][1] + (1 - phi)
									* (vertex[i][2][1] + vertex[i][0][1]))),
					x1
							+ (int) (length * ((2 * phi - 1) * vertex[i][2][0] + (1 - phi)
									* (vertex[i][3][0] + vertex[i][1][0]))),
					y
							+ (int) (length * ((2 * phi - 1) * vertex[i][2][1] + (1 - phi)
									* (vertex[i][3][1] + vertex[i][1][1]))),
					x1
							+ (int) (length * ((2 * phi - 1) * vertex[i][3][0] + (1 - phi)
									* (vertex[i][4][0] + vertex[i][2][0]))),
					y
							+ (int) (length * ((2 * phi - 1) * vertex[i][3][1] + (1 - phi)
									* (vertex[i][4][1] + vertex[i][2][1]))),
					x1
							+ (int) (length * ((2 * phi - 1) * vertex[i][4][0] + (1 - phi)
									* (vertex[i][0][0] + vertex[i][3][0]))),
					y
							+ (int) (length * ((2 * phi - 1) * vertex[i][4][1] + (1 - phi)
									* (vertex[i][0][1] + vertex[i][3][1]))),
					color[center[2 * i]]);
			for (int j = 0; j < 5; j++) {
				fillQuad(
						g,
						x1 + (int) (length * vertex[i][j][0]),
						y + (int) (length * vertex[i][j][1]),
						x1
								+ (int) (length * (phi * vertex[i][j][0] + (1 - phi)
										* vertex[i][(j + 1) % 5][0])),
						y
								+ (int) (length * (phi * vertex[i][j][1] + (1 - phi)
										* vertex[i][(j + 1) % 5][1])),
						x1
								+ (int) (length * ((2 * phi - 1)
										* vertex[i][j][0] + (1 - phi)
										* (vertex[i][(j + 1) % 5][0] + vertex[i][(j + 4) % 5][0]))),
						y
								+ (int) (length * ((2 * phi - 1)
										* vertex[i][j][1] + (1 - phi)
										* (vertex[i][(j + 1) % 5][1] + vertex[i][(j + 4) % 5][1]))),
						x1
								+ (int) (length * (phi * vertex[i][j][0] + (1 - phi)
										* vertex[i][(j + 4) % 5][0])),
						y
								+ (int) (length * (phi * vertex[i][j][1] + (1 - phi)
										* vertex[i][(j + 4) % 5][1])),
						color[facelet[2 * i][2 * j]]);
				fillQuad(
						g,
						x1
								+ (int) (length * (phi * vertex[i][j][0] + (1 - phi)
										* vertex[i][(j + 1) % 5][0])),
						y
								+ (int) (length * (phi * vertex[i][j][1] + (1 - phi)
										* vertex[i][(j + 1) % 5][1])),
						x1
								+ (int) (length * (phi
										* vertex[i][(j + 1) % 5][0] + (1 - phi)
										* vertex[i][j][0])),
						y
								+ (int) (length * (phi
										* vertex[i][(j + 1) % 5][1] + (1 - phi)
										* vertex[i][j][1])),
						x1
								+ (int) (length * ((2 * phi - 1)
										* vertex[i][(j + 1) % 5][0] + (1 - phi)
										* (vertex[i][(j + 2) % 5][0] + vertex[i][j][0]))),
						y
								+ (int) (length * ((2 * phi - 1)
										* vertex[i][(j + 1) % 5][1] + (1 - phi)
										* (vertex[i][(j + 2) % 5][1] + vertex[i][j][1]))),
						x1
								+ (int) (length * ((2 * phi - 1)
										* vertex[i][j][0] + (1 - phi)
										* (vertex[i][(j + 1) % 5][0] + vertex[i][(j + 4) % 5][0]))),
						y
								+ (int) (length * ((2 * phi - 1)
										* vertex[i][j][1] + (1 - phi)
										* (vertex[i][(j + 1) % 5][1] + vertex[i][(j + 4) % 5][1]))),
						color[facelet[2 * i][2 * j + 1]]);
			}
		}
		for (int i = 0; i < 6; i++) {
			fillPent(
					g,
					x2
							- (int) (length * ((2 * phi - 1) * vertex[i][0][0] + (1 - phi)
									* (vertex[i][1][0] + vertex[i][4][0]))),
					y
							+ (int) (length * ((2 * phi - 1) * vertex[i][0][1] + (1 - phi)
									* (vertex[i][1][1] + vertex[i][4][1]))),
					x2
							- (int) (length * ((2 * phi - 1) * vertex[i][1][0] + (1 - phi)
									* (vertex[i][2][0] + vertex[i][0][0]))),
					y
							+ (int) (length * ((2 * phi - 1) * vertex[i][1][1] + (1 - phi)
									* (vertex[i][2][1] + vertex[i][0][1]))),
					x2
							- (int) (length * ((2 * phi - 1) * vertex[i][2][0] + (1 - phi)
									* (vertex[i][3][0] + vertex[i][1][0]))),
					y
							+ (int) (length * ((2 * phi - 1) * vertex[i][2][1] + (1 - phi)
									* (vertex[i][3][1] + vertex[i][1][1]))),
					x2
							- (int) (length * ((2 * phi - 1) * vertex[i][3][0] + (1 - phi)
									* (vertex[i][4][0] + vertex[i][2][0]))),
					y
							+ (int) (length * ((2 * phi - 1) * vertex[i][3][1] + (1 - phi)
									* (vertex[i][4][1] + vertex[i][2][1]))),
					x2
							- (int) (length * ((2 * phi - 1) * vertex[i][4][0] + (1 - phi)
									* (vertex[i][0][0] + vertex[i][3][0]))),
					y
							+ (int) (length * ((2 * phi - 1) * vertex[i][4][1] + (1 - phi)
									* (vertex[i][0][1] + vertex[i][3][1]))),
					color[center[2 * i + 1]]);
			for (int j = 0; j < 5; j++) {
				fillQuad(
						g,
						x2 - (int) (length * vertex[i][j][0]),
						y + (int) (length * vertex[i][j][1]),
						x2
								- (int) (length * (phi * vertex[i][j][0] + (1 - phi)
										* vertex[i][(j + 1) % 5][0])),
						y
								+ (int) (length * (phi * vertex[i][j][1] + (1 - phi)
										* vertex[i][(j + 1) % 5][1])),
						x2
								- (int) (length * ((2 * phi - 1)
										* vertex[i][j][0] + (1 - phi)
										* (vertex[i][(j + 1) % 5][0] + vertex[i][(j + 4) % 5][0]))),
						y
								+ (int) (length * ((2 * phi - 1)
										* vertex[i][j][1] + (1 - phi)
										* (vertex[i][(j + 1) % 5][1] + vertex[i][(j + 4) % 5][1]))),
						x2
								- (int) (length * (phi * vertex[i][j][0] + (1 - phi)
										* vertex[i][(j + 4) % 5][0])),
						y
								+ (int) (length * (phi * vertex[i][j][1] + (1 - phi)
										* vertex[i][(j + 4) % 5][1])),
						color[facelet[2 * i + 1][(10 - 2 * j) % 10]]);
				fillQuad(
						g,
						x2
								- (int) (length * (phi * vertex[i][j][0] + (1 - phi)
										* vertex[i][(j + 1) % 5][0])),
						y
								+ (int) (length * (phi * vertex[i][j][1] + (1 - phi)
										* vertex[i][(j + 1) % 5][1])),
						x2
								- (int) (length * (phi
										* vertex[i][(j + 1) % 5][0] + (1 - phi)
										* vertex[i][j][0])),
						y
								+ (int) (length * (phi
										* vertex[i][(j + 1) % 5][1] + (1 - phi)
										* vertex[i][j][1])),
						x2
								- (int) (length * ((2 * phi - 1)
										* vertex[i][(j + 1) % 5][0] + (1 - phi)
										* (vertex[i][(j + 2) % 5][0] + vertex[i][j][0]))),
						y
								+ (int) (length * ((2 * phi - 1)
										* vertex[i][(j + 1) % 5][1] + (1 - phi)
										* (vertex[i][(j + 2) % 5][1] + vertex[i][j][1]))),
						x2
								- (int) (length * ((2 * phi - 1)
										* vertex[i][j][0] + (1 - phi)
										* (vertex[i][(j + 1) % 5][0] + vertex[i][(j + 4) % 5][0]))),
						y
								+ (int) (length * ((2 * phi - 1)
										* vertex[i][j][1] + (1 - phi)
										* (vertex[i][(j + 1) % 5][1] + vertex[i][(j + 4) % 5][1]))),
						color[facelet[2 * i + 1][9 - 2 * j]]);
			}
		}
	}
}
