package com.lagodiuk.clustering;

import java.util.Map;

public class SingleLink extends Hierarchical {

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

}
