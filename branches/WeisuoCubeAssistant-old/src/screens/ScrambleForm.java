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
	private Command drawCommand = new Command("显示打乱图", Command.SCREEN, 1);
	private Command scrambleCommand = new Command("新打乱", Command.SCREEN, 2);
	private Command statsCommand = new Command("查看成绩", Command.SCREEN, 3);
	private Command backCommand = new Command("返回", Command.BACK, 1);
	private Command continueCommand = new Command("继续", Command.OK, 1);
	private StringItem scrambleStringItem = new StringItem("", "");
	private TimerCanvas timerCanvas = null;
	private ScrambleCanvas scrambleCanvas = null;
	private Font scrambleFont = Font.getFont(Font.FACE_MONOSPACE,
			Font.STYLE_PLAIN, Font.SIZE_SMALL);
	private Scramble scrambler = null;

	public ScrambleForm(String title, Scramble scrambler) {
		super(title);
		this.scrambler = scrambler;
		this.addCommand(drawCommand);
		this.addCommand(continueCommand);
		this.addCommand(scrambleCommand);
		this.addCommand(statsCommand);
		this.addCommand(backCommand);
		this.setCommandListener(this);
		this.scrambleStringItem.setText(scrambler.scramble());
		this.scrambleStringItem.setFont(scrambleFont);
		this.append(scrambleStringItem);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == this.drawCommand) {
			scrambleCanvas = new ScrambleCanvas(false, this,
					scrambler.getName(), scrambler.getSequence());
			GlobalData.display.setCurrent(scrambleCanvas);
		} else if (c == this.scrambleCommand) {
			this.scrambleStringItem.setText("打乱中...");
			this.scrambleStringItem.setText(scrambler.scramble());
		} else if (c == this.statsCommand) {
			GlobalData.statsForm = new StatsForm("成绩", this);
			GlobalData.display.setCurrent(GlobalData.statsForm);
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
