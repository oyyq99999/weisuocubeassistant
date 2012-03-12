package statistics;

public class Data {
	int originaltime;
	int penalty;
	int time;
	String scramble;
	String comment;

	public Data(int time, String scramble) {
		this.originaltime = time;
		this.penalty = 0;
		this.time = time;
		this.scramble = new String(scramble);
		this.comment = "";
	}

	public Data(int time, int penalty, String scramble) {
		this.originaltime = time;
		this.penalty = penalty;
		this.time = this.penalty == 0x7fffffff ? 0x7fffffff : time + penalty;
		this.scramble = new String(scramble);
		this.comment = "";
	}

	public Data(int time, int penalty, String scramble, String comment) {
		this.originaltime = time;
		this.penalty = penalty;
		this.time = this.penalty == 0x7fffffff ? 0x7fffffff : time + penalty;
		this.scramble = new String(scramble);
		this.comment = new String(comment);
	}
}
