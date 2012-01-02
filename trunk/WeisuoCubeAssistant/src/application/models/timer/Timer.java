/**
 * 
 */
package application.models.timer;

import system.base.TimerBase;
import system.exception.TimerStateException;

/**
 * @author ouyangyunqi
 *
 */
public class Timer extends TimerBase {

	public Timer() {
		reset();
	}
	
	/**
	 * @param startImmediately
	 * @throws TimerStateException
	 */
	public Timer(boolean startImmediately) throws TimerStateException {
		reset();
		if (startImmediately) {
			start();
		}
	}
}
