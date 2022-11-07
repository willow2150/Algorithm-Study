import java.io.*;
import java.util.*;
public class Main {
	static int N,M,T,K;
	static ArrayList<int[]> stones;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		stones = new ArrayList<>();
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			stones.add(new int[] {x,y});
		}
		
//		for(int[] s: stones) {
//			System.out.println(Arrays.toString(s));
//		}
		
		int maxCnt = 0;
		int[] point = new int[2];
		for(int i=0;i<T;i++) {
			int x = stones.get(i)[0];
			if(x+K > N) {
				x = N - K;
			}
			
			for(int j=0;j<T;j++) {
				int y = stones.get(j)[1];
				if(y+K > M) y = M- K ;
				int tempCnt = check(x, y);
				if(maxCnt < tempCnt) {
					point[0] = x;
					point[1] = y+K;
					maxCnt = tempCnt;
				}
			}
		}
		System.out.println(point[0] + " " + point[1]);
		System.out.println(maxCnt);

	}
	private static int check(int x, int y) {
		int cnt = 0 ;
//		System.out.println("x: " + x + " y: " + y);
//		System.out.println("x end: " + (x+K) + " y end: " + (y-K));
		for(int i=0;i<T;i++) {
			int[] s = stones.get(i);
			if(x <= s[0] && s[0] <= x+K && y<=s[1] && s[1] <= y+K) {
				cnt ++;
			}
		}
//		System.out.println("cnt : " + cnt);
		return cnt;
	}

}