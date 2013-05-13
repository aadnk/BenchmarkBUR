package com.comphenix.testing.fibers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class RoundRobinExecutor implements FiberExecutor {
	// We cannot use a Deque as we have to support Java 5
	private Queue<FiberTask> tasks = new LinkedList<FiberTask>();
	
	private final long timeSlice;
	private final TimeUnit timeUnit;

	public RoundRobinExecutor(long timeSlice, TimeUnit timeUnit) {
		this.timeSlice = timeSlice;
		this.timeUnit = timeUnit;
	}

	public void submit(FiberTask task) {
		tasks.offer(task);
	}
	
	public boolean executeFibers(long duration, TimeUnit unit) {
		ExecutionPermit executorPermit = new ExecutionPermit(duration, unit);
		int executed = 0;
		
		while (!executorPermit.hasExpired() && tasks.size() > 0) {
			FiberTask current = tasks.poll();
			ExecutionPermit taskPermit = getPermit(current);
			
			if (!current.run(taskPermit)) {
				// Add it back
				tasks.offer(current);
			}
		}
		return executed > 0;
	}
	
	/**
	 * Retrieve an execution permit for a given fiber task.
	 * @param task - the task whose permit we're retrieving.
	 * @return The permit to use.
	 */
	protected ExecutionPermit getPermit(FiberTask task) {
		return new ExecutionPermit(timeSlice, timeUnit);
	}
	
	/**
	 * Retrieve the amount of time in each time slice used to fairly execute each task.
	 * @param unit - the unit of the resulting time.
	 * @return The length of each time slice in the provided unit.
	 */
	public long getTimeSlice(TimeUnit unit) {
		return unit.convert(timeSlice, timeUnit);
	}
}
