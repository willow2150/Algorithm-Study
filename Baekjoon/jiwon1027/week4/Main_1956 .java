package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1956 {
    static final int INF = 99999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int[][] data = new int[V + 1][V + 1];

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i != j) {
					data[i][j] = INF;
				}
			}
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			data[a][b] = c;
		}

		// 플로이드 와샬 알고리즘 수행
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (i == j) {
						continue;
					}
					if (data[i][j] > data[i][k] + data[k][j]) {
						data[i][j] = data[i][k] + data[k][j];
					}
				}
			}
		}

		int res = INF;
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j) 
					continue;
				
				if (data[i][j] != INF && data[j][i] != INF) {
					res = Math.min(res, data[i][j] + data[j][i]);
				}
			}
		}

		System.out.println((res == INF) ? -1 : res);
		
		
		

	}

}
