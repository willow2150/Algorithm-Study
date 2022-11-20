import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] data = new int[201];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				if (start > end) {
					int temp = start;
					start = end;
					end = temp;
				}
					
				if (start == end)
					continue;
				
				if ((start)%2==1)
					start++;
				
				if ((end)%2==1)
					end++;	
				
				for (int j = start/2; j < (end/2)+1; j++) {
					data[j]+=1;
				}
			}
			

			
			int res = 0;

			for (int j = 0; j < data.length; j++) {
				res = Math.max(res, data[j]);
			}
			
			System.out.println("#"+tc+" "+res);
			
		}
		
		
		
	}

}
