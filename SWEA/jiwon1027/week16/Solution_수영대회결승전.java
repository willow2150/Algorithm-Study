

import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Q. 상하좌우로 이동한다는 점, 1초에 한칸씩 이동하라는 말이 없는데?
 * Q. 왜 소용돌이가 사라지자 마자 이동하지않지? 사라지는걸 왜 보고만 있냐
 * 
 * start -> end 가장 빠른 길(갈 수 있는 경로들 중 min())
 * 벽돌 1, 소용돌이 2(생성되고 2초동안 유지되다가 1초동안 잠잠)
 * 0초 생성 -> 0,1초 유지 -> 2초 사라짐 -> 3,4초 유지 -> 5초 사라짐 ...

 * 소용돌이 위에서 머무를 수 있음(움직이지 않고 기다릴 수 있다는 것)
 * 
 * 모든 소용돌이는 동시에 생기고 없어지기 때문에 상태를 기억하는 변수 필요할 듯?
 * 
 * 
 * 
 * */

public class Solution {
	static int N, res, time;
	static int end_x, end_y;
	static int[][] board;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());


		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc < T+1; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			int start_x = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			end_x = Integer.parseInt(st.nextToken());
			end_y = Integer.parseInt(st.nextToken());
			
			res = Integer.MAX_VALUE;
			time = 0;
			
			
			
			bfs(start_x, start_y);
			
			if (res == Integer.MAX_VALUE)
				System.out.println("#"+tc+" "+ -1);
			else
				System.out.println("#"+tc+" "+ res);
			
		}
	}
	
	
	public static void bfs(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {i,j,0});
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			int[] poll_data = queue.poll();
			int x = poll_data[0];
			int y = poll_data[1];
			int time = poll_data[2];

			if (x == end_x && y == end_y)
				res = Math.min(res, time);
			
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny])
					continue;
				if (board[nx][ny] == 1)
					continue;
				if (board[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.add(new int[] {nx,ny, time+1});
				}
				
				if (board[nx][ny] == 2) {
					//2,5,8,11... 때 이동 가능
					if ((time+1)%3 == 0) {
						visited[nx][ny] = true;
						queue.add(new int[] {nx,ny,time+1});
					}
					else {
						int remain = (time+1)%3;
						
						queue.add(new int[] {x,y, 3-remain+time});
					}
					
				}
				
			}
		}
		
		
		
		
	}
	

}
