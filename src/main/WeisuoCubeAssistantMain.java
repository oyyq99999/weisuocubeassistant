package main;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import model.GlobalData;
import screens.MainMenu;

public class WeisuoCubeAssistantMain extends MIDlet {

	public void destroyApp(boolean arg0) throws MIDletStateChangeException {
		GlobalData.saveData();
	}

	public void pauseApp() {
	}

	public void startApp() throws MIDletStateChangeException {
		new Thread() {
			public void run() {
				while (true) {
					System.gc();
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		GlobalData.loadData();
		GlobalData.mainMIDlet = this;
		if (GlobalData.mainMenu == null) {
			GlobalData.mainMenu = new MainMenu("Weisuo Cube Assistant");
		}
		if (GlobalData.display == null) {
			GlobalData.display = Display.getDisplay(this);
		}
		GlobalData.width = GlobalData.mainMenu.getWidth();
		GlobalData.height = GlobalData.mainMenu.getHeight();
		GlobalData.display.setCurrent(GlobalData.mainMenu);
	}
}
