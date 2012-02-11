package scramble;

public class DinoScramble extends Scramble {

	public DinoScramble() {
		this.length = 25;
	}

	public DinoScramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		String[][][] generator = new String[][][] {
				{ { "URF", "ULF", "URB", "ULB" }, { "", "'" } },
				{ { "DRF", "DLF", "DRB", "DLB" }, { "", "'" } } };
		byte[][][] seq = new byte[][][] { { { 0, 2, 4, 6 }, { 0, 1 } },
				{ { 8, 10, 12, 14 }, { 0, 1 } } };
		scrambleSequence = generatorScramble(generator, seq, 0);
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Dino";
	}

}
