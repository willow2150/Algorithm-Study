package b2583;
import java.io.*;
import java.util.*;
public class Main {
	static int[][] board;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n;
	static int m;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<Integer> ans = new ArrayList<Integer>();
		board = new int[m][n];
		
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			int left_x = Integer.parseInt(st.nextToken());
			int left_y = m - Integer.parseInt(st.nextToken());
			int right_x = Integer.parseInt(st.nextToken());
			int right_y = m - Integer.parseInt(st.nextToken());
			for(int x=right_y;x<left_y;x++) {
				for(int y=left_x;y<right_x;y++) {
					if(board[x][y]==1) continue;
					board[x][y] = 1;
				}
			}
		}
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(board[i][j] == 1) continue;
				ans.add(dfs(i,j,1));
			}
		}
		System.out.println(ans.size());
		Collections.sort(ans);
		for(int num : ans) System.out.print(num+ " ");
		
		
	}
	public static int dfs(int x, int y, int area) {
		int nx, ny;
		board[x][y] = 1;
		for(int i=0;i<4;i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(nx >= m || ny >= n || nx < 0 || ny < 0) continue;
			else if(board[nx][ny] == 1) continue;
			area = dfs(nx,ny,area+1);
		}
		return area;
	}

}
