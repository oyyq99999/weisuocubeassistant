package scramble;

import java.util.Random;

public class ClockScramble extends Scramble {

	public ClockScramble() {
		this.length = 14;
	}
	
	static final Random rand = new Random();

	public String scramble() {
		sequence = new byte[14];
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 14; ++i) {
			sequence[i] = (byte) rand.nextInt(12);
		}
		sb.append("UR").append(hour2Str(sequence[0]));
		sb.append("DR").append(hour2Str(sequence[1]));
		sb.append("DL").append(hour2Str(sequence[2]));
		sb.append("UL").append(hour2Str(sequence[3]));
		sb.append('\n');
		sb.append("U").append(hour2Str(sequence[4]));
		sb.append("R").append(hour2Str(sequence[5]));
		sb.append("D").append(hour2Str(sequence[6]));
		sb.append("L").append(hour2Str(sequence[7]));
		sb.append('\n');
		sb.append("ALL").append(hour2Str(sequence[8]));
		sb.append("y2 ");
		sb.append('\n');
		sb.append("U").append(hour2Str(sequence[9]));
		sb.append("R").append(hour2Str(sequence[10]));
		sb.append("D").append(hour2Str(sequence[11]));
		sb.append("L").append(hour2Str(sequence[12]));
		sb.append('\n');
		sb.append("ALL").append(hour2Str(sequence[13]));
		int buttons = rand.nextInt(16);
		if ((buttons & 1) == 1) {
			sb.append("UR ");
		}
		if ((buttons & 2) == 2) {
			sb.append("DR ");
		}
		if ((buttons & 4) == 4) {
			sb.append("DL ");
		}
		if ((buttons & 8) == 8) {
			sb.append("UL ");
		}
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}

	private static String hour2Str(int hour) {
		StringBuffer sb = new StringBuffer();
		if (hour <= 6) {
			sb.append(hour).append('+');
		} else {
			sb.append(12 - hour).append('-');
		}
		sb.append(' ');
		return sb.toString();
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Clock";
	}
}
