package s7733;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1 {
    static int N;
    static int[][] board;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            int maxCheese = 0;
            int ans = 0;
            for(int i=0;i<N;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    maxCheese = Math.max(maxCheese, board[i][j]);
                }
            }
            for(int cVal=1;cVal<=maxCheese;cVal++) {
                int sum = 0;
                boolean[][] visited = new boolean[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (board[i][j] > cVal && !visited[i][j]) {
                            bfs(i, j, visited,cVal);
                            sum++;
                        } else{
                            visited[i][j] = true;
                        }
                    }

                }
                ans = Math.max(ans, sum);
            }
            if(ans < 1) ans = 1;
            sb.append("#"+t).append(" "+ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs(int sx,int sy, boolean[][] visited, int cVal) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx,sy});
        visited[sx][sy] = true;

        while(!q.isEmpty()){
            int[] now = q.poll();
            int x= now[0];
            int y = now[1];

            for(int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny= y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || board[nx][ny] <= cVal ) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx,ny});
            }
        }
    }


}
