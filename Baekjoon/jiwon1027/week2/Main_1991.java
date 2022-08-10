package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_1991 {
	static StringBuilder sb;
	static Map<String,String[]> map;
	
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		map = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			map.put(input[0], new String[] {input[1],input[2]});
		}	
		

		preorder("A");
		System.out.println();
		
		inorder("A");
		System.out.println();
		
		postorder("A");

		
	}
	
	
	public static void preorder(String root) {		
		if (!root.equals(".")) {
			System.out.print(root);
			preorder(map.get(root)[0]);
			preorder(map.get(root)[1]);
		}
	}

	
	public static void inorder(String root) {
		if (!root.equals(".")) {
			inorder(map.get(root)[0]);
			System.out.print(root);
			inorder(map.get(root)[1]);
		}
	}
	
	public static void postorder(String root) {
		if (!root.equals(".")) {
			postorder(map.get(root)[0]);
			postorder(map.get(root)[1]);
			System.out.print(root);
		}
	}
}
