package draw;

import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class DrawDino {

	private byte[] sequence;
	private byte[][] facelet = new byte[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 },
			{ 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 }, { 5, 5, 5, 5 } };
	private int[] color = ColourScheme.cube;

	public DrawDino(byte[] scrambleSequence) {
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
				cycle(0, 2, 2, 0, 4, 1, turn);
				cycle(0, 1, 2, 3, 4, 0, turn);
				break;
			case 1:
				cycle(0, 2, 4, 3, 3, 0, turn);
				cycle(0, 3, 4, 0, 3, 1, turn);
				break;
			case 2:
				cycle(0, 0, 5, 3, 2, 0, turn);
				cycle(0, 1, 5, 0, 2, 1, turn);
				break;
			case 3:
				cycle(0, 0, 3, 0, 5, 1, turn);
				cycle(0, 3, 3, 3, 5, 0, turn);
				break;
			case 4:
				cycle(1, 0, 4, 1, 2, 2, turn);
				cycle(1, 1, 4, 2, 2, 3, turn);
				break;
			case 5:
				cycle(1, 0, 3, 2, 4, 3, turn);
				cycle(1, 3, 3, 1, 4, 2, turn);
				break;
			case 6:
				cycle(1, 1, 2, 1, 5, 2, turn);
				cycle(1, 2, 2, 2, 5, 3, turn);
				break;
			case 7:
				cycle(1, 2, 5, 1, 3, 2, turn);
				cycle(1, 3, 5, 2, 3, 3, turn);
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
			fillTri(g, x + length * location[i][0],
					y + length * location[i][1], x + length
							* (location[i][0] - 1), y + length
							* (location[i][1] - 1), x + length
							* (location[i][0] + 1), y + length
							* (location[i][1] - 1), color[facelet[i][0]]);
			fillTri(g, x + length * location[i][0],
					y + length * location[i][1], x + length
							* (location[i][0] + 1), y + length
							* (location[i][1] - 1), x + length
							* (location[i][0] + 1), y + length
							* (location[i][1] + 1), color[facelet[i][1]]);
			fillTri(g, x + length * location[i][0],
					y + length * location[i][1], x + length
							* (location[i][0] + 1), y + length
							* (location[i][1] + 1), x + length
							* (location[i][0] - 1), y + length
							* (location[i][1] + 1), color[facelet[i][2]]);
			fillTri(g, x + length * location[i][0],
					y + length * location[i][1], x + length
							* (location[i][0] - 1), y + length
							* (location[i][1] + 1), x + length
							* (location[i][0] - 1), y + length
							* (location[i][1] - 1), color[facelet[i][3]]);
		}
	}

}
