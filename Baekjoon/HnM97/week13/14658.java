import java.io.*;
import java.util.*;
public class Main {
	static int N,M,L,K;
	static int maxCnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ArrayList<int[]> stars = new ArrayList<int[]>();
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			stars.add(new int[] {x,y});
		}
		maxCnt = 0;


		for(int[] s1 : stars) {
			for(int[] s2: stars) {
				maxCnt = Math.max(maxCnt,check(s1[0],s2[1],stars));
			}
		}

		System.out.println(K-maxCnt);
	}
	private static int check(int x, int y, ArrayList<int[]> stars) {
		int x_start = x;
		int x_end = x + L;
		int y_start  = y;
		int y_end = y + L;
		int cnt = 0;
		for(int i=0;i<K;i++) {
			int[] star = stars.get(i);
			if(x_start <= star[0] && star[0] <= x_end && y_start <= star[1] && star[1] <= y_end) {
				cnt ++;

			}
		}
		return cnt;
	}
}
