package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_6497_1 {
	static int n, m, total;
	static List<int[]>[] data;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			if (n == 0 && m == 0)
				break;

			visited = new boolean[n];
		    data = new ArrayList[n];
			

			 //초기화
		    for (int i = 0; i < n; i++) {
				data[i] = new ArrayList<>();
			}

			total = 0;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				data[from].add(new int[] {to, cost});
		    	data[to].add(new int[] {from, cost});
		    	
				total += cost;

			}
			prim();
			System.out.println(total);
		}
	}

	public static void prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
		visited[0] = true;
		int cnt = 1;
		pq.addAll(data[0]);

		while (!pq.isEmpty()) {
			int[] temp = pq.poll();
			int to = temp[0];
			int cost = temp[1];

			if (!visited[to]) {
				visited[to] = true;
				total -= cost;
				cnt++;
				
				if (cnt==n)
					return;
				
				pq.addAll(data[to]);
			}

		}

	}

}
