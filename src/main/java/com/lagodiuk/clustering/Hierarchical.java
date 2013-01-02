package com.lagodiuk.clustering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Hierarchical {

	protected abstract <T> double distance(
			TypedTreeNode<T> baseNode,
			TypedTreeNode<T> targetNode,
			Map<T, Map<T, Double>> distances);

	public <T> TypedTreeNode<T> clusterize(
			DistanceCalculator<T> distanceCalculator,
			Double conjunctionDist,
			T... items) {
		Map<T, Map<T, Double>> distances = this.calculateDistances(distanceCalculator, items);

		List<TypedTreeNode<T>> nodes = this.nodesFromItems(items);

		Map<TypedTreeNode<T>, SortedValuesMap<TypedTreeNode<T>, Double>> clusters = this.initialClusters(distances, nodes);

		while (clusters.size() > 1) {
			this.iteration(distances, clusters, conjunctionDist);
		}

		return clusters.keySet().iterator().next();
	}

	public <T extends Distanceable<T>> TypedTreeNode<T> clusterize(
			Double conjunctionDist,
			T... items) {
		Map<T, Map<T, Double>> distances = this.calculateDistances(items);

		List<TypedTreeNode<T>> nodes = this.nodesFromItems(items);

		Map<TypedTreeNode<T>, SortedValuesMap<TypedTreeNode<T>, Double>> clusters = this.initialClusters(distances, nodes);

		while (clusters.size() > 1) {
			this.iteration(distances, clusters, conjunctionDist);
		}

		return clusters.keySet().iterator().next();
	}

	private <T> void iteration(
			Map<T, Map<T, Double>> distances,
			Map<TypedTreeNode<T>, SortedValuesMap<TypedTreeNode<T>, Double>> clusters,
			Double conjunctionDist) {
		TypedTreeNode<T> clust1 = null;
		TypedTreeNode<T> clust2 = null;
		double minDist = Double.MAX_VALUE;
		for (TypedTreeNode<T> base : clusters.keySet()) {
			SortedValuesMap<TypedTreeNode<T>, Double> baseDist = clusters.get(base);

			TypedTreeNode<T> smallestDistNode = baseDist.getKeyWithSmallestValue();

			double currDist = baseDist.get(smallestDistNode);
			if (currDist < minDist) {
				clust1 = base;
				clust2 = smallestDistNode;
				minDist = currDist;
			}
		}
		clusters.remove(clust1);
		clusters.remove(clust2);
		TypedTreeNode<T> comb = this.combine(clust1, clust2, minDist, conjunctionDist);

		SortedValuesMap<TypedTreeNode<T>, Double> combDist = new SortedValuesMap<TypedTreeNode<T>, Double>();
		for (TypedTreeNode<T> base : clusters.keySet()) {
			SortedValuesMap<TypedTreeNode<T>, Double> baseDist = clusters.get(base);

			baseDist.remove(clust1);
			baseDist.remove(clust2);

			double dist = this.distance(base, comb, distances);
			baseDist.put(comb, dist);
			combDist.put(base, dist);
		}
		clusters.put(comb, combDist);
	}

	private <T> TypedTreeNode<T> combine(TypedTreeNode<T> clust1, TypedTreeNode<T> clust2, double dist, Double minDist) {
		TypedTreeNode<T> parent = new TypedTreeNode<T>();
		if ((minDist != null) && (dist <= minDist)) {
			Enumeration<?> nodesEnumeration = clust1.breadthFirstEnumeration();
			for (Object nodeObj : Collections.list(nodesEnumeration)) {
				@SuppressWarnings("unchecked")
				TypedTreeNode<T> node = (TypedTreeNode<T>) nodeObj;
				if (node.isLeaf()) {
					parent.add(node);
				}
			}

			nodesEnumeration = clust2.breadthFirstEnumeration();
			for (Object nodeObj : Collections.list(nodesEnumeration)) {
				@SuppressWarnings("unchecked")
				TypedTreeNode<T> node = (TypedTreeNode<T>) nodeObj;
				if (node.isLeaf()) {
					parent.add(node);
				}
			}
		} else {
			parent.add(clust1);
			parent.add(clust2);
		}
		return parent;
	}

	private <T> Map<TypedTreeNode<T>, SortedValuesMap<TypedTreeNode<T>, Double>> initialClusters(Map<T, Map<T, Double>> distances,
			List<TypedTreeNode<T>> nodes) {
		Map<TypedTreeNode<T>, SortedValuesMap<TypedTreeNode<T>, Double>> clustNodes =
				new LinkedHashMap<TypedTreeNode<T>, SortedValuesMap<TypedTreeNode<T>, Double>>();

		for (int i = 0; i < nodes.size(); i++) {
			TypedTreeNode<T> currNode = nodes.get(i);
			SortedValuesMap<TypedTreeNode<T>, Double> currNodeDist = new SortedValuesMap<TypedTreeNode<T>, Double>();

			for (int j = 0; j < nodes.size(); j++) {
				if (i != j) {
					TypedTreeNode<T> targetNode = nodes.get(j);

					double distance = this.distance(currNode, targetNode, distances);
					currNodeDist.put(targetNode, distance);
				}
			}
			clustNodes.put(currNode, currNodeDist);
		}
		return clustNodes;
	}

	private <T extends Distanceable<T>> Map<T, Map<T, Double>> calculateDistances(T... items) {
		Map<T, Map<T, Double>> distances = new LinkedHashMap<T, Map<T, Double>>();

		for (T currItem : items) {
			Map<T, Double> currItemDistances = new LinkedHashMap<T, Double>();

			for (T targetItem : items) {
				if (currItem != targetItem) {
					double dist = currItem.distance(targetItem);
					currItemDistances.put(targetItem, dist);
				}
			}
			distances.put(currItem, currItemDistances);
		}
		return distances;
	}

	private <T> Map<T, Map<T, Double>> calculateDistances(DistanceCalculator<T> distanceCalculator, T... items) {
		Map<T, Map<T, Double>> distances = new LinkedHashMap<T, Map<T, Double>>();

		for (int i = 0; i < items.length; i++) {
			T currItem = items[i];
			Map<T, Double> currItemDistances = new LinkedHashMap<T, Double>();

			for (int j = 0; j < items.length; j++) {
				if (i != j) {
					T targetItem = items[j];
					double dist = distanceCalculator.distance(currItem, targetItem);
					currItemDistances.put(targetItem, dist);
				}
			}
			distances.put(currItem, currItemDistances);
		}
		return distances;
	}

	private <T> List<TypedTreeNode<T>> nodesFromItems(T... items) {
		List<TypedTreeNode<T>> nodes = new ArrayList<TypedTreeNode<T>>(items.length);
		for (T item : items) {
			nodes.add(new TypedTreeNode<T>(item, false));
		}
		return nodes;
	}
}
