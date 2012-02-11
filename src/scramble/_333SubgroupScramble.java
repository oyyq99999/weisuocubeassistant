package scramble;

public class _333SubgroupScramble extends Scramble {

	private String subgroup;

	public _333SubgroupScramble(String subgroup) {
		this.length = 25;
		this.subgroup = subgroup;
	}

	public _333SubgroupScramble(String subgroup, int length) {
		this.length = length;
		this.subgroup = subgroup;
	}

	public String scramble() {
		sequence = new byte[length];
		String[][][] generator = null;
		byte[][][] seq = null;
		if (subgroup.equals("Roux")) {
			generator = new String[][][] { { { "M" }, { "", "2", "'" } },
					{ { "U" }, { "", "2", "'" } } };
			seq = new byte[][][] { { { -3 }, { 0, 1, 2 } },
					{ { 0 }, { 0, 1, 2 } } };
		} else if (subgroup.equals("RU")) {
			generator = new String[][][] { { { "U" }, { "", "2", "'" } },
					{ { "R" }, { "", "2", "'" } } };
			seq = new byte[][][] { { { 0 }, { 0, 1, 2 } },
					{ { 3 }, { 0, 1, 2 } } };
		} else if (subgroup.equals("RUL")) {
			generator = new String[][][] { { { "U" }, { "", "2", "'" } },
					{ { "R", "L" }, { "", "2", "'" } } };
			seq = new byte[][][] { { { 0 }, { 0, 1, 2 } },
					{ { 3, 12 }, { 0, 1, 2 } } };
		} else if (subgroup.equals("RrU")) {
			generator = new String[][][] { { { "U" }, { "", "2", "'" } },
					{ { "R", "Rw" }, { "", "2", "'" } } };
			seq = new byte[][][] { { { 0 }, { 0, 1, 2 } },
					{ { 3, 21 }, { 0, 1, 2 } } };
		} else if (subgroup.equals("RUF")) {
			generator = new String[][][] { { { "U" }, { "", "2", "'" } },
					{ { "R" }, { "", "2", "'" } },
					{ { "F" }, { "", "2", "'" } } };
			seq = new byte[][][] { { { 0 }, { 0, 1, 2 } },
					{ { 3 }, { 0, 1, 2 } }, { { 6 }, { 0, 1, 2 } } };
		} else if (subgroup.equals("HalfTurn")) {
			generator = new String[][][] { { { "U", "D" }, { "2" } },
					{ { "R", "L" }, { "2" } }, { { "F", "B" }, { "2" } } };
			seq = new byte[][][] { { { 0, 9 }, { 1 } }, { { 3, 12 }, { 1 } },
					{ { 6, 15 }, { 1 } } };
		}
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
