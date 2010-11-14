package main;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import screens.MainMenu;

public class WeisuoCubeAssistantMain extends MIDlet {

	private MainMenu mainMenu = null;
	private Display display = null;

	public void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	public void pauseApp() {
		// TODO Auto-generated method stub

	}

	public void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				while (true) {
					System.gc();
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
		mainMenu = new MainMenu("WeisuoCubeAssistant", this);
		if (display == null) {
			display = Display.getDisplay(this);
		}
		display.setCurrent(mainMenu);
	}

}
