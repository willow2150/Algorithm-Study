package b2697;
import java.io.*;
import java.util.*;
public class Main {
	static int[] numbers;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		for(int t=0;t<n;t++) {
			String num = br.readLine();
			numbers = new int[num.length()];
			boolean flag = false;
			for(int i=0;i<num.length();i++) {
				numbers[i] = num.charAt(i) - '0';
			}
			do {
				if(num.equals(Arrays.toString(numbers).replaceAll("[^0-9]", ""))) {
					flag = true;
				}
			}while(np(flag));
		}
	}
	public static boolean np(boolean flag) {
		int N = numbers.length;
		int i = N-1;
		while(i>0 && numbers[i-1] >= numbers[i]) --i;
		
		if(i==0) {
			if(flag) System.out.println("BIGGEST");
			return false;
		}
		int j = N-1;
		while(numbers[i-1]>= numbers[j]) --j;
		swap(i-1,j);
		int k  = N-1;
		while(i<k) swap(i++,k--);
		
		if(flag) {
			System.out.println(Arrays.toString(numbers).replaceAll("[^0-9]", ""));
			return false;
		}
		return true;
	}
	
	public static void swap(int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}

}
