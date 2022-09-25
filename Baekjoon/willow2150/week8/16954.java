import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static class Point {
        int row, col;
        int wallBit;

        Point(int row, int col, int wallBit) {
            this.row = row; this.col = col;
            this.wallBit = wallBit;
        }
    }

    private static final int MAP_SIZE = 8;

    public static void main(String[] args) {
        Queue<Point> queue = new ArrayDeque<>();
        Point[][] map = input(queue);
        if (map == null) return;
        downWall(queue, map);
        System.out.print(canMove(queue, map) ? 1 : 0);
    }

    public static Point[][] input(Queue<Point> queue) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final char EMPTY = '.';
            Point[][] map = new Point[MAP_SIZE][MAP_SIZE];
            for (int row = 0; row < MAP_SIZE; row++) {
                map[row] = new Point[MAP_SIZE];
                String line = reader.readLine();
                for (int col = 0; col < MAP_SIZE; col++)
                    if (line.charAt(col) == EMPTY) {
                        map[row][col] = new Point(row, col, 0);
                    } else {
                        map[row][col] = new Point(row, col, 1);
                        queue.add(map[row][col]);
                    }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void downWall(Queue<Point> queue, Point[][] map) {
        int boundary = MAP_SIZE - 1;
        int wallBit = 2;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                Point point = queue.poll();
                if (point.row < boundary) {
                    Point nextPoint = map[point.row + 1][point.col];
                    nextPoint.wallBit |= wallBit;
                    queue.add(nextPoint);
                }
            }
            wallBit <<= 1;
        }
    }

    public static boolean canMove(Queue<Point> queue, Point[][] map) {
        final int[][] DELTAS = {
                {-1, 0}, {0, 1}, {1, 0},
                {0, -1}, {-1, -1}, {-1, 1},
                {1, -1}, {1, 1}, {0, 0}
        };
        int destinationCol = MAP_SIZE - 1;
        int bit = 1;

        queue.add(map[MAP_SIZE - 1][0]);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            int nextBit = bit << 1;
            while (queueSize-- > 0) {
                Point point = queue.poll();
                for (int[] delta: DELTAS) {
                    int nr = point.row + delta[0];
                    int nc = point.col + delta[1];
                    if (nr < 0 || nc < 0 || nr == MAP_SIZE || nc == MAP_SIZE)
                        continue;
                    if (nr == 0 && nc == destinationCol) return true;
                    if ((map[nr][nc].wallBit & bit) == bit) continue;
                    if ((map[nr][nc].wallBit & nextBit) == nextBit) continue;
                    map[nr][nc].wallBit |= bit;
                    queue.add(map[nr][nc]);
                }
            }
            bit <<= 1;
        }
        return false;
    }
}
