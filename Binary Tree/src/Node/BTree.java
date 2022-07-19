package Node;

public class BTree {
	
	private node root;
	private node currentNode;
	
	public BTree() {
		root = null;
	}
	
	public boolean search (int data) {
		return search(root, data);
	}
	
	private boolean search (node Node, int data) {
		if (Node.getData() == data)
			return true;
		if (Node.getLeft() != null)
			if (search(Node.getLeft(),data))
				return true;
			if (Node.getRight() != null)
				if (search(Node.getLeft(),data))
					return true;
			return false;
	}
	
	public void printInOrder() {
		root.printInorder(root);
	}
	
	public void printPreOrder() {
		root.printPreorder(root);
	}
	
	public void printPostOrder() {
		root.printPostorder(root);
	}
	
	public node getRoot() {
		return root;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public int countNodes() {
		return countNodes(root);
	}
	
	private int countNodes(node Node) {
		int count = 1;
		if (Node == null) {
			return  0;
		} else {
			count += countNodes(Node.getLeft());
			count += countNodes(Node.getRight());
			return count;
		}
	}
	
	public void print() {
		root.print();
	}
	
	public node getCurrent() {
		return currentNode;
	}
	
	public void setCurrent(node Node) {
		this.currentNode = Node;
	}
	
	public void setRoot(node root) {
		this.root = root;
	}

}
