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

	private final Command exitCommand = new Command("退出", Command.EXIT, 1);
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
		this.append("二阶", null);
		this.append("三阶", null);
		this.append("四阶", null);
		this.append("五阶", null);
		this.append("六阶", null);
		this.append("七阶", null);
		this.append("九阶", null);
		this.append("十一阶", null);
		this.append("SQ1", null);
		this.append("五魔", null);
		this.append("金字塔", null);
		this.append("魔表", null);
		this.append("插销(LatchCube)", null);
		this.append("秒表", null);
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
			if (this.getString(this.getSelectedIndex()).equals("秒表")) {
				// 选择“秒表”时的动作
				if (timerCanvas == null) {
					timerCanvas = new TimerCanvas(false, mainMIDlet, this, this);
				}
				Display.getDisplay(mainMIDlet).setCurrent(timerCanvas);
			}
			if (this.getString(this.getSelectedIndex()).equals("插销(LatchCube)")) {
				// 选择“插销(LatchCube)”时的动作
				if (scramblerLatch == null) {
					scramblerLatch = new LatchScramble();
				}
				scrambleForm = new ScrambleForm("插销打乱", scramblerLatch,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("二阶")) {
				// 选择“二阶”时的动作
				if (scrambler222 == null) {
					scrambler222 = new _222Scramble();
				}
				scrambleForm = new ScrambleForm("二阶打乱", scrambler222,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("三阶")) {
				// 选择“三阶”时的动作
				if (scrambler333 == null) {
					scrambler333 = new CubeScramble(3);
				}
				scrambleForm = new ScrambleForm("三阶打乱", scrambler333,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("四阶")) {
				// 选择“四阶”时的动作
				if (scrambler444 == null) {
					scrambler444 = new CubeScramble(4);
				}
				scrambleForm = new ScrambleForm("四阶打乱", scrambler444,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("五阶")) {
				// 选择“五阶”时的动作
				if (scrambler555 == null) {
					scrambler555 = new CubeScramble(5);
				}
				scrambleForm = new ScrambleForm("五阶打乱", scrambler555,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("六阶")) {
				// 选择“六阶”时的动作
				if (scrambler666 == null) {
					scrambler666 = new CubeScramble(6);
				}
				scrambleForm = new ScrambleForm("六阶打乱", scrambler666,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("七阶")) {
				// 选择“七阶”时的动作
				if (scrambler777 == null) {
					scrambler777 = new CubeScramble(7);
				}
				scrambleForm = new ScrambleForm("七阶打乱", scrambler777,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("九阶")) {
				// 选择“九阶”时的动作
				if (scrambler999 == null) {
					scrambler999 = new CubeScramble(9);
				}
				scrambleForm = new ScrambleForm("九阶打乱", scrambler999,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("十一阶")) {
				// 选择“十一阶”时的动作
				if (scrambler111111 == null) {
					scrambler111111 = new CubeScramble(11);
				}
				scrambleForm = new ScrambleForm("十一阶打乱", scrambler111111,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("SQ1")) {
				// 选择“SQ1”时的动作
				if (scramblerSQ1 == null) {
					scramblerSQ1 = new SQ1Scramble();
				}
				scrambleForm = new ScrambleForm("SQ1打乱", scramblerSQ1,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("五魔")) {
				// 选择“五魔”时的动作
				if (scramblerMegaminx == null) {
					scramblerMegaminx = new MegaminxScramble();
				}
				scrambleForm = new ScrambleForm("五魔打乱", scramblerMegaminx,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("金字塔")) {
				// 选择“金字塔”时的动作
				if (scramblerPyraminx == null) {
					scramblerPyraminx = new PyraminxScramble();
				}
				scrambleForm = new ScrambleForm("金字塔打乱", scramblerPyraminx,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
			if (this.getString(this.getSelectedIndex()).equals("魔表")) {
				// 选择“魔表”时的动作
				if (scramblerClock == null) {
					scramblerClock = new ClockScramble();
				}
				scrambleForm = new ScrambleForm("魔表打乱", scramblerClock,
						this.mainMIDlet, this);
				Display.getDisplay(mainMIDlet).setCurrent(scrambleForm);
			}
		}
	}
}
