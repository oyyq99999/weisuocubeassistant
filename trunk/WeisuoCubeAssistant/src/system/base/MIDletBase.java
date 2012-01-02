package system.base;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public abstract class MIDletBase extends MIDlet {
	
	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		stopApp();
	}
	
	private void stopApp() {
		cleanUp();
		notifyDestroyed();
	}
	
	protected abstract void cleanUp();

	protected void pauseApp() {
	}
	
	protected void startApp() throws MIDletStateChangeException {
		runApp();
	}

	private void runApp() {
		prepare();
		go();
	}

	protected void prepare() {
		new Thread() {
			public void run() {
				while (true) {
					System.gc();
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
	}

	protected abstract void go();
}
