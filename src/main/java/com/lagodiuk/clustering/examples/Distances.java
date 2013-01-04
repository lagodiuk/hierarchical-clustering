package com.lagodiuk.clustering.examples;

import com.lagodiuk.clustering.DistanceCalculator;

public class Distances {

	private static final DistanceCalculator<Integer> integerDistCalc =
			new DistanceCalculator<Integer>() {
				@Override
				public double distance(Integer base, Integer target) {
					return Math.abs(base - target);
				}
			};

	private static final DistanceCalculator<String> levenshteinDistCalc =
			new DistanceCalculator<String>() {
				@Override
				public double distance(String base, String target) {
					double levenshteinDist = this.levenshteinDistance(base, target);
					return levenshteinDist / ((base.length() + target.length()) - levenshteinDist);
				}

				private int levenshteinDistance(String s1, String s2) {
					return this.dist(s1.toCharArray(), s2.toCharArray());
				}

				private int dist(char[] s1, char[] s2) {
					// distance matrix - to memoize distances between substrings
					// needed to avoid recursion
					int[][] d = new int[s1.length + 1][s2.length + 1];

					// d[i][j] - would contain distance between such substrings:
					// s1.subString(0, i) and s2.subString(0, j)

					for (int i = 0; i < (s1.length + 1); i++) {
						d[i][0] = i;
					}

					for (int j = 0; j < (s2.length + 1); j++) {
						d[0][j] = j;
					}

					for (int i = 1; i < (s1.length + 1); i++) {
						for (int j = 1; j < (s2.length + 1); j++) {
							int d1 = d[i - 1][j] + 1;
							int d2 = d[i][j - 1] + 1;
							int d3 = d[i - 1][j - 1];
							if (s1[i - 1] != s2[j - 1]) {
								d3 += 1;
							}
							d[i][j] = Math.min(Math.min(d1, d2), d3);
						}
					}
					return d[s1.length][s2.length];
				}
			};

	public static DistanceCalculator<Integer> integerDistCalc() {
		return integerDistCalc;
	}

	public static DistanceCalculator<String> levenshteinDistCalc() {
		return levenshteinDistCalc;
	}

}
