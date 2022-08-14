package b17266;
import java.io.*;
import java.util.*;
public class Main {
	static int n;
	static int m;
	static int[] positions;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		positions = new int[m];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for( int i=0;i<m;i++) {
			positions[i] = Integer.parseInt(st.nextToken());
		}
		int maxDist = positions[0];
		for(int i=1;i<m;i++) {
			int dis = positions[i]- positions[i-1];
			if(dis % 2 == 0) {
				maxDist= Math.max(maxDist, (positions[i]- positions[i-1]) / 2);
			} else {
				maxDist= Math.max(maxDist, (positions[i]- positions[i-1]) / 2 + 1); 
			}
			
		}
		maxDist = Math.max(maxDist, n - positions[m-1]);
		System.out.println(maxDist);
	}

}
