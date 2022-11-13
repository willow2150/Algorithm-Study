package b14923;
import java.io.*;
import java.util.*;
public class Main {
	public static int N,M,Hx,Hy,Ex,Ey;
	public static int[][] map;
	public static ArrayList<int[]> candidate = new ArrayList<>();
	public static int[] dx= {1,-1,0,0};
	public static int[] dy= {0,0,1,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int minDist = Integer.MAX_VALUE;


		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Hx =  Integer.parseInt(st.nextToken());
		Hy = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Ex =  Integer.parseInt(st.nextToken());
		Ey = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//		for(int i=1;i<=N;i++) {
		//			
		//			for(int j=1;j<=M;j++) {
		//				System.out.print(map[i][j]+ " ");
		//			}
		//			System.out.println();}

		bfs();


	}

	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {Hx,Hy,1,0});
		boolean[][][] visited = new boolean[N+1][M+1][2];
		visited[Hx][Hy][1] = true;
		int dist =0;
		while(!q.isEmpty()) {
			int[] point = q.poll();
			int wall = point[2];
			//			System.out.println(point[0] + " " + point[1]);
			if(point[0] == Ex && point[1] == Ey) {
				dist = point[3];
				break;
			}
			for(int i=0;i<4;i++) {
				int nx = point[0] + dx[i];
				int ny = point[1] + dy[i];
				if(nx < 1 || ny < 1 || nx > N || ny > M) continue;
				if(wall >0 && !visited[nx][ny][wall-1] && map[nx][ny] == 1) {
					visited[nx][ny][wall-1] = true;
					q.add(new int[]{nx,ny, wall - 1, point[3]+1});
				}
				
				if(!visited[nx][ny][wall] && map[nx][ny]==0) {
					visited[nx][ny][wall] = true;
					q.add(new int[]{nx,ny, wall, point[3]+1});
				}
			}
		}
		System.out.println(dist==0? -1 : dist);
	}

	public static void findCandidate() {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				boolean flag = false;
				if(map[i][j]==1) {
					for(int k=0;k<4;k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(nx >=1 && ny>=1 && nx <=N && ny <= M) {
							if(map[nx][ny] == 0) {
								flag = true;
								break;
							}
						}

					}
					if(flag) candidate.add(new int[] {i,j});
				}

			}

		}
	}

}
