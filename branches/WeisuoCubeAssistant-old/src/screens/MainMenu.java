package screens;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDletStateChangeException;

import model.GlobalData;
import scramble.*;

public class MainMenu extends List implements CommandListener {

	private final Command settingsCommand = new Command("设置", Command.ITEM, 1);
	private final Command returnCommand = new Command("返回", Command.EXIT, 1);
	private final Command exitCommand = new Command("退出", Command.EXIT, 1);
	private final Font listItemFont = Font.getFont(Font.FACE_SYSTEM,
			Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM);

	private static final String _222Confirm = "这是本次运行第一次进入随机状态二阶打乱，需要一些准备工作，准备过程可能需要几秒钟的时间，继续吗？";
	private static final String _333Confirm = "这是本次运行第一次进入随机状态三阶打乱，需要一些准备工作，准备过程可能需要几十秒的时间，其间需要读写文件(读写文件可能需要确认，各种手机可能不同)，共需内存800K，继续吗？";
	private static final String pyraminxConfirm = "这是本次运行第一次进入随机状态金字塔打乱，需要一些准备工作，准备过程可能需要几秒钟的时间，继续吗？";
	private static final String skewbConfirm = "这是本次运行第一次进入随机状态Skewb打乱，需要一些准备工作，准备过程可能需要几秒钟的时间，继续吗？";

	public MainMenu(String title) {
		super(title, Choice.IMPLICIT);
		this.addCommand(settingsCommand);
		this.addCommand(exitCommand);
		this.setCommandListener(this);
		this.append("WCA项目", null);
		this.append("高阶与异型", null);
		this.append("子集与子群", null);
		this.append("其他打乱", null);
		this.append("计时器", null);
		for (int i = 0; i < this.size(); i++) {
			this.setFont(i, listItemFont);
		}
		GlobalData.stats.reset();
	}

	public void commandAction(Command c, Displayable d) {
		if (c == settingsCommand) {
			if (GlobalData.settingForm == null) {
				GlobalData.settingForm = new SettingForm(this);
			}
			GlobalData.settingForm.refresh();
			GlobalData.display.setCurrent(GlobalData.settingForm);
		} else if (c == returnCommand) {
			this.setTitle("Weisuo Cube Assistant");
			this.removeCommand(returnCommand);
			this.removeCommand(exitCommand);
			this.addCommand(exitCommand);
			this.deleteAll();
			this.append("WCA项目", null);
			this.append("高阶与异型", null);
			this.append("子集与子群", null);
			this.append("其他打乱", null);
			this.append("计时器", null);
			for (int i = 0; i < this.size(); i++) {
				this.setFont(i, listItemFont);
			}
			this.setSelectedIndex(0, true);
		} else if (c == exitCommand) {
			try {
				GlobalData.mainMIDlet.destroyApp(true);
			} catch (MIDletStateChangeException e) {
				e.printStackTrace();
			}
			GlobalData.mainMIDlet.notifyDestroyed();
		} else if (c == List.SELECT_COMMAND) {
			if (this.getString(this.getSelectedIndex()).equals("WCA项目")) {
				this.setTitle("WCA项目");
				this.removeCommand(returnCommand);
				this.removeCommand(exitCommand);
				this.addCommand(returnCommand);
				this.deleteAll();
				this.append("三阶", null);
				this.append("四阶", null);
				this.append("五阶", null);
				this.append("二阶", null);
				this.append("五魔", null);
				this.append("金字塔", null);
				this.append("SQ1", null);
				this.append("魔表", null);
				this.append("六阶", null);
				this.append("七阶", null);
				for (int i = 0; i < this.size(); i++) {
					this.setFont(i, listItemFont);
				}
				this.setSelectedIndex(0, true);
			} else if (this.getString(this.getSelectedIndex()).equals("高阶与异型")) {
				this.setTitle("高阶与异型");
				this.removeCommand(returnCommand);
				this.removeCommand(exitCommand);
				this.addCommand(returnCommand);
				this.deleteAll();
				this.append("八阶", null);
				this.append("九阶", null);
				this.append("十一阶", null);
				this.append("五阶五魔", null);
				this.append("Master Pyraminx", null);
				this.append("Floppy (1x3x3)", null);
				this.append("Domino (2x3x3)", null);
				this.append("3x3x4", null);
				this.append("SQ2", null);
				this.append("Skewb", null);
				this.append("Dino", null);
				this.append("Helicopter", null);
				this.append("Pyraminx Crystal", null);
				this.append("插销 (Latch)", null);
				for (int i = 0; i < this.size(); i++) {
					this.setFont(i, listItemFont);
				}
				this.setSelectedIndex(0, true);
			} else if (this.getString(this.getSelectedIndex()).equals("子集与子群")) {
				this.setTitle("子集与子群");
				this.removeCommand(returnCommand);
				this.removeCommand(exitCommand);
				this.addCommand(returnCommand);
				this.deleteAll();
				this.append("三阶角块", null);
				this.append("三阶棱块", null);
				this.append("三阶顶层", null);
				this.append("三阶最后一组F2L", null);
				this.append("三阶 <R,U>", null);
				this.append("三阶 <R,U,L>", null);
				this.append("三阶 <R,r,U>", null);
				this.append("三阶 <R,U,F>", null);
				this.append("三阶桥式", null);
				this.append("三阶180°", null);
				this.append("四阶 <R,r,U,u>", null);
				this.append("五魔 <R,U>", null);
				this.append("金字塔最后四棱", null);
				this.append("SQ1捆绑", null);
				for (int i = 0; i < this.size(); i++) {
					this.setFont(i, listItemFont);
				}
				this.setSelectedIndex(0, true);
			} else if (this.getString(this.getSelectedIndex()).equals("其他打乱")) {
				this.setTitle("其他打乱");
				this.removeCommand(returnCommand);
				this.removeCommand(exitCommand);
				this.addCommand(returnCommand);
				this.deleteAll();
				this.append("三阶2B", null);
				for (int i = 0; i < this.size(); i++) {
					this.setFont(i, listItemFont);
				}
				this.setSelectedIndex(0, true);
			} else if (this.getString(this.getSelectedIndex()).equals("二阶")) {
				if (GlobalData.randomPosition222) {
					if (GlobalData.scrambler222 == null) {
						ConfirmForm confirm = new ConfirmForm("二阶打乱",
								_222Confirm, this);
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
				if (GlobalData.randomPosition333) {
					if (GlobalData.scrambler333 == null) {
						if (!GlobalData.randomstate333Scrambled) {
							ConfirmForm confirm = new ConfirmForm("三阶打乱",
									_333Confirm, this);
							GlobalData.display.setCurrent(confirm);
						} else {
							GlobalData.scrambler333 = _333Scramble
									.getInstance(
											GlobalData.randomPosition333MaxLength,
											null);
							GlobalData.scrambleForm = new ScrambleForm("三阶打乱",
									GlobalData.scrambler333);
							GlobalData.display
									.setCurrent(GlobalData.scrambleForm);
						}
					} else {
						GlobalData.scrambleForm = new ScrambleForm("三阶打乱",
								GlobalData.scrambler333);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					if (GlobalData.randomMoveScrambler333 == null) {
						GlobalData.randomMoveScrambler333 = new CubeScramble(3);
					}
					GlobalData.scrambleForm = new ScrambleForm("三阶打乱",
							GlobalData.randomMoveScrambler333);
					GlobalData.display.setCurrent(GlobalData.scrambleForm);
				}
			} else if (this.getString(this.getSelectedIndex()).equals("三阶2B")) {
				if (GlobalData.scrambler333Noob == null) {
					GlobalData.scrambler333Noob = new _333NoobScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("三阶2B打乱",
						GlobalData.scrambler333Noob);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("三阶角块")) {
				if (GlobalData.randomPosition333) {
					if (GlobalData.scrambler333Corners == null) {
						if (!GlobalData.randomstate333Scrambled) {
							ConfirmForm confirm = new ConfirmForm("三阶角块打乱",
									_333Confirm, this);
							GlobalData.display.setCurrent(confirm);
						} else {
							GlobalData.scrambler333Corners = new _333SubsetScramble(
									"Corner",
									GlobalData.randomPosition333MaxLength, null);
							GlobalData.scrambleForm = new ScrambleForm(
									"三阶角块打乱", GlobalData.scrambler333Corners);
							GlobalData.display
									.setCurrent(GlobalData.scrambleForm);
						}
					} else {
						GlobalData.scrambleForm = new ScrambleForm("三阶角块打乱",
								GlobalData.scrambler333Corners);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					Alert alert = new Alert("三阶角块打乱", "必须将三阶的打乱方式设置成\"随机状态\"！",
							null, AlertType.ERROR);
					alert.setTimeout(3000);
					GlobalData.display.setCurrent(alert, this);
				}
			} else if (this.getString(this.getSelectedIndex()).equals("三阶棱块")) {
				if (GlobalData.randomPosition333) {
					if (GlobalData.scrambler333Edges == null) {
						if (!GlobalData.randomstate333Scrambled) {
							ConfirmForm confirm = new ConfirmForm("三阶棱块打乱",
									_333Confirm, this);
							GlobalData.display.setCurrent(confirm);
						} else {
							GlobalData.scrambler333Edges = new _333SubsetScramble(
									"Edge",
									GlobalData.randomPosition333MaxLength, null);
							GlobalData.scrambleForm = new ScrambleForm(
									"三阶棱块打乱", GlobalData.scrambler333Edges);
							GlobalData.display
									.setCurrent(GlobalData.scrambleForm);
						}
					} else {
						GlobalData.scrambleForm = new ScrambleForm("三阶棱块打乱",
								GlobalData.scrambler333Edges);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					Alert alert = new Alert("三阶棱块打乱", "必须将三阶的打乱方式设置成\"随机状态\"！",
							null, AlertType.ERROR);
					alert.setTimeout(3000);
					GlobalData.display.setCurrent(alert, this);
				}
			} else if (this.getString(this.getSelectedIndex()).equals("三阶顶层")) {
				if (GlobalData.randomPosition333) {
					if (GlobalData.scrambler333LL == null) {
						if (!GlobalData.randomstate333Scrambled) {
							ConfirmForm confirm = new ConfirmForm("三阶顶层打乱",
									_333Confirm, this);
							GlobalData.display.setCurrent(confirm);
						} else {
							GlobalData.scrambler333LL = new _333SubsetScramble(
									"LL",
									GlobalData.randomPosition333MaxLength, null);
							GlobalData.scrambleForm = new ScrambleForm(
									"三阶顶层打乱", GlobalData.scrambler333LL);
							GlobalData.display
									.setCurrent(GlobalData.scrambleForm);
						}
					} else {
						GlobalData.scrambleForm = new ScrambleForm("三阶顶层打乱",
								GlobalData.scrambler333LL);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					Alert alert = new Alert("三阶顶层打乱", "必须将三阶的打乱方式设置成\"随机状态\"！",
							null, AlertType.ERROR);
					alert.setTimeout(3000);
					GlobalData.display.setCurrent(alert, this);
				}
			} else if (this.getString(this.getSelectedIndex()).equals(
					"三阶最后一组F2L")) {
				if (GlobalData.randomPosition333) {
					if (GlobalData.scrambler333LSLL == null) {
						if (!GlobalData.randomstate333Scrambled) {
							ConfirmForm confirm = new ConfirmForm(
									"三阶最后一组F2L打乱", _333Confirm, this);
							GlobalData.display.setCurrent(confirm);
						} else {
							GlobalData.scrambler333LSLL = new _333SubsetScramble(
									"LSLL",
									GlobalData.randomPosition333MaxLength, null);
							GlobalData.scrambleForm = new ScrambleForm(
									"三阶最后一组F2L打乱", GlobalData.scrambler333LSLL);
							GlobalData.display
									.setCurrent(GlobalData.scrambleForm);
						}
					} else {
						GlobalData.scrambleForm = new ScrambleForm(
								"三阶最后一组F2L打乱", GlobalData.scrambler333LSLL);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					Alert alert = new Alert("三阶最后一组F2L打乱",
							"必须将三阶的打乱方式设置成\"随机状态\"！", null, AlertType.ERROR);
					alert.setTimeout(3000);
					GlobalData.display.setCurrent(alert, this);
				}
			} else if (this.getString(this.getSelectedIndex()).equals("四阶")) {
				if (GlobalData.scrambler444 == null) {
					GlobalData.scrambler444 = new CubeScramble(4);
				}
				GlobalData.scrambleForm = new ScrambleForm("四阶打乱",
						GlobalData.scrambler444);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("五阶")) {
				if (GlobalData.scrambler555 == null) {
					GlobalData.scrambler555 = new CubeScramble(5);
				}
				GlobalData.scrambleForm = new ScrambleForm("五阶打乱",
						GlobalData.scrambler555);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("六阶")) {
				if (GlobalData.scrambler666 == null) {
					GlobalData.scrambler666 = new CubeScramble(6);
				}
				GlobalData.scrambleForm = new ScrambleForm("六阶打乱",
						GlobalData.scrambler666);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("七阶")) {
				if (GlobalData.scrambler777 == null) {
					GlobalData.scrambler777 = new CubeScramble(7);
				}
				GlobalData.scrambleForm = new ScrambleForm("七阶打乱",
						GlobalData.scrambler777);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("八阶")) {
				if (GlobalData.scrambler888 == null) {
					GlobalData.scrambler888 = new CubeScramble(8);
				}
				GlobalData.scrambleForm = new ScrambleForm("八阶打乱",
						GlobalData.scrambler888);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("九阶")) {
				if (GlobalData.scrambler999 == null) {
					GlobalData.scrambler999 = new CubeScramble(9);
				}
				GlobalData.scrambleForm = new ScrambleForm("九阶打乱",
						GlobalData.scrambler999);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("十一阶")) {
				if (GlobalData.scrambler111111 == null) {
					GlobalData.scrambler111111 = new CubeScramble(11);
				}
				GlobalData.scrambleForm = new ScrambleForm("十一阶打乱",
						GlobalData.scrambler111111);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"Floppy (1x3x3)")) {
				if (GlobalData.scramblerFloppy == null) {
					GlobalData.scramblerFloppy = new FloppyScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("Floppy打乱",
						GlobalData.scramblerFloppy);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"Domino (2x3x3)")) {
				if (GlobalData.scramblerDomino == null) {
					GlobalData.scramblerDomino = new DominoScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("Domino打乱",
						GlobalData.scramblerDomino);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("3x3x4")) {
				if (GlobalData.scrambler334 == null) {
					GlobalData.scrambler334 = new _334Scramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("334打乱",
						GlobalData.scrambler334);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"三阶 <R,U>")) {
				if (GlobalData.scrambler333RU == null) {
					GlobalData.scrambler333RU = new _333SubgroupScramble("RU");
				}
				GlobalData.scrambleForm = new ScrambleForm("三阶<R,U>打乱",
						GlobalData.scrambler333RU);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"三阶 <R,U,L>")) {
				if (GlobalData.scrambler333RUL == null) {
					GlobalData.scrambler333RUL = new _333SubgroupScramble("RUL");
				}
				GlobalData.scrambleForm = new ScrambleForm("三阶<R,U,L>打乱",
						GlobalData.scrambler333RUL);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"三阶 <R,r,U>")) {
				if (GlobalData.scrambler333RrU == null) {
					GlobalData.scrambler333RrU = new _333SubgroupScramble("RrU");
				}
				GlobalData.scrambleForm = new ScrambleForm("三阶<R,r,U>打乱",
						GlobalData.scrambler333RrU);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"三阶 <R,U,F>")) {
				if (GlobalData.scrambler333RUF == null) {
					GlobalData.scrambler333RUF = new _333SubgroupScramble("RUF");
				}
				GlobalData.scrambleForm = new ScrambleForm("三阶<R,U,F>打乱",
						GlobalData.scrambler333RUF);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("三阶桥式")) {
				if (GlobalData.scrambler333Roux == null) {
					GlobalData.scrambler333Roux = new _333SubgroupScramble(
							"Roux");
				}
				GlobalData.scrambleForm = new ScrambleForm("三阶桥式打乱",
						GlobalData.scrambler333Roux);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("三阶180°")) {
				if (GlobalData.scrambler333HalfTurn == null) {
					GlobalData.scrambler333HalfTurn = new _333SubgroupScramble(
							"HalfTurn");
				}
				GlobalData.scrambleForm = new ScrambleForm("三阶180°打乱",
						GlobalData.scrambler333HalfTurn);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"四阶 <R,r,U,u>")) {
				if (GlobalData.scrambler444RrUu == null) {
					GlobalData.scrambler444RrUu = new _444RrUuScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("四阶<R,r,U,u>打乱",
						GlobalData.scrambler444RrUu);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("五魔")) {
				if (GlobalData.scramblerMegaminx == null) {
					GlobalData.scramblerMegaminx = new MegaminxScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("五魔打乱",
						GlobalData.scramblerMegaminx);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("五阶五魔")) {
				if (GlobalData.scramblerGigaminx == null) {
					GlobalData.scramblerGigaminx = new GigaminxScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("五阶五魔打乱",
						GlobalData.scramblerGigaminx);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"五魔 <R,U>")) {
				if (GlobalData.scramblerMegaminxRU == null) {
					GlobalData.scramblerMegaminxRU = new MegaminxRUScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("五魔打乱",
						GlobalData.scramblerMegaminxRU);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("金字塔")) {
				if (GlobalData.randomPositionPyraminx) {
					if (GlobalData.scramblerPyraminx == null) {
						if (!GlobalData.randomstatePyraminxScrambled) {
							ConfirmForm confirm = new ConfirmForm("金字塔打乱",
									pyraminxConfirm, this);
							GlobalData.display.setCurrent(confirm);
						} else {
							GlobalData.scramblerPyraminx = PyraminxScramble
									.getInstance(GlobalData.randomPositionPyraminxMinLength);
							GlobalData.scrambleForm = new ScrambleForm("金字塔打乱",
									GlobalData.scramblerPyraminx);
							GlobalData.display
									.setCurrent(GlobalData.scrambleForm);
						}
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
			} else if (this.getString(this.getSelectedIndex()).equals(
					"Master Pyraminx")) {
				if (GlobalData.scramblerMasterPyraminx == null) {
					GlobalData.scramblerMasterPyraminx = new MasterPyraminxScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("Master Pyraminx打乱",
						GlobalData.scramblerMasterPyraminx);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex())
					.equals("金字塔最后四棱")) {
				if (GlobalData.randomPositionPyraminx) {
					if (GlobalData.scramblerPyraminxLast4Edges == null) {
						if (!GlobalData.randomstatePyraminxScrambled) {
							ConfirmForm confirm = new ConfirmForm("金字塔最后四棱打乱",
									pyraminxConfirm, this);
							GlobalData.display.setCurrent(confirm);
						} else {
							GlobalData.scramblerPyraminxLast4Edges = new PyraminxLast4EdgesScramble();
							GlobalData.scrambleForm = new ScrambleForm(
									"金字塔最后四棱打乱",
									GlobalData.scramblerPyraminxLast4Edges);
							GlobalData.display
									.setCurrent(GlobalData.scrambleForm);
						}
					} else {
						GlobalData.scrambleForm = new ScrambleForm("金字塔最后四棱打乱",
								GlobalData.scramblerPyraminxLast4Edges);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					Alert alert = new Alert("金字塔最后四棱打乱",
							"必须将金字塔的打乱方式设置成\"随机状态\"！", null, AlertType.ERROR);
					alert.setTimeout(3000);
					GlobalData.display.setCurrent(alert, this);
				}
			} else if (this.getString(this.getSelectedIndex()).equals("SQ1")) {
				if (GlobalData.scramblerSQ1 == null) {
					GlobalData.scramblerSQ1 = new SQ1Scramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("SQ1打乱",
						GlobalData.scramblerSQ1);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("SQ1捆绑")) {
				if (GlobalData.scramblerSQ1Bandaged == null) {
					GlobalData.scramblerSQ1Bandaged = new SQ1Scramble(true);
				}
				GlobalData.scrambleForm = new ScrambleForm("SQ1捆绑打乱",
						GlobalData.scramblerSQ1Bandaged);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("SQ2")) {
				if (GlobalData.scramblerSQ2 == null) {
					GlobalData.scramblerSQ2 = new SQ2Scramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("SQ2打乱",
						GlobalData.scramblerSQ2);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("魔表")) {
				if (GlobalData.scramblerClock == null) {
					GlobalData.scramblerClock = new ClockScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("魔表打乱",
						GlobalData.scramblerClock);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("Skewb")) {
				if (GlobalData.randomPositionSkewb) {
					if (GlobalData.scramblerSkewb == null) {
						ConfirmForm confirm = new ConfirmForm("Skewb打乱",
								skewbConfirm, this);
						GlobalData.display.setCurrent(confirm);
					} else {
						GlobalData.scrambleForm = new ScrambleForm("Skewb打乱",
								GlobalData.scramblerSkewb);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					if (GlobalData.randomMoveScramblerSkewb == null) {
						GlobalData.randomMoveScramblerSkewb = new OldSkewbScramble();
					}
					GlobalData.scrambleForm = new ScrambleForm("Skewb打乱",
							GlobalData.randomMoveScramblerSkewb);
					GlobalData.display.setCurrent(GlobalData.scrambleForm);
				}
			} else if (this.getString(this.getSelectedIndex()).equals("Dino")) {
				if (GlobalData.scramblerDino == null) {
					GlobalData.scramblerDino = new DinoScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("Dino打乱",
						GlobalData.scramblerDino);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"Helicopter")) {
				if (GlobalData.scramblerHelicopter == null) {
					GlobalData.scramblerHelicopter = new HelicopterScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("Helicopter打乱",
						GlobalData.scramblerHelicopter);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"插销 (Latch)")) {
				if (GlobalData.scramblerLatch == null) {
					GlobalData.scramblerLatch = new LatchScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("插销打乱",
						GlobalData.scramblerLatch);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals(
					"Pyraminx Crystal")) {
				if (GlobalData.scramblerPyraminxCrystal == null) {
					GlobalData.scramblerPyraminxCrystal = new PyraminxCrystalScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm(
						"Pyraminx Crystal打乱",
						GlobalData.scramblerPyraminxCrystal);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			} else if (this.getString(this.getSelectedIndex()).equals("计时器")) {
				if (GlobalData.timerCanvas == null) {
					GlobalData.timerCanvas = new TimerCanvas(false, this);
				}
				GlobalData.display.setCurrent(GlobalData.timerCanvas);
			}
		}
	}
}
