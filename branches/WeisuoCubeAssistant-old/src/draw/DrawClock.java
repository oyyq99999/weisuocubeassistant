package draw;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import model.GlobalData;

public class DrawClock {

	private int[] needle = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0 };
	private byte[] sequence;

	public DrawClock(byte[] scrambleSequence) {
		sequence = new byte[14];
		for (int i = 0; i < 14; i++)
			sequence[i] = scrambleSequence[i];
		scramble();
	}

	private void scramble() {
		int[] move = new int[] { 0066001, 0660100, 0330400, 0033004, 0077005,
				0666101, 0770500, 0333404, 0777505, 0005077, 0101666, 0500770,
				0404333, 0505777 };

		System.out.println(move.length);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if ((move[i] & (1 << j)) == 1 << j) {
					needle[j] += 12 - sequence[i];
				}
			}
			for (int j = 9; j < 18; j++) {
				if ((move[i] & (1 << j)) == 1 << j) {
					needle[j] += sequence[i];
				}
			}
		}
		for (int i = 9; i < 14; i++) {
			for (int j = 0; j < 9; j++) {
				if ((move[i] & (1 << j)) == 1 << j) {
					needle[j] += sequence[i];
				}
			}
			for (int j = 9; j < 18; j++) {
				if ((move[i] & (1 << j)) == 1 << j) {
					needle[j] += 12 - sequence[i];
				}
			}
		}
		for (int i = 0; i < 18; i++) {
			needle[i] = (needle[i] + 11) % 12 + 1;
		}
	}

	public void paint(Graphics g) {
		int gap = GlobalData.width / 10;
		g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		int y = GlobalData.height / 2 - gap - g.getFont().getHeight() / 2;
		g.setColor(0x000000);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				g.drawString(Integer.toString(needle[3 * j + i]),
						gap * (1 + i), y + gap * j, 0);
				g.drawString(Integer.toString(needle[9 + 3 * j + i]), gap
						* (6 + i), y + gap * j, 0);
			}
		}
	}
}
