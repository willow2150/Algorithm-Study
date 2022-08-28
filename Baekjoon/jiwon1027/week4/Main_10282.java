package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_10282 {
	static List<int[]>[] data;
	static boolean[] visited;
	static int[] dist;

	/*
	 * 가중치 있는 방향 그래프 특정 노드에서 시작해서 다른 모든 노드까지의 경로를 모두 더함 => 다익스트라 알고리즘
	 * 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int t = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			data = new ArrayList[n + 1];
			dist = new int[n + 1];
			visited = new boolean[n + 1];

			for (int i = 1; i < n + 1; i++) {
				dist[i] = Integer.MAX_VALUE;
				data[i] = new ArrayList<>();
			}

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());

				data[b].add(new int[] { a, s });
			}

			dijkstra(c);

			int infection = 0;
			int total = 0;

			for (int i = 1; i < n + 1; i++) {
				if (dist[i] != Integer.MAX_VALUE) {
					infection++;
					total = Math.max(total, dist[i]);
				}
			}

			System.out.println(infection + " " + total);

		}

	}

	public static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
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
	}

}
