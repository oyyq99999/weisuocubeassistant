package draw;

import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class DrawSQ1 {

	private byte[] sequence;
	private byte[] facelet = new byte[] { 0, 1, 1, 2, 3, 3, 4, 5, 5, 6, 7, 7,
			8, 9, 9, 10, 11, 11, 12, 13, 13, 14, 15, 15 };

	private final int[] color = new int[] { 0xffffff, 0xffff00 };
	private final int[] faceletcolor = new int[] { 0x00ff00, 0xff0000,
			0x0000ff, 0xff9000 };

	public DrawSQ1(byte[] scrambleSequence) {
		sequence = new byte[scrambleSequence.length];
		for (int i = 0; i < scrambleSequence.length; i++) {
			sequence[i] = scrambleSequence[i];
		}
		scramble();
	}

	private byte[] temp = new byte[12];

	private void twistUp(int twst) {
		for (int i = 0; i < 12; i++) {
			temp[i] = facelet[(i + twst) % 12];
		}
		for (int i = 0; i < 12; i++) {
			facelet[i] = temp[i];
		}
	}

	private void twistDown(int twst) {
		for (int i = 0; i < 12; i++) {
			temp[i] = facelet[12 + (12 + i - twst) % 12];
		}
		for (int i = 0; i < 12; i++) {
			facelet[12 + i] = temp[i];
		}
	}

	private void twist() {
		byte temp;
		for (int i = 0; i < 6; i++) {
			temp = facelet[i];
			facelet[i] = facelet[17 - i];
			facelet[17 - i] = temp;
		}
	}

	private void scramble() {
		for (int i = 0; i < sequence.length; i++) {
			int x = sequence[i] / 12;
			int y = sequence[i] % 12;
			if (y < 0) {
				x--;
				y += 12;
			}
			if (x < 0) {
				x += 12;
			}
			twistUp(x);
			twistDown(y);
			twist();
		}
	}

	private double[] dx = new double[] { (Math.sqrt(2) - Math.sqrt(6)) / 4,
			(Math.sqrt(6) - Math.sqrt(2)) / 4, (Math.sqrt(2)) / 2,
			(Math.sqrt(6) + Math.sqrt(2)) / 4,
			(Math.sqrt(6) + Math.sqrt(2)) / 4, (Math.sqrt(2)) / 2,
			(Math.sqrt(6) - Math.sqrt(2)) / 4,
			(Math.sqrt(2) - Math.sqrt(6)) / 4, (-Math.sqrt(2)) / 2,
			(-Math.sqrt(6) - Math.sqrt(2)) / 4,
			(-Math.sqrt(6) - Math.sqrt(2)) / 4, (-Math.sqrt(2)) / 2 };

	private double[] dy = new double[] { (Math.sqrt(6) + Math.sqrt(2)) / 4,
			(Math.sqrt(6) + Math.sqrt(2)) / 4, (Math.sqrt(2)) / 2,
			(Math.sqrt(6) - Math.sqrt(2)) / 4,
			(Math.sqrt(2) - Math.sqrt(6)) / 4, (-Math.sqrt(2)) / 2,
			(-Math.sqrt(6) - Math.sqrt(2)) / 4,
			(-Math.sqrt(6) - Math.sqrt(2)) / 4, (-Math.sqrt(2)) / 2,
			(Math.sqrt(2) - Math.sqrt(6)) / 4,
			(Math.sqrt(6) - Math.sqrt(2)) / 4, (Math.sqrt(2)) / 2 };

	public void paint(Graphics g) {
		int radius = (int) (GlobalData.width / ((14 + 8 * Math.sqrt(3)) / 3));
		double margin = 4.0 / 3;
		double corner = (1 + Math.sqrt(3)) / 2;
		int x1 = (int) (radius * (5 + 2 * Math.sqrt(3)) / 3);
		int x2 = GlobalData.width - x1;
		int y = GlobalData.height / 2;
		for (int i = 0; i < 12; i++) {
			if (facelet[i] % 2 == 0) {
				g.setColor(faceletcolor[(facelet[i] / 2) % 4]);
				g.fillTriangle(x1, y, x1 + (int) (radius * dx[i] * margin), y
						+ (int) (radius * dy[i] * margin), x1
						+ (int) (radius * dx[(i + 1) % 12] * margin), y
						+ (int) (radius * dy[(i + 1) % 12] * margin));
				g.setColor(color[facelet[i] / 8]);
				g.fillTriangle(x1, y, x1 + (int) (radius * dx[i]), y
						+ (int) (radius * dy[i]), x1
						+ (int) (radius * dx[(i + 1) % 12]), y
						+ (int) (radius * dy[(i + 1) % 12]));
				g.setColor(0x000000);
				g.drawLine(x1, y, x1 + (int) (radius * dx[i] * margin), y
						+ (int) (radius * dy[i] * margin));
				g.drawLine(x1, y, x1
						+ (int) (radius * dx[(i + 1) % 12] * margin), y
						+ (int) (radius * dy[(i + 1) % 12] * margin));
				g.drawLine(x1 + (int) (radius * dx[i]), y
						+ (int) (radius * dy[i]), x1
						+ (int) (radius * dx[(i + 1) % 12]), y
						+ (int) (radius * dy[(i + 1) % 12]));
				g.drawLine(x1 + (int) (radius * dx[i] * margin), y
						+ (int) (radius * dy[i] * margin), x1
						+ (int) (radius * dx[(i + 1) % 12] * margin), y
						+ (int) (radius * dy[(i + 1) % 12] * margin));
			} else {
				if (facelet[i] < 8) {
					g.setColor(faceletcolor[(facelet[i] / 2) % 4]);
				} else {
					g.setColor(faceletcolor[(facelet[i] / 2 + 1) % 4]);
				}
				g.fillTriangle(x1, y, x1 + (int) (radius * dx[i] * margin), y
						+ (int) (radius * dy[i] * margin), x1
						+ (int) (radius * dx[(i + 1) % 12] * margin * corner),
						y + (int) (radius * dy[(i + 1) % 12] * margin * corner));
				if (facelet[i] < 8) {
					g.setColor(faceletcolor[(facelet[i] / 2 + 1) % 4]);
				} else {
					g.setColor(faceletcolor[(facelet[i] / 2) % 4]);
				}
				g.fillTriangle(x1, y, x1
						+ (int) (radius * dx[(i + 2) % 12] * margin), y
						+ (int) (radius * dy[(i + 2) % 12] * margin), x1
						+ (int) (radius * dx[(i + 1) % 12] * margin * corner),
						y + (int) (radius * dy[(i + 1) % 12] * margin * corner));
				g.setColor(color[facelet[i] / 8]);
				g.fillTriangle(x1, y, x1 + (int) (radius * dx[i]), y
						+ (int) (radius * dy[i]), x1
						+ (int) (radius * dx[(i + 1) % 12] * corner), y
						+ (int) (radius * dy[(i + 1) % 12] * corner));
				g.fillTriangle(x1, y, x1 + (int) (radius * dx[(i + 2) % 12]), y
						+ (int) (radius * dy[(i + 2) % 12]), x1
						+ (int) (radius * dx[(i + 1) % 12] * corner), y
						+ (int) (radius * dy[(i + 1) % 12] * corner));
				g.setColor(0x000000);
				g.drawLine(x1, y, x1 + (int) (radius * dx[i] * margin), y
						+ (int) (radius * dy[i] * margin));
				g.drawLine(x1, y, x1
						+ (int) (radius * dx[(i + 2) % 12] * margin), y
						+ (int) (radius * dy[(i + 2) % 12] * margin));
				g.drawLine(x1 + (int) (radius * dx[(i + 1) % 12] * corner), y
						+ (int) (radius * dy[(i + 1) % 12] * corner), x1
						+ (int) (radius * dx[(i + 1) % 12] * margin * corner),
						y + (int) (radius * dy[(i + 1) % 12] * margin * corner));
				g.drawLine(x1 + (int) (radius * dx[i]), y
						+ (int) (radius * dy[i]), x1
						+ (int) (radius * dx[(i + 1) % 12] * corner), y
						+ (int) (radius * dy[(i + 1) % 12] * corner));
				g.drawLine(x1 + (int) (radius * dx[(i + 2) % 12]), y
						+ (int) (radius * dy[(i + 2) % 12]), x1
						+ (int) (radius * dx[(i + 1) % 12] * corner), y
						+ (int) (radius * dy[(i + 1) % 12] * corner));
				g.drawLine(x1 + (int) (radius * dx[i] * margin), y
						+ (int) (radius * dy[i] * margin), x1
						+ (int) (radius * dx[(i + 1) % 12] * margin * corner),
						y + (int) (radius * dy[(i + 1) % 12] * margin * corner));
				g.drawLine(x1 + (int) (radius * dx[(i + 2) % 12] * margin), y
						+ (int) (radius * dy[(i + 2) % 12] * margin), x1
						+ (int) (radius * dx[(i + 1) % 12] * margin * corner),
						y + (int) (radius * dy[(i + 1) % 12] * margin * corner));
				i++;
			}
		}
		for (int i = 0; i < 12; i++) {
			if (facelet[12 + i] % 2 == 0) {
				g.setColor(faceletcolor[(facelet[12 + i] / 2) % 4]);
				g.fillTriangle(x2, y, x2 + (int) (radius * dx[i] * margin), y
						- (int) (radius * dy[i] * margin), x2
						+ (int) (radius * dx[(i + 1) % 12] * margin), y
						- (int) (radius * dy[(i + 1) % 12] * margin));
				g.setColor(color[facelet[12 + i] / 8]);
				g.fillTriangle(x2, y, x2 + (int) (radius * dx[i]), y
						- (int) (radius * dy[i]), x2
						+ (int) (radius * dx[(i + 1) % 12]), y
						- (int) (radius * dy[(i + 1) % 12]));
				g.setColor(0x000000);
				g.drawLine(x2, y, x2 + (int) (radius * dx[i] * margin), y
						- (int) (radius * dy[i] * margin));
				g.drawLine(x2, y, x2
						+ (int) (radius * dx[(i + 1) % 12] * margin), y
						- (int) (radius * dy[(i + 1) % 12] * margin));
				g.drawLine(x2 + (int) (radius * dx[i]), y
						- (int) (radius * dy[i]), x2
						+ (int) (radius * dx[(i + 1) % 12]), y
						- (int) (radius * dy[(i + 1) % 12]));
				g.drawLine(x2 + (int) (radius * dx[i] * margin), y
						- (int) (radius * dy[i] * margin), x2
						+ (int) (radius * dx[(i + 1) % 12] * margin), y
						- (int) (radius * dy[(i + 1) % 12] * margin));
			} else {
				if (facelet[12 + i] < 8) {
					g.setColor(faceletcolor[(facelet[12 + i] / 2 + 1) % 4]);
				} else {
					g.setColor(faceletcolor[(facelet[12 + i] / 2) % 4]);
				}
				g.fillTriangle(x2, y, x2 + (int) (radius * dx[i] * margin), y
						- (int) (radius * dy[i] * margin), x2
						+ (int) (radius * dx[(i + 1) % 12] * margin * corner),
						y - (int) (radius * dy[(i + 1) % 12] * margin * corner));
				if (facelet[12 + i] < 8) {
					g.setColor(faceletcolor[(facelet[12 + i] / 2) % 4]);
				} else {
					g.setColor(faceletcolor[(facelet[12 + i] / 2 + 1) % 4]);
				}
				g.fillTriangle(x2, y, x2
						+ (int) (radius * dx[(i + 2) % 12] * margin), y
						- (int) (radius * dy[(i + 2) % 12] * margin), x2
						+ (int) (radius * dx[(i + 1) % 12] * margin * corner),
						y - (int) (radius * dy[(i + 1) % 12] * margin * corner));
				g.setColor(color[facelet[12 + i] / 8]);
				g.fillTriangle(x2, y, x2 + (int) (radius * dx[i]), y
						- (int) (radius * dy[i]), x2
						+ (int) (radius * dx[(i + 1) % 12] * corner), y
						- (int) (radius * dy[(i + 1) % 12] * corner));
				g.fillTriangle(x2, y, x2 + (int) (radius * dx[(i + 2) % 12]), y
						- (int) (radius * dy[(i + 2) % 12]), x2
						+ (int) (radius * dx[(i + 1) % 12] * corner), y
						- (int) (radius * dy[(i + 1) % 12] * corner));
				g.setColor(0x000000);
				g.drawLine(x2, y, x2 + (int) (radius * dx[i] * margin), y
						- (int) (radius * dy[i] * margin));
				g.drawLine(x2, y, x2
						+ (int) (radius * dx[(i + 2) % 12] * margin), y
						- (int) (radius * dy[(i + 2) % 12] * margin));
				g.drawLine(x2 + (int) (radius * dx[(i + 1) % 12] * corner), y
						- (int) (radius * dy[(i + 1) % 12] * corner), x2
						+ (int) (radius * dx[(i + 1) % 12] * margin * corner),
						y - (int) (radius * dy[(i + 1) % 12] * margin * corner));
				g.drawLine(x2 + (int) (radius * dx[i]), y
						- (int) (radius * dy[i]), x2
						+ (int) (radius * dx[(i + 1) % 12] * corner), y
						- (int) (radius * dy[(i + 1) % 12] * corner));
				g.drawLine(x2 + (int) (radius * dx[(i + 2) % 12]), y
						- (int) (radius * dy[(i + 2) % 12]), x2
						+ (int) (radius * dx[(i + 1) % 12] * corner), y
						- (int) (radius * dy[(i + 1) % 12] * corner));
				g.drawLine(x2 + (int) (radius * dx[i] * margin), y
						- (int) (radius * dy[i] * margin), x2
						+ (int) (radius * dx[(i + 1) % 12] * margin * corner),
						y - (int) (radius * dy[(i + 1) % 12] * margin * corner));
				g.drawLine(x2 + (int) (radius * dx[(i + 2) % 12] * margin), y
						- (int) (radius * dy[(i + 2) % 12] * margin), x2
						+ (int) (radius * dx[(i + 1) % 12] * margin * corner),
						y - (int) (radius * dy[(i + 1) % 12] * margin * corner));
				i++;
			}
		}
	}
}
