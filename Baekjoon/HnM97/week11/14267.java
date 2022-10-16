import java.io.*;
import java.util.*;
public class Main {
	static ArrayList<Node> nodes;
	public static class Node{
		int boss,cmpl;
		ArrayList<Integer> unders;
		public Node(int boss, int cmpl, ArrayList<Integer> unders) {
			super();
			this.boss = boss;
			this.cmpl = cmpl;
			this.unders = unders;

		}
		public Node() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Node [boss=" + boss + ", cmpl=" + cmpl + ", unders=" + unders + "]";
		}
		
		
	}
	static int N,M;
	static StringBuilder sb  = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nodes= new ArrayList<Node>();
		for(int i=0;i<=N;i++) {
			ArrayList<Integer> unders = new ArrayList<Integer>();
			nodes.add(new Node(0,0,unders));
		}
//		System.out.println(nodes.get(2));
		st = new StringTokenizer(br.readLine());
		int start = 0;
		for(int i=1;i<=N;i++) {
			int boss = Integer.parseInt(st.nextToken());
			if(boss==-1) {
				continue;
			}
//			System.out.println(i + " " + boss);
			nodes.get(i).boss = boss;
			nodes.get(boss).unders.add(i);
		}

		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int slave = Integer.parseInt(st.nextToken());
			int cmpl = Integer.parseInt(st.nextToken());
			nodes.get(slave).cmpl += cmpl;
		}

//		for(Node n: nodes) {
//			System.out.println(n);
//		}
//		System.out.println("==============");
		dfs(1);
//		for(Node n: nodes) {
//			System.out.println(n);
//		}
		int[] ans = new int[N+1];
		for(int i=1;i<=N;i++) {
			ans[i] = nodes.get(i).cmpl;
		}
		for(int i=1;i<=N;i++) {
			sb.append(ans[i]+ " ");
		}
		System.out.println(sb.toString());
		


	}
	public static void dfs(int start) {
		Node boss = nodes.get(start);
//		System.out.println(boss.unders);
		for(int n: boss.unders) {
			Node under = nodes.get(n);
			under.cmpl += boss.cmpl;
			dfs(n);
		}

	}

}