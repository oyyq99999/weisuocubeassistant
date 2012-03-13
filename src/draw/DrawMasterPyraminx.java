package draw;

import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class DrawMasterPyraminx {

	private byte[] sequence;
	private byte[][][] facelet = new byte[][][] {
			{ { 0 }, { 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } },
			{ { 1 }, { 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 } },
			{ { 2 }, { 2, 2, 2 }, { 2, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 2, 2 } },
			{ { 3 }, { 3, 3, 3 }, { 3, 3, 3, 3, 3 }, { 3, 3, 3, 3, 3, 3, 3 } } };
	private int[] color = ColourScheme.pyraminx;

	public DrawMasterPyraminx(byte[] scrambleSequence) {
		sequence = new byte[scrambleSequence.length];
		for (int i = 0; i < scrambleSequence.length; i++) {
			sequence[i] = scrambleSequence[i];
		}
		scramble();
	}

	private void cycle(int f1, int r1, int c1, int f2, int r2, int c2, int f3,
			int r3, int c3, int turn) {
		byte temp = facelet[f1][r1][c1];
		if (turn == 0) {
			facelet[f1][r1][c1] = facelet[f3][r3][c3];
			facelet[f3][r3][c3] = facelet[f2][r2][c2];
			facelet[f2][r2][c2] = temp;
		} else {
			facelet[f1][r1][c1] = facelet[f2][r2][c2];
			facelet[f2][r2][c2] = facelet[f3][r3][c3];
			facelet[f3][r3][c3] = temp;
		}
	}

	private void scramble() {
		for (int i = 0; i < sequence.length; i++) {
			int turn = sequence[i] & 1;
			switch (sequence[i] >>> 1) {
			case 4:
				cycle(0, 2, 0, 2, 2, 0, 1, 2, 0, turn);
				cycle(0, 2, 1, 2, 2, 1, 1, 2, 1, turn);
				cycle(0, 2, 2, 2, 2, 2, 1, 2, 2, turn);
				cycle(0, 2, 3, 2, 2, 3, 1, 2, 3, turn);
				cycle(0, 2, 4, 2, 2, 4, 1, 2, 4, turn);
			case 0:
				cycle(0, 1, 0, 2, 1, 0, 1, 1, 0, turn);
				cycle(0, 1, 1, 2, 1, 1, 1, 1, 1, turn);
				cycle(0, 1, 2, 2, 1, 2, 1, 1, 2, turn);
				cycle(0, 0, 0, 2, 0, 0, 1, 0, 0, turn);
				break;
			case 5:
				cycle(0, 1, 0, 3, 3, 2, 2, 3, 2, turn);
				cycle(0, 2, 1, 3, 3, 3, 2, 3, 3, turn);
				cycle(0, 2, 2, 3, 2, 2, 2, 2, 2, turn);
				cycle(0, 3, 3, 3, 2, 3, 2, 2, 3, turn);
				cycle(0, 3, 4, 3, 1, 2, 2, 1, 2, turn);
			case 1:
				cycle(0, 2, 0, 3, 3, 4, 2, 3, 4, turn);
				cycle(0, 3, 1, 3, 3, 5, 2, 3, 5, turn);
				cycle(0, 3, 2, 3, 2, 4, 2, 2, 4, turn);
				cycle(0, 3, 0, 3, 3, 6, 2, 3, 6, turn);
				break;
			case 6:
				cycle(0, 3, 2, 1, 1, 0, 3, 1, 0, turn);
				cycle(0, 3, 3, 1, 2, 1, 3, 2, 1, turn);
				cycle(0, 2, 2, 1, 2, 2, 3, 2, 2, turn);
				cycle(0, 2, 3, 1, 3, 3, 3, 3, 3, turn);
				cycle(0, 1, 2, 1, 3, 4, 3, 3, 4, turn);
			case 2:
				cycle(0, 3, 4, 1, 2, 0, 3, 2, 0, turn);
				cycle(0, 3, 5, 1, 3, 1, 3, 3, 1, turn);
				cycle(0, 2, 4, 1, 3, 2, 3, 3, 2, turn);
				cycle(0, 3, 4, 1, 3, 0, 3, 3, 0, turn);
				break;
			case 7:
				cycle(1, 3, 2, 2, 1, 0, 3, 2, 4, turn);
				cycle(1, 3, 3, 2, 2, 1, 3, 2, 3, turn);
				cycle(1, 2, 2, 2, 2, 2, 3, 2, 2, turn);
				cycle(1, 2, 3, 2, 3, 3, 3, 2, 1, turn);
				cycle(1, 1, 2, 2, 3, 4, 3, 2, 0, turn);
			case 3:
				cycle(1, 3, 4, 2, 2, 0, 3, 1, 2, turn);
				cycle(1, 3, 5, 2, 3, 1, 3, 1, 1, turn);
				cycle(1, 2, 4, 2, 3, 2, 3, 1, 0, turn);
				cycle(1, 3, 4, 2, 3, 0, 3, 0, 0, turn);
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
		int length = GlobalData.width / 12;
		int x = (GlobalData.width - 8 * length) / 2;
		int y = (int) ((GlobalData.height - 4 * Math.sqrt(3) * length) / 2);
		int x1, y1, x2, y2, x3, y3;
		x1 = x + length * 4;
		y1 = y;
		x2 = x + length * 2;
		y2 = y + (int) (length * 2 * Math.sqrt(3));
		x3 = x + length * 6;
		y3 = y + (int) (length * 2 * Math.sqrt(3));
		fillTri(g, x1, y1, (3 * x1 + x2) / 4, (3 * y1 + y2) / 4,
				(3 * x1 + x3) / 4, (3 * y1 + y3) / 4, color[facelet[0][0][0]]);
		fillTri(g, (3 * x1 + x2) / 4, (3 * y1 + y2) / 4, (x1 + x2) / 2,
				(y1 + y2) / 2, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				color[facelet[0][1][0]]);
		fillTri(g, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				(3 * x1 + x2) / 4, (3 * y1 + y2) / 4, (3 * x1 + x3) / 4,
				(3 * y1 + y3) / 4, color[facelet[0][1][1]]);
		fillTri(g, (3 * x1 + x3) / 4, (3 * y1 + y3) / 4, (x1 + x3) / 2,
				(y1 + y3) / 2, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				color[facelet[0][1][2]]);
		fillTri(g, (x1 + x2) / 2, (y1 + y2) / 2, (x1 + 3 * x2) / 4,
				(y1 + 3 * y2) / 4, (x1 + 2 * x2 + x3) / 4,
				(y1 + 2 * y2 + y3) / 4, color[facelet[0][2][0]]);
		fillTri(g, (x1 + x2) / 2, (y1 + y2) / 2, (2 * x1 + x2 + x3) / 4, (2
				* y1 + y2 + y3) / 4, (x1 + 2 * x2 + x3) / 4,
				(y1 + 2 * y2 + y3) / 4, color[facelet[0][2][1]]);
		fillTri(g, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				(x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				color[facelet[0][2][2]]);
		fillTri(g, (x1 + x3) / 2, (y1 + y3) / 2, (2 * x1 + x2 + x3) / 4, (2
				* y1 + y2 + y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[0][2][3]]);
		fillTri(g, (x1 + x3) / 2, (y1 + y3) / 2, (x1 + 3 * x3) / 4,
				(y1 + 3 * y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[0][2][4]]);
		fillTri(g, (x1 + 3 * x2) / 4, (y1 + 3 * y2) / 4, x2, y2,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, color[facelet[0][3][0]]);
		fillTri(g, (x1 + 3 * x2) / 4, (y1 + 3 * y2) / 4,
				(x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, color[facelet[0][3][1]]);
		fillTri(g, (x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, (x2 + x3) / 2,
				(y2 + y3) / 2, color[facelet[0][3][2]]);
		fillTri(g, (x2 + x3) / 2, (y2 + y3) / 2, (x1 + 2 * x2 + x3) / 4, (y1
				+ 2 * y2 + y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[0][3][3]]);
		fillTri(g, (x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, (x2 + x3) / 2,
				(y2 + y3) / 2, color[facelet[0][3][4]]);
		fillTri(g, (x1 + 3 * x3) / 4, (y1 + 3 * y3) / 4,
				(x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, color[facelet[0][3][5]]);
		fillTri(g, (x1 + 3 * x3) / 4, (y1 + 3 * y3) / 4, x3, y3,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, color[facelet[0][3][6]]);
		x1 = x + length * 4;
		y1 = y;
		x2 = x + length * 6;
		y2 = y + (int) (length * 2 * Math.sqrt(3));
		x3 = x + length * 8;
		y3 = y;
		fillTri(g, x1, y1, (3 * x1 + x2) / 4, (3 * y1 + y2) / 4,
				(3 * x1 + x3) / 4, (3 * y1 + y3) / 4, color[facelet[1][0][0]]);
		fillTri(g, (3 * x1 + x2) / 4, (3 * y1 + y2) / 4, (x1 + x2) / 2,
				(y1 + y2) / 2, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				color[facelet[1][1][0]]);
		fillTri(g, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				(3 * x1 + x2) / 4, (3 * y1 + y2) / 4, (3 * x1 + x3) / 4,
				(3 * y1 + y3) / 4, color[facelet[1][1][1]]);
		fillTri(g, (3 * x1 + x3) / 4, (3 * y1 + y3) / 4, (x1 + x3) / 2,
				(y1 + y3) / 2, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				color[facelet[1][1][2]]);
		fillTri(g, (x1 + x2) / 2, (y1 + y2) / 2, (x1 + 3 * x2) / 4,
				(y1 + 3 * y2) / 4, (x1 + 2 * x2 + x3) / 4,
				(y1 + 2 * y2 + y3) / 4, color[facelet[1][2][0]]);
		fillTri(g, (x1 + x2) / 2, (y1 + y2) / 2, (2 * x1 + x2 + x3) / 4, (2
				* y1 + y2 + y3) / 4, (x1 + 2 * x2 + x3) / 4,
				(y1 + 2 * y2 + y3) / 4, color[facelet[1][2][1]]);
		fillTri(g, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				(x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				color[facelet[1][2][2]]);
		fillTri(g, (x1 + x3) / 2, (y1 + y3) / 2, (2 * x1 + x2 + x3) / 4, (2
				* y1 + y2 + y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[1][2][3]]);
		fillTri(g, (x1 + x3) / 2, (y1 + y3) / 2, (x1 + 3 * x3) / 4,
				(y1 + 3 * y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[1][2][4]]);
		fillTri(g, (x1 + 3 * x2) / 4, (y1 + 3 * y2) / 4, x2, y2,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, color[facelet[1][3][0]]);
		fillTri(g, (x1 + 3 * x2) / 4, (y1 + 3 * y2) / 4,
				(x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, color[facelet[1][3][1]]);
		fillTri(g, (x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, (x2 + x3) / 2,
				(y2 + y3) / 2, color[facelet[1][3][2]]);
		fillTri(g, (x2 + x3) / 2, (y2 + y3) / 2, (x1 + 2 * x2 + x3) / 4, (y1
				+ 2 * y2 + y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[1][3][3]]);
		fillTri(g, (x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, (x2 + x3) / 2,
				(y2 + y3) / 2, color[facelet[1][3][4]]);
		fillTri(g, (x1 + 3 * x3) / 4, (y1 + 3 * y3) / 4,
				(x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, color[facelet[1][3][5]]);
		fillTri(g, (x1 + 3 * x3) / 4, (y1 + 3 * y3) / 4, x3, y3,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, color[facelet[1][3][6]]);
		x1 = x + length * 4;
		y1 = y;
		x2 = x;
		y2 = y;
		x3 = x + length * 2;
		y3 = y + (int) (length * 2 * Math.sqrt(3));
		fillTri(g, x1, y1, (3 * x1 + x2) / 4, (3 * y1 + y2) / 4,
				(3 * x1 + x3) / 4, (3 * y1 + y3) / 4, color[facelet[2][0][0]]);
		fillTri(g, (3 * x1 + x2) / 4, (3 * y1 + y2) / 4, (x1 + x2) / 2,
				(y1 + y2) / 2, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				color[facelet[2][1][0]]);
		fillTri(g, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				(3 * x1 + x2) / 4, (3 * y1 + y2) / 4, (3 * x1 + x3) / 4,
				(3 * y1 + y3) / 4, color[facelet[2][1][1]]);
		fillTri(g, (3 * x1 + x3) / 4, (3 * y1 + y3) / 4, (x1 + x3) / 2,
				(y1 + y3) / 2, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				color[facelet[2][1][2]]);
		fillTri(g, (x1 + x2) / 2, (y1 + y2) / 2, (x1 + 3 * x2) / 4,
				(y1 + 3 * y2) / 4, (x1 + 2 * x2 + x3) / 4,
				(y1 + 2 * y2 + y3) / 4, color[facelet[2][2][0]]);
		fillTri(g, (x1 + x2) / 2, (y1 + y2) / 2, (2 * x1 + x2 + x3) / 4, (2
				* y1 + y2 + y3) / 4, (x1 + 2 * x2 + x3) / 4,
				(y1 + 2 * y2 + y3) / 4, color[facelet[2][2][1]]);
		fillTri(g, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				(x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				color[facelet[2][2][2]]);
		fillTri(g, (x1 + x3) / 2, (y1 + y3) / 2, (2 * x1 + x2 + x3) / 4, (2
				* y1 + y2 + y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[2][2][3]]);
		fillTri(g, (x1 + x3) / 2, (y1 + y3) / 2, (x1 + 3 * x3) / 4,
				(y1 + 3 * y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[2][2][4]]);
		fillTri(g, (x1 + 3 * x2) / 4, (y1 + 3 * y2) / 4, x2, y2,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, color[facelet[2][3][0]]);
		fillTri(g, (x1 + 3 * x2) / 4, (y1 + 3 * y2) / 4,
				(x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, color[facelet[2][3][1]]);
		fillTri(g, (x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, (x2 + x3) / 2,
				(y2 + y3) / 2, color[facelet[2][3][2]]);
		fillTri(g, (x2 + x3) / 2, (y2 + y3) / 2, (x1 + 2 * x2 + x3) / 4, (y1
				+ 2 * y2 + y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[2][3][3]]);
		fillTri(g, (x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, (x2 + x3) / 2,
				(y2 + y3) / 2, color[facelet[2][3][4]]);
		fillTri(g, (x1 + 3 * x3) / 4, (y1 + 3 * y3) / 4,
				(x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, color[facelet[2][3][5]]);
		fillTri(g, (x1 + 3 * x3) / 4, (y1 + 3 * y3) / 4, x3, y3,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, color[facelet[2][3][6]]);
		x1 = x + length * 4;
		y1 = y + (int) (length * 4 * Math.sqrt(3));
		x2 = x + length * 6;
		y2 = y + (int) (length * 2 * Math.sqrt(3));
		x3 = x + length * 2;
		y3 = y + (int) (length * 2 * Math.sqrt(3));
		fillTri(g, x1, y1, (3 * x1 + x2) / 4, (3 * y1 + y2) / 4,
				(3 * x1 + x3) / 4, (3 * y1 + y3) / 4, color[facelet[3][0][0]]);
		fillTri(g, (3 * x1 + x2) / 4, (3 * y1 + y2) / 4, (x1 + x2) / 2,
				(y1 + y2) / 2, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				color[facelet[3][1][0]]);
		fillTri(g, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				(3 * x1 + x2) / 4, (3 * y1 + y2) / 4, (3 * x1 + x3) / 4,
				(3 * y1 + y3) / 4, color[facelet[3][1][1]]);
		fillTri(g, (3 * x1 + x3) / 4, (3 * y1 + y3) / 4, (x1 + x3) / 2,
				(y1 + y3) / 2, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				color[facelet[3][1][2]]);
		fillTri(g, (x1 + x2) / 2, (y1 + y2) / 2, (x1 + 3 * x2) / 4,
				(y1 + 3 * y2) / 4, (x1 + 2 * x2 + x3) / 4,
				(y1 + 2 * y2 + y3) / 4, color[facelet[3][2][0]]);
		fillTri(g, (x1 + x2) / 2, (y1 + y2) / 2, (2 * x1 + x2 + x3) / 4, (2
				* y1 + y2 + y3) / 4, (x1 + 2 * x2 + x3) / 4,
				(y1 + 2 * y2 + y3) / 4, color[facelet[3][2][1]]);
		fillTri(g, (2 * x1 + x2 + x3) / 4, (2 * y1 + y2 + y3) / 4,
				(x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				color[facelet[3][2][2]]);
		fillTri(g, (x1 + x3) / 2, (y1 + y3) / 2, (2 * x1 + x2 + x3) / 4, (2
				* y1 + y2 + y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[3][2][3]]);
		fillTri(g, (x1 + x3) / 2, (y1 + y3) / 2, (x1 + 3 * x3) / 4,
				(y1 + 3 * y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[3][2][4]]);
		fillTri(g, (x1 + 3 * x2) / 4, (y1 + 3 * y2) / 4, x2, y2,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, color[facelet[3][3][0]]);
		fillTri(g, (x1 + 3 * x2) / 4, (y1 + 3 * y2) / 4,
				(x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, color[facelet[3][3][1]]);
		fillTri(g, (x1 + 2 * x2 + x3) / 4, (y1 + 2 * y2 + y3) / 4,
				(3 * x2 + x3) / 4, (3 * y2 + y3) / 4, (x2 + x3) / 2,
				(y2 + y3) / 2, color[facelet[3][3][2]]);
		fillTri(g, (x2 + x3) / 2, (y2 + y3) / 2, (x1 + 2 * x2 + x3) / 4, (y1
				+ 2 * y2 + y3) / 4, (x1 + x2 + 2 * x3) / 4,
				(y1 + y2 + 2 * y3) / 4, color[facelet[3][3][3]]);
		fillTri(g, (x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, (x2 + x3) / 2,
				(y2 + y3) / 2, color[facelet[3][3][4]]);
		fillTri(g, (x1 + 3 * x3) / 4, (y1 + 3 * y3) / 4,
				(x1 + x2 + 2 * x3) / 4, (y1 + y2 + 2 * y3) / 4,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, color[facelet[3][3][5]]);
		fillTri(g, (x1 + 3 * x3) / 4, (y1 + 3 * y3) / 4, x3, y3,
				(x2 + 3 * x3) / 4, (y2 + 3 * y3) / 4, color[facelet[3][3][6]]);
	}
}