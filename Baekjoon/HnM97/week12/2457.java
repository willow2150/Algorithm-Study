package b2457;
import java.util.*;
import java.io.*;
public class Main {
	static class Flower implements Comparable<Flower> {
		int s, e;
		public Flower(int s, int e) {
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Flower o) {
			// if(this.s == o.s) {
			// 	return this.e - o.e;
			// }
			return this.s - o.s;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Flower> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			list.add(new Flower(s, e));		
		}
		Collections.sort(list);
		int ans=0;
		int fidx=0;
		int start=301;
		int maxEnd=0;
		while(start<1201) {
			maxEnd=0;
			boolean flag =false;
			for(int i=fidx;i<n;i++) {
				Flower cur= list.get(i);
				if(cur.s > start) break;
				if(cur.s <= start && maxEnd<cur.e) {
					maxEnd = cur.e;
					fidx=i+1;
					flag = true;
				}
			}
			if(flag) {
				start=maxEnd;
				ans++;
			}else
				break;
		}
		if(maxEnd<1201)
			System.out.println(0);
		else
			System.out.println(ans);
	}
}
