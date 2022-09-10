package b15671;
import java.io.*;
import java.util.*;
public class Main {
	static char[][] map = new char[7][7];
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	static int[] dy = {1,1,0,-1,-1,-1,0,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		for(int i=1;i<=6;i++) {
			for(int j=1;j<=6;j++) {
				map[i][j] = '.';
			}
		}
		map[3][3] ='W';
		map[4][4] ='W';
		map[3][4] ='B';
		map[4][3] ='B';
		
		// 검은 돌부터 시작
		char color = 'B';

		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine()); 
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());

			// 게임 로그 마다 돌 두고 색깔 반대로
			placeStone(row,col,color);
			color = toggleColor(color);
		}

		// 돌 개수 계산, 승리자 판정
		int b_cnt=0;
		int w_cnt=0;
		for(int i=1;i<=6;i++) {
			for(int j=1;j<=6;j++) {
				if(map[i][j]=='B') b_cnt++;
				else if (map[i][j]=='W') w_cnt++;
			}
		}
		printMap();
		if(b_cnt > w_cnt) System.out.println("Black");
		else System.out.println("White");
	}

/**
 * 8방향을 탐색하면서
 * 각 방향마다 현재 차레의 색과 반대의 색이 있을 경우 turn 호출
 */
	public static void placeStone(int row, int col, char color) {
		map[row][col] = color;
		for(int i=0;i<8;i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			if(nx >=1 && ny>=1 && nx <=6 && ny<=6) {
				if(color=='B' && map[nx][ny] == 'W') {
					turn(nx,ny,i,'W','B');
				}
				else if(color=='W' && map[nx][ny] == 'B') {
					turn(nx,ny,i,'B','W');
				}
			}
		}
	}
	
	/**
	 * placeStone에서 전달한 방향을 계속 탐색
	 * 현재 위치를 후보 리스트에 담음
	 * 조건에 어긋나기 전까지 리스트에 담다가
	 * 현재 차례의 색을 만나면 리스트에 담긴 좌표들을 갱신
	 */
	public static void turn(int row, int col, int dir, char from, char to) {
		ArrayList<int[]> candidate = new ArrayList<int[]>();
		int nx = row;
		int ny = col;

		candidate.add(new int[]{row,col});

		while(true) {
			nx += dx[dir];
			ny += dy[dir];
			if(nx < 1 || ny <1 || nx > 6 || ny > 6 || map[nx][ny] == '.') return;
			else if(map[nx][ny]==to) {
				for(int[] p: candidate) {
					map[p[0]][p[1]] = to;
				}
				return;
			} else if(map[nx][ny]==from) {
				candidate.add(new int[] {nx,ny});
			}
		}

	}

	// 맵 출력
	public static void printMap() {
		//		System.out.println("===================");
		for(int i=1;i<=6;i++) {
			for(int j=1;j<=6;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	// B -> W, W -> B
	public static char toggleColor(char before) {
		if(before=='B')  return 'W';
		else return 'B';
	}
}
