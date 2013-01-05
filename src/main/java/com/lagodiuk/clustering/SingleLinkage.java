package com.lagodiuk.clustering;

import java.util.Map;

public class SingleLinkage extends Hierarchical {

	@Override
	protected <T> double distance(TypedTreeNode<T> baseNode, TypedTreeNode<T> targetNode, Map<T, Map<T, Double>> distances) {
		double dist = Double.MAX_VALUE;
		for (T item1 : baseNode.breadthFirstItems()) {
			for (T item2 : targetNode.breadthFirstItems()) {
				dist = Math.min(distances.get(item1).get(item2), dist);
			}
		}
		return dist;
	}

	@Override
	protected <T> double fastDistance(TypedTreeNode<T> clust1, double clust1Dist, TypedTreeNode<T> clust2, double clust2Dist, TypedTreeNode<T> comb,
			TypedTreeNode<T> target, Map<T, Map<T, Double>> distances) {
		return Math.min(clust1Dist, clust2Dist);
	}

}
