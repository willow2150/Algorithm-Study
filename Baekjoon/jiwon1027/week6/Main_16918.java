package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/* 
 * 폭탄 3초 후에 i,j + 상하좌우 파괴 -> 파괴 후 빈칸이 됨
 * 상하좌우에 폭탄이 있으면 그 폭탄은 안터지고 그냥 파괴(연쇄반응 x) 
 * 
 * 1. 17퍼에서 틀렸음(반례가 있나봄)
 * N이 짝수이면 무조껀 꽉 채우는 거니까 fill_board 출력
 * N이 홀수면 처음꺼랑 한번터진거랑 왔다갔다 해서 홀,짝 구분해서 하면 되는 듯?
 * <반례>
	3 3 5
	OO.
	OOO
	OOO
	
	
 * 	2. 그냥 순서대로 하나씩 하는 구현으로 해보자
 * 	언제 1초가 흐르는지 조금 이해가 되지 않았는데 힌트를 보고 이해했다
 *  처음에 1초동안 그냥 아무것도 하지않아서  N--;
 *  폭탄은 동시에 터져야 하기 때문에 어디 리스트나 큐에 담아둬서 처리할 필요가 있어서 Queue에 담아서 나중에 한번에 터지게 했음
 *  나머진 bfs처럼 똑같이 구현

 * */


public class Main_16918 {
	static int R,C,N;
	static char[][] board;
	static int[] dx = {0,1,-1,0,0};
	static int[] dy = {0,0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		N = Integer.parseInt(input[2]);

		board = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = row.charAt(j);
			}
		}
		
		N--;
		while (N>0){
			Queue<int[]> queue = new ArrayDeque<>();
			search(queue);
			putBomb();
			
			N--;
			if (N==0)
				break;
			BoomBomb(queue);
			N--;
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		
		
	}
	
	
	public static void search(Queue<int[]> queue) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] == 'O')
					queue.add(new int[] {i,j});
			}
		}
	}
	
	public static void putBomb() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] == '.')
					board[i][j] = 'O';
			}
		}
	}
	
	
	
	public static void BoomBomb(Queue<int[]> queue) {
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			int x = poll[0];
			int y = poll[1];
			for (int k = 0; k < 5; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				
				if (0<=nx && 0<=ny && nx<R && ny<C && board[nx][ny]=='O') {
					board[nx][ny] = '.';
				}
			}
		}

	}
	

}
