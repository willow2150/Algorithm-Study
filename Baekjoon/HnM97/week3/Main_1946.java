package b1946;
import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int tt = Integer.parseInt(br.readLine());
		ArrayList<Pair> persons;
		for(int t=0;t<tt;t++) {
			int n = Integer.parseInt(br.readLine());
			persons = new ArrayList<>();
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				persons.add(new Pair(x,y));
			}
			int res = 0;
			Collections.sort(persons);
//			for(int i=0;i<n;i++) System.out.print(persons.get(i).x + " ");
//			System.out.println();
			boolean flag = true;
			int min_y=100001;
			for(int i=0;i<n;i++) {
				Pair p = persons.get(i);
				if(min_y > p.y) res++;
				min_y = Math.min(min_y, p.y);
				
			}
			sb.append(res).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();


	}

	public static class Pair implements Comparable<Pair>{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y= y;
		}
		@Override
		public int compareTo(Pair o) { 
			return this.x - o.x;
		}
	}
}
