package model;

import javax.microedition.lcdui.Display;
import javax.microedition.rms.RecordStore;

import main.WeisuoCubeAssistantMain;
import scramble.ClockScramble;
import scramble.CubeScramble;
import scramble.LatchScramble;
import scramble.MegaminxScramble;
import scramble.OldPyraminxScramble;
import scramble.PyraminxScramble;
import scramble.SQ1Scramble;
import scramble.SQ2Scramble;
import scramble._222Scramble;
import scramble._333Scramble;
import screens.MainMenu;
import screens.ScrambleForm;
import screens.SettingForm;
import screens.TimerCanvas;

public class GlobalData {
	public static boolean randomPosition222 = false;
	public static boolean randomPosition333 = false;
	public static boolean randomPositionPyraminx = false;
	public static byte randomPosition222MinLength = 0;
	public static byte randomPosition333MaxLength = 21;
	public static byte randomPositionPyraminxMinLength = 0;

	public static WeisuoCubeAssistantMain mainMIDlet = null;
	public static MainMenu mainMenu = null;

	public static TimerCanvas timerCanvas = null;
	public static ScrambleForm scrambleForm = null;
	public static SettingForm settingForm = null;

	public static _222Scramble scrambler222 = null;
	public static CubeScramble randomMoveScrambler222 = null;
	public static CubeScramble scrambler333 = null;
	public static _333Scramble randomStateScrambler333 = null;
	public static CubeScramble scrambler444 = null;
	public static CubeScramble scrambler555 = null;
	public static CubeScramble scrambler666 = null;
	public static CubeScramble scrambler777 = null;
	public static CubeScramble scrambler888 = null;
	public static CubeScramble scrambler999 = null;
	public static CubeScramble scrambler111111 = null;
	public static SQ1Scramble scramblerSQ1 = null;
	public static SQ2Scramble scramblerSQ2 = null;
	public static MegaminxScramble scramblerMegaminx = null;
	public static PyraminxScramble scramblerPyraminx = null;
	public static OldPyraminxScramble randomMoveScramblerPyraminx = null;
	public static ClockScramble scramblerClock = null;
	public static LatchScramble scramblerLatch = null;
	public static Display display = null;

	public static void saveData() {
		byte[] data = { (byte) (randomPosition222 ? 1 : 0),
				(byte) (randomPosition333 ? 1 : 0),
				(byte) (randomPositionPyraminx ? 1 : 0),
				randomPosition222MinLength, randomPosition333MaxLength,
				randomPositionPyraminxMinLength };
		RecordStore rs;
		try {
			rs = RecordStore.openRecordStore("WCASettings", true);
			if (rs.getNumRecords() == 0)
				rs.addRecord(data, 0, data.length);
			rs.setRecord(1, data, 0, data.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadData() {
		RecordStore rs;
		byte[] data = null;
		try {
			rs = RecordStore.openRecordStore("WCASettings", false);
			if (rs.getNumRecords() == 0)
				return;
			data = rs.getRecord(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("RSError");
		}
		if (data == null || data.length < 6)
			return;
		randomPosition222 = (data[0] == 1);
		randomPosition333 = (data[1] == 1);
		randomPositionPyraminx = (data[2] == 1);
		randomPosition222MinLength = data[3];
		randomPosition333MaxLength = data[4];
		randomPositionPyraminxMinLength = data[5];
	}
}
