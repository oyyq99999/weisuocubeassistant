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

public class MainMenu extends List implements CommandListener {

	private final Command exitCommand = new Command("�˳�", Command.EXIT, 1);
	private final Font listItemFont = Font.getFont(Font.FACE_SYSTEM,
			Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM);

	public MainMenu(String title) {
		super(title, List.IMPLICIT);
		this.addCommand(exitCommand);
		this.setCommandListener(this);
		this.append("����", null);
		this.append("����", null);
		this.append("�Ľ�", null);
		this.append("���", null);
		this.append("����", null);
		this.append("�߽�", null);
		this.append("�Ž�", null);
		this.append("ʮһ��", null);
		this.append("SQ1", null);
		this.append("��ħ", null);
		this.append("������", null);
		this.append("ħ��", null);
		this.append("����(LatchCube)", null);
		this.append("���", null);
		this.append("����", null);
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
			if (this.getString(this.getSelectedIndex()).equals("���")) {
				// ѡ�����ʱ�Ķ���
				if (GlobalData.timerCanvas == null) {
					GlobalData.timerCanvas = new TimerCanvas(false, this);
				}
				GlobalData.display.setCurrent(GlobalData.timerCanvas);
			}
			if (this.getString(this.getSelectedIndex()).equals("����(LatchCube)")) {
				// ѡ�񡰲���(LatchCube)��ʱ�Ķ���
				if (GlobalData.scramblerLatch == null) {
					GlobalData.scramblerLatch = new LatchScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("��������",
						GlobalData.scramblerLatch);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("����")) {
				// ѡ�񡰶��ס�ʱ�Ķ���
				if (GlobalData.randomPosition222) {
					if (GlobalData.scrambler222 == null) {
						ConfirmForm confirm = new ConfirmForm(
								"���״���",
								"���Ǳ������У���������ú󣩵�һ�ν������״̬���״��ң���ҪһЩ׼��������׼�����̿�����Ҫ�����ӵ�ʱ�䣬������",
								this);
						GlobalData.display.setCurrent(confirm);
					} else {
						GlobalData.scrambleForm = new ScrambleForm("���״���",
								GlobalData.scrambler222);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					if (GlobalData.randomMoveScrambler222 == null) {
						GlobalData.randomMoveScrambler222 = new CubeScramble(2);
					}
					GlobalData.scrambleForm = new ScrambleForm("���״���",
							GlobalData.randomMoveScrambler222);
					GlobalData.display.setCurrent(GlobalData.scrambleForm);
				}
			}
			if (this.getString(this.getSelectedIndex()).equals("����")) {
				// ѡ�����ס�ʱ�Ķ���
				if (GlobalData.randomPosition333) {
					if (GlobalData.randomStateScrambler333 == null) {
						ConfirmForm confirm = new ConfirmForm(
								"���״���",
								"���Ǳ������У���������ú󣩵�һ�ν������״̬���״��ң���ҪһЩ׼��������׼�����̿�����Ҫ�����ӵ�ʱ�䣬�����Ҫ��д�ļ�4��(ÿ�ζ��ļ�������Ҫһ��ȷ�ϣ�д�ļ�������Ҫ�Ĵ�ȷ�ϣ������ֻ����ܲ�ͬ)�������ڴ�11M��������",
								this);
						GlobalData.display.setCurrent(confirm);
					} else {
						GlobalData.scrambleForm = new ScrambleForm("���״���",
								GlobalData.randomStateScrambler333);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					if (GlobalData.scrambler333 == null) {
						GlobalData.scrambler333 = new CubeScramble(3);
					}
					GlobalData.scrambleForm = new ScrambleForm("���״���",
							GlobalData.scrambler333);
					GlobalData.display.setCurrent(GlobalData.scrambleForm);
				}
			}
			if (this.getString(this.getSelectedIndex()).equals("�Ľ�")) {
				// ѡ���Ľס�ʱ�Ķ���
				if (GlobalData.scrambler444 == null) {
					GlobalData.scrambler444 = new CubeScramble(4);
				}
				GlobalData.scrambleForm = new ScrambleForm("�Ľ״���",
						GlobalData.scrambler444);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("���")) {
				// ѡ����ס�ʱ�Ķ���
				if (GlobalData.scrambler555 == null) {
					GlobalData.scrambler555 = new CubeScramble(5);
				}
				GlobalData.scrambleForm = new ScrambleForm("��״���",
						GlobalData.scrambler555);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("����")) {
				// ѡ�����ס�ʱ�Ķ���
				if (GlobalData.scrambler666 == null) {
					GlobalData.scrambler666 = new CubeScramble(6);
				}
				GlobalData.scrambleForm = new ScrambleForm("���״���",
						GlobalData.scrambler666);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("�߽�")) {
				// ѡ���߽ס�ʱ�Ķ���
				if (GlobalData.scrambler777 == null) {
					GlobalData.scrambler777 = new CubeScramble(7);
				}
				GlobalData.scrambleForm = new ScrambleForm("�߽״���",
						GlobalData.scrambler777);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("�Ž�")) {
				// ѡ�񡰾Žס�ʱ�Ķ���
				if (GlobalData.scrambler999 == null) {
					GlobalData.scrambler999 = new CubeScramble(9);
				}
				GlobalData.scrambleForm = new ScrambleForm("�Ž״���",
						GlobalData.scrambler999);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("ʮһ��")) {
				// ѡ��ʮһ�ס�ʱ�Ķ���
				if (GlobalData.scrambler111111 == null) {
					GlobalData.scrambler111111 = new CubeScramble(11);
				}
				GlobalData.scrambleForm = new ScrambleForm("ʮһ�״���",
						GlobalData.scrambler111111);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("SQ1")) {
				// ѡ��SQ1��ʱ�Ķ���
				if (GlobalData.scramblerSQ1 == null) {
					GlobalData.scramblerSQ1 = new SQ1Scramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("SQ1����",
						GlobalData.scramblerSQ1);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("��ħ")) {
				// ѡ����ħ��ʱ�Ķ���
				if (GlobalData.scramblerMegaminx == null) {
					GlobalData.scramblerMegaminx = new MegaminxScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("��ħ����",
						GlobalData.scramblerMegaminx);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("������")) {
				// ѡ�񡰽�������ʱ�Ķ���
				if (GlobalData.randomPositionPyraminx) {
					if (GlobalData.scramblerPyraminx == null) {
						ConfirmForm confirm = new ConfirmForm(
								"����������",
								"���Ǳ������У���������ú󣩵�һ�ν������״̬���������ң���ҪһЩ׼��������׼�����̿�����Ҫ�����ӵ�ʱ�䣬������",
								this);
						GlobalData.display.setCurrent(confirm);
					} else {
						GlobalData.scrambleForm = new ScrambleForm("����������",
								GlobalData.scramblerPyraminx);
						GlobalData.display.setCurrent(GlobalData.scrambleForm);
					}
				} else {
					if (GlobalData.randomMoveScramblerPyraminx == null) {
						GlobalData.randomMoveScramblerPyraminx = new OldPyraminxScramble();
					}
					GlobalData.scrambleForm = new ScrambleForm("����������",
							GlobalData.randomMoveScramblerPyraminx);
					GlobalData.display.setCurrent(GlobalData.scrambleForm);
				}
			}
			if (this.getString(this.getSelectedIndex()).equals("ħ��")) {
				// ѡ��ħ��ʱ�Ķ���
				if (GlobalData.scramblerClock == null) {
					GlobalData.scramblerClock = new ClockScramble();
				}
				GlobalData.scrambleForm = new ScrambleForm("ħ�����",
						GlobalData.scramblerClock);
				GlobalData.display.setCurrent(GlobalData.scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("����")) {
				// ѡ�����á�ʱ�Ķ���
				if (GlobalData.settingForm == null) {
					GlobalData.settingForm = new SettingForm(this);
				}
				GlobalData.settingForm.refresh();
				GlobalData.display.setCurrent(GlobalData.settingForm);
			}
		}
	}
}
