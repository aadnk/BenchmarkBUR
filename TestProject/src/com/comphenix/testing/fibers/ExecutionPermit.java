package com.comphenix.testing.fibers;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.Validate;

/**
 * Keeps track of the remaining time allotted for an executing fiber.
 * @author Kristian
 */
public class ExecutionPermit {
	// When the execution times out
	private final long timeout;
	
	/**
	 * Construct a permit that will expire after the given delay.
	 * @param delay - the delay, in the provided unit.
	 * @param unit - the provided unit.
	 */
    public ExecutionPermit(long delay, TimeUnit unit) {
        this.timeout = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delay, unit);
    }
    /**
     * Determine if the current permit has expired.
     * <p>
     * Note that calling this method is not free, and should be invoked conservatively.
     * @return TRUE if it has, FALSE otherwise.
     */
    public boolean hasExpired() {
        return timeout < System.nanoTime();
    }
    
    /**
     * Retrieve the amount of time left until the execution permit expires.
     * <p>
     * The time will be negative once it has expired.
     * @param unit - the unit of the remaining time.
     * @return The time remaining until expiration. 
     */
    public long getRemainingTime(TimeUnit unit) {
    	Validate.notNull(unit, "Unit cannot be NULL.");

    	return unit.convert(timeout - System.nanoTime(), TimeUnit.NANOSECONDS);
    }
}
