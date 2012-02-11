package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import model.GlobalData;
import scramble.SkewbScramble;
import scramble._222Scramble;
import scramble._333CornerScramble;
import scramble._333EdgeScramble;
import scramble._333LLScramble;
import scramble._333LSLLScramble;
import scramble._333Scramble;
import scramble.PyraminxScramble;
import scramble.PyraminxLast4EdgesScramble;

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
	}

	public void commandAction(Command c, Displayable d) {
		if (c == cancelCommand) {
			GlobalData.display.setCurrent(former);
		}
		if (c == okCommand) {
			if (getTitle().equals("二阶打乱")) {
				this.setTitle("二阶打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scrambler222 = _222Scramble
						.getInstance(GlobalData.randomPosition222MinLength);
				ScrambleForm scrambleForm = new ScrambleForm("二阶打乱",
						GlobalData.scrambler222);
				GlobalData.display.setCurrent(scrambleForm);
			} else if (getTitle().equals("三阶打乱")) {
				this.setTitle("三阶打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scrambler333 = _333Scramble.getInstance(
						GlobalData.randomPosition333MaxLength, this);
				ScrambleForm scrambleForm = new ScrambleForm("三阶打乱",
						GlobalData.scrambler333);
				GlobalData.randomstate333Scrambled = true;
				GlobalData.display.setCurrent(scrambleForm);
			} else if (getTitle().equals("三阶角块打乱")) {
				this.setTitle("三阶打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scrambler333Corners = new _333CornerScramble(
						GlobalData.randomPosition333MaxLength, this);
				ScrambleForm scrambleForm = new ScrambleForm("三阶角块打乱",
						GlobalData.scrambler333Corners);
				GlobalData.randomstate333Scrambled = true;
				GlobalData.display.setCurrent(scrambleForm);
			} else if (getTitle().equals("三阶棱块打乱")) {
				this.setTitle("三阶打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scrambler333Edges = new _333EdgeScramble(
						GlobalData.randomPosition333MaxLength, this);
				ScrambleForm scrambleForm = new ScrambleForm("三阶棱块打乱",
						GlobalData.scrambler333Edges);
				GlobalData.randomstate333Scrambled = true;
				GlobalData.display.setCurrent(scrambleForm);
			} else if (getTitle().equals("三阶顶层打乱")) {
				this.setTitle("三阶打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scrambler333LL = new _333LLScramble(
						GlobalData.randomPosition333MaxLength, this);
				ScrambleForm scrambleForm = new ScrambleForm("三阶顶层打乱",
						GlobalData.scrambler333LL);
				GlobalData.randomstate333Scrambled = true;
				GlobalData.display.setCurrent(scrambleForm);
			} else if (getTitle().equals("三阶最后一组F2L打乱")) {
				this.setTitle("三阶打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scrambler333LSLL = new _333LSLLScramble(
						GlobalData.randomPosition333MaxLength, this);
				ScrambleForm scrambleForm = new ScrambleForm("三阶最后一组F2L打乱",
						GlobalData.scrambler333LSLL);
				GlobalData.randomstate333Scrambled = true;
				GlobalData.display.setCurrent(scrambleForm);
			} else if (getTitle().equals("金字塔打乱")) {
				this.setTitle("金字塔打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scramblerPyraminx = PyraminxScramble
						.getInstance(GlobalData.randomPositionPyraminxMinLength);
				ScrambleForm scrambleForm = new ScrambleForm("金字塔打乱",
						GlobalData.scramblerPyraminx);
				GlobalData.randomstatePyraminxScrambled = true;
				GlobalData.display.setCurrent(scrambleForm);
			} else if (getTitle().equals("金字塔最后四棱打乱")) {
				this.setTitle("金字塔打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scramblerPyraminxLast4Edges = new PyraminxLast4EdgesScramble();
				ScrambleForm scrambleForm = new ScrambleForm("金字塔最后四棱打乱",
						GlobalData.scramblerPyraminxLast4Edges);
				GlobalData.randomstatePyraminxScrambled = true;
				GlobalData.display.setCurrent(scrambleForm);
			} else if (getTitle().equals("Skewb打乱")) {
				this.setTitle("Skewb打乱准备中");
				this.deleteAll();
				this.removeCommand(okCommand);
				this.removeCommand(cancelCommand);
				GlobalData.scramblerSkewb = SkewbScramble
						.getInstance(GlobalData.randomPositionSkewbMinLength);
				ScrambleForm scrambleForm = new ScrambleForm("Skewb打乱",
						GlobalData.scramblerSkewb);
				GlobalData.display.setCurrent(scrambleForm);
			}
		}
	}
}
