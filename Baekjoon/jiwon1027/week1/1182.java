

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int res;
	static int n;
	static int s;
	static int[] data;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String[] input = br.readLine().split(" ");
		
		n = Integer.parseInt(input[0]);
		s = Integer.parseInt(input[1]);

		data = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
				
		dfs(0,0);
		
		System.out.println(res);
		

	}
	
	public static void dfs(int idx, int sum) {
		if (idx >= n)
			return;
		sum += data[idx];
		
		if (sum == s)
			res += 1;
		
		dfs(idx+1,sum);
		dfs(idx+1,sum-data[idx]);

			
	}
	

}
