package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDletStateChangeException;

import main.WeisuoCubeAssistantMain;

public class MainMenu extends List implements CommandListener {

	private final Command exitCommand = new Command("退出", Command.EXIT, 1);
	private final Font listItemFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED, Font.SIZE_LARGE);
	private WeisuoCubeAssistantMain mainMIDlet = null;
	private TimerCanvas timerCanvas = null;
	public MainMenu(String title, WeisuoCubeAssistantMain mainMIDlet) {
		super(title, List.IMPLICIT);
		this.mainMIDlet = mainMIDlet;
		this.addCommand(exitCommand);
		this.setCommandListener(this);
		this.append("秒表", null);
		for (int i = 0; i < this.size(); i++) {
			this.setFont(i, listItemFont);
		}
		// TODO Auto-generated constructor stub
	}

	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if (c == exitCommand) {
			try {
				this.mainMIDlet.destroyApp(true);
			} catch (MIDletStateChangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.mainMIDlet.notifyDestroyed();
		}
		if (c == List.SELECT_COMMAND) {
			if (this.getString(this.getSelectedIndex()).equals("秒表")) {
				//选择“秒表”时的动作
				if (timerCanvas == null) {
					timerCanvas = new TimerCanvas(false, mainMIDlet, this);
				}
				Display.getDisplay(mainMIDlet).setCurrent(timerCanvas);
			}
		}
	}
}
