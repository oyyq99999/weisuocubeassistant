package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import model.GlobalData;

public class DetailStatsForm extends Form implements CommandListener {
	private Command continueCommand = new Command("继续", Command.BACK, 1);
	private Command backCommand = new Command("返回", Command.BACK, 1);
	private Command briefCommand = new Command("简要信息", Command.OK, 1);
	private Command removeCommand = new Command("删除最近一次成绩", Command.SCREEN, 1);
	private Command resetCommand = new Command("删除所有成绩", Command.SCREEN, 2);
	StringItem statStringItem = new StringItem("", "");
	private Font statFont = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN,
			Font.SIZE_SMALL);
	private Displayable backTo = null;

	public DetailStatsForm(String title, Displayable backTo) {
		super(title);
		this.backTo = backTo;
		this.addCommand(removeCommand);
		this.addCommand(resetCommand);
		this.addCommand(briefCommand);
		if (backTo.equals(GlobalData.mainMenu)) {
			this.addCommand(backCommand);
		} else {
			this.addCommand(continueCommand);
		}
		this.setCommandListener(this);
		this.statStringItem.setText(GlobalData.stats.toString(false));
		this.statStringItem.setFont(statFont);
		this.append(statStringItem);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == this.continueCommand || c == this.backCommand) {
			GlobalData.display.setCurrent(backTo);
		} else if (c == this.briefCommand) {
			GlobalData.display.setCurrent(GlobalData.statsForm);
		} else if (c == this.removeCommand) {
			GlobalData.stats.pop_back();
			this.statStringItem.setText(GlobalData.stats.toString(false));
			GlobalData.statsForm.statStringItem.setText(GlobalData.stats
					.toString(true));
		} else if (c == this.resetCommand) {
			GlobalData.stats.reset();
			this.statStringItem.setText("没有成绩");
			GlobalData.statsForm.statStringItem.setText("没有成绩");
		}
	}
}