package com.comphenix.testing;

public interface RecycleIterator<T> {
	public boolean hasNext();
	public T next(T recycle);
}
