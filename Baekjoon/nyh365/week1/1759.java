import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int L;
	static char[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		arr = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) 
			arr[i] = st.nextToken().charAt(0);
		Arrays.sort(arr);
		boolean[] check = new boolean[C];
		func1(check, 0, 0, 0, 0);
		
	}
	
	public static void func1(boolean[] check, int current, int mo, int ja, int length) {
		if(length == L) {
			if(mo >= 1 && ja >= 2) {
				Print(check);
				return;
			}
			return;
		}
		if(current == arr.length)
			return;
		
		
		boolean flag = false;
		check[current] = true;
		if(arr[current] == 'a' || arr[current] == 'e' || arr[current] == 'i' || arr[current] == 'o' || arr[current] == 'u') {
			flag = true;
			mo++;
		} else {
			ja++;
		}
		func1(check, current + 1, mo, ja, length + 1);
		if(flag)
			mo--;
		else
			ja--;
		
		check[current] = false;
		func1(check, current + 1, mo, ja, length);
		
	}
	
	public static void Print(boolean[] check) {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < check.length; i++) {
			if(check[i])
				s.append(arr[i]);
		}
		s.append("\n");
		System.out.print(s);
	}
}
