import java.io.*;
import java.util.*;
public class Main {
	public static class Edge{
		int vertex,weight;
		public Edge(int v, int w) {
			vertex = v;
			weight = w;
		}
	}
	
	static ArrayList<Edge>[] graph;
	static int[] goalDist;
	static int N,M,X;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			graph[i]= new ArrayList<Edge>();
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to,weight));
		}
		dijkstra(X);
		int maxCost =0;
		for(int i=1;i<=N;i++) {
			if(i==X) continue;
			maxCost = Math.max(maxCost,dijkstra(i));
		}
		System.out.println(maxCost);


	}
	
	public static int dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<Main.Edge>((v1,v2) -> v1.weight - v2.weight);
		int[] dist = new int[N+1];
		
		Arrays.fill(dist, 987654321);
		dist[start] = 0;
		pq.offer(new Edge(start,0));
//		while(!pq.isEmpty()) {
//			Edge now = pq.poll();
//			for(Edge next: graph[now.vertex]) {
//				if(dist[next.vertex] > now.weight + next.weight) {
//					dist[next.vertex] = dist[now.vertex] + next.weight;
//					pq.offer(new Edge(next.vertex, dist[next.vertex]));
//				}
//			}
//		}
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			for(int i=0;i< graph[now.vertex].size();i++) {
				Edge next = graph[now.vertex].get(i);
				if(dist[next.vertex] > dist[now.vertex] + next.weight) {
					dist[next.vertex] = dist[now.vertex] + next.weight;
					pq.offer(new Edge(next.vertex, dist[next.vertex]));
				}
			}
		}
		if(start == X) goalDist = dist.clone();
		return dist[X] + goalDist[start];
		
	}

}
