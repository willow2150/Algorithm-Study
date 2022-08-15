package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_2697 {

	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			StringBuilder sb = new StringBuilder();
			
			String[] input = br.readLine().split("");
			
			List<Integer> list = new ArrayList<>();
			
			for(String x:input)
				list.add(Integer.parseInt(x));
			
			int idx = 0;
			
			for (int i = list.size()-1; i > 0; i--) {
				if (list.get(i-1) < list.get(i)) {
					idx = i-1;
					break;
				}
			}
			List<Integer> front = new ArrayList<>(list.subList(0, idx));
			List<Integer> back = new ArrayList<>(list.subList(idx, list.size()));
			

			if ((front.size() == 0) || (back.size()==0))
				System.out.println("BIGGEST");
			else {
				back.sort(null);
				for (int i = 0; i < back.size(); i++) {
					if (back.get(i) > list.get(idx)) {
						front.add(back.get(i));
						back.remove(i);
						break;
					}
				}
				
				for(int x:front)
					System.out.print(x);
				for(int x:back)
					System.out.print(x);
				System.out.println();
			}


			
		}
		
		
		
		
	}

}
