package scramble;

public class MegaminxRUScramble extends Scramble {

	public MegaminxRUScramble() {
		this.length = 30;
	}

	public MegaminxRUScramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		String[][][] generator = new String[][][] {
				{ { "U" }, { "", "2", "'", "2'", } },
				{ { "R" }, { "", "2", "'", "2'", } } };
		byte[][][] seq = new byte[][][] { { { 0 }, { 0, 1, 2, 3 } },
				{ { 4 }, { 0, 1, 2, 3 } } };
		scrambleSequence = generatorScramble(generator, seq, 0);
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Megaminx";
	}

}
