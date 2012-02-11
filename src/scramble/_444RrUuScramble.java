package scramble;

public class _444RrUuScramble extends Scramble {

	public _444RrUuScramble() {
		this.length = 40;
	}

	public _444RrUuScramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		String[][][] generator = new String[][][] {
				{ { "Uw", "U" }, { "", "2", "'" } },
				{ { "Rw", "R" }, { "", "2", "'" } } };
		byte[][][] seq = new byte[][][] { { { 18, 0 }, { 0, 1, 2 } },
				{ { 21, 3 }, { 0, 1, 2 } } };
		scrambleSequence = generatorScramble(generator, seq, 0);
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Cube4";
	}

}
