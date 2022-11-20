package s4193;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] origin, copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            origin = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    origin[i][j] = Integer.parseInt(st.nextToken());
                    origin[i][j] = origin[i][j] == 1 ? -1 : origin[i][j];
                }
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            int ans = bfs(sx, sy, ex, ey);
            sb.append("#" + t).append(" " + ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;

        int time = 0;
        while (!q.isEmpty()) {
            if (time % 3 == 0)
                copy = deepcopy(origin);

            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] now = q.poll();
                int x = now[0];
                int y = now[1];

                if (x == ex && y == ey) return time;
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || copy[nx][ny] == -1 || visited[nx][ny]) continue;
                    if (copy[nx][ny] == 0) {
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                    if (copy[nx][ny] > 0) {
                        q.add(now);
                    }
                }

            }
            time++;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    copy[i][j] = copy[i][j] > 0 ? copy[i][j] - 1 : copy[i][j];
                }
            }
        }
        return -1;
    }

    static int[][] deepcopy(int[][] arr) {
        int[][] tmp = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                tmp[i][j] = arr[i][j];
            }
        }
        return tmp;
    }
}
