package scramble;

import java.util.Random;

import javax.microedition.lcdui.Form;

import cs.min2phase.Search;

public class _333LSLLScramble extends _333Scramble {

	public _333LSLLScramble(byte maxLength) {
		this.length = maxLength;
	}

	public _333LSLLScramble(byte maxLength, Form prepare) {
		this(maxLength);
	}

	public String scramble() {
		byte[] cp = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		byte[] co = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		byte[] ep = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		byte[] eo = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		Random rand = new Random();
		byte cperm, eperm, cParity = 0, eParity = 0;
		do {
			cParity = eParity = 0;
			cperm = (byte) rand.nextInt(120);
			eperm = (byte) rand.nextInt(120);
			byte cidx = cperm, eidx = eperm;
			for (int i = 3; i >= 0; i--) {
				cParity += cidx % (5 - i);
				eParity += eidx % (5 - i);
				cidx /= 5 - i;
				eidx /= 5 - i;
			}
			cParity &= 1;
			eParity &= 1;
		} while ((cParity ^ eParity) != 0);
		cp[4] = ep[4] = 0;
		for (int i = 3; i >= 0; i--) {
			cp[i] = (byte) (cperm % (5 - i));
			ep[i] = (byte) (eperm % (5 - i));
			cperm /= 5 - i;
			eperm /= 5 - i;
			for (int j = i + 1; j < 5; j++) {
				if (cp[j] >= cp[i])
					cp[j]++;
				if (ep[j] >= ep[i])
					ep[j]++;
			}
		}
		if (ep[4] != 4) {
			ep[8] = ep[4];
			ep[4] = 4;
			for (int i = 0; i < 4; i++) {
				if (ep[i] == 4) {
					ep[i] = 8;
					break;
				}
			}
		}
		int o = rand.nextInt(81);
		co[0] = (byte) (o % 3);
		o /= 3;
		co[1] = (byte) (o % 3);
		o /= 3;
		co[2] = (byte) (o % 3);
		co[3] = (byte) (o / 3);
		co[4] = (byte) ((9 - co[0] - co[1] - co[2] - co[3]) % 3);
		o = rand.nextInt(16);
		for (int i = 0; i < 4; i++) {
			if ((o & (1 << i)) != 0) {
				eo[i] = 1;
			}
		}
		eo[8] = (byte) (eo[0] ^ eo[1] ^ eo[2] ^ eo[3]);
		scrambleSequence = new Search().solution(cp, co, ep, eo, length, 10000,
				false, true);
		str2seq();
		return scrambleSequence;
	}
}
