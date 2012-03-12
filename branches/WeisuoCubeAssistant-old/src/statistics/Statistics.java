package statistics;

import java.util.Vector;

public class Statistics {
	Vector data = new Vector(12);

	public Statistics() {
	}

	public void addData(Data data) {
		this.data.addElement(data);
	}

	public void reset() {
		data.removeAllElements();
	}

	public String toString() {
		if (data.isEmpty()) {
			return "没有成绩";
		}
		StringBuffer sb = new StringBuffer();
		int sum = 0, sumwithoutDNF = 0, DNFs = 0;
		int best = Integer.MAX_VALUE, worst = 0;
		for (int i = 0; i < data.size(); ++i) {
			StringBuffer seq = new StringBuffer();
			Data d = (Data) data.elementAt(i);
			seq.append(i + 1);
			seq.append(": ");
			if (d.time < Integer.MAX_VALUE) {
				seq.append(d.time);
			} else {
				seq.append("DNF");
			}
			if (d.time > d.originaltime) {
				seq.append(" (");
				seq.append(d.originaltime);
				seq.append(")");
			}
			sb.insert(0, seq);
			if (d.time < Integer.MAX_VALUE) {
				if (sum < Integer.MAX_VALUE) {
					sum += d.time;
				}
				sumwithoutDNF += d.time;
			} else {
				sum = Integer.MAX_VALUE;
				++DNFs;
			}
			if (d.time < best) {
				best = d.time;
			}
			if (d.time > worst) {
				worst = d.time;
			}
		}
		StringBuffer seq = new StringBuffer();
		seq.append("最快：");
		if (best < Integer.MAX_VALUE) {
			seq.append(best);
		} else {
			seq.append("DNF");
		}
		seq.append("最慢：");
		if (worst < Integer.MAX_VALUE) {
			seq.append(worst);
		} else {
			seq.append("DNF");
		}
		if (sum > 2) {
			seq.append("去头尾平均：");
			if (DNFs > 1) {
				seq.append("DNF");
			} else {
				double average;
				if (DNFs == 1) {
					average = ((double) (sumwithoutDNF - best))
							/ (data.size() - 2);
				} else {
					average = ((double) (sum - best - worst))
							/ (data.size() - 2);
				}
				seq.append((int) (average + 0.5));
			}
		}
		seq.append("平均：");
		if (DNFs > 0) {
			seq.append("DNF");
		} else {
			double average = ((double) sum) / (data.size() - 2);
			seq.append((int) (average + 0.5));
		}
		if (DNFs != data.size()) {
			seq.append("不计DNF平均：");
			double average = ((double) (sumwithoutDNF - best))
					/ (data.size() - 2);
			seq.append((int) (average + 0.5));
		}
		sb.insert(0, seq);
		return sb.toString();
	}
}
