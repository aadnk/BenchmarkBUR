package com.comphenix.testing;

import java.util.concurrent.TimeUnit;

import com.comphenix.testing.fibers.ExecutionPermit;
import com.comphenix.testing.fibers.FiberExecutor;
import com.comphenix.testing.fibers.FiberTask;
import com.comphenix.testing.fibers.RoundRobinExecutor;
import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class Test {
	public static void main(String[] args) throws Exception {
		Runner.main(RoundRobinTest.class, new String[] { "-DtimeSlice=1,2,8,16" });
	}
	
	public static class RoundRobinTest extends SimpleBenchmark {
		@Param public int timeSlice;

		private MockWorld world = new MockWorld(64, 64, 64);
		private FiberExecutor executor;
		
		@Override
		protected void setUp() throws Exception {
			executor = new RoundRobinExecutor(timeSlice, TimeUnit.MILLISECONDS);
		}
		
		public void timeDirect(int reps) {
			for (int i = 0; i < reps; i++) {
		        for (int x = 0; x < world.getWidth(); x++) {
		            for (int y = 0; y < world.getHeight(); y++) {
		            	for (int z = 0; z < world.getDepth(); z++) {
		            		world.setBlock(x, y, z, timeSlice, 0);
		            	}
		            }
		        }
			}
		}
		
		public void timeUpdateWorld(int reps) {
			for (int i = 0; i < reps; i++) {
				executor.submit(new FiberTask() {
				    private int x = 0;
	
					public boolean run(ExecutionPermit permit) {
				        for (; x < world.getWidth(); x++) {
				            for (int y = 0; y < world.getHeight(); y++) {
				            	for (int z = 0; z < world.getDepth(); z++) {
				            		world.setBlock(x, y, z, timeSlice, 0);
				            	}
				            }
	
				            if ((x % 2) == 0 && permit.hasExpired())
				                return false;
				        }
				        // We are done
				        return true;
					}
				});
				executor.executeFibers(500, TimeUnit.MILLISECONDS);
			}
		}
	}
}
