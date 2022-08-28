package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_6497 {
	static int n, m;
	static int[][] data;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			if (n==0 && m==0)
				break;
			
			data = new int[m][3];
			int total = 0;

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 3; j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
				}
				total += data[i][2];
			}

			int temp = 0;

			// make
			parent = new int[n + 1];
			for (int i = 1; i < n + 1; i++)
				parent[i] = i;

			// cost 오름차순
			Arrays.sort(data, (o1, o2) -> (o1[2] - o2[2]));

			int cnt = 0;

			// 크루스칼
			for (int[] row : data) {
				if (cnt == n - 1)
					break;

				if (union(row[0], row[1])) {
					temp += row[2];
					cnt++;
				}
			}

			System.out.println(total - temp);
		}
	}

	public static int find(int x) {
		if (parent[x] != x)
			parent[x] = find(parent[x]);
		return parent[x];
	}

	public static boolean union(int x, int y) {
		int rootA = find(x);
		int rootB = find(y);

		if (rootA == rootB)
			return false;

		if (rootA < rootB)
			parent[rootB] = rootA;
		else
			parent[rootA] = rootB;

		return true;

	}

}
