package com.lagodiuk.clustering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class TypedTreeNode<T> extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1L;

	public TypedTreeNode() {
		super();
	}

	public TypedTreeNode(T item) {
		super(item);
	}

	public TypedTreeNode(T item, boolean allowsChildren) {
		super(item, allowsChildren);
	}

	public Iterable<T> breadthFirstItems() {
		return this.itemsFromNodesEnumeration(this.breadthFirstEnumeration());
	}

	public Iterable<T> depthFirstItems() {
		return this.itemsFromNodesEnumeration(this.depthFirstEnumeration());
	}

	@SuppressWarnings("unchecked")
	private Iterable<T> itemsFromNodesEnumeration(final Enumeration<?> enumeration) {
		List<?> nodes = Collections.list(enumeration);
		List<T> items = new ArrayList<T>(nodes.size());
		for (Object node : nodes) {
			T item = (T) ((TypedTreeNode<T>) node).getUserObject();
			if (item != null) {
				items.add(item);
			}
		}
		return items;
	}

	@SuppressWarnings("unchecked")
	public String prettyPrint() {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		Object item = this.getUserObject();
		if (item != null) {
			sb.append(item);
			sb.append(", ");
		}
		for (int i = 0; i < this.getChildCount(); i++) {
			sb.append(((TypedTreeNode<T>) this.getChildAt(i)).prettyPrint());
			sb.append(", ");
		}
		if (sb.length() >= 3) {
			sb.setLength(sb.length() - 2);
		}
		sb.append('}');
		return sb.toString();
	}
}
