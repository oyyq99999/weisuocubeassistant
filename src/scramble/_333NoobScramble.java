package scramble;

public class _333NoobScramble extends Scramble {

	public _333NoobScramble() {
		this.length = 25;
	}

	public _333NoobScramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		String[][][] generator = new String[][][] {
				{ { "上面", "下面" }, { "顺时针转动90°", "转动180°", "逆时针转动90°" } },
				{ { "左面", "右面" }, { "顺时针转动90°", "转动180°", "逆时针转动90°" } },
				{ { "前面", "后面" }, { "顺时针转动90°", "转动180°", "逆时针转动90°" } } };
		byte[][][] seq = new byte[][][] { { { 0, 9 }, { 0, 1, 2 } },
				{ { 3, 12 }, { 0, 1, 2 } }, { { 6, 15 }, { 0, 1, 2 } } };
		scrambleSequence = generatorScramble(generator, seq, 0);
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Cube3";
	}

}
