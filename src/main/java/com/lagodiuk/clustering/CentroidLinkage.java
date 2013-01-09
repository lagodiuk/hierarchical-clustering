package com.lagodiuk.clustering;


public class CentroidLinkage extends Hierarchical {

	@Override
	protected <T> double fastDistance(
			TypedTreeNode<T> clust1, double clust1Dist,
			TypedTreeNode<T> clust2, double clust2Dist) {
		double clust1ItemsCount = clust1.itemsCount();
		double clust2ItemsCount = clust2.itemsCount();
		double dist =
				((clust1Dist * clust1ItemsCount) + (clust2Dist * clust2ItemsCount))
						/ (clust1ItemsCount + clust2ItemsCount);
		return dist;
	}

}
