package util;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Form;

public class PruningTable {

	// Phase 1 pruning tables
	private byte[] chooseFlipPruningTable = null;
	private byte[] chooseTwistPruningTable = null;
	private byte[] flipTwistPruningTable = null;

	// Phase 2 pruning tables
	private byte[] udESlicePruningTable = null;
	private byte[] cornerESlicePruningTable = null;

	private boolean tablesGenerated = false;
	private static PruningTable pruningTable = null;

	private PruningTable() {
		generatePruningTables(null);
	}

	private PruningTable(Form prepare) {
		generatePruningTables(prepare);
	}

	public static PruningTable getInstance() {
		if (pruningTable == null)
			pruningTable = new PruningTable();
		return pruningTable;
	}

	public static PruningTable getInstance(Form prepare) {
		if (pruningTable == null)
			pruningTable = new PruningTable(prepare);
		return pruningTable;
	}

	private void generatePruningTables(Form prepare) {
		if (tablesGenerated)
			return;

		// Phase 1
		chooseFlipPruningTable = new byte[495 * 2048];
		chooseTwistPruningTable = new byte[495 * 2187];
		flipTwistPruningTable = new byte[2048 * 2187];
		if (loadPhase1PruningTables("Phase1PruningTables.prunt", prepare) == false) {
			generateChooseFlipPruningTable(prepare);
			generateChooseTwistPrunningTable(prepare);
			generateFlipTwistPrunningTable(prepare);
			savePhase1PruningTables("Phase1PruningTables.prunt", prepare);
		}

		// Phase 2
		udESlicePruningTable = new byte[24 * 40320];
		cornerESlicePruningTable = new byte[24 * 40320];
		if (loadPhase2PruningTables("Phase2PruningTables.prunt", prepare) == false) {
			generateUdESlicePruningTable(prepare);
			generateCornerESlicePruningTable(prepare);
			savePhase2PruningTables("Phase2PruningTables.prunt", prepare);
		}
		tablesGenerated = true;
	}

	private void savePhase2PruningTables(String fileName, Form prepare) {
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
			// UD&ESliceEdgePruningTable
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Saving UD&ESliceEdge Pruning Table...");
			}
			for (int i = 0; i < 24 * 40320; i++) {
				dos.writeByte(udESlicePruningTable[i]);
			}
			// Corner&ESliceEdgePruningTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Saving Corner&ESliceEdge Pruning Table...");
			}
			for (int i = 0; i < 24 * 40320; i++) {
				dos.writeByte(cornerESlicePruningTable[i]);
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

	private boolean loadPhase2PruningTables(String fileName, Form prepare) {
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
			// UD&ESliceEdgePruningTable
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Loading UD&ESliceEdge Pruning Table...");
			}
			for (int i = 0; i < 24 * 40320; i++) {
				if ((udESlicePruningTable[i] = dis.readByte()) == -1) {
					return false;
				}
			}
			// Corner&ESliceEdgePruningTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Loading Corner&ESliceEdge Pruning Table...");
			}
			for (int i = 0; i < 24 * 40320; i++) {
				if ((cornerESlicePruningTable[i] = dis.readByte()) == -1) {
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

	private void savePhase1PruningTables(String fileName, Form prepare) {
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
			// Choose&FlipPruningTable
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Saving Choose&Flip Pruning Table...");
			}
			for (int i = 0; i < 495 * 2048; i++) {
				dos.writeByte(chooseFlipPruningTable[i]);
			}
			// Choose&TwistPruningTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Saving Choose&Twist Pruning Table...");
			}
			for (int i = 0; i < 495 * 2187; i++) {
				dos.writeByte(chooseTwistPruningTable[i]);
			}
			// Flip&TwistPruningTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Saving Flip&Twist Pruning Table...");
			}
			for (int i = 0; i < 2048 * 2187; i++) {
				dos.writeShort(flipTwistPruningTable[i]);
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

	private boolean loadPhase1PruningTables(String fileName, Form prepare) {
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
			// Choose&FlipPruningTable
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Loading Choose&Flip Pruning Table...");
			}
			for (int i = 0; i < 495 * 2048; i++) {
				if ((chooseFlipPruningTable[i] = dis.readByte()) == -1) {
					return false;
				}
			}
			// Choose&TwistPruningTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Loading Choose&Twist Pruning Table...");
			}
			for (int i = 0; i < 495 * 2048; i++) {
				if ((chooseTwistPruningTable[i] = dis.readByte()) == -1) {
					return false;
				}
			}
			// Flip&TwistPruningTable
			if (prepare != null) {
				prepare.append("Done\n");
				prepare.append("Loading Flip&Twist Pruning Table...");
			}
			for (int i = 0; i < 2048 * 2187; i++) {
				if ((flipTwistPruningTable[i] = dis.readByte()) == -1) {
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

	private void generateCornerESlicePruningTable(Form prepare) {
		// TODO Auto-generated method stub
		if (cornerESlicePruningTable == null)
			cornerESlicePruningTable = new byte[40320 * 24];
		for (int i = 0; i < 40320 * 24; i++) {
			cornerESlicePruningTable[i] = -1;
		}
		int cornerESliceCount = 0;
		for (byte depth = 0; cornerESliceCount < 40320 * 24; depth++) {
			int depthCount = 0;
			if (depth == 0) {
				cornerESlicePruningTable[0] = 0;
				cornerESliceCount++;
				depthCount++;
				// System.out.println("Depth " + depth + " initialized "
				// + cornerESliceCount + " cases.");
				if (prepare != null) {
					prepare.deleteAll();
					prepare.append("Initializing Corner&ESliceEdge Pruning Table...\nDepth: "
							+ (int) depth
							+ "\n"
							+ cornerESliceCount
							+ "/967680\n");
				}
				continue;
			} else {
				for (int i = 0; i < 40320 * 24; i++) {
					if (cornerESlicePruningTable[i] == depth - 1) {
						int corner = (i / 24);
						byte eSliceEdge = (byte) (i % 24);
						byte axis = -1;
						byte turns = 0;
						for (axis = 0; axis < 6; axis++) {
							for (turns = 1; turns <= 3; turns++) {
								if (axis != 0 && axis != 1 && turns != 2)
									continue;
								int newCorner = MoveTable.getInstance()
										.getCornerMoveTableValue(corner, axis,
												turns);
								byte newESliceEdge = MoveTable.getInstance()
										.getESliceEdgeMoveTableValue(
												eSliceEdge, axis, turns);
								if (cornerESlicePruningTable[newCorner * 24
										+ newESliceEdge] == -1) {
									cornerESlicePruningTable[newCorner * 24
											+ newESliceEdge] = depth;
									cornerESliceCount++;
								}
							}
						}
					}
				}
			}
			// System.out.println("Depth " + depth + " initialized "
			// + cornerESliceCount + " cases.");
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Initializing Corner&ESliceEdge Pruning Table...\nDepth: "
						+ (int) depth + "\n" + cornerESliceCount + "/967680\n");
			}
		}
	}

	private void generateUdESlicePruningTable(Form prepare) {
		// TODO Auto-generated method stub
		if (udESlicePruningTable == null)
			udESlicePruningTable = new byte[40320 * 24];
		for (int i = 0; i < 40320 * 24; i++) {
			udESlicePruningTable[i] = -1;
		}
		int udESliceCount = 0;
		for (byte depth = 0; udESliceCount < 40320 * 24; depth++) {
			if (depth == 0) {
				udESlicePruningTable[0] = 0;
				udESliceCount++;
				// System.out.println("Depth " + depth + " initialized "
				// + udESliceCount + " cases.");
				if (prepare != null) {
					prepare.deleteAll();
					prepare.append("Initializing UD&ESliceEdge Pruning Table...\nDepth: "
							+ (int) depth + "\n" + udESliceCount + "/967680\n");
				}
				continue;
			} else {
				for (int i = 0; i < 40320 * 24; i++) {
					if (udESlicePruningTable[i] == depth - 1) {
						int udEdge = (i / 24);
						byte eSliceEdge = (byte) (i % 24);
						byte axis = -1;
						byte turns = 0;
						for (axis = 0; axis < 6; axis++) {
							for (turns = 1; turns <= 3; turns++) {
								if (axis != 0 && axis != 1 && turns != 2)
									continue;
								int newUdEdge = MoveTable.getInstance()
										.getUdEdgeMoveTableValue(udEdge, axis,
												turns);
								byte newESliceEdge = MoveTable.getInstance()
										.getESliceEdgeMoveTableValue(
												eSliceEdge, axis, turns);
								if (udESlicePruningTable[newUdEdge * 24
										+ newESliceEdge] == -1) {
									udESlicePruningTable[newUdEdge * 24
											+ newESliceEdge] = depth;
									udESliceCount++;
								}
							}
						}
					}
				}
			}
			// System.out.println("Depth " + depth + " initialized "
			// + udESliceCount + " cases.");
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Initializing UD&ESliceEdge Pruning Table...\nDepth: "
						+ (int) depth + "\n" + udESliceCount + "/967680\n");
			}
		}
	}

	private void generateFlipTwistPrunningTable(Form prepare) {
		// TODO Auto-generated method stub
		if (flipTwistPruningTable == null)
			flipTwistPruningTable = new byte[2048 * 2187];
		for (int i = 0; i < 2048 * 2187; i++) {
			flipTwistPruningTable[i] = -1;
		}
		int flipTwistCount = 0;
		for (byte depth = 0; flipTwistCount < 2048 * 2187; depth++) {
			if (depth == 0) {
				flipTwistPruningTable[0] = 0;
				flipTwistCount++;
				// System.out.println("Depth " + depth + " initialized "
				// + flipTwistCount + " cases.");
				if (prepare != null) {
					prepare.deleteAll();
					prepare.append("Initializing Flip&Twist Pruning Table...\nDepth: "
							+ (int) depth
							+ "\n"
							+ flipTwistCount
							+ "/4478976\n");
				}
				continue;
			} else {
				for (int i = 0; i < 2048 * 2187; i++) {
					if (flipTwistPruningTable[i] == depth - 1) {
						short flip = (short) (i / 2187);
						short twist = (short) (i % 2187);
						byte axis = -1;
						byte turns = 0;
						for (axis = 0; axis < 6; axis++) {
							for (turns = 1; turns <= 3; turns++) {
								short newFlip = MoveTable.getInstance()
										.getFlipMoveTableValue(flip, axis,
												turns);
								short newTwist = MoveTable.getInstance()
										.getTwistMoveTableValue(twist, axis,
												turns);
								if (flipTwistPruningTable[newFlip * 2187
										+ newTwist] == -1) {
									flipTwistPruningTable[newFlip * 2187
											+ newTwist] = depth;
									flipTwistCount++;
								}
							}
						}
					}
				}
			}
			// System.out.println("Depth " + depth + " initialized "
			// + flipTwistCount + " cases.");
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Initializing Flip&Twist Pruning Table...\nDepth: "
						+ (int) depth + "\n" + flipTwistCount + "/4478976\n");
			}
		}
	}

	private void generateChooseTwistPrunningTable(Form prepare) {
		// TODO Auto-generated method stub
		if (chooseTwistPruningTable == null)
			chooseTwistPruningTable = new byte[495 * 2187];
		for (int i = 0; i < 495 * 2187; i++) {
			chooseTwistPruningTable[i] = -1;
		}
		int chooseTwistCount = 0;
		for (byte depth = 0; chooseTwistCount < 495 * 2187; depth++) {
			if (depth == 0) {
				chooseTwistPruningTable[0] = 0;
				chooseTwistCount++;
				// System.out.println("Depth " + depth + " initialized "
				// + chooseTwistCount + " cases.");
				if (prepare != null) {
					prepare.deleteAll();
					prepare.append("Initializing Choose&Twist Pruning Table...\nDepth: "
							+ (int) depth
							+ "\n"
							+ chooseTwistCount
							+ "/1082565\n");
				}
				continue;
			} else {
				for (int i = 0; i < 495 * 2187; i++) {
					if (chooseTwistPruningTable[i] == depth - 1) {
						short choose = (short) (i / 2187);
						short twist = (short) (i % 2187);
						byte axis = -1;
						byte turns = 0;
						for (axis = 0; axis < 6; axis++) {
							for (turns = 1; turns <= 3; turns++) {
								short newChoose = MoveTable.getInstance()
										.getChooseMoveTableValue(choose, axis,
												turns);
								short newTwist = MoveTable.getInstance()
										.getTwistMoveTableValue(twist, axis,
												turns);
								if (chooseTwistPruningTable[newChoose * 2187
										+ newTwist] == -1) {
									chooseTwistPruningTable[newChoose * 2187
											+ newTwist] = depth;
									chooseTwistCount++;
								}
							}
						}
					}
				}
			}
			// System.out.println("Depth " + depth + " initialized "
			// + chooseTwistCount + " cases.");
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Initializing Choose&Twist Pruning Table...\nDepth: "
						+ (int) depth + "\n" + chooseTwistCount + "/1082565\n");
			}
		}
	}

	private void generateChooseFlipPruningTable(Form prepare) {
		// TODO Auto-generated method stub
		if (chooseFlipPruningTable == null)
			chooseFlipPruningTable = new byte[495 * 2048];
		for (int i = 0; i < 495 * 2048; i++) {
			chooseFlipPruningTable[i] = -1;
		}
		int chooseFlipCount = 0;
		for (byte depth = 0; chooseFlipCount < 495 * 2048; depth++) {
			if (depth == 0) {
				chooseFlipPruningTable[0] = 0;
				chooseFlipCount++;
				System.out.println("Depth " + depth + " initialized "
						+ chooseFlipCount + " cases.");
				if (prepare != null) {
					prepare.deleteAll();
					prepare.append("Initializing Choose&Flip Pruning Table...\nDepth: "
							+ (int) depth
							+ "\n"
							+ chooseFlipCount
							+ "/1013760\n");
				}
				continue;
			} else {
				for (int i = 0; i < 495 * 2048; i++) {
					if (chooseFlipPruningTable[i] == depth - 1) {
						short choose = (short) (i / 2048);
						short flip = (short) (i % 2048);
						byte axis = -1;
						byte turns = 0;
						for (axis = 0; axis < 6; axis++) {
							for (turns = 1; turns <= 3; turns++) {
								short newChoose = MoveTable.getInstance()
										.getChooseMoveTableValue(choose, axis,
												turns);
								short newFlip = MoveTable.getInstance()
										.getFlipMoveTableValue(flip, axis,
												turns);
								if (chooseFlipPruningTable[newChoose * 2048
										+ newFlip] == -1) {
									chooseFlipPruningTable[newChoose * 2048
											+ newFlip] = depth;
									chooseFlipCount++;
								}
							}
						}
					}
				}
			}
			// System.out.println("Depth " + depth + " initialized "
			// + chooseFlipCount + " cases.");
			if (prepare != null) {
				prepare.deleteAll();
				prepare.append("Initializing Choose&Flip Pruning Table...\nDepth: "
						+ (int) depth + "\n" + chooseFlipCount + "/1013760\n");
			}
		}
	}

	public byte getChooseFlipPruningTableValue(short choose, short flip) {
		byte value = chooseFlipPruningTable[choose * 2048 + flip];
		return value;
	}

	public byte getChooseTwistPruningTableValue(short choose, short twist) {
		byte value = chooseTwistPruningTable[choose * 2187 + twist];
		return value;
	}

	public byte getFlipTwistPruningTableValue(short flip, short twist) {
		byte value = flipTwistPruningTable[flip * 2187 + twist];
		return value;
	}

	public byte getUdESlicePruningTableValue(int udEdge, byte eSliceEdge) {
		byte value = udESlicePruningTable[udEdge * 24 + eSliceEdge];
		return value;
	}

	public byte getCornerESlicePruningTableValue(int corner, byte eSliceEdge) {
		byte value = cornerESlicePruningTable[corner * 24 + eSliceEdge];
		return value;
	}

	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		MoveTable.getInstance();
		PruningTable.getInstance();
		System.out.println(System.currentTimeMillis() - start);
	}
}
