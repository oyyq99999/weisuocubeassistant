package scramble;

public class OldSkewbScramble extends Scramble {

	public OldSkewbScramble() {
		this.length = 25;
	}

	public OldSkewbScramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		String[][][] generator = new String[][][] { { { "R" }, { "", "'" } },
				{ { "L" }, { "", "'" } }, { { "D" }, { "", "'" } },
				{ { "B" }, { "", "'" } } };
		byte[][][] seq = new byte[][][] { { { 0 }, { 0, 1 } },
				{ { 2 }, { 0, 1 } }, { { 4 }, { 0, 1 } }, { { 6 }, { 0, 1 } } };
		scrambleSequence = generatorScramble(generator, seq, 0);
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Skewb";
	}

}
