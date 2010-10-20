package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import threads.PrepareThread;
import threads.TimingThread;

import main.WeisuoCubeAssistantMain;

public class TimerCanvas extends GameCanvas implements CommandListener {

	private WeisuoCubeAssistantMain mainMIDlet = null;
	private MainMenu mainMenu = null;
	private int state = 0;
	private int fontColor = 0x0;
	private int bgColorReady = 0x7f00ff;
	private int bgColorPressed = 0xff0000;
	private int bgColorOK = 0x00ff00;
	private String time = "0:0.00";
	private Font timeFont = Font.getFont(Font.FONT_STATIC_TEXT,
			Font.STYLE_BOLD, Font.SIZE_LARGE);
	private Command backCommand = new Command("·µ»Ø", Command.BACK, 1);
	private TimingThread timingThread = null;
	private PrepareThread prepareThread = null;

	protected TimerCanvas(boolean suppressKeyEvents,
			WeisuoCubeAssistantMain mainMIDlet, MainMenu mainMenu) {
		super(suppressKeyEvents);
		this.mainMIDlet = mainMIDlet;
		this.mainMenu = mainMenu;
		this.addCommand(backCommand);
		this.setCommandListener(this);
		// TODO Auto-generated constructor stub
	}

	protected void keyPressed(int keyCode) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		super.keyReleased(keyCode);
		if (prepareThread != null) {
			prepareThread.interrupt();
			prepareThread = null;
		}
		if (state == 2) {
			if (timingThread == null) {
				timingThread = new TimingThread(this);
			}
			if (timingThread.isRunning() == false) {
				timingThread.start();
			}
		} else {
			setState(0);
		}
	}
	

	protected void pointerPressed(int x, int y) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		super.pointerReleased(x, y);
		if (prepareThread != null) {
			prepareThread.interrupt();
			prepareThread = null;
		}
		if (state == 2) {
			if (timingThread == null) {
				timingThread = new TimingThread(this);
			}
			if (timingThread.isRunning() == false) {
				timingThread.start();
			}
		} else {
			setState(0);
		}
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
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
			break;
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(fontColor);
		g.setFont(timeFont);
		g.drawString(time, getWidth() / 2, getHeight() / 2, Graphics.HCENTER
				| Graphics.BASELINE);
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
		time = "0:0.00";
	}

	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if (c == backCommand) {
			resetTimer();
			setState(0);
			Display.getDisplay(mainMIDlet).setCurrent(mainMenu);
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
