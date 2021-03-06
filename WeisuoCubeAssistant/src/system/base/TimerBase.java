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
	 * start the timer
	 * 
	 * @throws TimerStateException when the timer is already running
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
	 * @throws TimerStateException when the time is not running
	 */
	public long stop() throws TimerStateException {
		if (status != TimerBase.TIMER_STATE_RUNNING) {
			throw new TimerStateException("Timer is not running!");
		}
		elapsedTime = System.currentTimeMillis() - startTime;
		status = TimerBase.TIMER_STATE_STOPPED;
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

	/**
	 * @return elapsed time at the moment
	 */
	public long getElapsedTime() {
		if (status == TimerBase.TIMER_STATE_RUNNING) {
			elapsedTime = System.currentTimeMillis() - startTime;
		}

		return elapsedTime;
	}

	public byte getStatus() {
		return status;
	}

}
