import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int row, col;
        char terrain;

        Point(int row, int col, char terrain) {
            this.row = row;
            this.col = col;
            this.terrain = terrain;
        }

        public void setTerrain(char terrain) {
            this.terrain = terrain;
        }
    }

    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int MAX_MAP_SIZE = 100;
    private static final char DOOR = '#';
    private static final char EMPTY = '.';
    private static final char PERSON = '$';
    private static final char WALL = '*';

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        Deque<Point> dequeForPersonA = new ArrayDeque<>();
        Deque<Point> dequeForPersonB = new ArrayDeque<>();
        Deque<Point> dequeForPersonC = new ArrayDeque<>();
        Point[][] map = new Point[MAX_MAP_SIZE][MAX_MAP_SIZE];
        int[][][] costArray = new int[3][MAX_MAP_SIZE][MAX_MAP_SIZE];
        int testCases = Integer.parseInt(reader.readLine());

        for (int tC = 1; tC <= testCases; tC++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int h = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            int hBoundary = h - 1;
            int wBoundary = w - 1;
            for (int row = 0; row < h; row++) {
                String inputLine = reader.readLine();
                Point[] line = map[row];
                for (int col = 0; col < w; col++) {
                    char terrain = inputLine.charAt(col);
                    if (line[col] == null) {
                        line[col] = new Point(row, col, terrain);
                    } else {
                        line[col].setTerrain(terrain);
                    }
                    costArray[0][row][col] = -1;
                    costArray[1][row][col] = -1;
                    costArray[2][row][col] = -1;
                    if (terrain == WALL)
                        continue;
                    if (row == 0 || row == hBoundary || col == 0 || col == wBoundary)
                        dequeForPersonC.add(line[col]);
                    if (terrain == PERSON) {
                        if (dequeForPersonA.isEmpty())
                            dequeForPersonA.add(line[col]);
                        else
                            dequeForPersonB.add(line[col]);
                        line[col].terrain = EMPTY;
                    }
                }
            }

            int minCostOfA = tryToEscape(dequeForPersonA, map, costArray[0], h, w);
            int minCostOfB = tryToEscape(dequeForPersonB, map, costArray[1], h, w);
            int minCostOfABC = Integer.MAX_VALUE;
            tryToEscape(dequeForPersonC, map, costArray[2], h, w);

            for (int row = 0; row < h; row++) {
                for (int col = 0; col < w; col++) {
                    costArray[2][row][col] +=
                            costArray[0][row][col] + costArray[1][row][col];
                    if (costArray[0][row][col] == -1 || costArray[1][row][col] == -1)
                        continue;
                    if (map[row][col].terrain == DOOR) {
                        minCostOfABC = Math.min(minCostOfABC, costArray[2][row][col] - 2);
                    } else {
                        minCostOfABC = Math.min(minCostOfABC, costArray[2][row][col]);
                    }
                }
            }
            output.append(Math.min(minCostOfA + minCostOfB, minCostOfABC)).append('\n');
        }
        System.out.print(output);
    }

    public static int tryToEscape(Deque<Point> deque, Point[][] map,
                                  int[][] cost, int h, int w) {
        int minCost = Integer.MAX_VALUE;

        for (Point point: deque) {
            if (map[point.row][point.col].terrain == EMPTY)
                cost[point.row][point.col] = 0;
            else
                cost[point.row][point.col] = 1;
        }

        while (!deque.isEmpty()) {
            Point point = deque.pollFirst();
            int row = point.row;
            int col = point.col;
            for (int[] delta: DELTAS) {
                int nr = row + delta[0];
                int nc = col + delta[1];
                if (nr < 0 || nc < 0 || nr == h || nc == w) {
                    minCost = Math.min(minCost, cost[row][col]);
                    continue;
                }
                if (map[nr][nc].terrain == WALL || cost[nr][nc] != -1)
                    continue;
                if (map[nr][nc].terrain == EMPTY) {
                    cost[nr][nc] = cost[row][col];
                    deque.addFirst(map[nr][nc]);
                } else {
                    cost[nr][nc] = cost[row][col] + 1;
                    deque.addLast(map[nr][nc]);
                }
            }
        }
        return minCost;
    }
}
