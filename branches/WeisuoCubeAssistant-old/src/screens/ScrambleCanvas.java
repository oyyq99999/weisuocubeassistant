package screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import draw.*;


import model.GlobalData;

public class ScrambleCanvas extends GameCanvas implements CommandListener {

	private String cubeType;
	private byte[] scrambleSequence;
	private Displayable backTo = null;
	private Command backCommand = new Command("返回", Command.BACK, 1);

	protected ScrambleCanvas(boolean suppressKeyEvents, Displayable backTo,
			String cubeType, byte[] scrambleSequence) {
		super(suppressKeyEvents);
		this.backTo = backTo;
		this.cubeType = cubeType;
		this.scrambleSequence = new byte[scrambleSequence.length];
		for (int i = 0; i < scrambleSequence.length; i++)
			this.scrambleSequence[i] = scrambleSequence[i];
		this.addCommand(backCommand);
		this.setCommandListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(0xffffff);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (cubeType.equals("Cube2")) {
			new DrawCube(2, scrambleSequence).paint(g);
		} else if (cubeType.equals("Cube3")) {
			new DrawCube(3, scrambleSequence).paint(g);
		} else if (cubeType.equals("Cube4")) {
			new DrawCube(4, scrambleSequence).paint(g);
		} else if (cubeType.equals("Cube5")) {
			new DrawCube(5, scrambleSequence).paint(g);
		} else if (cubeType.equals("Cube6")) {
			new DrawCube(6, scrambleSequence).paint(g);
		} else if (cubeType.equals("Cube7")) {
			new DrawCube(7, scrambleSequence).paint(g);
		} else if (cubeType.equals("Cube8")) {
			new DrawCube(8, scrambleSequence).paint(g);
		} else if (cubeType.equals("Cube9")) {
			new DrawCube(9, scrambleSequence).paint(g);
		} else if (cubeType.equals("Cube11")) {
			new DrawCube(11, scrambleSequence).paint(g);
		} else if (cubeType.equals("Floppy")) {
			new DrawFloppy(scrambleSequence).paint(g);
		} else if (cubeType.equals("Domino")) {
			new DrawDomino(scrambleSequence).paint(g);
		} else if (cubeType.equals("334")) {
			new Draw334(scrambleSequence).paint(g);
		} else if (cubeType.equals("Megaminx")) {
			new DrawMegaminx(scrambleSequence).paint(g);
		} else if (cubeType.equals("Pyraminx")) {
			new DrawPyraminx(scrambleSequence).paint(g);
		} else if (cubeType.equals("MasterPyraminx")) {
			new DrawMasterPyraminx(scrambleSequence).paint(g);
		} else if (cubeType.equals("SQ1")) {
			new DrawSQ1(scrambleSequence).paint(g);
		} else if (cubeType.equals("SQ2")) {
			new DrawSQ2(scrambleSequence).paint(g);
		} else if (cubeType.equals("Clock")) {
			new DrawClock(scrambleSequence).paint(g);
		} else if (cubeType.equals("Skewb")) {
			new DrawSkewb(scrambleSequence).paint(g);
		} else if (cubeType.equals("Dino")) {
			new DrawDino(scrambleSequence).paint(g);
		} else if (cubeType.equals("Helicopter")) {
			new DrawHelicopter(scrambleSequence).paint(g);
		}
	}

	public void commandAction(Command c, Displayable d) {
		if (c == backCommand) {
			GlobalData.display.setCurrent(backTo);
		}
	}

}
