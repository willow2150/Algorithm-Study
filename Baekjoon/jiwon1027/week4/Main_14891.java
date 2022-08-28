package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14891 {
	static List<Integer>[] data;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		data = new ArrayList[5];
		
		for (int i = 1; i <5; i++) 
			data[i] = new ArrayList<>();
		
		for (int i = 1; i < 5; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < 8; j++) 
				data[i].add(Integer.parseInt(input[j]));
		}

		
		//1(3) - 2(7)
		//2(3) - 3(7)   이 경우 살펴보면 될 듯
		//3(3) - 4(7)
		
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int direct = Integer.parseInt(st.nextToken());
			
			left_rotate(num-1,-direct);
			right_rotate(num+1,-direct);
			Collections.rotate(data[num], direct);			
		}
		
		int res = 0;
		
		for (int i = 0; i < 4; i++) { 
			res += data[i+1].get(0) * Math.pow(2, i);
		}
		System.out.println(res);
		
	}
	
	public static void left_rotate(int num, int direct) {
		if ((num<1))
			return;
		if (data[num].get(2) != data[num+1].get(6)) {
			left_rotate(num-1,-direct);
			Collections.rotate(data[num], direct);
		}
	}
	
	public static void right_rotate(int num, int direct) {
		if ((num>4))
			return;
		if (data[num-1].get(2) != data[num].get(6)) {
			right_rotate(num+1,-direct);
			Collections.rotate(data[num], direct);
		}
	}

}
