package b11286;
import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) > Math.abs(o2)) return 1;
				else if( Math.abs(o1) < Math.abs(o2)) return -1;
				else {
					return o1 - o2;
				}
			}
		});
		
		int n = Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) {
			int num = Integer.parseInt(br.readLine());
			if(num==0) {
				if(pq.size() > 0) {
					sb.append(pq.poll()+ "\n");
				} else sb.append(0 + "\n");
			} else {
				pq.add(num);
			}
		}
		System.out.println(sb.toString());
		
	}

}
