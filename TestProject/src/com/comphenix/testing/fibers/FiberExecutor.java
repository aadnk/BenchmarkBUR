package com.comphenix.testing.fibers;

import java.util.concurrent.TimeUnit;

public interface FiberExecutor {
	/**
	 * Submit a fiber task for execution.
	 * @param task - the task to submit.
	 */
	public void submit(FiberTask task);

	/**
	 * Execute fibers on the current thread for no longer that the given duration.
	 * @param duration - the given duration.
	 * @param unit - the current unit.
	 * @return TRUE if we executed at least one fiber, FALSE if there are no more fibers.
	 */
	public boolean executeFibers(long duration, TimeUnit unit);
}
