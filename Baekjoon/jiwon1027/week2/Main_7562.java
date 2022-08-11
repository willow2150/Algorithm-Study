package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_7562 {
	static int[][] board;
	static int end_x, end_y, n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			int start_x = Integer.parseInt(input[0]);
			int start_y = Integer.parseInt(input[1]);

			input = br.readLine().split(" ");
			end_x = Integer.parseInt(input[0]);
			end_y = Integer.parseInt(input[1]);

			board = new int[n][n];
			System.out.println(bfs(start_x, start_y));
		}

	}

	public static int bfs(int x, int y) {
		int[] dx = { -1, 1, 2, 2, 1, -1, -2, -2 };
		int[] dy = { 2, 2, 1, -1, -2, -2, -1, 1 };

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x, y });
		board[x][y] = 1;

		while (!queue.isEmpty()) {
			int[] poll_data = queue.poll();
			x = poll_data[0];
			y = poll_data[1];

			if ((x == end_x) && (y == end_y))
				return board[end_x][end_y] - 1;
			
			//단순히 res++하는게 아니라 값을 기억해두었다가 +1해줘야함
			
			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if ((0 <= nx) && (0 <= ny) && (nx < n) && (ny < n) && (board[nx][ny] == 0)) {
					queue.add(new int[] { nx, ny });
					board[nx][ny] = board[x][y] + 1;
				}

			}

		}
		return 0;

	}
}
