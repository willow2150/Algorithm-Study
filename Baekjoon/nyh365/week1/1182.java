import java.io.*;
import java.util.*;

public class Main {
	static int result = 0;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		func1(arr, S, 0, 0);
		if(S == 0) result--;
		System.out.println(result);
		
		
	}
	public static void func1(int[] arr,int S, int current, int sum) {
		if(current == arr.length) {
			if(sum == S) {
				result++;
			}
				return;
		}
		func1(arr, S, current + 1, sum);
		func1(arr, S, current + 1, sum + arr[current]);
	}
}
