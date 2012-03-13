package threads;

import screens.TimerCanvas;

public class TimingThread extends Thread {

	private long millis = 0;
	private long startTime = -1;
	private boolean running = false;
	private int time = 0;
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
			time = (int) millis;
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

	public int getTime() {
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
