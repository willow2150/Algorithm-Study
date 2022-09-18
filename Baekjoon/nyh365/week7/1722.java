import java.io.*;
import java.util.*;

public class Main {
	static int[] num;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		int problem = Integer.parseInt(st.nextToken());
    
    // 순열이 주어졌을 때 몇 번째 순열인지 구하는 
		if(problem == 1) {
			List<Integer> list = new LinkedList<>();
			for(int i = 1; i <= N; i++) {
				list.add(i);
			}
			Long k = Long.parseLong(st.nextToken());
			Long sum = 1L;
			for(int i = 1; i < N; i++) {
				sum *= i;
			}
			int index = 0;
			if(N == 1) {
				num[index] = 1;
			}
			else {
				for(int i = N - 1; i > 0; i--) {
					int a = (int)(k / sum);
					k %= sum;
					if(k == 0) {
						num[index++]= list.get(a-1);
						list.remove(a-1);
						int size = list.size();
						for(int j = size-1; j >= 0; j--) {
							num[index++] = list.get(j);
							list.remove(j);
						}
						break;
					}
					num[index++] = list.get(a);
					list.remove(a);
					sum /= i;
				}
			}
			System.out.println(Print());
		}
    // 순서가 주어졌을 때 주어진 순서의 순열을 구하는 부분
		else {
			List<Integer> list1 = new LinkedList<>();
			List<Integer> list2 = new LinkedList<>();
			Long result = 1L;
			for(int i = 0; i < N; i++) {
				int input = Integer.parseInt(st.nextToken());
				list1.add(input);
				list2.add(input);
			}
			Collections.sort(list2);
			Long sum = 1L;
			for(int i = 1; i < N; i++) {
				sum *= i;
			}
			
			for(int i = N-1; sum > 0 && i > 0; i--) {
				result += sum * list2.indexOf(list1.get(0));
				list2.remove(list2.indexOf(list1.get(0)));
				list1.remove(0);
				sum /= i;
			}
			
			System.out.println(result);
		}
	}
	public static StringBuilder Print() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < num.length; i++)
			sb.append(num[i]).append(" ");
		return sb;
	}
}
