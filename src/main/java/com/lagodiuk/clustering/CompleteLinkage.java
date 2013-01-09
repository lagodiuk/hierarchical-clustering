package com.lagodiuk.clustering;


public class CompleteLinkage extends Hierarchical {

	@Override
	protected <T> double fastDistance(
			TypedTreeNode<T> clust1, double clust1Dist,
			TypedTreeNode<T> clust2, double clust2Dist) {
		return Math.max(clust1Dist, clust2Dist);
	}

}
