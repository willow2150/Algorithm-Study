package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class boj_11650_1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] data = new int[n][2];

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			data[i][0] = Integer.parseInt(input[0]);
			data[i][1] = Integer.parseInt(input[1]);
		}

		Arrays.sort(data,new Comparator<int[]>() {
			@Override
			public int compare(int[] s1, int[] s2) {
				if (s1[0] == s2[0])
					return s1[1] - s2[1];
				else
					return s1[0] - s2[0];
			}
			
		});
		
		for(int[] row :data)
			System.out.println(row[0] + " " + row[1]);
		
		

	}
}

