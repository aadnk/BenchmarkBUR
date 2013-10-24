package com.comphenix.testing;

import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class Test {
	public static void main(String[] args) throws Exception {
		Runner.main(SpecialMessageTest.class, new String[] { "" });
	}
	
	public static class SpecialMessageTest extends SimpleBenchmark {
		@Override
		protected void setUp() throws Exception {
			
		}
		
		public void timeBasic(int reps) {
			for (int i = 0; i < reps; i++) {
				
			}
		}
		
		public void timeIntermediate(int reps) {
			for (int i = 0; i < reps; i++) {

			}
		}
		
		public void timeComplex(int reps) {
			for (int i = 0; i < reps; i++) {

			}
		}
	}
}
