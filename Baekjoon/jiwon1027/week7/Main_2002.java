package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/* 걸린 시간 : 30분
 * key-value를 써야된다는 생각을 바로 했다.
 * 
 * 이 차들이 추월했다는건 어떤 의미인가? => 나보다 높은 순위에 있는애들이 뒤에 있을때
 * */

public class Main_2002 {

	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Map<String,Integer> map = new HashMap<>();

		for (int i = 1; i < n+1; i++) {
			map.put(br.readLine(), i);
		}
		
		String[] exit = new String[n]; 
		
		for (int i = 0; i < n; i++) {
			exit[i] = br.readLine();
		}
		
		int res = 0;
		
		for (int i = 0; i < n; i++) {
			int std = map.get(exit[i]);
			for (int j = i+1; j < n; j++) {
				if (std > map.get(exit[j])) {
					res++;
					break;
				}
			}
		}

		System.out.println(res);
		
		
		
	}

}
