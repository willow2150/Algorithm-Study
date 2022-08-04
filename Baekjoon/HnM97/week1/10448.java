import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tt = Integer.parseInt(br.readLine());
		ArrayList<Integer> tri = new ArrayList<Integer>(); 
		int[] ans = new int[tt];
		tri.add(1);
		for(int t=0;t<tt;t++) {
			int n = Integer.parseInt(br.readLine());
			int res = 0;
			while(tri.get(tri.size()-1) < n) {
				int size =tri.size();
				tri.add(size*(size+1)/2);
			}
			for(int i=0;i<tri.size();i++) {
				int first = tri.get(i);
				for(int j=0;j<tri.size();j++) {
					int second = tri.get(j);
					for(int k=0;k<tri.size();k++) {
						int third = tri.get(k);
//						System.out.println(first+second+third);
						if(first+second+third == n) {
							res = 1;
						}
					}
				}
			}
			ans[t] = res;
		}
		for(int a: ans) {
			System.out.println(a);
		}
	}
}
