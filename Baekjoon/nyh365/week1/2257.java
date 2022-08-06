import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		int result = 0;
		String s = br.readLine();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == ')'){
				int sum = 0;
				
				while(true) {
					int n = stack.pop();
					if(n == -1) break;
					else{
						sum += n;
					}
				}
				
				stack.add(sum);
			}
			else {
				switch(c) {
				case 'H': stack.add(1); break;
				case 'C': stack.add(12); break;
				case 'O': stack.add(16); break;
				case '(': stack.add(-1); break;
				default : stack.add(stack.pop() * (c -'0'));
				}
			}
		}
		while(!stack.isEmpty()) {
			result += stack.pop();
		}
		System.out.println(result);
	}
}
