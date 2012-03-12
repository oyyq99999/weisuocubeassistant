package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import model.GlobalData;

public class StatsForm extends Form implements CommandListener {
	private Command backCommand = new Command("返回", Command.BACK, 1);
	private Command resetCommand = new Command("重置", Command.SCREEN, 1);
	private StringItem statStringItem = new StringItem("", "");
	private Font statFont = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN,
			Font.SIZE_SMALL);
	private Displayable backTo = null;

	public StatsForm(String title, Displayable backTo) {
		super(title);
		this.addCommand(backCommand);
		this.addCommand(resetCommand);
		this.setCommandListener(this);
		this.statStringItem.setText(GlobalData.stats.toString());
		this.statStringItem.setFont(statFont);
		this.append(statStringItem);
		this.backTo = backTo;
	}

	public void commandAction(Command c, Displayable d) {
		if (c == this.backCommand) {
			GlobalData.display.setCurrent(backTo);
		} else if (c == this.resetCommand) {
			GlobalData.stats.reset();
			this.statStringItem.setText("没有成绩");
		}
	}

}
