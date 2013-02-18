package com.lagodiuk.clustering.examples;

import java.util.Random;

import com.lagodiuk.clustering.HierarchicalClusteringBuilder;
import com.lagodiuk.clustering.TypedTreeNode;

public class StressTest {

	/**
	 * -Xms512m <br/>
	 * -Xmx512m <br/>
	 */
	public static void main(String[] args) {

		TypedTreeNode<Integer> root =
				HierarchicalClusteringBuilder
						.<Integer> newBuilder()
						.setDistanceCalculator(Distances.integerDistCalc())
						.setConjunctionDistance(2.0)
						.setThresholdSimilarityDistance(2.0)
						.setItems(generateData())
						.doSingleLinkage();

		UI.simpleVisualize(root, 300, 400);
	}

	private static Integer[] generateData() {
		Random rnd = new Random();
		Integer[] arr = new Integer[1000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rnd.nextInt(1000);
		}
		return arr;
	}
}
