import java.io.*;
import java.util.*;

public class Main {
	static int[] parent;
	static Edge[] edge;
  static int M, N;
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true) {
        	st = new StringTokenizer(br.readLine());
        	M = Integer.parseInt(st.nextToken());
          N = Integer.parseInt(st.nextToken());
          if(M == 0 && N == 0)
            	break;
          parent = new int[M];
            edge = new Edge[N];
            long total = 0;
            for(int i = 0; i < N; i++) {
            	st = new StringTokenizer(br.readLine());
            	int from = Integer.parseInt(st.nextToken());
            	int to = Integer.parseInt(st.nextToken());
            	int dis = Integer.parseInt(st.nextToken());
            	total += dis;
            	edge[i] = new Edge(from, to, dis);
            }
            make();
            Arrays.sort(edge);
            int result = 0;
            for(int i = 0; i < N; i++) {
            	if(union(edge[i].from, edge[i].to)) {
            		result += edge[i].dis;
            	}
            }
            sb.append(total - result).append("\n");  
     }
     System.out.println(sb);
	}   
	public static void make() {
		for(int i = 0; i < M; i++) {
			parent[i] = i;
		}
	}
	public static int find(int a) {
		if(a == parent[a]) return a;
		else {
			return parent[a] = find(parent[a]);
		}
	}
	public static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		
		if(aP == bP) return false;
		else {
			parent[bP] = aP;
			return true;
		}
	}
}
class Edge implements Comparable<Edge>{
	int from;
	int to;
	int dis;

	public Edge(int from, int to, int dis) {
		super();
		this.from = from;
		this.to = to;
		this.dis = dis;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.dis, o.dis);
	}
}
