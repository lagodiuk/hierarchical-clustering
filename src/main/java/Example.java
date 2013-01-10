import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.lagodiuk.clustering.DistanceCalculator;
import com.lagodiuk.clustering.Hierarchical;
import com.lagodiuk.clustering.SingleLinkage;
import com.lagodiuk.clustering.TypedTreeNode;

public class Example {

	public static void main(String[] args) {
		DistanceCalculator<Integer> distCalc =
				new DistanceCalculator<Integer>() {
					@Override
					public double distance(Integer base, Integer target) {
						return Math.abs(base - target);
					}
				};

		Hierarchical clusterizer = new SingleLinkage();

		TypedTreeNode<Integer> root =
				clusterizer.clusterize(
						distCalc,
						null,
						null,
						0, 0, 10, 20, 11, 2, 5, 6, 21);

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
