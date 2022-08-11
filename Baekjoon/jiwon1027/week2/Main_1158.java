package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;



//1. 범위를 벗어나면 list.size()로 나눈 나머지 만큼 index를 갱신 해주는 방법
//2. 원형 구조는 Queue의 구조를 띄고 있다는 성질 이용
public class Main_1158 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();

		int index = k-1;
		for (int i = 1; i < n+1; i++) 
			queue.add(i);
		
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		
		while (queue.size() > 1) {
			
			for (int i = 0; i < k-1; i++)
				queue.add(queue.poll());
			
			sb.append(queue.poll()).append(", ");
			
			
		}
		sb.append(queue.poll()).append(">");
		
		System.out.println(sb);
		
	}

}
