package util;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class CustomFont {

	private String fontSource = null;
	private Image fontImage = null;
	private int[] fontData = null;
	private int charWidth = 30;
	private int charHeight = 50;

	public CustomFont(String fontSource) {
		this.fontSource = fontSource;
		getFont();
	}

	private boolean getFont() {
		try {
			fontImage = Image.createImage(fontSource);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		fontData = new int[fontImage.getWidth() * fontImage.getHeight()];
		fontImage.getRGB(fontData, 0, fontImage.getWidth(), 0, 0,
				fontImage.getWidth(), fontImage.getHeight());
		for (int i = 0; i < fontData.length; i++) {
			if ((fontData[i] & 0x00ffffff) == 0x00ffffff) {
				fontData[i] &= 0x00ffffff;
			}
		}
		return true;
	}

	public void drawChar(Graphics g, int color, char character, int x, int y) {
		if (fontImage == null)
			return;
		int clipX = g.getClipX();
		int clipY = g.getClipY();
		int clipW = g.getClipWidth();
		int clipH = g.getClipHeight();
		if (character == '.' || character == ':') {
			g.setClip(x, y, charWidth / 3, charHeight);
		} else {
			g.setClip(x, y, charWidth, charHeight);
		}
		int drawingXPoint = 0;
		if (character >= '0' && character <= '9') {
			drawingXPoint = x - charWidth * (character - '0');
		} else if (character == '+') {
			drawingXPoint = x - charWidth * 10;
		} else if (character == '.') {
			drawingXPoint = x - charWidth * 11;
		} else if (character == ':') {
			drawingXPoint = x - charWidth * 11 - charWidth / 3;
		}
		for (int i = 0; i < fontData.length; i++) {
			if ((fontData[i] & 0x00ffffff) != 0x00ffffff) {
				fontData[i] = color;
			}
		}
		g.drawRGB(fontData, 0, fontImage.getWidth(), drawingXPoint, y,
				fontImage.getWidth(), fontImage.getHeight(), true);
		// g.drawImage(fontImage, drawingXPoint, y, anchor);
		g.setClip(clipX, clipY, clipW, clipH);
	}

	public void drawString(Graphics g, int color, String string, int x, int y,
			int anchor) {
		if (fontImage == null)
			return;

		int xPlus = 0;
		int yPlus = 0;
		if ((anchor & Graphics.LEFT) == Graphics.LEFT) {
			xPlus = 0;
		} else if ((anchor & Graphics.HCENTER) == Graphics.HCENTER) {
			int temp = 0;
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == '.' || string.charAt(i) == ':') {
					temp += charWidth / 3;
				} else
					temp += charWidth;
			}
			xPlus = 0 - temp / 2;
		} else if ((anchor & Graphics.RIGHT) == Graphics.RIGHT) {
			int temp = 0;
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == '.' || string.charAt(i) == ':') {
					temp += charWidth / 3;
				} else
					temp += charWidth;
			}
			xPlus = 0 - temp;
		}
		if ((anchor & Graphics.TOP) == Graphics.TOP) {
			yPlus = 0;
		} else if ((anchor & Graphics.VCENTER) == Graphics.VCENTER) {
			yPlus = 0 - fontImage.getHeight() / 2;
		} else if ((anchor & Graphics.BOTTOM) == Graphics.BOTTOM) {
			yPlus = 0 - fontImage.getHeight();
		} else if ((anchor & Graphics.BASELINE) == Graphics.BASELINE) {
			yPlus = 0 - fontImage.getHeight();
		}
		for (int i = 0; i < string.length(); i++) {
			drawChar(g, color, string.charAt(i), x + xPlus, y + yPlus);
			if (string.charAt(i) == '.' || string.charAt(i) == ':') {
				xPlus += charWidth / 3;
			} else
				xPlus += charWidth;
		}
	}
}
