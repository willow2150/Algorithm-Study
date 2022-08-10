package b1158;
import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n =Integer.parseInt(st.nextToken());
		int k =Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] ans = new int[n];
		for(int i=1;i<=n;i++) queue.add(i);

		for(int i=0;i<n;i++) {
			for(int j=0;j<k;j++) {
				if(j==k-1) ans[i] = queue.poll();
				else queue.add(queue.poll());
			}
		}
		sb.append("<");
		for(int i=0;i<n;i++) {
			if(i==n-1) sb.append(ans[i] + ">");
			else sb.append(ans[i] + ", ");
		}
		System.out.println(sb.toString());
		

	}

}
