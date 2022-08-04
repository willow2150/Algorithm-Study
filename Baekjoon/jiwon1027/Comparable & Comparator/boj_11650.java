package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class boj_11650 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		List<Pair> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			list.add(new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
		}

		Collections.sort(list);
		
		for (Pair p : list)
			System.out.println(p);

	}
}

//Pair로 할 수 있지만 2차원 배열로도 할 수 있음
class Pair implements Comparable<Pair> {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Pair p) {
		if (this.x == p.x)
			return Integer.compare(this.y, p.y);
		else
			return Integer.compare(this.x, p.x);
	}

	@Override
	public String toString() {
		return x + " " + y;
	}

}
