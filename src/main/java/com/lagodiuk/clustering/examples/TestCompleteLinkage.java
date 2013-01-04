package com.lagodiuk.clustering.examples;

import com.lagodiuk.clustering.CompleteLinkage;
import com.lagodiuk.clustering.Hierarchical;
import com.lagodiuk.clustering.TypedTreeNode;

public class TestCompleteLinkage {

	public static void main(String[] args) {
		Hierarchical clusterizer = new CompleteLinkage();
		TypedTreeNode<Integer> root =
				clusterizer.clusterize(
						Distances.integerDistCalc(),
						5.0,
						5.0,
						0, 0, 2, 3, 10, 11, 16, 17, 171, 175, 200, 205, 206, 208, 209);

		UI.simpleVisualize(root, 300, 400);
	}
}
