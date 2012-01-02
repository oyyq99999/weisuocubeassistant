package system.base;

/**
 * @author ouyangyunqi
 *
 */
public abstract class TimerBase {

	public static byte TIMER_STATE_RUNNING = 0;
	public static byte TIMER_STATE_STOPPED = 1;
	public static byte TIMER_STATE_RESET = 2;
	
	protected long startTime = 0L;
	protected long endTime = 0L;
	protected byte status = TimerBase.TIMER_STATE_RESET;
	
	/**
	 * starts the timer
	 */
	protected abstract void start();
	/**
	 * stop the timer
	 */
	protected abstract void stop();
}
