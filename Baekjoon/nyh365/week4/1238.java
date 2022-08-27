import java.io.*;
import java.util.*;
public class Main {
	static int N,M,X;
	static class Node implements Comparable<Node>{
		int number, weight;
		Node next;
		public Node(int number, int weight) {
			super();
			this.number = number;
			this.weight = weight;
		}
		public Node(int number, int weight, Node next) {
			super();
			this.number = number;
			this.weight = weight;
			this.next = next;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Node[] computer = new Node[N+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			computer[b] = new Node(a, s, computer[b]);
		}
		// 각각의 마을까지의 최소 경로 구하기
		boolean check[][] = new boolean[N + 1][N + 1];
		int[][] minValue = new int[N + 1][N + 1];
		for(int i = 1; i < N + 1; i++) {
			Arrays.fill(minValue[i], Integer.MAX_VALUE);
			minValue[i][i] = 0;
			PriorityQueue<Node> pQueue = new PriorityQueue<>();
			pQueue.offer(new Node(i, minValue[i][i]));
			
			while(!pQueue.isEmpty()) {
				Node current = pQueue.poll();
				if(check[i][current.number]) continue;
				check[i][current.number] = true;
				
				for(Node tmp = computer[current.number]; tmp != null; tmp = tmp.next) {
					if(!check[i][tmp.number] && minValue[i][tmp.number] > minValue[i][current.number] + tmp.weight) {
						minValue[i][tmp.number] = minValue[i][current.number] + tmp.weight;
						pQueue.offer(new Node(tmp.number, minValue[i][tmp.number]));
					}
				}			
			}
		}
    // 각각의 마을에서 파티가 열리는 마을까지 가고 오는 최단 경로 구하기
		int[] distance = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			distance[i] = minValue[i][X] + minValue[X][i];
		}
		Arrays.sort(distance);
		System.out.println(distance[N]);
	}
}
