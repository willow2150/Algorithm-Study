import java.io.*;
import java.util.*;
public class Main {
	public static class Node{
		int num,t;

		public Node(int num, int t) {
			super();
			this.num = num;
			this.t = t;
		}
		
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
		int[] times = new int[N+1];
		int[] indegree = new int[N+1];
		for(int i=0;i<N+1;i++) {
			array.add(new ArrayList<Integer>());
		}	
		
		for(int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			times[i] =  t;
			int prevNum = Integer.parseInt(st.nextToken());
			for(int j=0;j<prevNum;j++) {
				int idx = Integer.parseInt(st.nextToken());
				array.get(idx).add(i);
				indegree[i]++;
			}
		}
		
//		for(int i=1;i<=N;i++) {
//			System.out.println(array.get(i));
//		}
//		System.out.println(Arrays.toString(indegree));
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
		});
		
		for(int i=1;i<=N;i++) {
			if(indegree[i]==0) {
				pq.add(new int[] {i,times[i]});
			}
		}
		
		int totalTime = 0;
		while(!pq.isEmpty()) {
			int[] node = pq.poll();
			int idx = node[0];
			totalTime = node[1];
//			System.out.println(idx + " "+ totalTime);
			for(int next: array.get(idx)) {
				indegree[next] --;
				if(indegree[next]==0) {
					pq.add(new int[] {next, times[next]+totalTime});
				}
			}
		}
		System.out.println(totalTime);

	}

}