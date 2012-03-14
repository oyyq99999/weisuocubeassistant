package statistics;

public class Data {
	int originaltime;
	int penalty;
	int time;
	String scramble;
	String comment;

	public Data(int time) {
		this.originaltime = this.time = time;
		this.penalty = 0;
		this.scramble = "";
		this.comment = "";
	}

	public Data(int time, String scramble) {
		this.originaltime = this.time = time;
		this.penalty = 0;
		this.scramble = new String(scramble);
		this.comment = "";
	}

	public Data(int time, int penalty, String scramble) {
		this.originaltime = time;
		this.penalty = penalty;
		this.time = this.penalty == Integer.MAX_VALUE ? Integer.MAX_VALUE
				: time + penalty;
		this.scramble = new String(scramble);
		this.comment = "";
	}

	public Data(int time, int penalty, String scramble, String comment) {
		this.originaltime = time;
		this.penalty = penalty;
		this.time = this.penalty == Integer.MAX_VALUE ? Integer.MAX_VALUE
				: time + penalty;
		this.scramble = new String(scramble);
		this.comment = new String(comment);
	}

	public boolean isDNF() {
		return time == Integer.MAX_VALUE;
	}

	public static String time2str(int time) {
		StringBuffer sb = new StringBuffer();
		if (time < 10 * 60 * 1000) {
			int seconds = time / 1000;
			time %= 1000;
			int minutes = seconds / 60;
			seconds %= 60;
			if (minutes > 0) {
				sb.append(minutes).append(":");
				if (seconds < 10) {
					sb.append("0");
				}
			}
			sb.append(seconds).append(".");
			if (time < 100) {
				sb.append("0");
				if (time < 10) {
					sb.append("0");
				}
			}
			sb.append(time);
		} else {
			time += 500;
			int seconds = time / 1000;
			int minutes = seconds / 60;
			seconds %= 60;
			int hours = minutes / 60;
			minutes %= 60;
			if (hours > 0) {
				sb.append(hours).append(":");
				if (minutes < 10) {
					sb.append("0");
				}
			}
			sb.append(minutes).append(":");
			if (seconds < 10) {
				sb.append("0");
			}
			sb.append(seconds);
		}
		return sb.toString();
	}
}
