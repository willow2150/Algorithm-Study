import java.io.*;
import java.util.*;
public class Main {
	static int N,M;
	static ArrayList<ArrayList<Integer>> adjList;
	static int[] parents;
	static int[][] graph;
	static ArrayList<int[]> edges;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<>();
		parents = new int[N+1];
		graph = new int[N+1][N+1];
		edges = new ArrayList<>();
		
		for(int i=1;i<=N;i++) {
			parents[i] = i;
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			unionParent(a, b);
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int cost = Integer.parseInt(st.nextToken());
				if(i < j && i != 1 && j != 1 && cost != 0) {
					pq.add(new int[] {i,j,cost});
				}
			}
		}	
		boolean isStable = true;
		for(int i=2;i<N;i++) {
			if(findParent(i) != findParent(i+1)) {
				isStable = false;
				break;
			}
		}
		
		if(isStable) {
			System.out.println("0 0");
			return;
		}
		
		int sum = 0;
		int cnt = 0;
		ArrayList<int[]> connected = new ArrayList<>();
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int x = cur[0];
			int y = cur[1];
			
			if(findParent(x) != findParent(y)) {
				sum += cur[2];
				cnt ++;
				unionParent(x, y);
				connected.add(new int[] {x,y});
			}
		}
		
		System.out.println(sum + " " + cnt);
		for(int[] e : connected) {
			System.out.println(e[0] + " " +  e[1]);
		}
	}
	
	public static int findParent(int x) {
		if(x == parents[x]) {
			return x;
		} else {
			parents[x] = findParent(parents[x]);
			return parents[x];
		}
	}
	
	public static void unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		
		if(a > b) parents[a] = b;
		else parents[b] = a;
	}

}
