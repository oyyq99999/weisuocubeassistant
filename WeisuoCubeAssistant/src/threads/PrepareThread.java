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

<<<<<<< .mine	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (preparing) {
			if (System.currentTimeMillis() - pressedTime >= 500) {
				caller.setState(2);
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
=======	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (preparing) {
			if (System.currentTimeMillis() - pressedTime >= 550) {
				caller.setState(2);
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
>>>>>>> .theirs
	public boolean isPreparing() {
		return preparing;
	}

	public void setPreparing(boolean preparing) {
		this.preparing = preparing;
	}

	public void interrupt() {
		// TODO Auto-generated method stub
		this.setPreparing(false);
		super.interrupt();
	}

}
