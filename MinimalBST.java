import java.util.ArrayList;
import java.util.List;

public class MinimalBST {

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

	public List<Integer> getLeftElements(List<Integer> input) {
		List<Integer> left = new ArrayList<Integer>();
		for (int i = 0; i < input.size() / 2; i++) {
			int temp = input.get(i);
			left.add(temp);
		}
		return left;
	}

	public List<Integer> getRightElements(List<Integer> input) {
		List<Integer> right = new ArrayList<Integer>();
		for (int i = input.size() / 2 + 1; i < input.size(); i++) {
			int temp = input.get(i);
			right.add(temp);
		}
		return right;
	}

	public static void main(String args[]) {

		List<Integer> input = new ArrayList<Integer>();

//		input.add(0);
		input.add(1);
		input.add(2);
		input.add(3);
		input.add(4);
		input.add(5);
		input.add(6);
		input.add(7);
		input.add(100);

		MinimalBST m = new MinimalBST();
//		System.out.println(m.getMinimalBST(input));
		TreeExample.printBinaryTree(m.getMinimalBST(input), 0);

	}
}

class TreeExample {
	int data;
	TreeExample left;
	TreeExample right;

	public String toString() {
		return "data : " + this.data + " " + "left: " + this.left + "right: "
				+ this.right + "\n";
	}

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
}
