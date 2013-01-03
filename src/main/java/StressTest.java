import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.lagodiuk.clustering.DistanceCalculator;
import com.lagodiuk.clustering.Hierarchical;
import com.lagodiuk.clustering.SingleLinkage;
import com.lagodiuk.clustering.TypedTreeNode;

public class StressTest {

	/**
	 * -Xms512m <br/>
	 * -Xmx512m <br/>
	 */
	public static void main(String[] args) {
		Random rnd = new Random();
		Integer[] arr = new Integer[1000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rnd.nextInt(1000);
		}

		Hierarchical clusterizer = new SingleLinkage();
		TypedTreeNode<Integer> root = clusterizer.clusterize(
				new DistanceCalculator<Integer>() {
					@Override
					public double distance(Integer base, Integer target) {
						return Math.abs(base - target);
					}
				},
				2.0,
				2.0,
				arr);

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
