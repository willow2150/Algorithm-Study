
import java.io.*;
import java.util.*;

public class Main {
	public static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int weight;
		public Edge(int s,int e, int w) {
			this.start = s;
			this.end = e;
			this.weight = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}
	static int[] parents;
	static Edge[] edgeList;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if(m==0 && n==0) break;
			
			parents = new int[m+1];
			for(int i=1;i<=m;i++) {
				parents[i] = i;
			}
			edgeList = new Edge[n];
			int totalWeight = 0;
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				totalWeight += w;
				edgeList[i] = new Edge(s,e,w);
			}
			Arrays.sort(edgeList);
			int minWeight = 0;
			int cnt = 0;
			for(Edge edge: edgeList) {
				if(union(edge.start,edge.end)) {
					minWeight += edge.weight;
					if(++cnt == m-1) break;
				}
			}
			System.out.println(totalWeight-minWeight);
		}
	}
	public static int find(int a) {
		if(parents[a] == a) return a;
		else return parents[a] = find(parents[a]);
	}
	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		parents[a] = b;
		return true;
	}

}
