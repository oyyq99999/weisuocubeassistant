package scramble;

public class MasterPyraminxScramble extends Scramble {

	public MasterPyraminxScramble() {
		this.length = 40;
	}

	public MasterPyraminxScramble(int length) {
		this.length = length;
	}

	public String scramble() {
		sequence = new byte[length];
		String[][][] generator = new String[][][] {
				{ { "U", "Uw" }, { "", "'" } }, { { "L", "Lw" }, { "", "'" } },
				{ { "R", "Rw" }, { "", "'" } }, { { "B", "Bw" }, { "", "'" } } };
		byte[][][] seq = new byte[][][] { { { 0, 8 }, { 0, 1 } },
				{ { 2, 10 }, { 0, 1 } }, { { 4, 12 }, { 0, 1 } },
				{ { 6, 14 }, { 0, 1 } } };
		scrambleSequence = generatorScramble(generator, seq, 0);
		return scrambleSequence;
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "MasterPyraminx";
	}

}
