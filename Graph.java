import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class Node {

	int data;
	List<Node> adj;
	boolean visited;

	Node(int data) {
		this.data = data;
		adj = new ArrayList<Node>();
		visited = false;
	}

	Node() {

	}

	public String toString() {
		return "data: " + this.data + " visited = " + this.visited;
	}


}

public class Graph {
	private Set<Node> nodes;
	
	Graph(){
		nodes = new HashSet<Node>();
	}

	public void addEdge(Node a, Node b) {

		a.adj.add(b);
		this.nodes.add(a);
		this.nodes.add(b);

	}

	public String toString() {
		String s = "";
		for(Node node : this.nodes)
		{
			s = s + node;
		}
		return s;
	}

	public void reinitializeGraph()
	{
		for(Node node : this.nodes)
		{
			node.visited = false;
		}
	}
	
	
	public void bfs(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		node.visited = true;
		queue.add(node);
		while (queue.size() > 0) {
			Node currentNode = queue.poll();
			System.out.print(currentNode.data + " ");
			Iterator<Node> nodeIterator = currentNode.adj.iterator();
			while (nodeIterator.hasNext()) {
				Node n = nodeIterator.next();
				if (!(n.visited)) {
					queue.add(n);
					n.visited = true;
				}

			}
		}
		
		this.reinitializeGraph();
		
	}
	
	public void dfs(Node node) {
		Stack<Node> stack = new Stack<Node>(); 
		node.visited = true;
		stack.push(node);
		
		while(stack.size() > 0)
		{
			Node currentNode = stack.pop();
			System.out.print(currentNode.data + " ");
			Iterator<Node> nodeIterator = currentNode.adj.iterator();
			while(nodeIterator.hasNext())
			{
				Node n = nodeIterator.next();
				if(!(n.visited))
				{
					stack.push(n);
					n.visited = true;

				}
			}
		}
		this.reinitializeGraph();
	}

	public static void main(String args[]) {
		System.out.println("hi");
		Graph g = new Graph();

		Node node2 = new Node(2);
		Node node0 = new Node(0);
		Node node1 = new Node(1);
		Node node3 = new Node(3);
		
		g.addEdge(node0, node1);
		g.addEdge(node1, node2);
		g.addEdge(node2, node0);
		g.addEdge(node2, node3);
		g.addEdge(node3, node3);

		
		System.out.println("Following is Breadth First Traversal "
				+ "(starting from vertex 2)");

		g.bfs(node2);
		
		System.out.println("\nFollowing is Depth First Traversal "
				+ "(starting from vertex 2)");

		g.dfs(node2);
		
//		System.out.println(g + "graoh");
	}
}
