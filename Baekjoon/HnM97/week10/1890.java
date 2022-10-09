import java.io.*;
import java.util.*;
public class Main {
	public static int N;
	static int[][] map;
	static int[] dx = {1,0};
	static int[] dy = {0,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		long[][] dp = new long[N][N];
		dp[0][0] = 1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==0) break;
				for(int d=0;d<2;d++) {
					int nx = i + dx[d]*map[i][j];
					int ny = j + dy[d]*map[i][j];
					if(nx>=0 && ny>=0 && ny<N && nx <N) {
						dp[nx][ny] += dp[i][j];
					}
				}

			}
		}

		System.out.println(dp[N-1][N-1]);

	}
//	private static int bfs(int sx, int sy) {
//		Queue<int[]> q = new ArrayDeque<int[]>();
//		q.add(new int[] {sx,sy,1});
//		int[][] dp = new int[N][N]; 
//		int ans = 0;
//		while(!q.isEmpty()) {
//			int[] cur = q.poll();
//			int x = cur[0];
//			int y = cur[1];
//			int cnt = cur[2];
//			//			System.out.println(x + " " + y);
//			if(x==N-1 && y==N-1) {
//				dp[N-1][N-1] += cnt;
//				continue;
//			}
//			for(int i=0;i<2;i++) {
//				int nx = x+dx[i] * map[x][y];
//				int ny = y+dy[i] * map[x][y];
//				if(nx>=0 && ny>=0 && ny<N && nx <N && dp[nx][ny] != cnt ) {
//					dp[nx][ny] += cnt;
//					q.add(new int[] {nx,ny,dp[nx][ny]});
//				}
//
//			}
//		}
//		return ans;
//
//	}

}