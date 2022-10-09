package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/* 걸린시간 : 20분
*
* 원래 class 따로 안만들고 무조껀 int[]로 넘기는 습관이 있느데 종종 class 만들어서 하는 방법도 연습해야겠다.
* Point는  0에서 1로 갔을 때만 체크를 해줘야 한다. 그래야 가장자리 부분만 녹아서 사라지기 때문!
* */

public class Main_2636 {
    static int R,C;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class P {
        int r;
        int c;

        public P(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[][] board = new int[R][C];

        int left = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1)
                    left++;
            }
        }

        int time = 0;
        int before = left;

        while (left != 0) {
            before = left;

            bfs(board);

            left = count(board);
            time++;
        }

        System.out.println(time);
        System.out.println(before);
    }

    private static int count(int[][] board) {
        int count = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == -1)
                    board[i][j] = 0;
                else if (board[i][j] == 1)
                    count++;
            }
        }
        return count;
    }

    private static void bfs(int[][] board) {
        Queue<P> queue = new ArrayDeque<>();
        queue.add(new P(0, 0));

        boolean[][] visited = new boolean[R][C];
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            P p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.r + dx[i];
                int ny = p.c + dy[i];

                if (0 <= nx && nx < R && 0 <= ny && ny < C && board[nx][ny] != -1 && !visited[nx][ny]) {
                    if (board[nx][ny] == 1)
                        board[nx][ny] = -1;
                    else {
                        visited[nx][ny] = true;
                        queue.add(new P(nx, ny));
                    }
                }
            }
        }
    }

}

