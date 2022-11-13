import java.io.*;
import java.util.*;
public class Main {
	public static int N,M;
	public static int[][] map;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int before;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		boolean flag = true;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==1) {
					cnt ++;
					flag = false;
				}
			}
		}
		if(flag) {
			System.out.println(0);
			System.out.println(0);
		} else {
			before = cnt;
		}
		
		int time=1;
		while(!bfs()) {
			time ++;
		}
		System.out.println(time);
		System.out.println(before);
	}
	private static boolean bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] {0,0});
		boolean[][] visited = new boolean[N][M];
		ArrayList<int[]> candidate = new ArrayList<int[]>();
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			for(int i=0;i<4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx >=0 && ny>=0 && nx < N && ny <M && !visited[nx][ny]) {
					if(map[nx][ny]==0) {
						visited[nx][ny] = true;
						q.add(new int[] {nx,ny});
					} else {
						visited[nx][ny] = true;
						candidate.add(new int[] {nx,ny});
					}
				}
			}
		}
		
		for(int[] p: candidate) {
			map[p[0]][p[1]] = 0;
		}
		boolean flag = true;
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==1) {
					cnt ++;
					flag = false;
				}
			}
		}
		if(!flag) {
			before = cnt;
		}
		return flag;
	}

}