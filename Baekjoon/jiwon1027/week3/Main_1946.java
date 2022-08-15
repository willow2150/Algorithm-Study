package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1946 {

	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] data = new int[n][2];
			
			for (int i = 0; i < n; i++) {
				String[] input = br.readLine().split(" ");

				data[i][0] = Integer.parseInt(input[0]);
				data[i][1] = Integer.parseInt(input[1]);
			}
			
			Arrays.sort(data,(o1,o2)->o1[0]-o2[0]);
			
			int std = data[0][1];
			int res = 1;
			
			for (int i = 0; i < n; i++) {
				if (data[i][1] < std) {
					res++;
					std = Math.min(std, data[i][1]);
				}
			}
			
			System.out.println(res);
			
			
			
			
			
			
			
			
		}
		
		
		
	}

}
