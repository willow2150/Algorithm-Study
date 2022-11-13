package b16918;
import java.io.*;
import java.util.*;
public class Main {
	public static char[][] map, newMap;
	public static ArrayList<int[]> bombs;
	public static int R,C;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		newMap = new char[R][C];
		// 폭탄의 좌표를 저장할 Array List
		bombs = new ArrayList<>();
		
		// 처음에 배치된 폭탄의 위치를 저장
		for(int i=0;i<R;i++) {
			String line = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j]=='O') bombs.add(new int[]{i,j});
			}
		}
		
		// 처음 1초에 아무것도 안하므로 1부터 시작
		// 1,3,5.. 와 같이 홀수 시간초에 폭파
		for(int i=1;i<T;i++) {
			if(i % 2 == 1) {
				explode();
			}
		}
		
		// T가 2,4,6..과 같이 짝수 시간초이면 꽉채워서 출력, 아니면 현재 맵 출력
		if(T % 2 == 0 ) printFullMap();
		else printMap();
	}
	public static void explode() {
		// 현재 맵을 기반으로 폭발 후의 상황을 기록할 newMap 초기화
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				newMap[i][j] = 'O';
			}
		}
		// 저장된 폭탄으로 newMap에 폭파된 위치 기록
		for(int[] b: bombs) {
			int x = b[0];
			int y = b[1];
			newMap[x][y] = '.';
			if(x+1>=0 && y>=0 && x+1 <R && y <C)
				newMap[x+1][y] = '.';
			if(x-1>=0 && y>=0 && x-1 <R && y <C)
				newMap[x-1][y] = '.';
			if(x>=0 && y+1>=0 && x <R && y+1 <C)
				newMap[x][y+1] = '.';
			if(x>=0 && y-1>=0 && x <R && y-1 <C)
				newMap[x][y-1] = '.';
		}
		// 폭파 이후의 폭탄 위치를 저장하기 위해 초기화
		bombs.clear();
		
		// 다음 홀수 번에 폭파되는 폭탄들을 저장
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				map[i][j] = newMap[i][j];
				if(newMap[i][j]=='O') bombs.add(new int[] {i,j}); 
			}
		}
	}
	// 짝수 시간 초에 폭탄으로 꽉 채운 맵 출력
	public static void printFullMap() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print('O');
			}
			System.out.println();
		}
	}
	// 홀수 시간 초에 현재 맵 출력
	public static void printMap() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
