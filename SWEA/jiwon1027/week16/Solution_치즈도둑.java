import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 100일동안 치즈를 갉아먹는데, X번째날에는 맛있는 정도가 X인 칸을 먹어버린다.
* 
* X번째날에 대한 방문처리를 누적으로 쌓아가면서 bfs를 돌려서 영역 구하기
*
* */

public class Solution {
    static int N,res,max_value;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            visited = new boolean[N][N];
            board = new int[N][N];
            max_value = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    max_value = Math.max(max_value, board[i][j]);
                }
            }

            res = 1;
            for (int k = 1; k < max_value+1; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (board[i][j] == k){
                            visited[i][j] = true;
                        }
                    }
                }
                res = Math.max(res,count_area());
            }
            System.out.println("#"+tc+" "+res);
        }
    }

    public static void print(boolean[][] visited){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(visited[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int count_area(){
        boolean[][] copy_visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy_visited[i][j] = visited[i][j];
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!copy_visited[i][j]){
                    cnt++;
                    //print(copy_visited);
                    bfs(copy_visited,i,j);
                    //print(copy_visited);
                }
            }
        }

        return cnt;
    }

    public static void bfs(boolean[][] copy_visited,int i, int j){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i,j});
        copy_visited[i][j] = true;

        while (!queue.isEmpty()){
            int[] poll_data = queue.poll();
            int x = poll_data[0];
            int y = poll_data[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx<0 || ny<0 || nx>=N || ny>=N || copy_visited[nx][ny])
                    continue;
                copy_visited[nx][ny] = true;
                queue.add(new int[]{nx,ny});
            }
        }
    }
}
