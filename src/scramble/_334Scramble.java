package scramble;

public class _334Scramble extends Scramble {

	public _334Scramble() {
		this.length = 40;
	}

	public _334Scramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		String[][][] generator = new String[][][] {
				{ { "Uw", "U", "D" }, { "", "2", "'" } },
				{ { "R", "L" }, { "2" } }, { { "F", "B" }, { "2" } } };
		byte[][][] seq = new byte[][][] { { { 3, 0, 6 }, { 0, 1, 2 } },
				{ { 9, 11 }, { 0 } }, { { 10, 12 }, { 0 } } };
		scrambleSequence = generatorScramble(generator, seq, 0);
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "334";
	}

}
