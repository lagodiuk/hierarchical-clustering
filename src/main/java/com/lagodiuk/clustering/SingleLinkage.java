package com.lagodiuk.clustering;


public class SingleLinkage extends Hierarchical {

	@Override
	protected <T> double fastDistance(
			TypedTreeNode<T> clust1, double clust1Dist,
			TypedTreeNode<T> clust2, double clust2Dist) {
		return Math.min(clust1Dist, clust2Dist);
	}

}
