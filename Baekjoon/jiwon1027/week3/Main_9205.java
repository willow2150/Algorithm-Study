package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 저번주에 풀었던 주유소 문제처럼 내가 지나온 길에 편의점이 있었다면 
 * 그걸 기억해둔다음에 부족할떄마다 써먹는 방식으로 하려고 했음
 * but, 내가 지나온 길이 편의점인지 아닌지 확인하기 위해서 board 전체에 대해
 * 참조 해야하는데 범위가 너무 커서 안됨
 * 
 * 그냥 지금 맥주로 갈 수있으면 return, 안되면 편의점 들렸다가 다시 가는 dfs, bfs로 풀어야될 것 같음
 * 
 * 
 * */
public class Main_9205 {
	static int[][] store;
	static boolean[] visited;
	static int n, home_x, home_y, fest_x, fest_y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());

			String[] input = br.readLine().split(" ");
			home_x = Integer.parseInt(input[0]);
			home_y = Integer.parseInt(input[1]);

			store = new int[n][2];
			visited = new boolean[n];
			for (int i = 0; i < n; i++) {
				input = br.readLine().split(" ");
				store[i][0] = Integer.parseInt(input[0]);
				store[i][1] = Integer.parseInt(input[1]);
			}
			input = br.readLine().split(" ");
			fest_x = Integer.parseInt(input[0]);
			fest_y = Integer.parseInt(input[1]);

			if (dfs(home_x, home_y,false))
				System.out.println("happy");
			else
				System.out.println("sad");

		}
	}

	public static int distance(int x, int y, int x1, int y1) {
		return Math.abs(x - x1) + Math.abs(y - y1);
	}

	public static boolean dfs(int x, int y,boolean res) {
		if (distance(x, y, fest_x, fest_y) <= 1000)
			return true;
		for (int i = 0; i < n; i++) {
			if (distance(x, y, store[i][0], store[i][1]) <= 1000) {
				if (!visited[i]) {
					visited[i] = true;
					res = dfs(store[i][0], store[i][1],res);
				}
			}

		}
		return res;
		

	}

}
