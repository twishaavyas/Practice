import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class MinimalBST {

	// BST with minimum height
	public TreeExample getMinimalBST(List<Integer> input) {

		TreeExample output = new TreeExample();
		if (input.size() > 1) {
			int mid = input.get(input.size() / 2);
			output.data = mid;
			output.left = getMinimalBST(this.getLeftElements(input)); // 12
			output.right = getMinimalBST(this.getRightElements(input)); // 45
		} else if (input.size() == 1) {
			output.data = input.get(0);
		}
		return output;
	}

	// helper method
	private List<Integer> getLeftElements(List<Integer> input) {
		List<Integer> left = new ArrayList<Integer>();
		for (int i = 0; i < input.size() / 2; i++) {
			int temp = input.get(i);
			left.add(temp);
		}
		return left;
	}

	// helper method
	private List<Integer> getRightElements(List<Integer> input) {
		List<Integer> right = new ArrayList<Integer>();
		for (int i = input.size() / 2 + 1; i < input.size(); i++) {
			int temp = input.get(i);
			right.add(temp);
		}
		return right;
	}

}

public class TreeExample {
	int data;
	TreeExample left;
	TreeExample right;

	//constructor with no params
	TreeExample() {
		data = 9999999;
	}

	//constructor with one parameter
	TreeExample(int data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return this.data + " ";
	}

	// prints the graphical form of the binary tree starting from the root
	public static void printBinaryTree(TreeExample root, int level) {
		if (root == null)
			return;
		printBinaryTree(root.right, level + 1);
		if (level != 0) {
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			System.out.println("|-------" + root.data);
		} else
			System.out.println(root.data);
		printBinaryTree(root.left, level + 1);
	}

	// Breadth first tree traversal
	public void treeBFS() {
		int count = 0;
		LinkedList<TreeExample> child = new LinkedList<TreeExample>();
		LinkedList<TreeExample> parent = new LinkedList<TreeExample>();

		child.add(this);
		System.out.println(child + " " + count++);
		parent.addAll(child);
		child.clear();

		while (parent.size() > 0) {

			for (TreeExample tr : parent) {

				if (tr.left != null) {
					child.add(tr.left);
				}

				if (tr.right != null) {
					child.add(tr.right);
				}
			}
			System.out.println(child + " " + count++);
			parent.clear();
			parent.addAll(child);
			child.clear();
		}
	}

	// Check if a given BST is balanced
	public boolean isBalancedBST() {

		if (this.checkIfBalanced())
			return true;
		else if (this.right != null && this.left != null)
			return (this.right.isBalancedBST() && this.left.isBalancedBST());
		else
			return false;

	}

	//helper method
	private boolean checkIfBalanced() {
		return (this.hasNoChildren())
				|| (this.right == null && this.left.hasNoChildren())
				|| (this.left == null && this.right.hasNoChildren());
	}
	
	//helper method
	private boolean hasNoChildren()
	{
		return (this.right == null && this.left == null);
	}
	
	public static void main(String args[]) {

		List<Integer> input = new ArrayList<Integer>();

		input.add(1);
		input.add(2);
		input.add(3);
		input.add(4);
		input.add(5);
		input.add(6);
		input.add(7);
		input.add(100);

		MinimalBST m = new MinimalBST();
		// System.out.println(m.getMinimalBST(input));
		printBinaryTree(m.getMinimalBST(input), 0);
//		m.getMinimalBST(input).treeBFS();
//		System.out.println(m.getMinimalBST(input).isBalancedBST());
		
		TreeExample t1 =  new TreeExample(1);
		TreeExample t2 =  new TreeExample(2);
		TreeExample t3 =  new TreeExample(3);
		TreeExample t4 =  new TreeExample(4);
		TreeExample t5 =  new TreeExample(5);
		
		
		t1.right = t2;
		t1.left = t3;
		t2.left = t4;
		t3.left = t5;
		
		System.out.println(t1.isBalancedBST());
		printBinaryTree(t1, 0);
		
		

	}

}
