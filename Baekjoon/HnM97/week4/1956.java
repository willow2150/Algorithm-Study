import java.io.*;
import java.util.*;
public class Main {
	static final int INF = 9000000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[][] map = new int[V+1][V+1];
		for(int i=1;i<V+1;i++) {
			for(int j=1;j<V+1;j++) {
				if(i==j) map[i][j] = 0;
				else map[i][j] = INF;
			}
		}
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			map[from][to] = weight;
		}
		
		for(int k=1;k<V+1;k++) {
			for(int i=1;i<V+1;i++) {
				for(int j=1;j<V+1;j++) { 
					if(i==j) continue;
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
				}
			}
		}
		
		int minCost = INF;
		for(int i=1;i<V+1;i++) {
			for(int j=1;j<V+1;j++) {
				if(i==j) continue;
                if (map[i][j] != INF && map[j][i] != INF) {
                    minCost = Math.min(minCost, map[i][j] + map[j][i]);
                }
			}
		}
		if(minCost == INF) System.out.println(-1);
		else System.out.println(minCost);

	}
}
