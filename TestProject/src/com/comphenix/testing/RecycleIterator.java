package com.comphenix.testing;

public interface RecycleIterator<T> {
	public boolean moveNext();
	public BlockUpdateRecord getCurrent(BlockUpdateRecord recycle);
}
