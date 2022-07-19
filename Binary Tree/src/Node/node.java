package Node;

public class node {

	int data;
	node left;
	node right;
	
	public node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
	
	public void setLeft(node Node) {
		if (left == null)
			left = Node;
	}
	
	public void setRight(node Node) {
		if (right == null)
			right = Node;
	}
	
	public node getLeft() {
		return left;
	}
	
	public node getRight() {
		return right;
	}
	
	public int getData() {
		return data;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	// end of node class
	
	
	void printPreorder(node Node) {
		if (Node == null)
			return;
		
		System.out.print(Node.data + " ");
		printPreorder(Node.left);
		printPreorder(Node.right);
	}
	
	void printPostorder(node Node) {
		if (Node == null)
			return;
		
		printPostorder(Node.left);
		printPostorder(Node.right);
		System.out.print(Node.data + " ");
	}
	
	void printInorder(node Node) {
		if (Node == null)
			return;
		
		printInorder(Node.left);
		System.out.print(Node.data + " ");
		printInorder(Node.right);
	}
	// end of tree traversal (in-pre-post order)
	
	
	public String print() {
		return this.print("",true,"");
	}
	
	public String print(String prefix, boolean isTail, String sb) {
		if (right != null) {
			right.print(prefix + (isTail ? "|  " : "  "), false, sb);
		}
		System.out.println( prefix+(isTail ? "\\-- " : "/-- ")+data);
		if (left != null) {
			left.print(prefix+(isTail ? "  " : "|  "), true, sb);
		}
		return sb;
	}
	
}

