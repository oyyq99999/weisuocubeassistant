package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDletStateChangeException;

import model.GlobalData;
import scramble.ClockScramble;
import scramble.CubeScramble;
import scramble.LatchScramble;
import scramble.MegaminxScramble;
import scramble.OldPyraminxScramble;
import scramble.SQ1Scramble;
import scramble.SQ2Scramble;

public class MainMenu extends List implements CommandListener {

	private final Command exitCommand = new Command("退出", Command.EXIT, 1);
	private final Font listItemFont = Font.getFont(Font.FACE_SYSTEM,
			Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM);

	public MainMenu(String title) {
		super(title, List.IMPLICIT);
		this.addCommand(exitCommand);
		this.setCommandListener(this);
		this.append("二阶", null);
		this.append("三阶", null);
		this.append("四阶", null);
		this.append("五阶", null);
		this.append("六阶", null);
		this.append("七阶", null);
		this.append("九阶", null);
		this.append("十一阶", null);
		this.append("SQ1", null);
		this.append("SQ2", null);
		this.append("五魔", null);
		this.append("金字塔", null);
		this.append("魔表", null);
		this.append("插销(LatchCube)", null);
		this.append("秒表", null);
		this.append("设置", null);
		for (int i = 0; i < this.size(); i++) {
			this.setFont(i, listItemFont);
		}
		// TODO Auto-generated constructor stub
	}

	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if (c == exitCommand) {
			try {
				GlobalData.mainMIDlet.destroyApp(true);
			} catch (MIDletStateChangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GlobalData.mainMIDlet.notifyDestroyed();
		}
		if (c == List.SELECT_COMMAND) {
			if (this.getString(this.getSelectedIndex()).equals("二阶")) {
				// 选择“二阶”时的动作
				if (GlobalData.randomPosition222) {
					if (GlobalData.scrambler222 == null) {
						ConfirmForm confirm = new ConfirmForm(
								"二阶打乱",
								"这是本次运行（或更改设置后）第一次进入随机状态二阶打乱，需要一些准备工作，准备过程可能需要几秒钟的时间，继续吗？",
								this);
						GlobalData.display.setCurrent(confirm);
					} else {
						GlobalData.scrambleForm = new ScrambleForm("二阶打乱",
								GlobalData.scrambler222);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					if (GlobalData.randomMoveScrambler222 == null) {
						GlobalData.randomMoveScrambler222 = new CubeScramble(2);
					}
					GlobalData.scrambleForm = new ScrambleForm("二阶打乱",
							GlobalData.randomMoveScrambler222);
					GlobalData.display.setCurrent(GlobalData.scrambleForm);
				}
			} else if (this.getString(this.getSelectedIndex()).equals("三阶")) {
				// 选择“三阶”时的动作
				if (GlobalData.randomPosition333) {
					if (GlobalData.randomStateScrambler333 == null) {
						ConfirmForm confirm = new ConfirmForm(
								"三阶打乱",
								"这是本次运行（或更改设置后）第一次进入随机状态三阶打乱，需要一些准备工作，准备过程可能需要几十秒的时间，其间需要读写文件(读写文件可能需要确认，各种手机可能不同)，共需内存1.7M，继续吗？",
								this);
						GlobalData.display.setCurrent(confirm);
					} else {
						GlobalData.scrambleForm = new ScrambleForm("三阶打乱",
								GlobalData.randomStateScrambler333);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					if (GlobalData.scrambler333 == null) {
						GlobalData.scrambler333 = new CubeScramble(3);
					}
					GlobalData.scrambleForm = new ScrambleForm("三阶打乱",
							GlobalData.scrambler333);
					GlobalData.display.setCurrent(GlobalData.scrambleForm);
				}
			} else if (this.getString(this.getSelectedIndex()).equals("四阶")) {
				// 选择“四阶”时的动作
				if (GlobalData.scrambler444 == null) {
					GlobalData.scrambler444 = new CubeScramble(4);
				}
				GlobalData.scrambleForm = new ScrambleForm("四阶打乱",
						GlobalData.scrambler444);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("五阶")) {
				// 选择“五阶”时的动作
				if (GlobalData.scrambler555 == null) {
					GlobalData.scrambler555 = new CubeScramble(5);
				}
				GlobalData.scrambleForm = new ScrambleForm("五阶打乱",
						GlobalData.scrambler555);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("六阶")) {
				// 选择“六阶”时的动作
				if (GlobalData.scrambler666 == null) {
					GlobalData.scrambler666 = new CubeScramble(6);
				}
				GlobalData.scrambleForm = new ScrambleForm("六阶打乱",
						GlobalData.scrambler666);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("七阶")) {
				// 选择“七阶”时的动作
				if (GlobalData.scrambler777 == null) {
					GlobalData.scrambler777 = new CubeScramble(7);
				}
				GlobalData.scrambleForm = new ScrambleForm("七阶打乱",
						GlobalData.scrambler777);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("九阶")) {
				// 选择“九阶”时的动作
				if (GlobalData.scrambler999 == null) {
					GlobalData.scrambler999 = new CubeScramble(9);
				}
				GlobalData.scrambleForm = new ScrambleForm("九阶打乱",
						GlobalData.scrambler999);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("十一阶")) {
				// 选择“十一阶”时的动作
				if (GlobalData.scrambler111111 == null) {
					GlobalData.scrambler111111 = new CubeScramble(11);
				}
				GlobalData.scrambleForm = new ScrambleForm("十一阶打乱",
						GlobalData.scrambler111111);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("SQ1")) {
				// 选择“SQ1”时的动作
				if (GlobalData.scramblerSQ1 == null) {
					GlobalData.scramblerSQ1 = new SQ1Scramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("SQ1打乱",
						GlobalData.scramblerSQ1);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("SQ2")) {
				// 选择“SQ2”时的动作
				if (GlobalData.scramblerSQ2 == null) {
					GlobalData.scramblerSQ2 = new SQ2Scramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("SQ2打乱",
						GlobalData.scramblerSQ2);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("五魔")) {
				// 选择“五魔”时的动作
				if (GlobalData.scramblerMegaminx == null) {
					GlobalData.scramblerMegaminx = new MegaminxScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("五魔打乱",
						GlobalData.scramblerMegaminx);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("金字塔")) {
				// 选择“金字塔”时的动作
				if (GlobalData.randomPositionPyraminx) {
					if (GlobalData.scramblerPyraminx == null) {
						ConfirmForm confirm = new ConfirmForm(
								"金字塔打乱",
								"这是本次运行（或更改设置后）第一次进入随机状态金字塔打乱，需要一些准备工作，准备过程可能需要几秒钟的时间，继续吗？",
								this);
						GlobalData.display.setCurrent(confirm);
					} else {
						GlobalData.scrambleForm = new ScrambleForm("金字塔打乱",
								GlobalData.scramblerPyraminx);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					if (GlobalData.randomMoveScramblerPyraminx == null) {
						GlobalData.randomMoveScramblerPyraminx = new OldPyraminxScramble();
					}
					GlobalData.scrambleForm = new ScrambleForm("金字塔打乱",
							GlobalData.randomMoveScramblerPyraminx);
					GlobalData.display.setCurrent(GlobalData.scrambleForm);
				}
			} else if (this.getString(this.getSelectedIndex()).equals("魔表")) {
				// 选择“魔表”时的动作
				if (GlobalData.scramblerClock == null) {
					GlobalData.scramblerClock = new ClockScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("魔表打乱",
						GlobalData.scramblerClock);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"插销(LatchCube)")) {
				// 选择“插销(LatchCube)”时的动作
				if (GlobalData.scramblerLatch == null) {
					GlobalData.scramblerLatch = new LatchScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("插销打乱",
						GlobalData.scramblerLatch);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("秒表")) {
				// 选择“秒表”时的动作
				if (GlobalData.timerCanvas == null) {
					GlobalData.timerCanvas = new TimerCanvas(false, this);
				}
				GlobalData.display.setCurrent(GlobalData.timerCanvas);
			} else if (this.getString(this.getSelectedIndex()).equals("设置")) {
				// 选择“设置”时的动作
				if (GlobalData.settingForm == null) {
					GlobalData.settingForm = new SettingForm(this);
				}
				GlobalData.settingForm.refresh();
				GlobalData.display.setCurrent(GlobalData.settingForm);
			}
		}
	}
}
