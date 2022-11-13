package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class boj_10814 {

	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		
		List<Pair1> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			list.add(new Pair1(Integer.parseInt(input[0]),input[1]));
		}
		
		Collections.sort(list);
		
		for(Pair1 p:list)
			System.out.println(p);
		
		
	}

}

class Pair1 implements Comparable<Pair1> {
	int age;
	String name;

	public Pair1(int age, String name) {
		this.age = age;
		this.name = name;
	}

	@Override
	public int compareTo(Pair1 p) {
		return Integer.compare(this.age, p.age);
	}

	@Override
	public String toString() {
		return age + " " + name;
	}

}