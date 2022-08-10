import java.io.*;
import java.util.*;

public class Main {
	static int[] tri = new int[45];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < 45; i++) {
			tri[i] = i * (i + 1) / 2;
		}
		for(int round = 0; round < N; round++) {
			int num = Integer.parseInt(br.readLine());
			if(find(num)) sb.append("1").append("\n");
			else sb.append("0").append("\n");
		}
		
		System.out.println(sb);
	}
	public static boolean find(int num) {
		for(int i = 1; i < 45; i++) {
			for(int j = 1; j < 45; j++) {
				for(int k = 1; k < 45; k++) {
					if(tri[i] + tri[j] + tri[k] == num) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
