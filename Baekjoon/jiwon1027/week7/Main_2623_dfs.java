package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;



/* DFS로 풀어본 코드
 * 
 * 틀린 코드입니다! (9%에서 틀림)
 * 
 * 
 * 
 * */
public class Main_2623_dfs{
	static int n,m;
	static List<Integer>[] board;
	static Stack<Integer> stack;
	static boolean[] visited;
	static List<Integer> res;
	static int[] data;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	
		board = new ArrayList[n+1];
		for (int i = 1; i <n+1; i++) {
			board[i] = new ArrayList<>();
		}
		data = new int[n+1];

		visited = new boolean[n+1];
		stack = new Stack<>();
		res = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			
			for (int j = 1; j < Integer.parseInt(input[0]); j++) {
				int from = Integer.parseInt(input[j]);
				int to = Integer.parseInt(input[j+1]);
				board[from].add(to);
				data[to]++;

				}
		}

		topologySort();
		
		if(res.size()==n) {
			for (int i = n-1; i >=0; i--) {
				System.out.println(res.get(i));
			}
		}
		else
			System.out.println(0);

	}
	
	public static void topologySort() {
        for(int i = 1; i < n+1; i++) {
            if(!visited[i] && data[i]==0){
                dfs(i);
            }
        }
	}
	
	
	

	public static void dfs(int i) {
		if (visited[i])
			return;
		
        visited[i] = true;

        for(int v : board[i]){
            if(!visited[v]){
            	data[v]--;
                dfs(v);
            }
        }
        res.add(i);
    }

}