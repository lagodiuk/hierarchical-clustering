package com.lagodiuk.clustering.examples;

import com.lagodiuk.clustering.HierarchicalClusteringBuilder;
import com.lagodiuk.clustering.TypedTreeNode;

public class TestCompleteLinkage {

	public static void main(String[] args) {

		TypedTreeNode<Integer> root =
				HierarchicalClusteringBuilder
						.<Integer> newBuilder()
						.setDistanceCalculator(Distances.integerDistCalc())
						.setConjunctionDistance(5.0)
						.setThresholdSimilarityDistance(5.0)
						.setItems(0, 0, 2, 3, 10, 11, 16, 17, 171, 175, 200, 205, 206, 208, 209)
						.doCompleteLinkage();

		UI.simpleVisualize(root, 300, 400);
	}
}
