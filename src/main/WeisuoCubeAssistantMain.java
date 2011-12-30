package main;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import model.GlobalData;

import screens.MainMenu;

public class WeisuoCubeAssistantMain extends MIDlet {

	public void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		GlobalData.saveData();
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
		GlobalData.loadData();
		GlobalData.mainMIDlet = this;
		if (GlobalData.mainMenu == null)
			GlobalData.mainMenu = new MainMenu("WeisuoCubeAssistant");
		if (GlobalData.display == null) {
			GlobalData.display = Display.getDisplay(this);
		}
		GlobalData.display.setCurrent(GlobalData.mainMenu);
	}

}
