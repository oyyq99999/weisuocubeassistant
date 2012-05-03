package scramble;

import javax.microedition.lcdui.Form;

import cs.min2phase.Search;
import cs.min2phase.Tools;

public class _333CornerScramble extends _333Scramble {

	public _333CornerScramble(byte maxLength) {
		this.length = maxLength;
	}

	public _333CornerScramble(byte maxLength, Form prepare) {
		this(maxLength);
	}

	public String scramble() {
		scrambleSequence = new Search().solution(Tools.randomEdgeSolved(), length, 10000, 0, Search.INVERSE_SOLUTION);
		str2seq();
		return scrambleSequence;
	}
}
