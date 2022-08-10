import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		int M = Integer.parseInt(st.nextToken()); //높이
		int N = Integer.parseInt(st.nextToken()); //넓이
		int K = Integer.parseInt(st.nextToken());
		List<Integer> result_list = new ArrayList<>();
		
		boolean[][] area = new boolean[M][N];
		int[] loc = new int[4];
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				loc[i] = Integer.parseInt(st.nextToken());
			}
			for(int i = loc[0]; i < loc[2]; i++) {
				for(int j = loc[1]; j < loc[3]; j++) {
					area[j][i] = true;
				}
			}
		}
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!area[i][j]) {
					int wide = 0;
					queue.add(j);
					queue.add(i);
					wide++;
					area[i][j] = true;
					while(!queue.isEmpty()) {
						int x = queue.remove();
						int y = queue.remove();
						for(int k = 0; k < 4; k++) {
							int xx = x + dx[k];
							int yy = y + dy[k];
							if(xx >=0 && xx < N && yy >= 0 && yy < M) {
								if(!area[yy][xx]) {
									area[yy][xx] = true;
									wide++;
									queue.add(xx);
									queue.add(yy);
								}
							}
						}
					}
					result_list.add(wide);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(result_list.size()).append("\n");
		Collections.sort(result_list);
		for(int e : result_list) {
			sb.append(e).append(" ");
		}
		
		System.out.println(sb);
	}
}
