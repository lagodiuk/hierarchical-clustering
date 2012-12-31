package com.lagodiuk.clustering;

public interface Distanceable<T extends Distanceable<T>> {

	public double distance(T other);

}
