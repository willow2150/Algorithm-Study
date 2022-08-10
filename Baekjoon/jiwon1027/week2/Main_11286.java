package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_11286 {

	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0])
					return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
		});
		

		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(br.readLine());
			if (temp != 0)
				pq.add(new int[] {Math.abs(temp),temp});
			else {
				try {
					System.out.println(pq.poll()[1]);	
				}catch (Exception e) {
					System.out.println(0);
				}
				
			}
		}
		

		
		
		
		
		
		
	}

}
