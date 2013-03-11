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
	private Command plus02Command = new Command("+2", Command.OK, 2);
	private Command plus04Command = new Command("+4", Command.OK, 3);
	private Command plus06Command = new Command("+6", Command.OK, 4);
	private Command plus08Command = new Command("+8", Command.OK, 5);
	private Command plus10Command = new Command("+10", Command.OK, 6);
	private Command plus12Command = new Command("+12", Command.OK, 7);
	private Command plus14Command = new Command("+14", Command.OK, 8);
	private Command plus16Command = new Command("+16", Command.OK, 9);
	private Command DNFCommand = new Command("DNF", Command.OK, 10);
	private TimingThread timingThread = null;
	private PrepareThread prepareThread = null;
	private CustomFont timeFont = null;
	private String scramble = null;

	protected TimerCanvas(boolean suppressKeyEvents, Displayable backTo) {
		super(suppressKeyEvents);
		this.backTo = backTo;
		this.setTitle("计时器");
		this.addCommand(backCommand);
		this.setCommandListener(this);
		timeFont = new CustomFont("/digiface.png");
		this.scramble = "";
	}

	protected TimerCanvas(boolean suppressKeyEvents, Displayable backTo,
			String scramble) {
		this(suppressKeyEvents, backTo);
		this.scramble = scramble;
	}
	
	private void addCommands() {
		this.addCommand(continueCommand);
		this.addCommand(plus02Command);
		this.addCommand(plus04Command);
		this.addCommand(plus06Command);
		this.addCommand(plus08Command);
		this.addCommand(plus10Command);
		this.addCommand(plus12Command);
		this.addCommand(plus14Command);
		this.addCommand(plus16Command);
		this.addCommand(DNFCommand);
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
			if (!backTo.getClass().equals(GlobalData.mainMenu.getClass())) {
				this.addCommands();
			}
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
			if (!timingThread.isRunning()) {
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
			if (!backTo.getClass().equals(GlobalData.mainMenu.getClass())) {
				this.addCommands();
			}
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
			if (!timingThread.isRunning()) {
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
			saveNewData(new Data(time, scramble));
		} else if (c == plus02Command) {
			saveNewData(new Data(time, 2000, scramble));
		} else if (c == plus04Command) {
			saveNewData(new Data(time, 4000, scramble));
		} else if (c == plus06Command) {
			saveNewData(new Data(time, 6000, scramble));
		} else if (c == plus08Command) {
			saveNewData(new Data(time, 8000, scramble));
		} else if (c == plus10Command) {
			saveNewData(new Data(time, 10000, scramble));
		} else if (c == plus12Command) {
			saveNewData(new Data(time, 12000, scramble));
		} else if (c == plus14Command) {
			saveNewData(new Data(time, 14000, scramble));
		} else if (c == plus16Command) {
			saveNewData(new Data(time, 16000, scramble));
		} else if (c == DNFCommand) {
			saveNewData(new Data(time, Integer.MAX_VALUE, scramble));
		}
	}

	private void saveNewData(Data data) {
		GlobalData.stats.push_back(data);
		resetTimer();
		setState(0);
		GlobalData.statsForm = new StatsForm("成绩统计", backTo);
		GlobalData.detailStatsForm = new DetailStatsForm("成绩统计", backTo);
		GlobalData.display.setCurrent(GlobalData.statsForm);
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
