package com.lagodiuk.clustering;

import java.util.Map;

public class CentroidLinkage extends Hierarchical {

	@Override
	protected <T> double distance(TypedTreeNode<T> baseNode, TypedTreeNode<T> targetNode, Map<T, Map<T, Double>> distances) {
		double dist = 0;
		int itemsCount = 1;
		for (T item1 : baseNode.breadthFirstItems()) {
			for (T item2 : targetNode.breadthFirstItems()) {
				dist += distances.get(item1).get(item2);
				itemsCount += 1;
			}
			itemsCount += 1;
		}
		return dist / itemsCount;
	}

}
