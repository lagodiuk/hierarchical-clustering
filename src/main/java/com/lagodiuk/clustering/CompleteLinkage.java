package com.lagodiuk.clustering;

import java.util.Map;

public class CompleteLinkage extends Hierarchical {

	@Override
	protected <T> double distance(TypedTreeNode<T> baseNode, TypedTreeNode<T> targetNode, Map<T, Map<T, Double>> distances) {
		double dist = -1;
		for (T item1 : baseNode.breadthFirstItems()) {
			for (T item2 : targetNode.breadthFirstItems()) {
				dist = Math.max(distances.get(item1).get(item2), dist);
			}
		}
		return dist;
	}

	@Override
	protected <T> double fastDistance(TypedTreeNode<T> clust1, double clust1Dist, TypedTreeNode<T> clust2, double clust2Dist, TypedTreeNode<T> comb,
			TypedTreeNode<T> target, Map<T, Map<T, Double>> distances) {
		return Math.max(clust1Dist, clust2Dist);
	}

}
