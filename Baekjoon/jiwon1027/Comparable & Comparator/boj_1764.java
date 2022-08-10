import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		
		Map<String,Integer> data1 = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			data1.put(br.readLine(),1);
		}
		
		List<String> res = new LinkedList<>();
		
		for (int i = 0; i < m; i++) {
			String temp = br.readLine();
			
			if (data1.containsKey(temp))
				res.add(temp);
		}

		Collections.sort(res);
		
		System.out.println(res.size());
		for(String x:res)
			System.out.println(x);
		
		
		
	}

}
