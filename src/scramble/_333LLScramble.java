package scramble;

import java.util.Random;

import javax.microedition.lcdui.Form;

import cs.min2phase.Search;

public class _333LLScramble extends _333Scramble {

	public _333LLScramble(byte maxLength) {
		this.length = maxLength;
	}

	public _333LLScramble(byte maxLength, Form prepare) {
		this(maxLength);
	}

	private static final char[] eori = new char[] { 0, 3, 5, 6, 9, 10, 12, 15 };

	public String scramble() {
		byte[] cp = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		byte[] co = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		byte[] ep = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		byte[] eo = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		Random rand = new Random();
		byte cperm, eperm, cParity = 0, eParity = 0;
		do {
			cParity = eParity = 0;
			cperm = (byte) rand.nextInt(24);
			eperm = (byte) rand.nextInt(24);
			byte cidx = cperm, eidx = eperm;
			for (int i = 2; i >= 0; i--) {
				cParity += cidx % (4 - i);
				eParity += eidx % (4 - i);
				cidx /= 4 - i;
				eidx /= 4 - i;
			}
			cParity &= 1;
			eParity &= 1;
		} while ((cParity ^ eParity) != 0);
		cp[3] = ep[3] = 0;
		for (int i = 2; i >= 0; i--) {
			cp[i] = (byte) (cperm % (4 - i));
			ep[i] = (byte) (eperm % (4 - i));
			cperm /= 4 - i;
			eperm /= 4 - i;
			for (int j = i + 1; j < 4; j++) {
				if (cp[j] >= cp[i])
					cp[j]++;
				if (ep[j] >= ep[i])
					ep[j]++;
			}
		}
		int o = rand.nextInt(27);
		co[0] = (byte) (o % 3);
		o /= 3;
		co[1] = (byte) (o % 3);
		co[2] = (byte) (o / 3);
		co[3] = (byte) ((6 - co[0] - co[1] - co[2]) % 3);
		o = rand.nextInt(8);
		for (int i = 0; i < 4; i++) {
			if ((eori[o] & (1 << i)) != 0) {
				eo[i] = 1;
			}
		}
		scrambleSequence = new Search().solution(cp, co, ep, eo, length, 10000,
				false, true);
		str2seq();
		return scrambleSequence;
	}
}
