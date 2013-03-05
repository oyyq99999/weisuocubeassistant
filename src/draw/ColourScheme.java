package draw;

public class ColourScheme {
	static int[] cube;
	static int[] sq1topbottom;
	static int[] sq1side;
	static int[] pyraminx;
	static int[] megaminx;
	static {
		cube = new int[] { 0xffffff, 0xffff00, 0xff0000, 0xff9000, 0x00ff00,
				0x0000ff };
		sq1topbottom = new int[] { 0xffffff, 0xffff00 };
		sq1side = new int[] { 0x00ff00, 0xff0000, 0x0000ff, 0xff9000 };
		pyraminx = new int[] { 0x00ff00, 0xff0000, 0x0000ff, 0xffff00 };
		megaminx = new int[] { 0xffffff, 0x6c676b, 0x1e7608, 0x2bbd00,
				0x5d028b, 0xe281b6, 0xeeee00, 0xfff063, 0x1b3fb7, 0x1c90f7,
				0xd81207, 0xf59c02 };
	}
}
