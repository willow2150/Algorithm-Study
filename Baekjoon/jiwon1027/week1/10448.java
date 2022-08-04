package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_10448 {
	static List<Integer> tn = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 1; i < 45; i++) {
			tn.add(i*(i+1)/2);
		}
		
		
		int n  = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(br.readLine());
			System.out.println(fun(temp));
		}
	}

	public static int fun(int x) {
		for (int one:tn) {
			for (int two:tn) {
				for (int three:tn) {
					if (one+two+three == x)
						return 1;
				}
			}
		}
		return 0;
	}
}
