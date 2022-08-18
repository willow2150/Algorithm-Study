package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 플로이드 와샬
public class Main_9205_1 {
	static int[][] data, dp;
	static boolean[] visited;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			data = new int[n + 2][2];
			dp = new int[n + 2][n + 2];

			for (int i = 0; i < n + 2; i++) {
				String[] input = br.readLine().split(" ");
				data[i][0] = Integer.parseInt(input[0]);
				data[i][1] = Integer.parseInt(input[1]);
			}

			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < n + 2; j++) {
					dp[i][j] = 100000;
				}
			}

			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < n + 2; j++) {
					if (i == j)
						continue;
					if (distance(data[i][0], data[i][1], data[j][0], data[j][1]) <= 1000)
						dp[i][j] = 1;

				}
			}

			

			for (int k = 0; k < n + 2; k++) {
				for (int i = 0; i < n + 2; i++) {
					for (int j = 0; j < n + 2; j++) {
						if (dp[i][j] > dp[i][k] + dp[k][j])
							dp[i][j] = dp[i][k] + dp[k][j];
					}
				}
			}
			
			
//			System.out.println();
//			for(int[] row:dp)
//				System.out.println(Arrays.toString(row));
//			System.out.println();
			if ((dp[0][n + 1] < 100000))
				System.out.println("happy");
			else
				System.out.println("sad");

		}
	}

	public static int distance(int x, int y, int x1, int y1) {
		return Math.abs(x - x1) + Math.abs(y - y1);
	}

}
