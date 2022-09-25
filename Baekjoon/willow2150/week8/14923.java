import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int row, col;
        int state;

        Point(int row, int col, int state) {
            this.row = row; this.col = col;
            this.state = state;
        }

        public boolean equals(Point point) {
            return this.row == point.row && this.col == point.col;
        }
    }

    private static Point dep, arr;
    private static int N, M;

    public static void main(String[] args) {
        Point[][] map = input();
        if (map == null) return;
        System.out.print(canMove(map));
    }

    public static Point[][] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());
            tokenizer = new StringTokenizer(reader.readLine());
            int depRow = Integer.parseInt(tokenizer.nextToken());
            int depCol = Integer.parseInt(tokenizer.nextToken());
            tokenizer = new StringTokenizer(reader.readLine());
            int arrRow = Integer.parseInt(tokenizer.nextToken());
            int arrCol = Integer.parseInt(tokenizer.nextToken());
            
            Point[][] map = new Point[N + 1][M + 1];
            for (int row = 1; row <= N; row++) {
                String line = reader.readLine();
                for (int col = 1; col <= M; col++)
                    map[row][col] = new Point(row, col, line.charAt((col - 1) << 1) - '0');
            }
            dep = map[depRow][depCol];
            arr = map[arrRow][arrCol];
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int canMove(Point[][] map) {
        final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        final int EMPTY = 0;
        final int WALL = 1;
        final int UNBROKEN_STATE = 2;
        final int BROKEN_STATE = 4;
        Queue<Point> queue = new ArrayDeque<>();
        int time = 1;

        dep.state = UNBROKEN_STATE;
        queue.add(dep);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                Point point = queue.poll();
                for (int[] delta: DELTAS) {
                    int nr = point.row + delta[0];
                    int nc = point.col + delta[1];
                    if (nr == 0 || nc == 0 || nr > N || nc > M) continue;
                    if (map[nr][nc].equals(arr)) return time;
                    if (point.state == UNBROKEN_STATE) {
                        if (map[nr][nc].state == WALL) {
                            map[nr][nc].state |= BROKEN_STATE;
                            queue.add(map[nr][nc]);
                        } else if (map[nr][nc].state != UNBROKEN_STATE
                                && (map[nr][nc].state & WALL) != WALL) {
                            map[nr][nc].state = UNBROKEN_STATE;
                            queue.add(map[nr][nc]);
                        }
                    } else if (map[nr][nc].state == EMPTY) {
                        map[nr][nc].state = BROKEN_STATE;
                        queue.add(map[nr][nc]);
                    }
                }
            }
            time++;
        }
        return -1;
    }
}
