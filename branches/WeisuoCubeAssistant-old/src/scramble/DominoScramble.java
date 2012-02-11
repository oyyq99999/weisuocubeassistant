package scramble;

public class DominoScramble extends Scramble {

	public DominoScramble() {
		this.length = 25;
	}

	public DominoScramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		String[][][] generator = new String[][][] {
				{ { "U" }, { "", "2", "'" } }, { { "R", "L" }, { "2" } },
				{ { "F", "B" }, { "2" } } };
		byte seq[][][] = new byte[][][] { { { 0 }, { 0, 1, 2 } },
				{ { 3, 5 }, { 0 } }, { { 4, 6 }, { 0 } } };
		scrambleSequence = generatorScramble(generator, seq, 0);
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Domino";
	}

}
