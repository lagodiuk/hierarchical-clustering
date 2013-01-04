package com.lagodiuk.clustering.examples;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.lagodiuk.clustering.TypedTreeNode;

public class UI {

	public static JFrame simpleVisualize(TypedTreeNode<?> root, int width, int height) {
		JTree tree = new JTree(root);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new JScrollPane(tree), BorderLayout.CENTER);
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
		frame.setSize(width, height);
		// put frame at center of screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return frame;
	}

}
