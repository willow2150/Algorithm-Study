import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		Map<String, Integer> map = new HashMap<>(); //키값에 대하여 정렬
	
		
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			map.put(input, map.getOrDefault(input, 0)+1);
		}

		
		//System.out.println(map.toString());
		
		List<Map.Entry<String, Integer>> temp = new ArrayList<>(map.entrySet());
		List<Pair> list = new ArrayList<>();

		for (int i = 0; i < temp.size(); i++) {
			list.add(new Pair(temp.get(i).getKey(), temp.get(i).getValue()));
		}
		
		Collections.sort(list);
		
		//System.out.println(list.toString());
		System.out.println(list.get(0).key);
		
	}

}

class Pair implements Comparable<Pair>{
	String key;
	int value;
	
	public Pair(String key, int value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public int compareTo(Pair o1) {
		if (this.value == o1.value)
			return this.key.compareTo(o1.key);
		else
			return o1.value - this.value;
	}

	@Override
	public String toString() {
		return key + " "+ value;
	}
	
	
	
}
