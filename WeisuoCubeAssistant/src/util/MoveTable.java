package util;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Form;

import model.RubiksCubicCube;

public class MoveTable {

	// Phase 1 move tables
	private short[] chooseMoveTable = null;
	private short[] flipMoveTable = null;
	private short[] twistMoveTable = null;

	// Phase 2 move tables
	private short[] udEdgeMoveTable = null;
	private byte[] eSliceEdgeMoveTable = null;
	private short[] cornerMoveTable = null;

	private boolean tablesGenerated = false;
	private static MoveTable moveTable = null;

	private MoveTable() {
		generateMoveTables(null);
	}

	private MoveTable(Form prepare) {
		generateMoveTables(prepare);
	}

	public static MoveTable getInstance() {
		if (moveTable == null)
			moveTable = new MoveTable();
		return moveTable;
	}

	public static MoveTable getInstance(Form prepare) {
		if (moveTable == null)
			moveTable = new MoveTable(prepare);
		return moveTable;
	}

	private void generateMoveTables(Form prepare) {
		// TODO Auto-generated method stub
		if (tablesGenerated)
			return;

		// Phase 1
		chooseMoveTable = new short[495 * 6 * 3];
		flipMoveTable = new short[2048 * 6 * 3];
		twistMoveTable = new short[2187 * 6 * 3];
		if (loadPhase1MoveTables("Phase1MoveTables.movt", prepare) == false) {
			generateChooseMoveTable(prepare);
			generateFlipMoveTable(prepare);
			generateTwistMoveTable(prepare);
			savePhase1MoveTables("Phase1MoveTables.movt", prepare);
		}

		// Phase 2
		udEdgeMoveTable = new short[40320 * 6 * 3];
		eSliceEdgeMoveTable = new byte[24 * 6 * 3];
		cornerMoveTable = new short[40320 * 6 * 3];
		if (loadPhase2MoveTables("Phase2MoveTables.movt", prepare) == false) {
			generateUdEdgeMoveTable(prepare);
			generateESliceEdgeMoveTable(prepare);
			generateCornerMoveTable(prepare);
			savePhase2MoveTables("Phase2MoveTables.movt", prepare);
		}
		tablesGenerated = true;
	}

	private void savePhase2MoveTables(String fileName, Form prepare) {
		// TODO Auto-generated method stub
		FileConnection file;
		DataOutputStream dos;
		try {
			file = (FileConnection) Connector.open("file:///e:/WCA/");
			if (!file.exists()) {
				file.mkdir();
			}
			file = (FileConnection) Connector
					.open("file:///e:/WCA/" + fileName);
			if (file.exists())
				file.delete();
			file.create();
			dos = file.openDataOutputStream();
			// UdEdgeMoveTable
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Saving UdEdge Move Table...");
			}
			for (int i = 0; i < 40320 * 6 * 3; i++) {
				dos.writeShort(udEdgeMoveTable[i]);
			}
			// ESliceMoveTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Saving ESliceEdge Move Table...");
			}
			for (int i = 0; i < 24 * 6 * 3; i++) {
				dos.writeByte(eSliceEdgeMoveTable[i]);
			}
			// CornerMoveTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Saving Corner Move Table...");
			}
			for (int i = 0; i < 40320 * 6 * 3; i++) {
				dos.writeShort(cornerMoveTable[i]);
			}
			if (prepare != null) {
				prepare.append("Done\n");
			}
			dos.flush();
			dos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	private boolean loadPhase2MoveTables(String fileName, Form prepare) {
		// TODO Auto-generated method stub
		FileConnection file;
		DataInputStream dis;
		try {
			file = (FileConnection) Connector.open(
					"file:///e:/WCA/" + fileName, Connector.READ);
			if (!file.exists()) {
				return false;
			}
			dis = file.openDataInputStream();
			// UdEdgeMoveTable
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Loading UdEdge Move Table...");
			}
			for (int i = 0; i < 40320 * 6 * 3; i++) {
				if ((udEdgeMoveTable[i] = dis.readShort()) == -1) {
					return false;
				}
			}
			// ESliceEdgeMoveTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Loading ESliceEdge Move Table...");
			}
			for (int i = 0; i < 24 * 6 * 3; i++) {
				if ((eSliceEdgeMoveTable[i] = dis.readByte()) == -1) {
					return false;
				}
			}
			// CornerMoveTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Loading Corner Move Table...");
			}
			for (int i = 0; i < 40320 * 6 * 3; i++) {
				if ((cornerMoveTable[i] = dis.readShort()) == -1) {
					return false;
				}
			}
			if (prepare != null) {
				prepare.append("Done\n");
			}
			dis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void savePhase1MoveTables(String fileName, Form prepare) {
		// TODO Auto-generated method stub
		FileConnection file;
		DataOutputStream dos;
		try {
			file = (FileConnection) Connector.open("file:///e:/WCA/");
			if (!file.exists()) {
				file.mkdir();
			}
			file = (FileConnection) Connector
					.open("file:///e:/WCA/" + fileName);
			if (file.exists())
				file.delete();
			file.create();
			dos = file.openDataOutputStream();
			// ChooseMoveTable
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Saving Choose Move Table...");
			}
			for (int i = 0; i < 495 * 6 * 3; i++) {
				dos.writeShort(chooseMoveTable[i]);
			}
			// FlipMoveTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Saving Flip Move Table...");
			}
			for (int i = 0; i < 2048 * 6 * 3; i++) {
				dos.writeShort(flipMoveTable[i]);
			}
			// TwistMoveTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Saving Twist Move Table...");
			}
			for (int i = 0; i < 2187 * 6 * 3; i++) {
				dos.writeShort(twistMoveTable[i]);
			}
			if (prepare != null) {
				prepare.append("Done\n");
			}
			dos.flush();
			dos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	private boolean loadPhase1MoveTables(String fileName, Form prepare) {
		// TODO Auto-generated method stub
		FileConnection file;
		DataInputStream dis;
		try {
			file = (FileConnection) Connector.open(
					"file:///e:/WCA/" + fileName, Connector.READ);
			if (!file.exists()) {
				return false;
			}
			dis = file.openDataInputStream();
			// ChooseMoveTable
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Loading Choose Move Table...");
			}
			for (int i = 0; i < 495 * 6 * 3; i++) {
				if ((chooseMoveTable[i] = dis.readShort()) == -1) {
					return false;
				}
			}
			// FlipMoveTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Loading Flip Move Table...");
			}
			for (int i = 0; i < 2048 * 6 * 3; i++) {
				if ((flipMoveTable[i] = dis.readShort()) == -1) {
					return false;
				}
			}
			// TwistMoveTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Loading Twist Move Table...");
			}
			for (int i = 0; i < 2187 * 6 * 3; i++) {
				if ((twistMoveTable[i] = dis.readShort()) == -1) {
					return false;
				}
			}
			if (prepare != null) {
				prepare.append("Done\n");
			}
			dis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void generateCornerMoveTable(Form prepare) {
		// TODO Auto-generated method stub
		if (prepare != null) {
			prepare.deleteAll();
			prepare.append("Initializing Corner Move Table...\n");
		}
		if (cornerMoveTable == null)
			cornerMoveTable = new short[40320 * 6 * 3];
		RubiksCubicCube cube = new RubiksCubicCube();
		int count = 0;
		for (int cornerPermCode = 0; cornerPermCode < 40320; cornerPermCode++) {
			byte[] cornerPermState = cube
					.getCornerPermutationState(cornerPermCode);
			for (byte axis = 0; axis < 6; axis++) {
				for (byte turns = 1; turns <= 3; turns++) {
					if (axis != 0 && axis != 1 && turns != 2)
						continue;
					for (byte i = RubiksCubicCube.FIRST_CORNER; i <= RubiksCubicCube.LAST_CORNER; i++) {
						cube.cornerPermutations[i] = cornerPermState[i];
					}
					cube.doMove(axis, turns);
					cornerMoveTable[cornerPermCode * 6 * 3 + axis * 3 + turns
							- 1] = intToShort(cube
							.getCornerPermutationCode(cube.cornerPermutations));
					count++;
				}
			}
			// if (prepare != null && cornerPermCode % 10 == 0) {
			// prepare.deleteAll();
			// prepare.append("Initializing Corner Move Table...\n" + count
			// + "/403200\n");
			// }
		}
	}

	private short intToShort(int intNumber) {
		// TODO Auto-generated method stub
		if (intNumber > 32767)
			return (short) (intNumber - 65536);
		return (short) intNumber;
	}

	private int shortToInt(short shortNumber) {
		if (shortNumber < 0)
			return (shortNumber + 65536);
		return shortNumber;
	}

	private void generateESliceEdgeMoveTable(Form prepare) {
		// TODO Auto-generated method stub
		if (prepare != null) {
			prepare.deleteAll();
			prepare.append("Initializing ESliceEdge Move Table...\n");
		}
		if (eSliceEdgeMoveTable == null)
			eSliceEdgeMoveTable = new byte[24 * 6 * 3];
		RubiksCubicCube cube = new RubiksCubicCube();
		int count = 0;
		for (byte eSliceEdgePermCode = 0; eSliceEdgePermCode < 24; eSliceEdgePermCode++) {
			byte[] eSliceEdgePermState = cube.getESliceEdgePermutationState(
					eSliceEdgePermCode, (short) 0);
			for (byte axis = 0; axis < 6; axis++) {
				for (byte turns = 1; turns <= 3; turns++) {
					if (axis != 0 && axis != 1 && turns != 2)
						continue;
					for (byte i = RubiksCubicCube.FIRST_EDGE; i <= RubiksCubicCube.LAST_EDGE; i++) {
						cube.edgePermutations[i] = eSliceEdgePermState[i];
					}
					cube.doMove(axis, turns);
					eSliceEdgeMoveTable[eSliceEdgePermCode * 6 * 3 + axis * 3
							+ turns - 1] = (byte) cube
							.getESliceEdgePermutationCode(cube.edgePermutations);
					count++;
				}
			}
			// if (prepare != null && eSliceEdgePermCode % 10 == 0) {
			// prepare.deleteAll();
			// prepare.append("Initializing ESlice Edge Move Table...\n"
			// + count + "/240\n");
			// }
		}
	}

	private void generateUdEdgeMoveTable(Form prepare) {
		// TODO Auto-generated method stub
		if (prepare != null) {
			prepare.deleteAll();
			prepare.append("Initializing UDEdge Move Table...\n");
		}
		if (udEdgeMoveTable == null)
			udEdgeMoveTable = new short[40320 * 6 * 3];
		RubiksCubicCube cube = new RubiksCubicCube();
		int count = 0;
		for (int udEdgePermCode = 0; udEdgePermCode < 40320; udEdgePermCode++) {
			byte[] udEdgePermState = cube.getUdEdgePermutationState(
					udEdgePermCode, (short) 0);
			for (byte axis = 0; axis < 6; axis++) {
				for (byte turns = 1; turns <= 3; turns++) {
					if (axis != 0 && axis != 1 && turns != 2)
						continue;
					for (byte i = RubiksCubicCube.FIRST_EDGE; i <= RubiksCubicCube.LAST_EDGE; i++) {
						cube.edgePermutations[i] = udEdgePermState[i];
					}
					cube.doMove(axis, turns);
					udEdgeMoveTable[udEdgePermCode * 6 * 3 + axis * 3 + turns
							- 1] = intToShort(cube
							.getUdEdgePermutationCode(cube.edgePermutations));
					count++;
				}
			}
			// if (prepare != null && udEdgePermCode % 10 == 0) {
			// prepare.deleteAll();
			// prepare.append("Initializing UDEdge Move Table...\n" + count
			// + "/403200\n");
			// }
		}
	}

	private void generateTwistMoveTable(Form prepare) {
		// TODO Auto-generated method stub
		if (prepare != null) {
			prepare.deleteAll();
			prepare.append("Initializing Twist Move Table...\n");
		}
		if (twistMoveTable == null)
			twistMoveTable = new short[2187 * 6 * 3];
		RubiksCubicCube cube = new RubiksCubicCube();
		int count = 0;
		for (short twist = 0; twist < 2187; twist++) {
			byte[] twistState = cube.getCornerOrientationState(twist);
			for (byte axis = 0; axis < 6; axis++) {
				for (byte turns = 1; turns <= 3; turns++) {
					for (byte i = RubiksCubicCube.FIRST_CORNER; i <= RubiksCubicCube.LAST_CORNER; i++) {
						cube.cornerOrientations[i] = twistState[i];
					}
					cube.doMove(axis, turns);
					twistMoveTable[twist * 6 * 3 + axis * 3 + turns - 1] = (short) cube
							.getCornerOrientationCode(cube.cornerOrientations);
					count++;
				}
			}
			// if (prepare != null && twist % 10 == 0) {
			// prepare.deleteAll();
			// prepare.append("Initializing Twist Move Table...\n" + count
			// + "/39366\n");
			// }
		}
	}

	private void generateFlipMoveTable(Form prepare) {
		// TODO Auto-generated method stub
		if (prepare != null) {
			prepare.deleteAll();
			prepare.append("Initializing Flip Move Table...\n");
		}
		if (flipMoveTable == null)
			flipMoveTable = new short[2048 * 6 * 3];
		RubiksCubicCube cube = new RubiksCubicCube();
		int count = 0;
		for (short flip = 0; flip < 2048; flip++) {
			byte[] flipState = cube.getEdgeOrientationState(flip);
			for (byte axis = 0; axis < 6; axis++) {
				for (byte turns = 1; turns <= 3; turns++) {
					for (byte i = RubiksCubicCube.FIRST_EDGE; i <= RubiksCubicCube.LAST_EDGE; i++) {
						cube.edgeOrientations[i] = flipState[i];
					}
					cube.doMove(axis, turns);
					flipMoveTable[flip * 6 * 3 + axis * 3 + turns - 1] = (short) cube
							.getEdgeOrientationCode(cube.edgeOrientations);
					count++;
				}
			}
			// if (prepare != null && flip % 10 == 0) {
			// prepare.deleteAll();
			// prepare.append("Initializing Flip Move Table...\n" + count
			// + "/36864\n");
			// }
		}
	}

	private void generateChooseMoveTable(Form prepare) {
		// TODO Auto-generated method stub
		if (prepare != null) {
			prepare.deleteAll();
			prepare.append("Initializing Choose Move Table...\n");
		}
		if (chooseMoveTable == null)
			chooseMoveTable = new short[495 * 6 * 3];
		RubiksCubicCube cube = new RubiksCubicCube();
		int count = 0;
		for (short choose = 0; choose < 495; choose++) {
			byte[] chooseState = cube.getChooseState(choose);
			for (byte axis = 0; axis < 6; axis++) {
				for (byte turns = 1; turns <= 3; turns++) {
					for (byte i = RubiksCubicCube.FIRST_EDGE; i <= RubiksCubicCube.LAST_EDGE; i++) {
						cube.edgePermutations[i] = chooseState[i];
					}
					cube.doMove(axis, turns);
					chooseMoveTable[choose * 6 * 3 + axis * 3 + turns - 1] = (short) cube
							.getChooseCode(cube.edgePermutations);
					count++;
				}
			}
			// if (prepare != null && choose % 10 == 0) {
			// prepare.deleteAll();
			// prepare.append("Initializing Choose Move Table...\n" + count
			// + "/8910\n");
			// }
		}
	}

	public int getUdEdgeMoveTableValue(int state, int axis, int turns) {
		short value = udEdgeMoveTable[state * 6 * 3 + axis * 3 + turns - 1];
		return shortToInt(value);
	}

	public int getCornerMoveTableValue(int state, int axis, int turns) {
		short value = cornerMoveTable[state * 6 * 3 + axis * 3 + turns - 1];
		return shortToInt(value);
	}

	public short getChooseMoveTableValue(int state, int axis, int turns) {
		short value = chooseMoveTable[state * 6 * 3 + axis * 3 + turns - 1];
		return value;
	}

	public short getFlipMoveTableValue(int state, int axis, int turns) {
		short value = flipMoveTable[state * 6 * 3 + axis * 3 + turns - 1];
		return value;
	}

	public short getTwistMoveTableValue(int state, int axis, int turns) {
		short value = twistMoveTable[state * 6 * 3 + axis * 3 + turns - 1];
		return value;
	}

	public byte getESliceEdgeMoveTableValue(int state, int axis, int turns) {
		byte value = eSliceEdgeMoveTable[state * 6 * 3 + axis * 3 + turns - 1];
		return value;
	}

	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		MoveTable.getInstance();
		System.out.println(System.currentTimeMillis() - start);
		// System.out.println(moveTable.getUdEdgeMoveTableValue(
		// moveTable.getUdEdgeMoveTableValue(0, 4, 2), 1, 1));
		// System.out.println(moveTable.getCornerMoveTableValue(0, 5, 1));
		// moveTable.saveMoveTable("xx.txt", null, 0);
	}
}
