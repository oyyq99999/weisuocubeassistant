package statistics;

import java.util.Vector;

public class Statistics {
	Vector data;
	int best, worst, sum, DNFs;

	public Statistics() {
		data = new Vector();
		sum = DNFs = 0;
		best = Integer.MAX_VALUE;
		worst = 0;
	}

	public void push_back(Data data) {
		this.data.addElement(data);
		if (data.time == Integer.MAX_VALUE) {
			++DNFs;
			worst = Integer.MAX_VALUE;
		} else {
			sum += data.time;
			if (data.time < best) {
				best = data.time;
			}
			if (data.time > worst) {
				worst = data.time;
			}
		}
	}

	public void pop_back() {
		this.data.removeElementAt(data.size() - 1);
		sum = DNFs = 0;
		best = Integer.MAX_VALUE;
		worst = 0;
		for (int i = 0; i < data.size(); ++i) {
			Data d = (Data) data.elementAt(i);
			if (d.time == Integer.MAX_VALUE) {
				++DNFs;
				worst = Integer.MAX_VALUE;
			} else {
				sum += d.time;
				if (d.time < best) {
					best = d.time;
				}
				if (d.time > worst) {
					worst = d.time;
				}
			}
		}
	}

	public void reset() {
		data.removeAllElements();
		sum = DNFs = 0;
		best = Integer.MAX_VALUE;
		worst = 0;
	}

	public String toString() {
		if (data.isEmpty()) {
			return "没有成绩";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("最快/最慢成绩：");
		if (best < Integer.MAX_VALUE) {
			sb.append(Data.time2str(best)).append(" / ");
			if (worst < Integer.MAX_VALUE) {
				sb.append(Data.time2str(worst));
			} else {
				sb.append("DNF");
			}
		} else {
			sb.append("DNF / DNF");
		}
		if (data.size() > 2) {
			sb.append("\n").append("去头尾平均：");
			if (DNFs > 1) {
				sb.append("DNF");
			} else {
				double average;
				if (DNFs == 1) {
					average = ((double) (sum - best)) / (data.size() - 2);
				} else {
					average = ((double) (sum - best - worst))
							/ (data.size() - 2);
				}
				sb.append(Data.time2str((int) (average + 0.5)));
			}
		}
		sb.append("\n").append("平均：");
		if (DNFs > 0) {
			sb.append("DNF");
		} else {
			double average = ((double) sum) / data.size();
			sb.append(Data.time2str((int) (average + 0.5)));
		}
		if (DNFs != data.size()) {
			sb.append("\n").append("不计DNF平均：");
			double average = ((double) sum) / (data.size() - DNFs);
			sb.append(Data.time2str((int) (average + 0.5)));
		}
		for (int i = data.size(); --i >= 0;) {
			Data d = (Data) data.elementAt(i);
			sb.append("\nNo.").append(i + 1);
			sb.append(":  ");
			if (d.time < Integer.MAX_VALUE) {
				sb.append(Data.time2str(d.time));
			} else {
				sb.append("DNF");
			}
			if (d.time > d.originaltime) {
				sb.append(" (");
				sb.append(Data.time2str(d.originaltime));
				sb.append(")");
			}
		}
		return sb.toString();
	}
}
