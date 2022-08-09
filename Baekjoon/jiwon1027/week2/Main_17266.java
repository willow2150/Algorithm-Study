package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_17266 {

	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		String[] input = br.readLine().split(" ");
		int[] data = new int[m];
		for (int i = 0; i < m; i++) {
			data[i] = Integer.parseInt(input[i]);
		}
		
		int max_size = 0;
		
		for (int i = 1; i < m; i++)
			max_size = Math.max(max_size,data[i]-data[i-1]);
		
		int[] res = {(max_size+1)/2, data[0],n-data[m-1]};
		Arrays.sort(res);
		System.out.println(res[2]);
		
		
		
		
		
		
	}

}
