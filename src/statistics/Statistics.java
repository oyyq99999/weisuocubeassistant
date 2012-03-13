package statistics;

import java.util.Vector;

public class Statistics {
	Vector data = new Vector();

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
			System.out.println("VisitedVisitedVisitedVisitedVisitedVisited");
			return "没有成绩";
		}
		System.out.println("VisitedVisitedVisitedVisitedVisitedVisited2");
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		int sum = 0, sumwithoutDNF = 0, DNFs = 0;
		int best = Integer.MAX_VALUE, worst = 0;
		for (int i = 0; i < data.size(); ++i) {
			StringBuffer seq = new StringBuffer();
			Data d = (Data) data.elementAt(i);
			seq.append("\n").append(i + 1);
			seq.append(": ");
			if (d.time < Integer.MAX_VALUE) {
				seq.append(Data.time2str(d.time));
			} else {
				seq.append("DNF");
			}
			if (d.time > d.originaltime) {
				seq.append(" (");
				seq.append(Data.time2str(d.originaltime));
				seq.append(")");
			}
			sb2.insert(0, seq);
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
		sb.append("最快：");
		if (best < Integer.MAX_VALUE) {
			sb.append(Data.time2str(best));
		} else {
			sb.append("DNF");
		}
		sb.append("\n").append("最慢：");
		if (worst < Integer.MAX_VALUE) {
			sb.append(Data.time2str(worst));
		} else {
			sb.append("DNF");
		}
		if (sum > 2) {
			sb.append("\n").append("去头尾平均：");
			if (DNFs > 1) {
				sb.append("DNF");
			} else {
				double average;
				if (DNFs == 1) {
					average = ((double) (sumwithoutDNF - best))
							/ (data.size() - 2);
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
			double average = ((double) sumwithoutDNF) / (data.size() - DNFs);
			sb.append(Data.time2str((int) (average + 0.5)));
		}
		sb.append(sb2);
		return sb.toString();
	}
}
