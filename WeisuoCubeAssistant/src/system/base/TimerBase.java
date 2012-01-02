package system.base;

import system.exception.TimerStateException;

/**
 * @author ouyangyunqi
 * 
 */
public abstract class TimerBase {

	public static final byte TIMER_STATE_RUNNING = 0;
	public static final byte TIMER_STATE_STOPPED = 1;
	public static final byte TIMER_STATE_RESET = 2;

	protected long startTime = -1L;
	protected long elapsedTime = -1L;
	protected byte status = TimerBase.TIMER_STATE_RESET;

	/**
	 * starts the timer
	 * 
	 * @throws TimerStateException
	 */
	public void start() throws TimerStateException {
		if (status == TimerBase.TIMER_STATE_RUNNING) {
			throw new TimerStateException("Timer is already running!");
		} else if (status == TimerBase.TIMER_STATE_STOPPED) {
			reset();
		}
		startTime = System.currentTimeMillis();
		elapsedTime = -1;
		status = TimerBase.TIMER_STATE_RUNNING;
	}

	/**
	 * @return elapsed time
	 * @throws TimerStateException
	 */
	public long stop() throws TimerStateException {
		if (status != TimerBase.TIMER_STATE_RUNNING) {
			throw new TimerStateException("Timer is not running!");
		}
		elapsedTime = System.currentTimeMillis() - startTime;
		return elapsedTime;
	}

	/**
	 * reset the timer
	 */
	public void reset() {
		startTime = -1;
		elapsedTime = -1;
		status = TimerBase.TIMER_STATE_RESET;
	}

	public long getElapsedTime() {
		if (status == TimerBase.TIMER_STATE_RUNNING) {
			elapsedTime = System.currentTimeMillis() - startTime;
		}

		return elapsedTime;
	}

}
