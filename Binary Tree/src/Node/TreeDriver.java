package Node;

public class TreeDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Creating Tree");
		BTree tree = new BTree();
		
		System.out.println("Count nodes in empty tree");
		System.out.println(tree.countNodes());
		
		System.out.println("Create nodes with data 1");
		node root = new node(1);
		
		System.out.println("Set node as root");
		tree.setRoot(root);
		
		System.out.println("Count nodes in tree with only root added");
		System.out.println(tree.countNodes());
		
		node node2 = new node(2);
		node node3 = new node(3);
		node node4 = new node(4);
		node node5 = new node(5);
		node node6 = new node(6);
		node node7 = new node(7);
		
		root.setLeft(node2);
		node2.setLeft(node4);
		node2.setRight(node5);
		node5.setLeft(node7);
		root.setRight(node3);
		node3.setRight(node6);
		
		System.out.println("Set the currennt node to be the root");
		tree.setCurrent(tree.getRoot());
		
		System.out.println("Display the current node");
		System.out.println(tree.getCurrent().getData());
		
		node currentNode = tree.getCurrent();
		
		System.out.println("Count nodes in tree with 7 nodes added");
		System.out.println(tree.countNodes());
		
		System.out.println("In Order Print");
		tree.printInOrder();

		System.out.println("\nPre order print");
		tree.printPreOrder();
		
		System.out.println("\nPost order print");
		tree.printPostOrder();
		
		System.out.println("\nDislay the nodes as tree diagram");
		tree.print();
	}

}
