package threads;

import screens.TimerCanvas;

public class TimingThread extends Thread {

	private long hours = 0;
	private long minutes = 0;
	private long seconds = 0;
	private long millis = 0;
	private long startTime = -1;
	private boolean running = false;
	private String time = "0.000";
	private TimerCanvas caller = null;

	public TimingThread(TimerCanvas caller, long startTime) {
		this.caller = caller;
		this.startTime = startTime;
	}

	public void run() {
		super.run();
		do {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			millis = System.currentTimeMillis() - startTime;
			if (millis < 10 * 60 * 1000) {
				seconds = millis / 1000;
				millis %= 1000;
				if (seconds < 60) {
					time = seconds
							+ "."
							+ (millis < 10 ? ("00" + Long.toString(millis))
									: (millis < 100 ? ("0" + Long
											.toString(millis)) : Long
											.toString(millis)));

				} else {
					minutes = seconds / 60;
					seconds %= 60;
					time = minutes
							+ ":"
							+ (seconds < 10 ? ("0" + Long.toString(seconds))
									: Long.toString(seconds))
							+ "."
							+ (millis < 10 ? ("00" + Long.toString(millis))
									: (millis < 100 ? ("0" + Long
											.toString(millis)) : Long
											.toString(millis)));
				}
			} else {
				millis += 500;
				seconds = millis / 1000;
				minutes = seconds / 60;
				seconds %= 60;
				if (minutes < 60) {
					time = minutes
							+ ":"
							+ (seconds < 10 ? ("0" + Long.toString(seconds))
									: Long.toString(seconds));
				} else {
					hours = minutes / 60;
					minutes %= 60;
					time = hours
							+ ":"
							+ (minutes < 10 ? ("0" + Long.toString(minutes))
									: Long.toString(minutes))
							+ ":"
							+ (seconds < 10 ? ("0" + Long.toString(seconds))
									: Long.toString(seconds));
				}
			}
			caller.setTime(time);
			caller.repaint();
		} while (running);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public String getTime() {
		return time;
	}

	public void interrupt() {
		super.interrupt(); // delete when s40 java.lang.InterruptedException
		setRunning(false);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void start() {
		setRunning(true);
		super.start();
	}

}
