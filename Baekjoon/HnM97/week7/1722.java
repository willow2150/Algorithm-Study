package b1722;
import java.io.*;
import java.util.*;
public class Main {
	public static int[] numInput;
	public static long[] factorials;
	public static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		factorials =  new long[N+1];
		factorials[0] = 1;
		factorials[1] = 1;
		initFact();
		StringTokenizer st = new StringTokenizer(br.readLine());
		if(st.nextToken().equals("2")) {
			numInput = new int[N];
			for(int i=0;i<N;i++) {
				numInput[i] = Integer.parseInt(st.nextToken());
			}
			findOrder();
		} else {
			long idx = Long.parseLong(st.nextToken());
			findNumbers(idx);
		}
	}
	
	public static void findOrder() {
		long res=0;
		int cur =0;
		ArrayList<Integer> remains = new ArrayList<Integer>();
		
		for(int i=1;i<=N;i ++) {
			remains.add(i);
		}
		
		for(int i=0;i<N;i++) {
			cur = numInput[i];
			int nextOrder = findNextOrder(remains, cur);
			res += factorials[N-i-1] * nextOrder;
		}
		
		System.out.println(res+1);
	}
	public static void initFact() {
		for(int i=2;i<=N;i++) {
			factorials[i] = factorials[i-1] * i;
		}
	}
	
	public static int findNextOrder(ArrayList<Integer> remains, int target) {
		for(int i=0;i<N;i++) {
			if(target == remains.get(i)) {
				remains.remove(i);
				return i;
			}
		}
		return 0;
	}
	
	public static void findNumbers(long idx) {
		ArrayList<Integer> remains = new ArrayList<Integer>();
		int[] ans = new int[N];
		long temp =0 ;
		for(int i=1;i<=N;i ++) {
			remains.add(i);
		}
		
		for(int i=0;i<N-1;i++) {
			for(int j = i; j<N;j++) {
				temp = factorials[N-i-1] * (N-j-1);
				if(temp < idx) {
					idx -= temp;
					ans[i] = remains.get(N-1-j);
					remains.remove(N-1-j);
					break;
				}
			}
		}
		ans[N-1] = remains.get(0);
		
		for(int i=0;i<N;i++) {
			System.out.print(ans[i] + " ");
		}
	}

}
