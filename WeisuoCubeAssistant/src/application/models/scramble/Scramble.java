/**
 * 
 */
package application.models.scramble;

/**
 * @author chenshuang
 *
 */
public class Scramble {

	public final static int SCRAMBLE_TYPE_NULL = 0;
	public final static int SCRAMBLE_TYPE_333 = 3;
	public final static int SCRAMBLE_TYPE_222 = 2;
	public final static int SCRAMBLE_TYPE_444 = 4;
	public final static int SCRAMBLE_TYPE_555 = 5;
	public final static int SCRAMBLE_TYPE_666 = 6;
	public final static int SCRAMBLE_TYPE_777 = 7;
	public final static int SCRAMBLE_TYPE_888 = 8;
	public final static int SCRAMBLE_TYPE_999 = 9;
	public final static int SCRAMBLE_TYPE_AAA = 10;
	public final static int SCRAMBLE_TYPE_BBB = 11;

	public final int type;

	public byte[] data;

	public Scramble(int type) {
		this.type = type;
		data = null;
	}

	final private Scramble() {}

	public String toString() {
		return "";
	}
}
