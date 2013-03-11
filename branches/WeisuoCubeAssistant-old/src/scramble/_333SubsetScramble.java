package scramble;

import javax.microedition.lcdui.Form;

import cs.min2phase.Search;
import cs.min2phase.Tools;

public class _333SubsetScramble extends _333Scramble {

	private String subset;

	public _333SubsetScramble(String subset, byte maxLength) {
		this.subset = subset;
		this.length = maxLength;
	}

	public _333SubsetScramble(String subset, byte maxLength, Form prepare) {
		this(subset, maxLength);
	}

	public String scramble() {
		if (subset.equals("Corner")) {
			scrambleSequence = new Search().solution(Tools.randomEdgeSolved(),
					length, 10000, 0, Search.INVERSE_SOLUTION);
		} else if (subset.equals("Edge")) {
			scrambleSequence = new Search().solution(
					Tools.randomCornerSolved(), length, 10000, 0,
					Search.INVERSE_SOLUTION);
		} else if (subset.equals("LL")) {
			scrambleSequence = new Search().solution(Tools.randomLastLayer(),
					length, 10000, 0, Search.INVERSE_SOLUTION);
		} else if (subset.equals("LSLL")) {
			scrambleSequence = new Search().solution(Tools.randomLastSlot(),
					length, 10000, 0, Search.INVERSE_SOLUTION);
		} else if (subset.equals("F2L")) {
			scrambleSequence = new Search().solution(Tools.randomCrossSolved(),
					length, 10000, 0, Search.INVERSE_SOLUTION);
		}
		str2seq();
		return scrambleSequence;
	}

}
