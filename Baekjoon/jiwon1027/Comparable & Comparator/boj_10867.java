import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Set<Integer> data = new HashSet<>();
		String[] input = br.readLine().split(" ");
		
		for (int i = 0; i < n; i++) {
			data.add(Integer.parseInt(input[i]));
		}
		
		List<Integer> res = new ArrayList<>(data);
		
		Collections.sort(res);
		
		for(int x:res)
			System.out.print(x + " ");
		
	}

}
