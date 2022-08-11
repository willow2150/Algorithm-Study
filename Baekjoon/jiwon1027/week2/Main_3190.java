package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_3190 {
	static int[][] board;
	static int dx = 0;
	static int dy = 1;
	static Queue<String[]> queue;
	static int n;
	
	
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		board = new int[n][n];
		
		for (int i = 0; i < k; i++) {
			String[] input = br.readLine().split(" ");
			// 1: 사과
			board[Integer.parseInt(input[0])-1][Integer.parseInt(input[1])-1] = 1;
			
		}
		
		int l = Integer.parseInt(br.readLine());
		queue = new ArrayDeque<>();
		
		for (int i = 0; i < l; i++) {
			String[] input = br.readLine().split(" ");
			queue.add(input);
		}
		
		bfs();
		
		
	}
	public static void rotate(String c) {
		if (c.equals("L")) {
	        if ((dx == 0) && (dy == 1)) {
	            dx = -1;
	            dy = 0;
	        }
	        else if ((dx == -1) && (dy == 0)) {
	            dx = 0;
	            dy = -1;
	        }
	        else if ((dx == 0) && (dy == -1)) {
	            dx = 1;
	            dy = 0;
	        }
	        else if ((dx == 1) && (dy == 0)) {
	            dx = 0;
	            dy = 1;
	        }

		}
		else {
	        if ((dx == 0) && (dy == 1)) {
	            dx = 1;
	            dy = 0;
	        }
	        else if ((dx == -1) && (dy == 0)) {
	            dx = 0;
	            dy = 1;
	        }
	        else if ((dx == 0) && (dy == -1)) {
	            dx = -1;
	            dy = 0;
	        }
	        else if ((dx == 1) && (dy == 0)) {
	            dx = 0;
	            dy = -1;
	        }
		}
		
		
		
	}
	public static void bfs() {
		String[] input = queue.poll();
		String t = input[0];
		String c = input[1];
		
		int head_x = 0;
		int head_y = 0;
		int res = 0;
		board[0][0] = 2;
		Queue<int[]> snake = new ArrayDeque<>();
		snake.add(new int[] {0,0});
		
		while (true) {
			res++;
			int nx = head_x + dx;
			int ny = head_y + dy;

			if (res == Integer.parseInt(t)) {
				rotate(c);
				try{
					input = queue.poll();
					t = input[0];
					c = input[1];
				}catch (Exception e) {
				}	
			}
			
			
			if (!(0<=nx) || !(nx<n) || !(0<=ny) || !(ny<n) || board[nx][ny]==2) {
				System.out.println(res);
				break;
			}
			
			else if(board[nx][ny]==1) {
				board[nx][ny] = 2;
				snake.add(new int[] {nx,ny});
			}
			
			else {
				board[nx][ny] = 2;
				snake.add(new int[] {nx,ny});
				int[] temp = snake.poll();
				board[temp[0]][temp[1]] = 0;
			}
		
			head_x = nx;
			head_y = ny;
			
			
		}
		
	}

}
