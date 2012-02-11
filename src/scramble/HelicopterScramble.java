package scramble;

public class HelicopterScramble extends Scramble {

	public HelicopterScramble() {
		this.length = 40;
	}

	public HelicopterScramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		String[][][] generator = new String[][][] {
				{ { "UR", "UL", "DR", "DL" }, { "" } },
				{ { "UF", "UB", "DF", "DB" }, { "" } },
				{ { "RF", "LF", "RB", "LB" }, { "" } } };
		byte[][][] seq = new byte[][][] { { { 0, 2, 4, 6 }, { 0 } },
				{ { 1, 3, 5, 7 }, { 0 } }, { { 8, 9, 10, 11 }, { 0 } } };
		scrambleSequence = generatorScramble(generator, seq, 0);
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Helicopter";
	}

}
