import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.lagodiuk.clustering.DistanceCalculator;
import com.lagodiuk.clustering.Hierarchical;
import com.lagodiuk.clustering.SingleLink;
import com.lagodiuk.clustering.TypedTreeNode;

public class CopyOfTest {

	public static void main(String[] args) {
		Hierarchical clusterizer = new SingleLink();
		TypedTreeNode<Integer> root = clusterizer.clusterize(
				new DistanceCalculator<Integer>() {
					@Override
					public double distance(Integer base, Integer target) {
						return Math.abs(base - target);
					}
				},
				0, 0, 2, 3, 10, 11, 16, 17, 171, 175, 200, 205, 206, 208, 209);

		System.out.println(root.prettyPrint());

		JTree tree = new JTree(root);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new JScrollPane(tree), BorderLayout.CENTER);
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
		frame.setSize(275, 350);
		frame.setVisible(true);
	}
}
