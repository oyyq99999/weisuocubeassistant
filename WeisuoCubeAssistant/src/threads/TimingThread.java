package threads;

import screens.TimerCanvas;

public class TimingThread extends Thread {

	private long minutes = 0;
	private long seconds = 0;
	private long hundredths = 0;
	private long startTime = -1;
	private boolean running = false;
	private String time = "0:0.00";
	private TimerCanvas caller = null;

	public TimingThread(TimerCanvas caller, long startTime) {
		this.caller = caller;
		this.startTime = startTime;
	}

	public void run() {
		// TODO Auto-generated method stub
		super.run();
		do {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hundredths = System.currentTimeMillis() - startTime;
			hundredths /= 10;
			if (hundredths >= 100) {
				seconds = hundredths / 100;
				hundredths %= 100;
			}
			if (seconds >= 60) {
				minutes = seconds / 60;
				seconds %= 60;
			}
			time = minutes
					+ ":"
					+ seconds
					+ "."
					+ (hundredths < 10 ? ("0" + Long.toString(hundredths))
							: Long.toString(hundredths));
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
		// TODO Auto-generated method stub
		super.interrupt();
		setRunning(false);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void start() {
		// TODO Auto-generated method stub
		setRunning(true);
		super.start();
	}

}
