package com.lagodiuk.clustering;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;

public class SortedValuesMap<K, V extends Comparable<V>> implements Map<K, V> {

	private TreeMultimap<V, K> valueToKeys =
			TreeMultimap.create(Ordering.natural(), Ordering.arbitrary());

	private HashMap<K, V> keyToValue = new HashMap<K, V>();

	@Override
	public V put(K key, V value) {
		this.remove(key);
		this.valueToKeys.put(value, key);
		this.keyToValue.put(key, value);
		return value;
	}

	@Override
	public V remove(Object key) {
		V value = this.keyToValue.get(key);
		this.keyToValue.remove(key);
		if (value == null) {
			return value;
		}
		this.valueToKeys.get(value).remove(key);
		return value;
	}

	@Override
	public void clear() {
		this.keyToValue.clear();
		this.valueToKeys.clear();
	}

	@Override
	public Set<K> keySet() {
		return this.keyToValue.keySet();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.keyToValue.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.keyToValue.containsValue(value);
	}

	@Override
	public boolean isEmpty() {
		return this.keyToValue.isEmpty();
	}

	@Override
	public int size() {
		return this.keyToValue.size();
	}

	@Override
	public V get(Object key) {
		return this.keyToValue.get(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> other) {
		for (Entry<? extends K, ? extends V> e : other.entrySet()) {
			this.put(e.getKey(), e.getValue());
		}
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return this.keyToValue.entrySet();
	}

	@Override
	public Collection<V> values() {
		return this.keyToValue.values();
	}

	public K getKeyWithSmallestValue() {
		if (this.valueToKeys.isEmpty()) {
			return null;
		}
		V smallestValue = this.valueToKeys.keySet().first();
		K key = this.valueToKeys.get(smallestValue).first();
		return key;
	}

	public static void main(String[] args) {
		SortedValuesMap<String, Integer> pm = new SortedValuesMap<String, Integer>();
		pm.put("10", 10);
		pm.put("1", 1);
		pm.put("5", 5);
		pm.put("3", 3);
		pm.put("3", 5);
		pm.put("2", 2);
		pm.put("1'", 1);

		pm.remove("3");
		pm.put("4", 4);
		pm.put("3", 30);
		pm.remove("1");
	}
}
