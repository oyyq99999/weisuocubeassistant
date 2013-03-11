package model;

import javax.microedition.lcdui.Display;
import javax.microedition.rms.RecordStore;

import main.WeisuoCubeAssistantMain;
import scramble.*;
import screens.*;
import statistics.Statistics;

public class GlobalData {

	public static int width;
	public static int height;

	public static boolean randomPosition222 = false;
	public static boolean randomPosition333 = false;
	public static boolean randomPositionPyraminx = false;
	public static boolean randomPositionSkewb = false;
	public static byte randomPosition222MinLength = 0;
	public static byte randomPosition333MaxLength = 21;
	public static byte randomPositionPyraminxMinLength = 0;
	public static byte randomPositionSkewbMinLength = 0;

	public static WeisuoCubeAssistantMain mainMIDlet = null;
	public static MainMenu mainMenu = null;

	public static TimerCanvas timerCanvas = null;
	public static ScrambleForm scrambleForm = null;
	public static SettingForm settingForm = null;
	public static StatsForm statsForm = null;
	public static DetailStatsForm detailStatsForm = null;
	public static Statistics stats = new Statistics();

	public static boolean randomstate333Scrambled = false;
	public static boolean randomstatePyraminxScrambled = false;

	public static _222Scramble scrambler222 = null;
	public static CubeScramble randomMoveScrambler222 = null;
	public static CubeScramble randomMoveScrambler333 = null;
	public static _333Scramble scrambler333 = null;
	public static CubeScramble scrambler444 = null;
	public static CubeScramble scrambler555 = null;
	public static CubeScramble scrambler666 = null;
	public static CubeScramble scrambler777 = null;
	public static CubeScramble scrambler888 = null;
	public static CubeScramble scrambler999 = null;
	public static CubeScramble scrambler111111 = null;
	public static _333NoobScramble scrambler333Noob = null;
	public static _333SubsetScramble scrambler333Corners = null;
	public static _333SubsetScramble scrambler333Edges = null;
	public static _333SubsetScramble scrambler333LL = null;
	public static _333SubsetScramble scrambler333LSLL = null;
	public static _333SubgroupScramble scrambler333RU = null;
	public static _333SubgroupScramble scrambler333RUF = null;
	public static _333SubgroupScramble scrambler333RUL = null;
	public static _333SubgroupScramble scrambler333RrU = null;
	public static _333SubgroupScramble scrambler333Roux = null;
	public static _333SubgroupScramble scrambler333HalfTurn = null;
	public static _444RrUuScramble scrambler444RrUu = null;
	public static FloppyScramble scramblerFloppy = null;
	public static DominoScramble scramblerDomino = null;
	public static _334Scramble scrambler334 = null;
	public static SQ1Scramble scramblerSQ1 = null;
	public static SQ1Scramble scramblerSQ1Bandaged = null;
	public static SQ2Scramble scramblerSQ2 = null;
	public static MegaminxScramble scramblerMegaminx = null;
	public static GigaminxScramble scramblerGigaminx = null;
	public static MegaminxRUScramble scramblerMegaminxRU = null;
	public static PyraminxCrystalScramble scramblerPyraminxCrystal = null;
	public static PyraminxScramble scramblerPyraminx = null;
	public static MasterPyraminxScramble scramblerMasterPyraminx = null;
	public static PyraminxLast4EdgesScramble scramblerPyraminxLast4Edges = null;
	public static OldPyraminxScramble randomMoveScramblerPyraminx = null;
	public static ClockScramble scramblerClock = null;
	public static SkewbScramble scramblerSkewb = null;
	public static OldSkewbScramble randomMoveScramblerSkewb = null;
	public static DinoScramble scramblerDino = null;
	public static HelicopterScramble scramblerHelicopter = null;
	public static LatchScramble scramblerLatch = null;
	public static Display display = null;

	public static void saveData() {
		byte[] data = { (byte) (randomPosition222 ? 1 : 0),
				(byte) (randomPosition333 ? 1 : 0),
				(byte) (randomPositionPyraminx ? 1 : 0),
				(byte) (randomPositionSkewb ? 1 : 0),
				randomPosition222MinLength, randomPosition333MaxLength,
				randomPositionPyraminxMinLength, randomPositionSkewbMinLength };
		RecordStore rs;
		try {
			rs = RecordStore.openRecordStore("WCASettings", true);
			if (rs.getNumRecords() == 0)
				rs.addRecord(data, 0, data.length);
			rs.setRecord(1, data, 0, data.length);
		} catch (Exception e) {
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
			e.printStackTrace();
			System.out.println("RSError");
		}
		if (data == null || data.length < 8)
			return;
		randomPosition222 = (data[0] == 1);
		randomPosition333 = (data[1] == 1);
		randomPositionPyraminx = (data[2] == 1);
		randomPositionSkewb = (data[3] == 1);
		randomPosition222MinLength = data[4];
		randomPosition333MaxLength = data[5];
		randomPositionPyraminxMinLength = data[6];
		randomPositionSkewbMinLength = data[7];
	}
}
