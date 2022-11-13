import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_boj2636_치즈 {

    static int R, C, time, remain;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0, 0);
        System.out.println(time);
        System.out.println(remain);
    }

    private static void bfs(int sr, int sc) {
        boolean[][] visited = new boolean[R][C];
        Queue<Point> airs = new ArrayDeque<>();

        // 초기설정
        visited[sr][sc] = true;
        airs.offer(new Point(sr, sc));

        // 녹을 치즈(= 다음 공기) 저장할 큐
        Queue<Point> cheeses = new ArrayDeque<>();

        while (true) {
            // bfs로 완탐 -> cheeses 찾기
            while (!airs.isEmpty()) {
                Point point = airs.poll();

                for (int d = 0; d < 4; d++) {
                   int nr = point.r + dr[d];
                   int nc = point.c + dc[d];

                   if (0 <= nr && 0 <= nc && nr < R && nc < C && !visited[nr][nc]) {
                       if (map[nr][nc] == 1) {  // 치즈인 경우
                           cheeses.add(new Point(nr, nc));
                           map[nr][nc] = 0;
                       } else {     // map[nr][nc] == 0인 공기인 경우
                           airs.add(new Point(nr, nc));
                       }
                       visited[nr][nc] = true;
                   }
                }
            }   // 1시간 경과

            // 치즈가 없는 경우
            if (cheeses.isEmpty()) {
                break;
            } else {    // 치즈가 남은 경우, 한번더
                time++;
                remain = cheeses.size();
                for (Point p : cheeses) {
                    airs.add(p);
                }
                cheeses.clear();
           }
        }
    }
}
