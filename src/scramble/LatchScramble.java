package scramble;

import java.util.Random;

public class LatchScramble extends Scramble {

	private final int u = 0;
	private final int d = 1;
	private final int l = 2;
	private final int r = 3;
	private final int f = 4;
	private final int b = 5;
	private int[] moveFaces = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private int[] moveTimes = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private String[] moveNames = { "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "" };
	private int moveCount = 0;
	private int currentMove = -1;
	private int currentFace = -1;
	private int currentTime = 0;
	private int[] movableFaces = { 0, 1, 2, 3, 4, 5 };
	private int movableFaceCount = 0;
	private boolean bothDirections = false;
	private StringBuffer sb = new StringBuffer();

	private int[][] label = { { 0, -1, -1, 0 }, { 0, 1, 1, 0 },
			{ 0, -1, -1, 0 }, { 0, 1, 1, 0 }, { -1, 0, 0, -1 }, { 1, 0, 0, 1 }, };

	public LatchScramble(int length) {
		this.length = length;
	}

	public LatchScramble() {
		this.length = 25;
	}

	private boolean check(int face) {
		int sum = 0;
		int count = 0;
		for (int i = 0; i < 4; i++) {
			sum += label[face][i];
			count += Math.abs(label[face][i]);
		}
		sum = Math.abs(sum);
		if (sum == count)
			return true;
		return false;
	}

	private boolean isCounterClockwise(int face) {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += label[face][i];
		}
		bothDirections = (sum == 0);
		if (sum < 0)
			return true;
		return false;
	}

	private void determineMovableFaces() {
		movableFaceCount = 0;
		for (int i = 0; i < 6; i++) {
			movableFaces[i] = check(i) ? i : -1;
		}
		for (int i = 0; i < 6; i++) {
			movableFaceCount += movableFaces[i] >= 0 ? 1 : 0;
		}
	}

	private boolean moveU(int times) {
		int i = 0;
		for (i = 0; i < times; i++) {

			// 转动
			int tmp = label[u][0];
			label[u][0] = label[u][1];
			label[u][1] = label[u][3];
			label[u][3] = label[u][2];
			label[u][2] = tmp;

			tmp = label[f][0];
			label[f][0] = label[r][0];
			label[r][0] = label[b][0];
			label[b][0] = label[l][0];
			label[l][0] = tmp;
		}
		return true;
	}

	private boolean moveD(int times) {
		int i = 0;
		for (i = 0; i < times; i++) {

			// 转动
			int tmp = label[d][0];
			label[d][0] = label[d][1];
			label[d][1] = label[d][3];
			label[d][3] = label[d][2];
			label[d][2] = tmp;

			tmp = label[f][3];
			label[f][3] = label[l][3];
			label[l][3] = label[b][3];
			label[b][3] = label[r][3];
			label[r][3] = tmp;
		}
		return true;
	}

	private boolean moveL(int times) {
		int i = 0;
		for (i = 0; i < times; i++) {

			// 转动
			int tmp = label[l][0];
			label[l][0] = label[l][1];
			label[l][1] = label[l][3];
			label[l][3] = label[l][2];
			label[l][2] = tmp;

			tmp = label[u][1];
			label[u][1] = label[b][2];
			label[b][2] = label[d][1];
			label[d][1] = label[f][1];
			label[f][1] = tmp;
		}
		return true;
	}

	private boolean moveR(int times) {
		int i = 0;
		for (i = 0; i < times; i++) {

			// 转动
			int tmp = label[r][0];
			label[r][0] = label[r][1];
			label[r][1] = label[r][3];
			label[r][3] = label[r][2];
			label[r][2] = tmp;

			tmp = label[u][2];
			label[u][2] = label[f][2];
			label[f][2] = label[d][2];
			label[d][2] = label[b][1];
			label[b][1] = tmp;
		}
		return true;
	}

	private boolean moveF(int times) {
		int i = 0;
		for (i = 0; i < times; i++) {

			// 转动
			int tmp = label[f][0];
			label[f][0] = label[f][1];
			label[f][1] = label[f][3];
			label[f][3] = label[f][2];
			label[f][2] = tmp;

			tmp = label[u][3];
			label[u][3] = label[l][2];
			label[l][2] = label[d][0];
			label[d][0] = label[r][1];
			label[r][1] = tmp;
		}
		return true;
	}

	private boolean moveB(int times) {
		int i = 0;
		for (i = 0; i < times; i++) {

			// 转动
			int tmp = label[b][0];
			label[b][0] = label[b][1];
			label[b][1] = label[b][3];
			label[b][3] = label[b][2];
			label[b][2] = tmp;

			tmp = label[u][0];
			label[u][0] = label[r][2];
			label[r][2] = label[d][3];
			label[d][3] = label[l][1];
			label[l][1] = tmp;
		}
		return true;
	}

	private void reset() {
		label[0][0] = 0;
		label[0][1] = -1;
		label[0][2] = -1;
		label[0][3] = 0;
		label[1][0] = 0;
		label[1][1] = 1;
		label[1][2] = 1;
		label[1][3] = 0;
		label[2][0] = 0;
		label[2][1] = -1;
		label[2][2] = -1;
		label[2][3] = 0;
		label[3][0] = 0;
		label[3][1] = 1;
		label[3][2] = 1;
		label[3][3] = 0;
		label[4][0] = -1;
		label[4][1] = 0;
		label[4][2] = 0;
		label[4][3] = -1;
		label[5][0] = 1;
		label[5][1] = 0;
		label[5][2] = 0;
		label[5][3] = 1;
		for (int i = 0; i < moveFaces.length; i++) {
			moveFaces[i] = -1;
		}
		for (int i = 0; i < moveTimes.length; i++) {
			moveTimes[i] = 0;
		}
		for (int i = 0; i < moveNames.length; i++) {
			moveNames[i] = "";
		}
		currentMove = -1;
		currentFace = -1;
		currentTime = 0;
		movableFaceCount = 0;
		for (int i = 0; i < movableFaces.length; i++) {
			movableFaces[i] = i;
		}
		moveCount = 0;
		sb.delete(0, sb.length());
	}

	private void undoLastMove() {
		switch (moveFaces[moveCount - 1]) {
		case 0:
			moveU((4 - moveTimes[moveCount - 1]) % 4);
			break;
		case 1:
			moveD((4 - moveTimes[moveCount - 1]) % 4);
			break;
		case 2:
			moveL((4 - moveTimes[moveCount - 1]) % 4);
			break;
		case 3:
			moveR((4 - moveTimes[moveCount - 1]) % 4);
			break;
		case 4:
			moveF((4 - moveTimes[moveCount - 1]) % 4);
			break;
		case 5:
			moveB((4 - moveTimes[moveCount - 1]) % 4);
			break;
		}
		moveFaces[moveCount - 1] = -1;
		moveTimes[moveCount - 1] = 0;
		moveNames[moveCount - 1] = "";
		moveCount--;
	}

	public String scramble() {
		sequence = new byte[25];
		reset();
		Random rand = new Random();
		for (int i = 0; i < this.length; i++, moveCount++) {
			determineMovableFaces();
			currentMove = rand.nextInt(18);
			currentFace = currentMove / 3;
			currentTime = currentMove % 3 + 1;
			if (movableFaces[currentFace] == -1) {
				i--;
				moveCount--;
				continue;
			}
			if (movableFaceCount < 2) {
				undoLastMove();
				i--;
				i--;
				moveCount--;
				continue;
			}
			if (movableFaceCount < 3) {
				if (moveCount > 1
						&& moveFaces[moveCount - 1] / 2 == moveFaces[moveCount - 2] / 2) {
					undoLastMove();
					undoLastMove();
					i -= 3;
					moveCount--;
					continue;
				}
			}
			if (moveCount > 0 && currentFace == moveFaces[moveCount - 1]) {
				i--;
				moveCount--;
				continue;
			}
			if (moveCount > 1
					&& currentFace / 2 == moveFaces[moveCount - 1] / 2
					&& currentFace / 2 == moveFaces[moveCount - 2] / 2) {
				i--;
				moveCount--;
				continue;
			}
			addMove(currentFace, currentTime);
		}
		for (int i = 0; i < 25; i++) {
			sb.append(moveNames[i]);
			sb.append(' ');
			switch (moveNames[i].charAt(0)) {
			case 'U':
				sequence[i] = 0;
				break;
			case 'R':
				sequence[i] = 3;
				break;
			case 'F':
				sequence[i] = 6;
				break;
			case 'D':
				sequence[i] = 9;
				break;
			case 'L':
				sequence[i] = 12;
				break;
			case 'B':
				sequence[i] = 15;
				break;
			}
			if (moveNames[i].length() >= 2) {
				if (moveNames[i].charAt(1) == '2') {
					sequence[i]++;
				} else if (moveNames[i].length() == 2) {
					sequence[i] += 2;
				}
			}
			System.out.println(sequence[i]);
		}
		scrambleSequence = sb.toString().trim();
		return scrambleSequence;
	}

	private void addMove(int face, int time) {
		switch (face) {
		case 0:
			moveU(time);
			moveNames[moveCount] = "U";
			break;
		case 1:
			moveD(time);
			moveNames[moveCount] = "D";
			break;
		case 2:
			moveL(time);
			moveNames[moveCount] = "L";
			break;
		case 3:
			moveR(time);
			moveNames[moveCount] = "R";
			break;
		case 4:
			moveF(time);
			moveNames[moveCount] = "F";
			break;
		case 5:
			moveB(time);
			moveNames[moveCount] = "B";
			break;
		}
		if (isCounterClockwise(face))
			time -= 4;
		moveFaces[moveCount] = face;
		moveTimes[moveCount] = time;
		switch (time) {
		case -3:
			moveNames[moveCount] += "3'";
			break;
		case -2:
			moveNames[moveCount] += "2'";
			break;
		case -1:
			moveNames[moveCount] += "'";
			break;
		case 1:
			break;
		case 2:
			moveNames[moveCount] += "2";
			break;
		case 3:
			if (bothDirections) {
				moveNames[moveCount] += "'";
			} else {
				moveNames[moveCount] += "3";
			}
			break;
		}
	}

	public byte[] getSequence() {
		return sequence;
	}

	public String getName() {
		return "Cube3";
	}

}
