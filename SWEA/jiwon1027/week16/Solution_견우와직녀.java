import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 1. 오작교가 만들어지는 시간을 기다리는 처리를 하기 위해 기다리는 시간을 나머지연산으로 구해서 nx,ny에 갔을때의 time을 한번에 계산해서 넣어줌
 * 
 * 이렇게 되면 visited check에 대해서 문제가 생기는데 시간이 더 많이 나오는 애들이 먼저 직녀쪽으로 가서 방문을 할 수 있기 때문에
 * 
 * visited를 3차원으로 선언하고 관리해야됨(z : 오작교 건설 flag(0,1))
 * 
 * 
 * 
 * 2. 시간복잡도는 좀 더 걸릴지라도 제자리에서 기다리는거 자체를 x,y,time+1 자체를 게속 queue에 add하는 거임
 * 
 * 이렇게 되면 visited를 2차원으로 해도 된다고 생각함. 어짜피 직녀쪽에 가깝게 방문하는 애들이 당연히 미리 방문을 할테니까
 * 
 * 그 뒤에 오는 애들은 먼저 온 애들보다 무조껀 값이 클 것이기 때문에.
 * 
 * "잘못된 생각인가?"
 * 만약 틀렸다면 이 부분도 그냥 3차원으로 방문처리를 해줘야하는 것인데 [수영대회 결승전]은 이러한 생각으로 풀었을 떄 맞았는데?
 * [수영대회 결승전] 문제는 그냥 단순히 시간만 기다려서 상관없었던건데 이 문제는 단순히 시간만 기다리는거 + 오작교 만들어서 기다리는 것
 * 오작교를 새로 만들었는지 아닌지에 대한 상황 자체가 다르기 때문에 그냥 필수적으로 해줘야하는 부분인가?
 * 
 * 찾았다
 * 
 * 5 5
 * 1 1 1 1 1
 * 10 0 0 0 0
 * 1 1 1 1 1
 * 1 1 1 1 0
 * 1 1 1 0 1
 * 
 * (2,4)에 저장되는 값은 뭐가 되야할까? 
 * ㄴ자 경로로 time:13, flag=true인 상태로 와야하는데
 * 내가 쓴 코드로 하면 그냥 ㄱ경로로 (1,4)에 오작교를 만들고 (2,4)에 방문한 time:6, flag=false인 상태가 저장이 된다
 * 
 * 즉, 똑같은 위치를 방문하더라도 오작교를 만들 수 있는 상태, 없는 상태로 나뉘어지기 때문에 visited를 3차원으로 만드는게 불가피하네
 * 
 * Q.근데 내 코드에서 flag 세워가지고 다리 만들 수 있냐 없냐로 판단했잖아. 그럼 된거 아닌가?
 * => 애초에 visited를 이용한 이유가 한번 방문한 자리를 재방문 하지 않기 위해서 하는건데 
 * 위의 반례 보면(2,4)에 ㄱ자 경로로 time이 작은 녀석이 방문처리를 해버려서 ㄴ자 경로로 온 time이 큰 녀석이
 * (2,4) 자체를 방문도 못하고 그냥 넘어가게 되는데 이렇게 되면 안된다 이거지.
 * 
 * 
 * 
 * */


public class Solution {
	static int N,M,res;
	static int[][] board;
	static boolean[][][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			board = new int[N][N];
			visited = new boolean[N][N][2];

			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken()); 
				}

			}
			

			res = Integer.MAX_VALUE;
			bfs();
			
			System.out.println("#"+tc+" "+res);
			
		}

	}
	
	public static void bfs() {

		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0,0,0,0,0});
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] poll_data = queue.poll();
			int x = poll_data[0];
			int y = poll_data[1];
			int now = poll_data[2];
			int temp = poll_data[3]; // 이전에 오작교 건넜는지 flag
			int flag = poll_data[4];
			//System.out.println(x+" "+y+" "+now+" "+(temp));

			if (x == N-1 && y == N-1) {
				res = Math.min(res, now);
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny][flag])
					continue;

				
				if (board[nx][ny] == 0 && board[nx][ny] >1) {
					boolean[] dir = new boolean[4];
					
					
					for (int j = 0; j < 4; j++) {
						int cx = nx + dx[i];
						int cy = ny + dy[i];
						if (cx<0 || cy<0 || cx>=N || cy>=N)
							continue;
						
						dir[j] = true;
					}
					
					if (dir[0] && dir[3])
						continue;
					else if (dir[0] && dir[2])
						continue;
					else if (dir[1] && dir[2])
						continue;
					else if (dir[1] && dir[3])
						continue;	
				}
				
				
				if (flag == 0 && board[nx][ny] == 0 && temp == 0) { //오작교 건설

					if (((now+1)%M) == 0) {
						visited[nx][ny][flag] = true;
						queue.add(new int[] {nx,ny, now+1,1,1});
					}
					else {
						queue.add(new int[] {x,y, now+1,0,flag});
					}		
				}
				
				else if(board[nx][ny] > 1 && temp == 0) { // 미리 만들어진 오작교
					int temp_time = board[nx][ny];
					
					if (((now+1)%temp_time) == 0) {
						visited[nx][ny][flag] = true;

						queue.add(new int[] {nx,ny, now+1,1,flag});
					}
					else {
						queue.add(new int[] {x,y, now+1,0,flag});
					}
	
				} 
				
				else if(board[nx][ny] == 1) { //이동 가능 길
					visited[nx][ny][flag] = true;

					queue.add(new int[] {nx,ny, now+1,0,flag});
				}
					
			}
			
		}
				
		
		
		
		
		
		
		
		
	}

}
