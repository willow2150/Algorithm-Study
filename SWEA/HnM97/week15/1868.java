import java.io.*;
import java.util.*;
public class Solution {
    static boolean[][] zeroBoard;
    static char[][] board;
    static int N;
    static int[] dx = {1,1,1,0,0,-1,-1,-1};
    static int[] dy = {1,0,-1,1,-1,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            zeroBoard = new boolean[N][N];
            board = new char[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    zeroBoard[i][j] = true;
                    board[i][j] = '.';
                }
            }
            /***
             * 지뢰 주변 칸은 모두 false
             */
            for(int i=0;i<N;i++){
                String line = br.readLine();
                for (int j=0;j<N;j++){
                    char cur = line.charAt(j);
                    board[i][j] = cur;
                    if(cur=='*'){
                        zeroBoard[i][j] = false;
                        for(int x=0;x<8;x++){
                            for(int y=0;y<8;y++){
                                int nx = i + dx[x];
                                int ny = j + dy[y];
                                if(nx < 0 || ny < 0 || nx >= N || ny >= N || !zeroBoard[nx][ny]) continue;
                                zeroBoard[nx][ny]=false;
                            }
                        }
                    }
                }
            }
//            for(int i=0;i<N;i++){
//                for(int j=0;j<N;j++){
//                    System.out.print(board[i][j] + " ");
//                }
//                System.out.println();
//            }

            /**
             * true(사방이 0) 만 bfs로 방문처리
             * bfs 한 번당 클릭 한 번과 같음
             */
            boolean[][] visited = new boolean[N][N];
            int cnt =0;
            for(int i=0;i<N;i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    else if (zeroBoard[i][j] == true && isClickable(i, j, visited)) {
                        bfs(visited, i, j);
                        cnt++;
                    } else if (board[i][j] == '*') {
                        visited[i][j] = true;
                    }
                }
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(visited[i][j]==false) cnt ++;
                }
            }
            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean isClickable(int x, int y, boolean[][] visited) {
        boolean res = false;
        for(int i=0;i<8;i++){
            int nx = x +dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == '*') continue;
            else res = true;
        }
        return res;
    }

    public static void bfs(boolean[][] visited, int sx, int sy){
        Queue<int[]> q = new ArrayDeque<>();
        visited[sx][sy] = true;
        q.add(new int[]{sx,sy});

        while(!q.isEmpty()){
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            for(int i=0;i<8;i++){
                int nx = x +dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
                if(zeroBoard[nx][ny] == true){
                    q.add(new int[]{nx,ny});
                }
                visited[nx][ny] = true;
            }
        }
    }
}
