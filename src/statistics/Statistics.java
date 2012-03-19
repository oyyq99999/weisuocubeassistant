package statistics;

import java.util.Vector;

public class Statistics {
	Vector data;
	int best, worst, sum, DNFs;
	long sos;
	int bestmo3, bestao5, bestao12;
	Vector mo3, ao5, ao12;

	public Statistics() {
		data = new Vector(12);
		sum = DNFs = 0;
		best = Integer.MAX_VALUE;
		worst = 0;
		sos = 0;
		mo3 = new Vector(12);
		ao5 = new Vector(12);
		ao12 = new Vector(12);
		bestmo3 = bestao5 = bestao12 = Integer.MAX_VALUE;
	}

	public void push_back(Data data) {
		this.data.addElement(data);
		int n = this.data.size();
		Data[] recentstats;
		int mo3 = 0, ao5 = 0, ao12 = 0;
		if (n < 3) {
			this.mo3.addElement(new Integer(0));
		} else {
			recentstats = new Data[3];
			int sum = 0;
			boolean flag = true;
			for (int i = 0; i < 3; ++i) {
				recentstats[i] = (Data) this.data.elementAt(n - 3 + i);
				if (recentstats[i].isDNF()) {
					mo3 = Integer.MAX_VALUE;
					flag = false;
					break;
				}
				sum += recentstats[i].time;
			}
			if (flag) {
				mo3 = (int) ((double) sum / 3 + 0.5);
			}
			this.mo3.addElement(new Integer(mo3));
		}
		if (n < 5) {
			this.ao5.addElement(new Integer(0));
		} else {
			recentstats = new Data[5];
			int sum = 0, best = Integer.MAX_VALUE, worst = 0, DNFs = 0;
			for (int i = 0; i < 5; ++i) {
				recentstats[i] = (Data) this.data.elementAt(n - 5 + i);
				if (recentstats[i].isDNF()) {
					worst = Integer.MAX_VALUE;
					++DNFs;
				} else {
					sum += recentstats[i].time;
					if (recentstats[i].time < best) {
						best = recentstats[i].time;
					}
					if (recentstats[i].time > worst) {
						worst = recentstats[i].time;
					}
				}
			}
			if (DNFs > 1) {
				ao5 = Integer.MAX_VALUE;
			} else if (DNFs == 1) {
				ao5 = (int) ((double) (sum - best) / 3 + 0.5);
			} else {
				ao5 = (int) ((double) (sum - best - worst) / 3 + 0.5);
			}
			this.ao5.addElement(new Integer(ao5));
		}
		if (n < 12) {
			this.ao12.addElement(new Integer(0));
		} else {
			recentstats = new Data[12];
			int sum = 0, best = Integer.MAX_VALUE, worst = 0, DNFs = 0;
			for (int i = 0; i < 12; ++i) {
				recentstats[i] = (Data) this.data.elementAt(n - 12 + i);
				if (recentstats[i].isDNF()) {
					worst = Integer.MAX_VALUE;
					++DNFs;
				} else {
					sum += recentstats[i].time;
					if (recentstats[i].time < best) {
						best = recentstats[i].time;
					}
					if (recentstats[i].time > worst) {
						worst = recentstats[i].time;
					}
				}
			}
			if (DNFs > 1) {
				ao12 = Integer.MAX_VALUE;
			} else if (DNFs == 1) {
				ao12 = (int) ((double) (sum - best) / 10 + 0.5);
			} else {
				ao12 = (int) ((double) (sum - best - worst) / 10 + 0.5);
			}
			this.ao12.addElement(new Integer(ao12));
		}
		if (data.time == Integer.MAX_VALUE) {
			++DNFs;
			worst = Integer.MAX_VALUE;
		} else {
			sum += data.time;
			sos += (long) data.time * data.time;
			if (data.time < best) {
				best = data.time;
			}
			if (data.time > worst) {
				worst = data.time;
			}
		}
		if (mo3 > 0 && mo3 < bestmo3) {
			bestmo3 = mo3;
		}
		if (ao5 > 0 && ao5 < bestao5) {
			bestao5 = ao5;
		}
		if (ao12 > 0 && ao12 < bestao12) {
			bestao12 = ao12;
		}
	}

	public void pop_back() {
		if (data.size() == 0) {
			return;
		}
		this.data.removeElementAt(data.size() - 1);
		mo3.removeElementAt(mo3.size() - 1);
		ao5.removeElementAt(ao5.size() - 1);
		ao12.removeElementAt(ao12.size() - 1);
		sum = DNFs = 0;
		best = Integer.MAX_VALUE;
		worst = 0;
		sos = 0;
		bestmo3 = bestao5 = bestao12 = Integer.MAX_VALUE;
		for (int i = 0; i < data.size(); ++i) {
			Data d = (Data) data.elementAt(i);
			if (d.time == Integer.MAX_VALUE) {
				++DNFs;
				worst = Integer.MAX_VALUE;
			} else {
				sum += d.time;
				sos += (long) d.time * d.time;
				if (d.time < best) {
					best = d.time;
				}
				if (d.time > worst) {
					worst = d.time;
				}
			}
			int mo3 = ((Integer) this.mo3.elementAt(i)).intValue();
			if (mo3 > 0 && mo3 < bestmo3) {
				bestmo3 = mo3;
			}
			int ao5 = ((Integer) this.ao5.elementAt(i)).intValue();
			if (ao5 > 0 && ao5 < bestao5) {
				bestao5 = ao5;
			}
			int ao12 = ((Integer) this.ao12.elementAt(i)).intValue();
			if (ao12 > 0 && ao12 < bestao12) {
				bestao12 = ao12;
			}
		}
	}

	public void reset() {
		data.removeAllElements();
		mo3.removeAllElements();
		ao5.removeAllElements();
		ao12.removeAllElements();
		sum = DNFs = 0;
		best = Integer.MAX_VALUE;
		worst = 0;
		sos = 0;
		bestmo3 = bestao5 = bestao12 = Integer.MAX_VALUE;
	}

	public String toString(boolean brief) {
		if (data.isEmpty()) {
			return "没有成绩";
		}
		int n = data.size();
		StringBuffer sb = new StringBuffer();
		if (brief) {
			sb.append("最快/最慢成绩：");
			if (best < Integer.MAX_VALUE) {
				sb.append(Data.time2str(best)).append("/")
						.append(Data.time2str(worst));
			} else {
				sb.append("DNF/DNF");
			}
			if (n > 2) {
				sb.append("\n").append("去头尾平均：");
				if (DNFs > 1) {
					sb.append("DNF");
				} else {
					double average;
					if (DNFs == 1) {
						average = ((double) (sum - best)) / (n - 2);
					} else {
						average = ((double) (sum - best - worst)) / (n - 2);
					}
					sb.append(Data.time2str((int) (average + 0.5)));
				}
			}
			if (DNFs != n) {
				int m = n - DNFs;
				sb.append("\n").append("平均/标准差： ");
				double average = ((double) sum / m);
				sb.append(Data.time2str((int) (average + 0.5))).append("/");
				double stddev = Math.sqrt(m * sos - (long) sum * sum) / m;
				sb.append(Data.time2str((int) (stddev + 0.5)));
			}
			if (n >= 3) {
				sb.append("\n").append("最近/最好3次平均： ");
				sb.append(Data.time2str(((Integer) mo3.elementAt(n - 1))));
				sb.append("/").append(Data.time2str(bestmo3));
				if (n >= 5) {
					sb.append("\n").append("最近/最好5次平均： ");
					sb.append(Data.time2str(((Integer) ao5.elementAt(n - 1))));
					sb.append("/").append(Data.time2str(bestao5));
					if (n >= 12) {
						sb.append("\n").append("最近/最好12次平均： ");
						sb.append(Data.time2str(((Integer) ao12
								.elementAt(n - 1))));
						sb.append("/").append(Data.time2str(bestao12));
					}
				}
			}
			for (int i = data.size(); --i >= 0;) {
				Data d = (Data) data.elementAt(i);
				sb.append("\nNo.").append(i + 1).append(":  ");
				sb.append(Data.time2str(d.time));
				if (d.time > d.originaltime) {
					sb.append(" (").append(Data.time2str(d.originaltime))
							.append(")");
				}
			}
		} else {
			for (int i = data.size(); --i >= 0;) {
				Data d = (Data) data.elementAt(i);
				sb.append("No.").append(i + 1).append(":  ");
				sb.append(Data.time2str(d.time));
				if (d.time > d.originaltime) {
					sb.append(" (").append(Data.time2str(d.originaltime))
							.append(")");
				}
				sb.append("\n").append(d.scramble).append("\n\n");
			}
			sb.delete(sb.length() - 2, sb.length());
		}
		return sb.toString();
	}
}
