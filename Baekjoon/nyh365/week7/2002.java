import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int result = 0;
		int N = Integer.parseInt(br.readLine());
		List<String> list = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			list.add(br.readLine());
		}
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			int index = list.indexOf(input);
			if(index == 0) {
				list.remove(index);
			}
			else if(index > 0){
				result++;
				list.remove(index);
			}
		}
	
		System.out.println(result);
	}
}
