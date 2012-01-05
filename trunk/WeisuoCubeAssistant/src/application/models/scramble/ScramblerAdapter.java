/**
 * 
 */
package application.models.scramble;

import java.util.Random;
/**
 * @author chenshuang
 *
 */
public class ScramblerAdapter implements Scrambler {

	protected Random gen;
	
	public ScramblerAdapter(Random gen) {
		this.gen = gen;
	}
	
	public ScramblerAdapter() {
		gen = new Random();
	}

	public void setRandomSource(Random gen) {
		this.gen = gen;
	}

	public Scramble getScramble() {
		return new Scramble(0);
	}

}
