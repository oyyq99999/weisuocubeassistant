package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import model.GlobalData;
import scramble.PyraminxScramble;
import scramble._222Scramble;
import scramble._333Scramble;

public class ConfirmForm extends Form implements CommandListener {
	private final Command okCommand = new Command("确定", Command.OK, 1);
	private final Command cancelCommand = new Command("取消", Command.CANCEL, 2);
	private Displayable former;

	public ConfirmForm(String title, String info, Displayable former) {
		super(title);
		this.append(info);
		this.addCommand(okCommand);
		this.addCommand(cancelCommand);
		this.setCommandListener(this);
		this.former = former;
		// TODO Auto-generated constructor stub
	}

	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if (c == cancelCommand) {
			GlobalData.display.setCurrent(former);
		}
		if (c == okCommand) {
			if (getTitle().indexOf("二阶") >= 0) {
				this.setTitle("二阶打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scrambler222 = new _222Scramble(
						GlobalData.randomPosition222MinLength, this);
				ScrambleForm scrambleForm = new ScrambleForm("二阶打乱",
						GlobalData.scrambler222);
				GlobalData.display.setCurrent(scrambleForm);
			} else if (getTitle().indexOf("三阶") >= 0) {
				this.setTitle("三阶打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.randomStateScrambler333 = new _333Scramble(
						GlobalData.randomPosition333MaxLength, this);
				ScrambleForm scrambleForm = new ScrambleForm("三阶打乱",
						GlobalData.randomStateScrambler333);
				GlobalData.display.setCurrent(scrambleForm);
			} else if (getTitle().indexOf("金字塔") >= 0) {
				this.setTitle("金字塔打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scramblerPyraminx = new PyraminxScramble(
						GlobalData.randomPositionPyraminxMinLength, this);
				ScrambleForm scrambleForm = new ScrambleForm("金字塔打乱",
						GlobalData.scramblerPyraminx);
				GlobalData.display.setCurrent(scrambleForm);
			}
		}
	}
}
