import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.lagodiuk.clustering.Hierarchical;
import com.lagodiuk.clustering.SingleLinkage;
import com.lagodiuk.clustering.TypedTreeNode;

public class Test {

	public static void main(String[] args) {
		Hierarchical clusterizer = new SingleLinkage();
		TypedTreeNode<Num> root = clusterizer.clusterize(
				null,
				null,
				new Num(1),
				new Num(2),
				new Num(10),
				new Num(11),
				new Num(4),
				new Num(5),
				new Num(16),
				new Num(17),
				new Num(171),
				new Num(175),
				new Num(201),
				new Num(210),
				new Num(212),
				new Num(213));
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
