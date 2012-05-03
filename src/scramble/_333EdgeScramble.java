package scramble;

import javax.microedition.lcdui.Form;

import cs.min2phase.Search;
import cs.min2phase.Tools;

public class _333EdgeScramble extends _333Scramble {

	public _333EdgeScramble(byte maxLength) {
		this.length = maxLength;
	}

	public _333EdgeScramble(byte maxLength, Form prepare) {
		this(maxLength);
	}

	public String scramble() {
		scrambleSequence = new Search().solution(Tools.randomCornerSolved(), length, 10000, 0, Search.INVERSE_SOLUTION);
		str2seq();
		return scrambleSequence;
	}
}
