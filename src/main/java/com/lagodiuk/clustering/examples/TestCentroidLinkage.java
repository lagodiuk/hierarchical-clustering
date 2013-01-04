package com.lagodiuk.clustering.examples;

import com.lagodiuk.clustering.CentroidLinkage;
import com.lagodiuk.clustering.Hierarchical;
import com.lagodiuk.clustering.TypedTreeNode;

public class TestCentroidLinkage {

	public static void main(String[] args) {
		Hierarchical clusterizer = new CentroidLinkage();
		TypedTreeNode<String> root =
				clusterizer.clusterize(
						Distances.levenshteinDistCalc(),
						0.3,
						0.6,
						strings());

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
