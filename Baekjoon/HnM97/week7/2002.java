package b2002;
import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> inMap = new HashMap<String, Integer>();
		HashMap<String, Integer> outMap = new HashMap<String, Integer>();
		String[] inOrder = new String[N];
		boolean[] checked = new boolean[N];
		
		for(int i=0;i<N;i++) {
			inOrder[i] = br.readLine();
			inMap.put(inOrder[i], i);
		}
		for(int i =0;i<N;i++) {
			outMap.put(br.readLine(), i);
		}
		int res = 0;
		
		for(int i=0;i<N;i++) {
			if(checked[i]==true) {
				continue;
			}
			else if(outMap.get(inOrder[i]) == i) {
				checked[i] = true;
			}
			else {
				// map(0)
				for(int j=N-1;j>=i;j--) {
					if(outMap.get(inOrder[j]) < outMap.get(inOrder[i]) && checked[j] == false) {
						res ++;
						checked[j] = true;
					}
				}
			}
		}
		System.out.println(res);
	}

}
