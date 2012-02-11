package scramble;

import java.util.Random;

import javax.microedition.lcdui.Form;

import cs.min2phase.Search;

public class _333EdgeScramble extends _333Scramble {

	public _333EdgeScramble(byte maxLength) {
		this.length = maxLength;
	}

	public _333EdgeScramble(byte maxLength, Form prepare) {
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
			perm = rand.nextInt(479001600);
			int p = perm;
			for (int i = 10; i >= 0; i--) {
				parity += p % (12 - i);
				p /= 12 - i;
			}
			parity &= 1;
		} while (parity == 1);
		ep[11] = 0;
		for (int i = 10; i >= 0; i--) {
			ep[i] = (byte) (perm % (12 - i));
			perm /= 12 - i;
			for (int j = i + 1; j < 12; j++) {
				if (ep[j] >= ep[i]) {
					ep[j]++;
				}
			}
		}
		int ori = rand.nextInt(2048);
		for (int i = 0; i < 11; i++) {
			eo[i] = (byte) (ori & 1);
			ori >>>= 1;
		}
		eo[11] = (byte) (eo[0] ^ eo[1] ^ eo[2] ^ eo[3] ^ eo[4] ^ eo[5] ^ eo[6]
				^ eo[7] ^ eo[8] ^ eo[9] ^ eo[10]);
		scrambleSequence = new Search().solution(cp, co, ep, eo, length, 10000,
				false, true);
		str2seq();
		return scrambleSequence;
	}
}
