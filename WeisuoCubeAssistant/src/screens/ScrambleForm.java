package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import model.GlobalData;
import scramble.Scramble;

public class ScrambleForm extends Form implements CommandListener {
	private Command scrambleCommand = new Command("�´���", Command.SCREEN, 1);
	private Command backCommand = new Command("����", Command.BACK, 1);
	private Command continueCommand = new Command("����", Command.OK, 1);
	private StringItem scrambleStringItem = new StringItem("", "");
	private TimerCanvas timerCanvas = null;
	private Font scrambleFont = Font.getFont(Font.FACE_MONOSPACE,
			Font.STYLE_PLAIN, Font.SIZE_SMALL);
	private Scramble scrambler = null;

	public ScrambleForm(String title, Scramble scrambler) {
		super(title);
		// TODO Auto-generated constructor stub
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
			this.scrambleStringItem.setText("������...");
			this.scrambleStringItem.setText(scrambler.scramble());
		} else if (c == this.backCommand) {
			GlobalData.display.setCurrent(GlobalData.mainMenu);
			this.scrambleStringItem.setText(scrambler.scramble());
		} else if (c == this.continueCommand) {
			if (timerCanvas == null) {
				timerCanvas = new TimerCanvas(false, this);
			}
			GlobalData.display.setCurrent(timerCanvas);
			this.scrambleStringItem.setText(scrambler.scramble());
		}
	}

}
