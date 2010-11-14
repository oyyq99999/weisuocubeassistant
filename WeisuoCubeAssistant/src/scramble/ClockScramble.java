package scramble;

import java.util.Random;

public class ClockScramble extends Scramble {

	private boolean verbose;
	private String ul = "U";
	private String ur = "U";
	private String dl = "d";
	private String dr = "d";

	public ClockScramble() {
		this.length = 14;
		this.verbose = true;
	}

	public ClockScramble(boolean verbose) {
		this.length = 14;
		this.verbose = verbose;
	}

	public String scramble() {
		StringBuffer sequence = new StringBuffer();
		Random rand = new Random();
		ul = "U";
		ur = "U";
		dl = "d";
		dr = "d";
		int u = rand.nextInt(12) - 5;
		int d = rand.nextInt(12) - 5;
		addMove(sequence, u, d);
		ul = "d";
		ur = "U";
		dl = "d";
		dr = "U";
		u = rand.nextInt(12) - 5;
		d = rand.nextInt(12) - 5;
		addMove(sequence, u, d);
		ul = "d";
		ur = "d";
		dl = "U";
		dr = "U";
		u = rand.nextInt(12) - 5;
		d = rand.nextInt(12) - 5;
		addMove(sequence, u, d);
		ul = "U";
		ur = "d";
		dl = "U";
		dr = "d";
		u = rand.nextInt(12) - 5;
		d = rand.nextInt(12) - 5;
		addMove(sequence, u, d);
		ul = "d";
		ur = "U";
		dl = "U";
		dr = "U";
		u = rand.nextInt(12) - 5;
		addMoveU(sequence, u);
		ul = "U";
		ur = "d";
		dl = "U";
		dr = "U";
		u = rand.nextInt(12) - 5;
		addMoveU(sequence, u);
		ul = "U";
		ur = "U";
		dl = "U";
		dr = "d";
		u = rand.nextInt(12) - 5;
		addMoveU(sequence, u);
		ul = "U";
		ur = "U";
		dl = "d";
		dr = "U";
		u = rand.nextInt(12) - 5;
		addMoveU(sequence, u);
		ul = "U";
		ur = "U";
		dl = "U";
		dr = "U";
		u = rand.nextInt(12) - 5;
		addMoveU(sequence, u);
		ul = "d";
		ur = "d";
		dl = "d";
		dr = "d";
		d = rand.nextInt(12) - 5;
		addMoveD(sequence, d);
		ul = rand.nextInt(2) == 0 ? "U" : "d";
		ur = rand.nextInt(2) == 0 ? "U" : "d";
		dl = rand.nextInt(2) == 0 ? "U" : "d";
		dr = rand.nextInt(2) == 0 ? "U" : "d";
		addMovePins(sequence);
		scrambleSequence = sequence.toString().trim();
		return scrambleSequence;
	}

	private void addMovePins(StringBuffer sequence) {
		sequence.append(ul + ur + dl + dr);
	}

	private void addMoveD(StringBuffer sequence, int d) {
		if (verbose) {
			sequence.append(ul + ur + dl + dr + " ");
		}
		sequence.append("d=" + d + " / ");
	}

	private void addMoveU(StringBuffer sequence, int u) {
		if (verbose) {
			sequence.append(ul + ur + dl + dr + " ");
		}
		sequence.append("U=" + u + " / ");
	}

	private void addMove(StringBuffer sequence, int u, int d) {
		if (verbose) {
			sequence.append(ul + ur + dl + dr + " ");
		}
		sequence.append("U=" + u + ", d=" + d + " / ");
	}

	public static void main(String[] args) {
		System.out.println(new ClockScramble(false).scramble());
	}

}
