package b7562;
import java.io.*;
import java.util.*;
public class Main {
		static int n;
		static int x_goal, y_goal;
		static int[] dx= {-2,-1,1,2,2,1,-1,-2};
		static int[] dy= {1,2,2,1,-1,-2,-2,-1};
		static int minCnt = 0;
		static boolean[][] visited;
		static StringBuilder sb = new StringBuilder();
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		
		int t = Integer.parseInt(br.readLine());
		for(int i=0;i<t;i++) {
			minCnt = 0;
			n = Integer.parseInt(br.readLine());
			visited = new boolean[n][n];
			st = new StringTokenizer(br.readLine());
			int x_pos = Integer.parseInt(st.nextToken());
			int y_pos = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			x_goal = Integer.parseInt(st.nextToken());
			y_goal = Integer.parseInt(st.nextToken());
			bfs(x_pos,y_pos);
		}
		System.out.println(sb.toString());
	}
	public static void bfs(int a, int b) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(a,b,0));
		visited[a][b] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			if(x == x_goal && y== y_goal) {
				sb.append(cur.cnt + "\n");
				return;
			}
			
			for(int i=0;i<8;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
					continue;
				}
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Point(nx,ny,cur.cnt+1));
				}
			}
		}
	}
	
	public static class Point{
		int x;
		int y;
		int cnt;
		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
}
