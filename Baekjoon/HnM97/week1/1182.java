package b1182;
import java.io.*;
import java.util.*;
public class Main {
	static int n,s;
	static int[] arr = new int[21];
	static int ans = 0;
	static int pick =0;
	static boolean[] visited = new boolean[21];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());	
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		func(0,0);
		if(s==0) ans -=1;
		System.out.println(ans);
		
	}
	public static void func(int cnt,int total) {

		if (cnt == n) {
			if(total == s) {
				ans++;
			}
			return;
		}

		func(cnt+1, total+arr[cnt]);

		func(cnt+1, total);
		
	}
}
