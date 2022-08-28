package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//MST가 아니니까 최단 거리 알고리즘 해야되는데 플로이드와샬 O(1000)^3이라 안됨

public class Main_1238 {
	static List<int[]>[] go_data, back_data;

	static int n,m,x;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		go_data = new ArrayList[n+1];
		back_data = new ArrayList[n+1];

		
		for (int i = 1; i < n + 1; i++) {
			go_data[i] = new ArrayList<>();
			back_data[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			go_data[from].add(new int[] {to,cost});
			back_data[to].add(new int[] {from,cost});
		}
		

		int[] go = dijkstra(go_data,x);
		int[] back = dijkstra(back_data,x);

		System.out.println(Arrays.toString(go));
		System.out.println(Arrays.toString(back));

		int res = Integer.MIN_VALUE;
		
		for(int i=1; i<n+1; i++) {
			int dis = go[i] + back[i];
			if(dis > res) { 
				res = dis;
			}
		}
		
		System.out.println(res);

	}
	
	
	public static int[] dijkstra(List<int[]>[] data, int start) {
		boolean[] visited = new boolean[n+1];

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
		int[] dist = new int[n+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		
		dist[start] = 0;
		pq.add(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] temp = pq.poll();
			int to = temp[0];
			int cost = temp[1];
			
			if (!visited[to]) {
				visited[to] = true;
				for (int[] next : data[to]) {
					if (dist[next[0]] > cost + next[1]) { //cost == dist[to]
						dist[next[0]] = cost + next[1];
						pq.add(new int[] { next[0], dist[next[0]] });
					}
				}

			}
		}
		
		return dist;
	}
	
	

}
