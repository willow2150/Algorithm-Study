import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        static int boundary;
        int row, col;
        char trace;

        static void setBoundary(int boundary) {
            Point.boundary = boundary;
        }

        Point(int row, int col, char trace) {
            this.row = row; this.col = col;
            this.trace = trace;
        }

        @Override
        public int hashCode() {
            return this.row * boundary + this.col;
        }

        @Override
        public boolean equals(Object obj) {
            // if (obj instanceof Point point) {
            //     return this.row == point.row && this.col == point.col;
            // }
            if (obj instanceof Point) {
                Point point = (Point) obj;
                return this.row == point.row && this.col == point.col;
            }
            return false;
        }

        @Override
        public int compareTo(Point point) {
            if (this.row == point.row)
                return this.col <= point.col ? -1 : 1;
            return this.row < point.row ? -1 : 1;
        }
    }

    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static Map<Point, Point> passengers;
    private static Point[][] map;
    private static Point taxi;
    private static int N;
    private static int fuel;

    public static void main(String[] args) {
        if (!input()) return;
        char trace = '1';
        while (!passengers.isEmpty()) {
            taxi.trace = ++trace;
            if (pickUpPassenger(++trace) && moveToDestination(++trace))
                continue;
            fuel = -1;
            break;
        }
        System.out.print(fuel);
    }

    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final char EMPTY = '0';
            final char WALL = Character.MAX_VALUE;
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            fuel = Integer.parseInt(tokenizer.nextToken());
            map = new Point[N + 1][N + 1];
            Point.setBoundary(N + 1);
            for (int row = 1; row <= N; row++) {
                String line = reader.readLine();
                for (int col = 1; col <= N; col++) {
                    map[row][col] = new Point(
                            row,
                            col,
                            line.charAt((col - 1) << 1) == EMPTY ? EMPTY : WALL
                    );
                }
            }

            tokenizer = new StringTokenizer(reader.readLine());
            int tRow = Integer.parseInt(tokenizer.nextToken());
            int tCol = Integer.parseInt(tokenizer.nextToken());
            taxi = map[tRow][tCol];

            passengers = new HashMap<>();
            for (int passenger = 0; passenger < m; passenger++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int pRow = Integer.parseInt(tokenizer.nextToken());
                int pCol = Integer.parseInt(tokenizer.nextToken());
                int dRow = Integer.parseInt(tokenizer.nextToken());
                int dCol = Integer.parseInt(tokenizer.nextToken());
                passengers.put(map[pRow][pCol], map[dRow][dCol]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean pickUpPassenger(char trace) {
        if (passengers.containsKey(taxi))
            return true;
        Queue<Point> queue = new ArrayDeque<>();
        Queue<Point> pickUpCandidates = new PriorityQueue<>();
        int distance = 1;
        
        queue.add(taxi);
        while (distance <= fuel) {
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                Point point = queue.poll();
                int row = point.row;
                int col = point.col;
                for (int[] delta: DELTAS) {
                    int nr = row + delta[0];
                    int nc = col + delta[1];
                    if (nr == 0 || nc == 0 || nr > N || nc > N) continue;
                    if (map[nr][nc].trace >= trace) continue;
                    if (passengers.containsKey(map[nr][nc]))
                        pickUpCandidates.add(map[nr][nc]);
                    map[nr][nc].trace = trace;
                    queue.add(map[nr][nc]);
                }
            }
            if (pickUpCandidates.isEmpty()) {
                distance++;
            } else {
                taxi = pickUpCandidates.poll();
                fuel -= distance;
                return true;
            }
        }
        return false;
    }
    
    public static boolean moveToDestination(char trace) {
        Queue<Point> queue = new ArrayDeque<>();
        Point destination = passengers.get(taxi);
        int distance = 1;

        passengers.remove(taxi);
        queue.add(taxi);
        while (distance <= fuel) {
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                Point point = queue.poll();
                int row = point.row;
                int col = point.col;
                for (int[] delta: DELTAS) {
                    int nr = row + delta[0];
                    int nc = col + delta[1];
                    if (nr == 0 || nc == 0 || nr > N || nc > N) continue;
                    if (map[nr][nc].trace >= trace) continue;
                    if (map[nr][nc] == destination) {
                        fuel += distance;
                        taxi = destination;
                        return true;
                    }
                    map[nr][nc].trace = trace;
                    queue.add(map[nr][nc]);
                }
            }
            distance++;
        }
        return false;
    }
}
