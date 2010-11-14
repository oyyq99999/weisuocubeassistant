package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import main.WeisuoCubeAssistantMain;

import scramble.Scramble;

public class ScrambleForm extends Form implements CommandListener {
	private WeisuoCubeAssistantMain mainMIDlet = null;
	private MainMenu mainMenu = null;
	private Command scrambleCommand = new Command("新打乱", Command.SCREEN, 1);
	private Command backCommand = new Command("返回", Command.BACK, 1);
	private Command continueCommand = new Command("继续", Command.OK, 1);
	private StringItem scrambleStringItem = new StringItem("", "");
	private TimerCanvas timerCanvas = null;
	private Font scrambleFont = Font.getFont(Font.FACE_MONOSPACE,
			Font.STYLE_PLAIN, Font.SIZE_SMALL);
	private Scramble scrambler = null;

	public ScrambleForm(String title, Scramble scrambler,
			WeisuoCubeAssistantMain mainMIDlet, MainMenu mainMenu) {
		super(title);
		// TODO Auto-generated constructor stub
		this.mainMIDlet = mainMIDlet;
		this.mainMenu = mainMenu;
		this.scrambler = scrambler;
		this.addCommand(continueCommand);
		this.addCommand(scrambleCommand);
		this.addCommand(backCommand);
		this.setCommandListener(this);
		this.scrambleStringItem.setText(scrambler.scramble());
		this.scrambleStringItem.setFont(scrambleFont);
		this.append(scrambleStringItem);
	}

	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if (c == this.scrambleCommand) {
			this.scrambleStringItem.setText("打乱中...");
			this.scrambleStringItem.setText(scrambler.scramble());
		} else if (c == this.backCommand) {
			Display.getDisplay(mainMIDlet).setCurrent(this.mainMenu);
			this.scrambleStringItem.setText(scrambler.scramble());
		} else if (c == this.continueCommand) {
			if (timerCanvas == null) {
				timerCanvas = new TimerCanvas(false, mainMIDlet, this.mainMenu,
						this);
			}
			Display.getDisplay(mainMIDlet).setCurrent(timerCanvas);
			this.scrambleStringItem.setText(scrambler.scramble());
		}
	}

}
