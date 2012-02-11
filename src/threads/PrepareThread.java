package threads;

import screens.TimerCanvas;

public class PrepareThread extends Thread {

	private long pressedTime = -1;
	private TimerCanvas caller = null;
	private boolean preparing = true;

	public PrepareThread(long pressedTime, TimerCanvas caller) {
		this.pressedTime = pressedTime;
		this.caller = caller;
	}

	public void run() {
		super.run();
		while (preparing) {
			if (System.currentTimeMillis() - pressedTime >= 550) {
				caller.setState(2);
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isPreparing() {
		return preparing;
	}

	public void setPreparing(boolean preparing) {
		this.preparing = preparing;
	}

	public void interrupt() {
		this.setPreparing(false);
		super.interrupt(); // delete when s40 java.lang.InterruptedException
	}

}
