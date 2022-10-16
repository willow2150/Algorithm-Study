import java.io.*;
import java.util.*;
public class Main {
	// N : 접시 수, D : 초밥의 가짓수, K: 연속해서 먹는 접시 수, C: 쿠폰 번호
	static int N,D,K,C;
	static int[] sushi;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sushi = new int[N];
		for(int i=0;i<N;i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		int start = 0;
		int end = K-1;
		boolean flag= true;
		HashSet<Integer> set = new HashSet<Integer>();
		int maxSushi = 0;
		
		for(int cur=0;cur<N;cur++) {
			set.clear();
			flag = false;

			for(int i=0;i<K;i++) {
				int idx = (cur+i) % N;
				if(sushi[idx]==C) {
					flag = true;
				}
				set.add(sushi[idx]);
			}
//			System.out.println(set.toString());
			if(!flag) {
				maxSushi = Math.max(maxSushi, set.size() + 1);
			} else {
				maxSushi = Math.max(maxSushi, set.size());
			}
					
		}
		
		System.out.println(maxSushi);
		
//		while(start <= end) {
//			set.clear();
//			flag = true;
//			int tmpCnt =0;
//			for(int i=0;i<K;i++) {
//				if(set.contains(sushi[i])) {
//					continue;
//				} else {
//					set.add(sushi[i]);
//				}
//			}
//			maxSushi = Math.max(maxSushi, tmpCnt);			
//		}


	}

}
