package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/* 걸린시간 : 20분
 * 
 * 순서를 지키면서 문제를 풀어야하기 때문에 stack, queue를 써서 뭔가 해보면 좋겠다는 생각을 처음으로 하고
 * 계속해서 next step으로 파고 들어야되니까 dfs, bfs문제구나라고 생각했다.
 * 
 * 위의 내용 자체가 위상정렬에 대한 이론이랑 똑같음. 즉 위상정렬 기본 문제!
 * 
 * 해당 풀이는 BFS(Queue)로 푼 풀이
 * DFS로 도전을 해봤는데 9%에서 자꾸 틀린다. 계속 해결해보려고 하는데 해결하지 못했다ㅠ
 * 
 * */
public class Main_2623_bfs {
	static int n,m;
	static List<Integer>[] board;
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

		bfs();
		
		if(res.size() == n) {
			for(Integer num:res)
				System.out.println(num);
		}
		else
			System.out.println(0);
	}
	
	
	public static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i < n+1; i++) {
			if(data[i]==0)
				queue.add(i);
		}
		
		
		while(!queue.isEmpty()) {
			int v = queue.poll();
			res.add(v);
			
			for(Integer num:board[v]) {
				data[num]--;	
				
				if(data[num]==0)
					queue.add(num);
			}
		}
		return;
		
		
	}

}