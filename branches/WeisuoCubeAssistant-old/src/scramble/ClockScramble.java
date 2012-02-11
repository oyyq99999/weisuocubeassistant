package scramble;

import java.util.Random;

public class ClockScramble extends Scramble {

	private boolean verbose;
	private String button;

	public ClockScramble() {
		this.length = 14;
		this.verbose = true;
	}

	public ClockScramble(boolean verbose) {
		this.length = 14;
		this.verbose = verbose;
	}

	public String scramble() {
		sequence = new byte[14];
		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		button = "UUdd";
		int u = rand.nextInt(12) - 5;
		int d = rand.nextInt(12) - 5;
		addMove(sb, u, d);
		sequence[0] = (byte) u;
		sequence[1] = (byte) d;
		button = "dUdU";
		u = rand.nextInt(12) - 5;
		d = rand.nextInt(12) - 5;
		addMove(sb, u, d);
		sequence[2] = (byte) u;
		sequence[3] = (byte) d;
		sb.append("\n");
		button = "ddUU";
		u = rand.nextInt(12) - 5;
		d = rand.nextInt(12) - 5;
		addMove(sb, u, d);
		sequence[4] = (byte) u;
		sequence[5] = (byte) d;
		button = "UdUd";
		u = rand.nextInt(12) - 5;
		d = rand.nextInt(12) - 5;
		addMove(sb, u, d);
		sequence[6] = (byte) u;
		sequence[7] = (byte) d;
		sb.append("\n");
		button = "dUUU";
		u = rand.nextInt(12) - 5;
		addMoveU(sb, u);
		sequence[8] = (byte) u;
		button = "UdUU";
		u = rand.nextInt(12) - 5;
		addMoveU(sb, u);
		sequence[9] = (byte) u;
		sb.append("\n");
		button = "UUUd";
		u = rand.nextInt(12) - 5;
		sequence[10] = (byte) u;
		addMoveU(sb, u);
		button = "UUdU";
		u = rand.nextInt(12) - 5;
		addMoveU(sb, u);
		sequence[11] = (byte) u;
		sb.append("\n");
		button = "UUUU";
		u = rand.nextInt(12) - 5;
		addMoveU(sb, u);
		sequence[12] = (byte) u;
		button = "dddd";
		d = rand.nextInt(12) - 5;
		addMoveD(sb, d);
		sequence[13] = (byte) d;
		sb.append("\n");
		String[] randomButton = new String[] { "UUUU", "UUUd", "UUdU", "UUdd",
				"UdUU", "UdUd", "UddU", "Uddd", "dUUU", "dUUd", "dUdU", "dUdd",
				"ddUU", "ddUd", "dddU", "dddd" };
		button = randomButton[rand.nextInt(16)];
		addMovePins(sb);
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}

	private void addMovePins(StringBuffer sequence) {
		sequence.append(button);
	}

	private void addMoveD(StringBuffer sequence, int d) {
		if (verbose) {
			sequence.append(button + " ");
		}
		sequence.append("d="
				+ (d >= 0 ? (" " + Integer.toString(d)) : Integer.toString(d))
				+ " / ");
	}

	private void addMoveU(StringBuffer sequence, int u) {
		if (verbose) {
			sequence.append(button + " ");
		}
		sequence.append("U="
				+ (u >= 0 ? (" " + Integer.toString(u)) : Integer.toString(u))
				+ " / ");
	}

	private void addMove(StringBuffer sequence, int u, int d) {
		if (verbose) {
			sequence.append(button + " ");
		}
		sequence.append("U="
				+ (u >= 0 ? (" " + Integer.toString(u)) : Integer.toString(u))
				+ ", d="
				+ (d >= 0 ? (" " + Integer.toString(d)) : Integer.toString(d))
				+ " / ");
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Clock";
	}
}
