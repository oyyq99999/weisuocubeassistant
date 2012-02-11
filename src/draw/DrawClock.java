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
		String[] move = new String[] { "111111000101000000",
				"000000101000111111", "011011011100000100",
				"100000100011011011", "000111111000000101",
				"101000000111111000", "110110110001000001",
				"001000001110110110", "011111111100000101",
				"110111111001000101", "111111110101000001",
				"111111011101000100", "111111111101000101",
				"101000101111111111" };
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 18; j++) {
				if (move[i].charAt(j) == '1') {
					needle[j] += sequence[i];
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			needle[i] = (needle[i] + 47) % 12 + 1;
			needle[i + 9] = 12 - (needle[i + 9] + 36) % 12;
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
