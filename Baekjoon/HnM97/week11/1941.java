import java.io.*;
import java.util.*;
public class Main {
	static final int N = 5;
	static char[][] map = new char[N][N];
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int res = 0;
	static ArrayList<ArrayList<Integer>> totSubset;
	static int[] selected;
	static boolean[] visited;
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = line.charAt(j);
			}
		}
		visited = new boolean[N*N];
		selected = new int[7];
		dfs(0, 0, 0, 0);
		
		
		System.out.println(res);
	}
	
	private static void dfs(int idx, int cnt ,int sCnt,int yCnt) {
		if(cnt == 7) {
			if(sCnt >= 4) {
//				System.out.println(Arrays.toString(selected));
				if(checkAdj()) {
					res++;
					return;
				}
			}
			return;
		}

		if(yCnt >= 4 ) return;
		
		
		for(int i=idx;i<N*N;i++) {
			visited[i] = true;
			selected[cnt] = i;
			int x = i / N;
			int y = i % N;
			if(map[x][y]=='S') {
				dfs(i+1,cnt+1,sCnt+1,yCnt);
			} else {
				dfs(i+1,cnt+1,sCnt, yCnt+1);
			}
			visited[i] = false;
		}
		
	}

	private static boolean checkAdj() {
		boolean[] adjVisited = new boolean[N*N];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(selected[0]);
		int cnt =1;
		while(!q.isEmpty()) {
			int now = q.poll();
			int x = now / N;
			int y = now % N;
			adjVisited[now] = true;
			
			for(int i=0;i<4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(adjVisited[nx*N + ny]) continue;
				if(!visited[nx*N+ny]) continue;
				cnt ++;
				adjVisited[nx*N + ny] = true;
				q.add(nx*N + ny);
			}

		}
		if(cnt == 7) return true;
		else return false;
	}
	
	private static void makeSubset(int cnt, int start, int selected) {
//		System.out.println(Integer.toBinaryString(selected));
		if(cnt == N-1) {
			ArrayList<Integer> newSubset = new ArrayList<Integer>();
			for(int i=0;i<4;i++) {
				if((selected & (1<<i)) != 0) {
					newSubset.add(i);
				}
			}
			totSubset.add(newSubset);
			return;
		}
		selected |= (1<<start);
		makeSubset(cnt+1,start+1,selected);
		selected &= ~(1 << start);
		makeSubset(cnt+1,start+1,selected);
		
	}

	private static void bt(int x, int y, int size, boolean[][] visited) {
		Point[] candidate = new Point[4];
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			candidate[size++] = new Point(nx,ny);
		}
		
		boolean[][] newVisited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				newVisited[i][j] = visited[i][j];
			}
		}
		
		for(ArrayList<Integer> subset : totSubset) {
			boolean flag = true;
			int newSize = size;
			for(int dir: subset) {
				int nx = x + dx[dir];
				int ny = x + dx[dir];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N )	{
					flag= false;
					break;
				}
				newVisited[nx][ny] = true;
				newSize ++;
			}
			
			if(flag) {
				for(int dir: subset) {
					int nx = x + dx[dir];
					int ny = x + dx[dir];
					bt(nx,ny,newSize,newVisited);
				}
			}
		}
		
		
		
		
	}
	


}