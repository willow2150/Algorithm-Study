import java.io.*;
import java.util.*;

public class Main {
	static char[][] board;
	static int[][] bomb;
	static int R,C,N;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		bomb = new int[R][C];
		for(int i = 0; i < R; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 0; j < C; j++) {
				board[i][j] = input[j];
				if(board[i][j] == 'O') {
					bomb[i][j] = 1;
				}
			}
		}
		if(N % 2 == 0) {
			process3();
			Print();
		}
		else {
			int curTime = 1;
			while(curTime != N) {
				process3();
				curTime += 1;
				process4();
				
				curTime += 1;
				secondLater();
				process4();
			}
			Print();
		}
	}
	public static void secondLater() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(bomb[i][j] == 0) continue;
				bomb[i][j] += 1;
			}
		}
	}
	public static void process3() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				bomb[i][j] += 1;
				board[i][j] = 'O';
			}
		}
	}
	public static void process4() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(bomb[i][j] == 3) {
					bomb[i][j] = 0;
					board[i][j] = '.';
					for(int k = 0; k < 4; k++) {
						int xx = j + dx[k], yy = i + dy[k];
						if(xx < 0 || xx >= C || yy < 0 || yy >= R) continue;
						if(bomb[yy][xx] == 3) continue;
						bomb[yy][xx] = 0;
						board[yy][xx] = '.';
					}
				}
			}
		}
	}
	public static void Print() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
