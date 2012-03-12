package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import model.GlobalData;
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
	private String time = "0.000";
	private Command backCommand = new Command("返回", Command.BACK, 1);
	private Command continueCommand = new Command("继续", Command.OK, 1);
	private TimingThread timingThread = null;
	private PrepareThread prepareThread = null;
	private CustomFont timeFont = null;

	protected TimerCanvas(boolean suppressKeyEvents, Displayable backTo) {
		super(suppressKeyEvents);
		this.backTo = backTo;
		this.setTitle("计时器");
		this.addCommand(backCommand);
		if (!backTo.getClass().equals(GlobalData.mainMenu.getClass())) {
			this.addCommand(continueCommand);
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
			if (time.equals("0.000")) {
				break;
			}
			int index;
			int ch;
			if ((index = time.indexOf(".")) >= 0) {
				if ((time.charAt(index - 1) == '0' || time.charAt(index - 1) == '5')) {
					GlobalData.display.flashBacklight(0);
				}
			} else if ((ch = time.charAt(time.length() - 1)) == '0'
					|| ch == '5') {
				GlobalData.display.flashBacklight(0);
			}
			break;
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		timeFont.drawString(g, fontColor, time, getWidth() / 2,
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
		time = "0.000";
	}

	public void commandAction(Command c, Displayable d) {
		if (c == backCommand) {
			resetTimer();
			setState(0);
			GlobalData.display.setCurrent(GlobalData.mainMenu);
		}
		if (c == continueCommand) {
			resetTimer();
			setState(0);
			GlobalData.display.setCurrent(backTo);
		}
	}

	public void setTime(String time) {
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
