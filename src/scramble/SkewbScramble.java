package scramble;

public class SkewbScramble extends Scramble {

	public SkewbScramble() {
		this.length = 25;
	}

	public SkewbScramble(int length) {
		this.length = length;
	}

	public String scramble() {
		String[][][] generator = new String[][][] { { { "U" }, { "", "'" } },
				{ { "R" }, { "", "'" } }, { { "L" }, { "", "'" } },
				{ { "B" }, { "", "'" } } };
		this.scrambleSequence = generatorScramble(generator);
		return scrambleSequence;
	}

	public static void main(String[] args) {
		System.out.println(new SkewbScramble().scramble());
	}

}
