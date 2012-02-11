package scramble;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Form;

import util.StringTokenizer;

import cs.min2phase.Search;
import cs.min2phase.Tools;

public class _333Scramble extends Scramble {

	private static _333Scramble instance = null;

	protected _333Scramble() {
		this((byte) 21);
	}

	protected _333Scramble(byte maxLength) {
		// 最大长度
		this.length = maxLength;
		init();
	}

	protected _333Scramble(byte maxLength, Form prepare) {
		this(maxLength);
	}

	public static _333Scramble getInstance(byte length, Form prepare) {
		if (instance == null) {
			instance = new _333Scramble(length);
		}
		instance.length = length;
		return instance;
	}

	protected void init() {
		if (!Tools.inited) {
			// System.out.println("Initialization");
			try {
				FileConnection file;
				file = (FileConnection) Connector.open(
						"file:///e:/WCA/twophase.data", Connector.READ);
				DataInputStream dis;
				if (file.exists()
						&& Tools.initFrom(dis = file.openDataInputStream())) {
					dis.close();
				} else {
					file = (FileConnection) Connector.open("file:///e:/WCA/");
					if (!file.exists()) {
						file.mkdir();
					}
					file = (FileConnection) Connector
							.open("file:///e:/WCA/twophase.data");
					if (file.exists())
						file.delete();
					file.create();
					DataOutputStream dos;
					Tools.initTo(dos = file.openDataOutputStream());
					dos.flush();
					dos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String scramble() {
		scrambleSequence = new Search().solution(Tools.randomCube(), length,
				10000, false, true);
		str2seq();
		return scrambleSequence;
	}

	protected void str2seq() {
		int move = 0;
		StringTokenizer scr = new StringTokenizer(scrambleSequence, " ");
		sequence = new byte[scr.countTokens()];
		while (scr.hasMoreTokens()) {
			String str = scr.nextToken();
			switch (str.charAt(0)) {
			case 'U':
				sequence[move] = 0;
				break;
			case 'R':
				sequence[move] = 3;
				break;
			case 'F':
				sequence[move] = 6;
				break;
			case 'D':
				sequence[move] = 9;
				break;
			case 'L':
				sequence[move] = 12;
				break;
			case 'B':
				sequence[move] = 15;
				break;
			}
			if (str.length() == 2) {
				if (str.charAt(1) == '2') {
					sequence[move]++;
				} else {
					sequence[move] += 2;
				}
			}
			move++;
		}
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Cube3";
	}

}
