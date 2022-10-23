package b14722;
import java.io.*;
import java.util.*;
public class Main {
	static int[][] board;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int i=0;i<N;i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] dx = {1,0};
		int[] dy = {0,1};
		int[][] dp = new int[N][N];
		Queue<int[]> q= new ArrayDeque<int[]>();
		if(board[0][0]==0) {
			dp[0][0] = 1;
			q.add(new int[] {0,0,1,0});
		} else {
			q.add(new int[] {0,0,0,-1});
		}


		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int dist = now[2];
			int preMilkType = now[3];
			dp[x][y] = Math.max(dist, dp[x][y]);
			if(x==N-1 && y==N-1) continue;
			if(dist < dp[x][y]) continue;

			for(int i=0;i<2;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(board[nx][ny] == nextMilk(preMilkType) && dp[nx][ny] < dist+1) {
					q.add(new int[] {nx,ny, dist+1,nextMilk(preMilkType)});
					dp[nx][ny] = dist + 1;
				}  
				q.add(new int[] {nx,ny, dist,preMilkType});

			}

		}
//		printDP(dp,N);
		System.out.println(dp[N-1][N-1]);


	}
	public static int nextMilk(int cur) {
		if(cur==-1) {
			return 0;
		} else if(cur==0) {
			return 1;
		} else if (cur==1) {
			return 2;
		} else {
			return 0;
		}
	}

	static void printDP(int[][] dp,int N) {
		System.out.println("===========");
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(dp[i][j]);
			}
			System.out.println();
		}
	}
}
