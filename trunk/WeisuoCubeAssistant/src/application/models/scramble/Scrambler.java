/**
 * 
 */
package application.models.scramble;

import java.util.Random;
/**
 * @author chenshuang
 *
 */
public interface Scrambler {

	public void setRandomSource(Random gen);

	public Scramble getScramble();

}
