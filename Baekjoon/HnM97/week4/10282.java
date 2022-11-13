import java.io.*;
import java.util.*;
public class Main {
	public static class Node{
		int vertex;
		int weight;
		Node next;
		public Node(int v, int w,Node next) {
			this.vertex = v;
			this.weight = w;
			this.next = next;
		}

	}

	public static class Edge{
		int start;
		int end;
		int weight;
		public Edge(int s,int e, int w) {
			this.start = s;
			this.end= e;
			this.weight = w;
		}

	}

	public static class Pair implements Comparable<Pair>{
		int vertex;
		int time;
		public Pair(int v, int time) {
			this.vertex=v;
			this.time=time;
		}
		@Override
		public int compareTo(Pair o) {
			return this.time-o.time;
		}

	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for(int t=0;t<tt;t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			Node[] nodeList = new Node[n+1];
			for(int i=0;i<d;i++) {
				st= new StringTokenizer(br.readLine());
				int e =Integer.parseInt(st.nextToken());
				int s =Integer.parseInt(st.nextToken());
				int w =Integer.parseInt(st.nextToken());
				nodeList[s] = new Node(e,w,nodeList[s]);
			}
			boolean[] visited = new boolean[n+1];
			int[] dist = new int[n+1];
			for(int i=0;i<n+1;i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			dist[c] = 0;
			for(int i=1;i<=n;i++) {
				int nodeVal = Integer.MAX_VALUE;
				int nodeIdx = 0;
				for(int j=1;j<=n;j++) {
					if(!visited[j] && dist[j] < nodeVal) {
						nodeVal = dist[j];
						nodeIdx = j;
					}
				}
				visited[nodeIdx] = true;
				for(Node temp=nodeList[nodeIdx];temp!=null;temp=temp.next) {
					if(!visited[temp.vertex] && dist[temp.vertex] > dist[nodeIdx] + temp.weight) {
						dist[temp.vertex] = dist[nodeIdx] + temp.weight;
					}
				}
			}
			int cnt=0;
			int maxTime = 0;
			for(int i=1;i<=n;i++) {
				if(dist[i] != Integer.MAX_VALUE) {
					cnt++;
					maxTime = Math.max(maxTime, dist[i]);
				}
			}
			System.out.println(cnt + " " + maxTime);
		}
	}

}
