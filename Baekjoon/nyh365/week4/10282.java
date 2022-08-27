import java.io.*;
import java.util.*;

public class Main {
	static int N,D,C;
	
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
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			Node[] computer = new Node[N+1];
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				computer[b] = new Node(a, s, computer[b]);
			}
			
			boolean check[] = new boolean[N + 1];
			int[] minValue = new int[N + 1];
			Arrays.fill(minValue, Integer.MAX_VALUE);
			minValue[C] = 0;
			PriorityQueue<Node> pQueue = new PriorityQueue<>();
			pQueue.offer(new Node(C, minValue[C]));
			
			while(!pQueue.isEmpty()) {
				Node current = pQueue.poll();
				if(check[current.number]) continue;
				check[current.number] = true;
				
				for(Node tmp = computer[current.number]; tmp != null; tmp = tmp.next) {
					if(!check[tmp.number] && minValue[tmp.number] > minValue[current.number] + tmp.weight) {
						minValue[tmp.number] = minValue[current.number] + tmp.weight;
						pQueue.offer(new Node(tmp.number, minValue[tmp.number]));
					}
				}			
			}
			int cnt = 0, time = Integer.MIN_VALUE;
			for(int i = 1; i < N + 1; i++) {
				if(minValue[i] != Integer.MAX_VALUE) {
					cnt++;
					time = Math.max(time, minValue[i]);
				}
			}
			sb.append(cnt).append(" ").append(time).append("\n");
		}
		System.out.println(sb);
	}
}
