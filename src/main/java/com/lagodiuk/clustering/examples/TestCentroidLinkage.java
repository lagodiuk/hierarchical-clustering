package com.lagodiuk.clustering.examples;

import com.lagodiuk.clustering.HierarchicalClusteringBuilder;
import com.lagodiuk.clustering.TypedTreeNode;

public class TestCentroidLinkage {

	public static void main(String[] args) {
		TypedTreeNode<String> root =
				HierarchicalClusteringBuilder
						.<String> newBuilder()
						.setDistanceCalculator(Distances.levenshteinDistCalc())
						.setConjunctionDistance(0.3)
						.setThresholdSimilarityDistance(0.3)
						.setItems(strings())
						.doCentroidLinkage();

		UI.simpleVisualize(root, 300, 400);
	}

	private static String[] strings() {
		return new String[] {
				"apple",
				"apples",
				"Java",
				"JavaEE",
				"class",
				"classes",
				"glass",
				"glasses",
				"Apple",
				"application",
				"JavaSE",
				"auto",
				"automatic",
				"automatical" };
	}
}
