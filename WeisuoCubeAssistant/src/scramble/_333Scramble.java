package scramble;

import javax.microedition.lcdui.Form;

import util.TwoPhaseSearch;

public class _333Scramble extends Scramble {

	private Form prepare = null;

	public _333Scramble() {
		this(25);
	}

	public _333Scramble(int maxLength) {
		// 最大长度
		this.length = maxLength;
	}

	public _333Scramble(int maxLength, Form prepare) {
		this(maxLength);
		this.prepare = prepare;
	}

	public String scramble() {
		// TODO Auto-generated method stub
		scrambleSequence = TwoPhaseSearch.getInstance(prepare).scramble(length);
		return scrambleSequence;
	}

}
