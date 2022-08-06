import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		
		for(int i = 0; i <= input.length / 2; i++) {
			if(input[i] == '?' && input[input.length - 1 - i] == '?') {
				input[i] = 'a'; input[input.length - 1 - i] ='a';
			}	
			else {
				if(input[i] == '?') {
					input[i] = input[input.length - 1 - i];
				}
				else {
					input[input.length - 1 - i] = input[i];
				}
			}
		}
		for(char c : input) sb.append(c);
		System.out.println(sb);
	}
}
