package com.lagodiuk.clustering;

import java.util.Map;

public class CentroidLinkage extends Hierarchical {

	@Override
	protected <T> double distance(TypedTreeNode<T> baseNode, TypedTreeNode<T> targetNode, Map<T, Map<T, Double>> distances) {
		double dist = 0;
		int itemsCount = 0;
		for (T item1 : baseNode.breadthFirstItems()) {
			for (T item2 : targetNode.breadthFirstItems()) {
				dist += distances.get(item1).get(item2);
				itemsCount += 1;
			}
		}
		return dist / itemsCount;
	}

	@Override
	protected <T> double fastDistance(
			TypedTreeNode<T> clust1, double clust1Dist,
			TypedTreeNode<T> clust2, double clust2Dist,
			TypedTreeNode<T> comb, TypedTreeNode<T> target, Map<T, Map<T, Double>> distances) {
		double clust1ItemsCount = clust1.itemsCount();
		double clust2ItemsCount = clust2.itemsCount();
		double dist =
				((clust1Dist * clust1ItemsCount) + (clust2Dist * clust2ItemsCount))
						/ (clust1ItemsCount + clust2ItemsCount);
		return dist;
	}

}
