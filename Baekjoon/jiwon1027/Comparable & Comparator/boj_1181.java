package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class boj_1181 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Set<String> temp = new HashSet();

		for (int i = 0; i < n; i++) {
			temp.add(br.readLine());
		}
		
		List<String> list = new ArrayList<>(temp);

		
		
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String s1,String s2) {
				if (s1.length() == s2.length())
					return s1.compareTo(s2);
				else
					return s1.length() - s2.length();
			}
		});
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		

	}

}
