package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDletStateChangeException;

import main.WeisuoCubeAssistantMain;
import scramble.ClockScramble;
import scramble.CubeScramble;
import scramble.LatchScramble;
import scramble.MegaminxScramble;
import scramble.PyraminxScramble;
import scramble.SQ1Scramble;
import scramble._222Scramble;

public class MainMenu extends List implements CommandListener {

	private final Command exitCommand = new Command("�˳�", Command.EXIT, 1);
	private final Font listItemFont = Font.getFont(Font.FACE_SYSTEM,
			Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM);
	private WeisuoCubeAssistantMain mainMIDlet = null;

	private TimerCanvas timerCanvas = null;
	private ScrambleForm scrambleForm = null;

	private _222Scramble scrambler222 = null;
	private CubeScramble scrambler333 = null;
	private CubeScramble scrambler444 = null;
	private CubeScramble scrambler555 = null;
	private CubeScramble scrambler666 = null;
	private CubeScramble scrambler777 = null;
	private CubeScramble scrambler999 = null;
	private CubeScramble scrambler111111 = null;
	private SQ1Scramble scramblerSQ1 = null;
	private MegaminxScramble scramblerMegaminx = null;
	private PyraminxScramble scramblerPyraminx = null;
	private ClockScramble scramblerClock = null;
	private LatchScramble scramblerLatch = null;

	public MainMenu(String title, WeisuoCubeAssistantMain mainMIDlet) {
		super(title, List.IMPLICIT);
		this.mainMIDlet = mainMIDlet;
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
		for (int i = 0; i < this.size(); i++) {
			this.setFont(i, listItemFont);
		}
		// TODO Auto-generated constructor stub
	}

	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if (c == exitCommand) {
			try {
				this.mainMIDlet.destroyApp(true);
			} catch (MIDletStateChangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.mainMIDlet.notifyDestroyed();
		}
		if (c == List.SELECT_COMMAND) {
			if (this.getString(this.getSelectedIndex()).equals("���")) {
				// ѡ�����ʱ�Ķ���
				if (timerCanvas == null) {
					timerCanvas = new TimerCanvas(false, mainMIDlet, this, this);
				}
				Display.getDisplay(mainMIDlet).setCurrent(timerCanvas);
			}
			if (this.getString(this.getSelectedIndex()).equals("����(LatchCube)")) {
				// ѡ�񡰲���(LatchCube)��ʱ�Ķ���
				if (scramblerLatch == null) {
					scramblerLatch = new LatchScramble();
				}
				scrambleForm = new ScrambleForm("��������", scramblerLatch,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("����")) {
				// ѡ�񡰶��ס�ʱ�Ķ���
				if (scrambler222 == null) {
					scrambler222 = new _222Scramble();
				}
				scrambleForm = new ScrambleForm("���״���", scrambler222,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("����")) {
				// ѡ�����ס�ʱ�Ķ���
				if (scrambler333 == null) {
					scrambler333 = new CubeScramble(3);
				}
				scrambleForm = new ScrambleForm("���״���", scrambler333,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("�Ľ�")) {
				// ѡ���Ľס�ʱ�Ķ���
				if (scrambler444 == null) {
					scrambler444 = new CubeScramble(4);
				}
				scrambleForm = new ScrambleForm("�Ľ״���", scrambler444,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("���")) {
				// ѡ����ס�ʱ�Ķ���
				if (scrambler555 == null) {
					scrambler555 = new CubeScramble(5);
				}
				scrambleForm = new ScrambleForm("��״���", scrambler555,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("����")) {
				// ѡ�����ס�ʱ�Ķ���
				if (scrambler666 == null) {
					scrambler666 = new CubeScramble(6);
				}
				scrambleForm = new ScrambleForm("���״���", scrambler666,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("�߽�")) {
				// ѡ���߽ס�ʱ�Ķ���
				if (scrambler777 == null) {
					scrambler777 = new CubeScramble(7);
				}
				scrambleForm = new ScrambleForm("�߽״���", scrambler777,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("�Ž�")) {
				// ѡ�񡰾Žס�ʱ�Ķ���
				if (scrambler999 == null) {
					scrambler999 = new CubeScramble(9);
				}
				scrambleForm = new ScrambleForm("�Ž״���", scrambler999,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("ʮһ��")) {
				// ѡ��ʮһ�ס�ʱ�Ķ���
				if (scrambler111111 == null) {
					scrambler111111 = new CubeScramble(11);
				}
				scrambleForm = new ScrambleForm("ʮһ�״���", scrambler111111,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("SQ1")) {
				// ѡ��SQ1��ʱ�Ķ���
				if (scramblerSQ1 == null) {
					scramblerSQ1 = new SQ1Scramble();
				}
				scrambleForm = new ScrambleForm("SQ1����", scramblerSQ1,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("��ħ")) {
				// ѡ����ħ��ʱ�Ķ���
				if (scramblerMegaminx == null) {
					scramblerMegaminx = new MegaminxScramble();
				}
				scrambleForm = new ScrambleForm("��ħ����", scramblerMegaminx,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("������")) {
				// ѡ�񡰽�������ʱ�Ķ���
				if (scramblerPyraminx == null) {
					scramblerPyraminx = new PyraminxScramble();
				}
				scrambleForm = new ScrambleForm("����������", scramblerPyraminx,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("ħ��")) {
				// ѡ��ħ��ʱ�Ķ���
				if (scramblerClock == null) {
					scramblerClock = new ClockScramble();
				}
				scrambleForm = new ScrambleForm("ħ�����", scramblerClock,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
		}
	}
}
