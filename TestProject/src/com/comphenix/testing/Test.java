package com.comphenix.testing;

import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class Test {
	public static void main(String[] args) throws Exception {
		Runner.main(MassBlockUpdateBenchark.class, args);
	}

	public static class MockMassBlockUpdate {
		private MockWorld world;
		private RecycleIterable<BlockUpdateRecord> provider;
		
		public MockMassBlockUpdate(MockWorld world) {
			this.world = world;
		}
		
		public void setProvider(RecycleIterable<BlockUpdateRecord> provider) {
			this.provider = provider;
		}
		
		public MockWorld getWorld() {
			return world;
		}
		
		public void applyUpdates() {
			BlockUpdateRecord record = new BlockUpdateRecord();
			
			for (RecycleIterator<BlockUpdateRecord> it = provider.iterator(); it.hasNext(); ) {
				record = it.next(record);
				world.setBlock(record.getX(), record.getY(), record.getZ(), record.getMaterialId(), record.getData());
			}
		}
	}
	
	public static class MassBlockUpdateBenchark extends SimpleBenchmark {
		private MockMassBlockUpdate blockUpdate;
		
		@Override
		protected void setUp() {
			blockUpdate = new MockMassBlockUpdate(new MockWorld(16, 16, 16));
		}

		
		public void timeProvider(int reps) {
			for (int i = 0; i < reps; i++) {
				blockUpdate.setProvider(getProvider(16, 16, 16));
				blockUpdate.applyUpdates();
			}
		}
		
		public void timeDirect(int reps) {
			for (int i = 0; i < reps; i++) {
				MockWorld mock = blockUpdate.getWorld();
				
				// The direct approach
				for (int x = 0; x < 16; x++) {
					for (int y = 0; y < 16; y++) {
						for (int z = 0; z < 16; z++) {
							mock.setBlock(x, y, z, (x * y * z) & 0xFF, 0);
						}
					}
				}
			}
		}
	}
	
	private static RecycleIterable<BlockUpdateRecord> getProvider(final int width, final int height, final int depth) {
		return new RecycleIterable<BlockUpdateRecord>() {
			@Override
			public RecycleIterator<BlockUpdateRecord> iterator() {
				return new RecycleIterator<BlockUpdateRecord>() {
					private int x = 0, y = 0, z = 0;
					
					@Override
					public boolean hasNext() {
						return x < (width - 1) || y < (height - 1) || z < (depth - 1);
					}
					
					@Override
					public BlockUpdateRecord next(BlockUpdateRecord recycle) {
						// new BlockUpdateRecord(x, y, z, (x * y * z) & 0xFF, (byte) 0);
						recycle.setX(x);
						recycle.setY(y);
						recycle.setZ(z);
						recycle.setMaterialId((x * y * z) & 0xFF);
						
						// Increment
						if (++x >= width) {
							if (++y >= height) {
								y = 0;
								z++;
							}
							x = 0;
						}
						return recycle;
					}
				};
			}
		};
	}
}
