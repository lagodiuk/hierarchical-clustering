rss-clustering
==============

Hierarchical clustering algorithms: single linkage, complete linkage, centroid linkage

### add maven dependency ###
<ol>
<li> git clone https://github.com/lagodiuk/hierarchical-clustering.git </li>
<li> mvn -f hierarchical-clustering/pom.xml install </li>
</ol>

### the simplest example ###
```java
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.lagodiuk.clustering.DistanceCalculator;
import com.lagodiuk.clustering.HierarchicalClusteringBuilder;
import com.lagodiuk.clustering.TypedTreeNode;

public class Example {

	public static void main(String[] args) {

		TypedTreeNode<Integer> root =
				HierarchicalClusteringBuilder
						.<Integer> newBuilder()
						.setDistanceCalculator(
								new DistanceCalculator<Integer>() {
									@Override
									public double distance(Integer base, Integer target) {
										return Math.abs(base - target);
									}
								})
						.setItems(0, 0, 10, 20, 11, 2, 5, 6, 21)
						.doSingleLinkage();

		display(root, 300, 400);
	}

	public static JFrame display(TypedTreeNode<?> root, int width, int height) {
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
```