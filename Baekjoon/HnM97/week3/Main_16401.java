package b16401;
import java.io.*;
import java.util.*;
public class Main {
	static int[] snacks;
	static int n;
	static int m;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		snacks = new int[m];
		for(int i=0;i<m;i++) {
			snacks[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(snacks);
		int start = 1;
		int end = snacks[m-1];
		int mid = 0;
		int snkCnt = 0;
		int ans = 0;
		while(start <= end) {
			mid = (start+end) / 2;
			snkCnt = sliceSnk(mid);
//			System.out.println("===========");
//			System.out.println("mid: " + mid + " snkCnt: " + snkCnt);
//			System.out.println("start: " + start + " end: " + end);
			if(snkCnt >= n) {
				start = mid + 1;
				ans = mid;
			} else {
				end = mid - 1;
			}
		}
		
		System.out.println(ans);
	}
	public static int sliceSnk(int len) {
		int res =0;
		
		for(int i=0;i<m;i++) {
			if(snacks[i] >= len)
				res += snacks[i] / len;
		}
		
		return res;
	}
	

}
