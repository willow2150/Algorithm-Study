package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_2583 {
	static boolean[][] board;
	static int m,n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		m = Integer.parseInt(input[0]);
		n = Integer.parseInt(input[1]);
		int k = Integer.parseInt(input[2]);

		board = new boolean[m][n];

		for (int tc = 0; tc < k; tc++) {
			input = br.readLine().split(" ");
			int x1 = Integer.parseInt(input[0]);
			int y1 = Integer.parseInt(input[1]);
			int x2 = Integer.parseInt(input[2]);
			int y2 = Integer.parseInt(input[3]);

			// 왼쪽 아래가 0,0이기 때문에 조절 해줘야됨
			for (int i = m - y2; i < m - y1; i++) {
				for (int j = x1; j < x2; j++) {
					board[i][j] = true;
				}
			}
		}
		
		
		List<Integer> res = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!board[i][j])
					res.add(bfs(i,j));
			}
		}
		
		System.out.println(res.size());
		res.sort(null);
		for (int x:res)
			System.out.print(x + " ");

	}

	
	public static int bfs(int x, int y) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x,y});
		board[x][y] = true;
		
		int temp = 1;
		while(!queue.isEmpty()) {
			int[] poll_data = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = poll_data[0] + dx[i];
				int ny = poll_data[1] + dy[i];
				if ((0<=nx) && (0<=ny) && (nx<m) && (ny<n) && (!board[nx][ny])) {
					queue.add(new int[] {nx,ny});
					board[nx][ny] = true;
					temp++;
				}
			
			}


		}
		
		return temp;
		
		
	}
}
