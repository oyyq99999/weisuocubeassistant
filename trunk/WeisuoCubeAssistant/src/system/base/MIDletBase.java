package system.base;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * @author ouyangyunqi
 *
 */
public abstract class MIDletBase extends MIDlet {
	
	/* (non-Javadoc)
	 * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	 */
	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		stopApp();
	}
	
	/**
	 * template method overwrites destroyApp()
	 * do cleanUp first, then exit
	 */
	private void stopApp() {
		cleanUp();
		notifyDestroyed();
	}
	
	/**
	 * do something before exit
	 */
	protected abstract void cleanUp();

	/* (non-Javadoc)
	 * @see javax.microedition.midlet.MIDlet#pauseApp()
	 */
	protected void pauseApp() {
	}
	
	/* (non-Javadoc)
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 */
	protected void startApp() throws MIDletStateChangeException {
		runApp();
	}

	/**
	 * template method overwrites startApp()
	 * do something prepared for the app, then run.
	 */
	private void runApp() {
		prepare();
		go();
	}

	/**
	 * done before app starts, default starts a gc thread
	 */
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

	/**
	 *  do something to start the app!
	 */
	protected abstract void go();
}
