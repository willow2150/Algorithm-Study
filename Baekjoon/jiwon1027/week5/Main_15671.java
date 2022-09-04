package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/* 걸린시간 : 40분
 * 
 * 돌 놓은 자리 기준으로 8방 탐색하면서 std_stone 다시 만나면 그걸로 다 바꾸면 되는거 같은데?
 * 돌을 어디다 놓을지 판단하는게 어지러웠는데 그걸 입력해서 제시해줘서 엄청 다행이라고 생각했음(만약 저 위치를 찾는 거면 어려웠을듯)
 * IF 위처럼 문제 나왔으면 어떻게 했을까?
 * 지금 흑돌차례라면 모든 백돌의 8방향에 빈자리에 한번씩 놔보는 DFS 완전탐색을 했을 것 같음
 * 문제에서 나오는 종료조건 3개로 DFS의 기저조건을 두면서하는 완탐 문제가 되지않았을까 싶다.
 * 돌을 착수 하고 8방탐색 하면서 반대색깔 돌만날때마다 Queue에 넣어주고 똑같은돌 만나면 Queue에 있는거 싹 다 똑같이 바꾸기
 * 
 * */
public class Main_15671 {
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	static int[] dy = {1,1,0,-1,-1,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		char[][] board = new char[7][7];
		
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				board[i][j] = '.';
			}
		}
		
		board[3][3] = board[4][4] = 'W'; //백돌
		board[3][4] = board[4][3] = 'B'; //흑돌

		
		char stone = 'B'; //흑돌이 선
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			board[x][y] = stone;
			
			//8방 탐색하면서 돌 뒤집어 줘야함
			for (int j = 0; j < 8; j++) {
				int k = 1;
				Queue<int[]> queue = new ArrayDeque<>();
				while (true) {
					int nx = x + dx[j]*k;
					int ny = y + dy[j]*k;
					if(nx<1 || ny<1 || nx>6 || ny>6 || board[nx][ny]=='.')
						break;
					
					else if (board[nx][ny] == stone) {
						while(!queue.isEmpty()) {
							int[] data = queue.poll();
							board[data[0]][data[1]] = stone;
						}
						break;
					}
					
					queue.add(new int[] {nx,ny});
					k++;
	
					}
				}
		
			if (stone == 'B')
				stone = 'W';
			else
				stone = 'B';
		}
		
		
		int white_cnt = 0;
		int black_cnt = 0;
		
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				if (board[i][j] == 'W')
					white_cnt++;
				else if (board[i][j] == 'B')
					black_cnt++;
				System.out.print(board[i][j]);
			}
			System.out.println();
		}

		System.out.println(white_cnt<black_cnt?"Black":"White");
		
		
		
		
	}
}
