package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import model.GlobalData;
import statistics.Data;
import threads.PrepareThread;
import threads.TimingThread;
import util.CustomFont;

public class TimerCanvas extends GameCanvas implements CommandListener {

	private Displayable backTo = null;
	private int state = 0;
	private int bgColorReady = 0xffffffff;
	private int bgColorPressed = 0xffff0000;
	private int bgColorOK = 0xff00ff00;
	private int fontColor = 0xff000000;
	private int time = 0;
	private Command backCommand = new Command("返回", Command.BACK, 1);
	private Command continueCommand = new Command("无惩罚", Command.OK, 1);
	private Command plus2Command = new Command("+2", Command.OK, 2);
	private Command plus4Command = new Command("+4", Command.OK, 3);
	private Command plus6Command = new Command("+6", Command.OK, 4);
	private Command DNFCommand = new Command("DNF", Command.OK, 5);
	private TimingThread timingThread = null;
	private PrepareThread prepareThread = null;
	private CustomFont timeFont = null;
	private StatsForm statsForm = null;

	protected TimerCanvas(boolean suppressKeyEvents, Displayable backTo) {
		super(suppressKeyEvents);
		this.backTo = backTo;
		this.setTitle("计时器");
		this.addCommand(backCommand);
		if (!backTo.getClass().equals(GlobalData.mainMenu.getClass())) {
			this.addCommand(continueCommand);
			this.addCommand(plus2Command);
			this.addCommand(plus4Command);
			this.addCommand(plus6Command);
			this.addCommand(DNFCommand);
		}
		this.setCommandListener(this);
		timeFont = new CustomFont("/digiface.png");
	}

	protected void keyPressed(int keyCode) {
		super.keyPressed(keyCode);
		if (state == 0) {
			setState(1);
			if (prepareThread == null) {
				prepareThread = new PrepareThread(System.currentTimeMillis(),
						this);
				prepareThread.start();
			}
		} else if (state == 2) {
			timingThread.interrupt();
			timingThread = null;
			setState(0);
		}
	}

	protected void keyReleased(int keyCode) {
		long startTime = System.currentTimeMillis();
		super.keyReleased(keyCode);
		if (prepareThread != null) {
			prepareThread.interrupt();
			prepareThread = null;
		}
		if (state == 2) {
			if (timingThread == null) {
				timingThread = new TimingThread(this, startTime);
			}
			if (timingThread.isRunning() == false) {
				timingThread.start();
			}
		} else {
			setState(0);
		}
	}

	protected void pointerPressed(int x, int y) {
		super.pointerPressed(x, y);
		if (state == 0) {
			setState(1);
			if (prepareThread == null) {
				prepareThread = new PrepareThread(System.currentTimeMillis(),
						this);
				prepareThread.start();
			}
		} else if (state == 2) {
			timingThread.interrupt();
			timingThread = null;
			setState(0);
		}
	}

	protected void pointerReleased(int x, int y) {
		long startTime = System.currentTimeMillis();
		super.pointerReleased(x, y);
		if (prepareThread != null) {
			prepareThread.interrupt();
			prepareThread = null;
		}
		if (state == 2) {
			if (timingThread == null) {
				timingThread = new TimingThread(this, startTime);
			}
			if (timingThread.isRunning() == false) {
				timingThread.start();
			}
		} else {
			setState(0);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		switch (state) {
		case 0:
			g.setColor(bgColorReady);
			break;
		case 1:
			g.setColor(bgColorPressed);
			break;
		case 2:
			g.setColor(bgColorOK);
			if (time == 0) {
				break;
			}
			int second = time < 10 * 60 * 1000 ? ((time / 1000) % 10)
					: time % 10;
			if (second == 0 || second == 5) {
				GlobalData.display.flashBacklight(0);
			}
			break;
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		timeFont.drawString(g, fontColor, Data.time2str(time), getWidth() / 2,
				getHeight() / 2, Graphics.HCENTER | Graphics.VCENTER);
	}

	public void resetTimer() {
		if (timingThread != null) {
			timingThread.interrupt();
			timingThread = null;
		}
		if (prepareThread != null) {
			prepareThread.interrupt();
			prepareThread = null;
		}
		time = 0;
	}

	public void commandAction(Command c, Displayable d) {
		if (c == backCommand) {
			resetTimer();
			setState(0);
			GlobalData.display.setCurrent(GlobalData.mainMenu);
		} else if (c == continueCommand) {
			GlobalData.stats.push_back(new Data(time, "Scramble"));
			resetTimer();
			setState(0);
			statsForm = new StatsForm("成绩", backTo);
			GlobalData.display.setCurrent(statsForm);
		} else if (c == plus2Command) {
			GlobalData.stats.push_back(new Data(time, 2000, "Scramble"));
			resetTimer();
			setState(0);
			statsForm = new StatsForm("成绩", backTo);
			GlobalData.display.setCurrent(statsForm);
		} else if (c == plus4Command) {
			GlobalData.stats.push_back(new Data(time, 4000, "Scramble"));
			resetTimer();
			setState(0);
			statsForm = new StatsForm("成绩", backTo);
			GlobalData.display.setCurrent(statsForm);
		} else if (c == plus6Command) {
			GlobalData.stats.push_back(new Data(time, 6000, "Scramble"));
			resetTimer();
			setState(0);
			statsForm = new StatsForm("成绩", backTo);
			GlobalData.display.setCurrent(statsForm);
		} else if (c == DNFCommand) {
			GlobalData.stats.push_back(new Data(time, Integer.MAX_VALUE,
					"Scramble"));
			resetTimer();
			setState(0);
			statsForm = new StatsForm("成绩", backTo);
			GlobalData.display.setCurrent(statsForm);
		}
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		if (state == 2) {
			resetTimer();
		}
		repaint();
	}

}
