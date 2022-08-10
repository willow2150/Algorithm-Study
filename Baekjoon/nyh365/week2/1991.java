import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Tree tree = new Tree();
		for(int i = 0; i < N; i++) {
			char[] c = br.readLine().replace(" ", "").toCharArray();
			tree.add(c[0], c[1], c[2]);
		}
		tree.preorder(tree.root);
		System.out.println();
		tree.inorder(tree.root);
		System.out.println();
		tree.postorder(tree.root);
	}
}
class Node{
	char data;
	Node left, right;
	
	public Node(char data) {
		this.data = data;
	}
}

class Tree{
	Node root;
	public void add(char current, char left, char right) {
		if(root == null) {
			root = new Node(current);
			if(left != '.')
				root.left = new Node(left);
			if(right != '.')
				root.right = new Node(right);
		}	
		else {
			findLocation(root, current, left, right);
		}
	}
	public void findLocation(Node current, char target, char left, char right) {
		if(current == null) return;
		if(current.data == target) {
			if(left != '.')
				current.left = new Node(left);
			if(right != '.')
				current.right = new Node(right);
			return;
		}
		else{
			findLocation(current.left, target, left, right);
			findLocation(current.right, target, left, right);
		}
	}
	public void preorder(Node root) {
		if(root == null) {
			return;
		}
		System.out.print(root.data);
		preorder(root.left);
		preorder(root.right);
			
	}
	public void inorder(Node root) {
		if(root == null) {
			return;
		}
		inorder(root.left);
		System.out.print(root.data);
		inorder(root.right);
	}
	public void postorder(Node root) {
		if(root == null) {
			return;
		}
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data);
	}
}
