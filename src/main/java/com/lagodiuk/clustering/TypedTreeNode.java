package com.lagodiuk.clustering;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;

import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.common.base.Function;

public class TypedTreeNode<T> extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1L;

	private final Function<Object, T> nodeToItem = new Function<Object, T>() {
		@SuppressWarnings("unchecked")
		@Override
		public T apply(Object input) {
			TypedTreeNode<T> node = (TypedTreeNode<T>) input;
			return (T) node.getUserObject();
		}
	};

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

	private Iterable<T> itemsFromNodesEnumeration(Enumeration<?> enumeration) {
		return filter(
				transform(enumerationToIterable(enumeration), this.nodeToItem),
				notNull());
	}

	public static Iterable<Object> enumerationToIterable(final Enumeration<?> enumeration) {
		return new Iterable<Object>() {
			@Override
			public Iterator<Object> iterator() {
				return new Iterator<Object>() {
					@Override
					public boolean hasNext() {
						return enumeration.hasMoreElements();
					}

					@Override
					public Object next() {
						return enumeration.nextElement();
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
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
