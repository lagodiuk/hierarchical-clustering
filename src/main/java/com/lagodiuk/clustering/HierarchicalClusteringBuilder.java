package com.lagodiuk.clustering;

import java.util.List;

public class HierarchicalClusteringBuilder<T> {

	private DistanceCalculator<T> distanceCalculator;

	private Double conjunctionDistance;

	private Double thresholdSimilarityDistance;

	private T[] items;

	public TypedTreeNode<T> doSingleLinkage() {
		SingleLinkage clusterizer = new SingleLinkage();
		return clusterizer.clusterize(this.distanceCalculator, this.conjunctionDistance, this.thresholdSimilarityDistance, this.items);
	}

	public TypedTreeNode<T> doCompleteLinkage() {
		CompleteLinkage clusterizer = new CompleteLinkage();
		return clusterizer.clusterize(this.distanceCalculator, this.conjunctionDistance, this.thresholdSimilarityDistance, this.items);
	}

	public TypedTreeNode<T> doCentroidLinkage() {
		CentroidLinkage clusterizer = new CentroidLinkage();
		return clusterizer.clusterize(this.distanceCalculator, this.conjunctionDistance, this.thresholdSimilarityDistance, this.items);
	}

	public DistanceCalculator<T> getDistanceCalculator() {
		return this.distanceCalculator;
	}

	public HierarchicalClusteringBuilder<T> setDistanceCalculator(DistanceCalculator<T> distanceCalculator) {
		this.distanceCalculator = distanceCalculator;
		return this;
	}

	public Double getConjunctionDistance() {
		return this.conjunctionDistance;
	}

	public HierarchicalClusteringBuilder<T> setConjunctionDistance(Double conjunctionDistance) {
		this.conjunctionDistance = conjunctionDistance;
		return this;
	}

	public Double getThresholdSimilarityDistance() {
		return this.thresholdSimilarityDistance;
	}

	public HierarchicalClusteringBuilder<T> setThresholdSimilarityDistance(Double thresholdSimilarityDistance) {
		this.thresholdSimilarityDistance = thresholdSimilarityDistance;
		return this;
	}

	public T[] getItems() {
		return this.items;
	}

	public HierarchicalClusteringBuilder<T> setItems(T... items) {
		this.items = items;
		return this;
	}

	public HierarchicalClusteringBuilder<T> setItems(List<T> items) {
		this.items = items.toArray(this.items);
		return this;
	}

	public static <T> HierarchicalClusteringBuilder<T> newBuilder() {
		return new HierarchicalClusteringBuilder<T>();
	}
}
