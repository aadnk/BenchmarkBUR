package com.comphenix.testing.fibers;

/**
 * A low-priority task that may be executed on the main thread multiple times in short bursts.
 * 
 * @author Kristian
 */
public interface FiberTask {
	/**
	 * Execute the current fiber for as long as the provided permit has not expired.
	 * <p>
	 * Fibers should not run for no more than 250 microseconds after the permit has expired.
	 * @param permit - the execution permit.
	 * @return TRUE if the task is complete, FALSE otherwise.
	 */
	public boolean run(ExecutionPermit permit);
}
