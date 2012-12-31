package com.lagodiuk.clustering;

public interface DistanceCalculator<T> {

	public double distance(T base, T target);

}
