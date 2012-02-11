package scramble;

import java.util.Random;

import javax.microedition.lcdui.Form;

import cs.min2phase.Search;

public class _333CornerScramble extends _333Scramble {

	public _333CornerScramble(byte maxLength) {
		this.length = maxLength;
	}

	public _333CornerScramble(byte maxLength, Form prepare) {
		this(maxLength);
	}

	private byte[] cp = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 };
	private byte[] co = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
	private byte[] ep = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	private byte[] eo = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public String scramble() {
		Random rand = new Random();
		int perm;
		byte parity;
		do {
			parity = 0;
			perm = rand.nextInt(40320);
			int p = perm;
			for (int i = 6; i >= 0; i--) {
				parity += p % (8 - i);
				p /= 8 - i;
			}
			parity &= 1;
		} while (parity == 1);
		cp[7] = 0;
		for (int i = 6; i >= 0; i--) {
			cp[i] = (byte) (perm % (8 - i));
			perm /= 8 - i;
			for (int j = i + 1; j < 8; j++) {
				if (cp[j] >= cp[i]) {
					cp[j]++;
				}
			}
		}
		int ori = rand.nextInt(2187);
		for (int i = 0; i < 7; i++) {
			co[i] = (byte) (ori % 3);
			ori /= 3;
		}
		co[7] = (byte) ((15 - co[0] - co[1] - co[2] - co[3] - co[4] - co[5] - co[6]) % 3);
		scrambleSequence = new Search().solution(cp, co, ep, eo, length, 10000,
				false, true);
		str2seq();
		return scrambleSequence;
	}
}
