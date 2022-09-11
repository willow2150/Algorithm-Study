import java.io.*;
import java.util.*;

public class Main {
	static int[] result = new int[26], dictionary = new int[26], puzzle = new int[26];
	static int N;
	static StringBuilder sb = new StringBuilder();;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			func1(1, 1, "1");
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	public static void func1(int current, int sum, String result) {
		if(current == N) {
			if(sum == 0) {
				sb.append(result).append("\n");
			}
			return;
		}
		//공백(숫자 이어붙이기)
		if(result.length() >= 2) {
      //예를 들어 result가 1+2+3일 때 1+2+3 4의 형식으로 만들어준다. 
			if(result.charAt(result.length()-2) == '-') {
				func1(current + 1, sum + current - (10*current + current + 1), result + " " + (current + 1));
			}
      //예를 들어 result가 1+2-3일 때 1+2-3 4의 형식으로 만들어준다.
			else if(result.charAt(result.length()-2) == '+'){
				func1(current + 1, sum - current + (10*current + current + 1), result + " " + (current + 1));
			}
		}else {
			func1(current + 1, sum - current + (10*current + current + 1), result + " " + (current + 1));
		}
		//더하기
		func1(current + 1, sum + (current + 1), result + "+" + (current + 1));
		
		//빼기
		func1(current + 1, sum - (current + 1), result + "-" + (current + 1));
		
	}
}
