import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    static class Home {
        int row, col;

        public Home(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void setRowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int MAX_N = 20;
    private static final char HOME = '1';
    private static int numOfHomes;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        Home[] homes = new Home[MAX_N * MAX_N];
        int[][] furthestDistanceMap = new int[MAX_N][MAX_N];
        int T = Integer.parseInt(reader.readLine());

        for (int tC = 1; tC <= T; tC++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            numOfHomes = 0;
            inputMap(reader, N, homes, furthestDistanceMap);
            fillDistance(N, furthestDistanceMap);
            output.append('#')
                    .append(tC)
                    .append(' ')
                    .append(findMaxNumOfHomesInArea(N, M, furthestDistanceMap, homes))
                    .append('\n');
        }
        System.out.print(output);
    }

    public static void inputMap(BufferedReader reader, int N, Home[] homes,
                                int[][] furthestDistanceMap) throws Exception {
        for (int row = 0; row < N; row++) {
            String inputLine = reader.readLine();
            int[] furthestDistanceMapLine = furthestDistanceMap[row];
            for (int col = 0; col < N; col++) {
                furthestDistanceMapLine[col] = 0;
                if (inputLine.charAt(col << 1) == HOME) {
                    if (homes[numOfHomes] == null)
                        homes[numOfHomes++] = new Home(row, col);
                    else
                        homes[numOfHomes++].setRowAndCol(row, col);
                }
            }
        }
    }

    public static void fillDistance(int N, int[][] furthestDistanceMap) {
        Queue<int[]> queue = new ArrayDeque<>();
        int halfN = N >> 1;
        int furthestDistance = N;
        if ((N & 1) == 0) {
            furthestDistance++;
            queue.add(new int[] {halfN, halfN});
            queue.add(new int[] {halfN - 1, halfN});
            queue.add(new int[] {halfN, halfN - 1});
            queue.add(new int[] {halfN - 1, halfN - 1});
            furthestDistanceMap[halfN][halfN] = furthestDistance;
            furthestDistanceMap[halfN - 1][halfN] = furthestDistance;
            furthestDistanceMap[halfN][halfN - 1] = furthestDistance;
            furthestDistanceMap[halfN - 1][halfN - 1] = furthestDistance;
        } else {
            queue.add(new int[] {halfN, halfN});
            furthestDistanceMap[halfN][halfN] = furthestDistance;
        }
        while (!queue.isEmpty()) {
            furthestDistance++;
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] point = queue.poll();
                int row = point[0];
                int col = point[1];
                for (int[] delta: DELTAS) {
                    int nr = row + delta[0];
                    int nc = col + delta[1];
                    if (nr < 0 || nc < 0 || nr == N || nc == N) continue;
                    if (furthestDistanceMap[nr][nc] != 0) continue;
                    furthestDistanceMap[nr][nc] = furthestDistance;
                    queue.add(new int[] {nr, nc});
                }
            }
        }
    }

    public static int findMaxNumOfHomesInArea(int N, int M,
                                              int[][] furthestDistanceMap, Home[] homes) {
        int maxNumOfHomesInArea = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                for (int k = furthestDistanceMap[row][col]; k > 0; k--) {
                    int numOfHomesInArea = 0;
                    int cost = -(k * k + (k - 1) * (k - 1));
                    for (int homeIdx = 0; homeIdx < numOfHomes; homeIdx++) {
                        if (k - 1 >= Math.abs(homes[homeIdx].row - row)
                                + Math.abs(homes[homeIdx].col - col)) {
                            numOfHomesInArea++;
                            cost += M;
                        }
                    }
                    if (0 <= cost && maxNumOfHomesInArea < numOfHomesInArea)
                        maxNumOfHomesInArea = numOfHomesInArea;
                }
            }
        }
        return maxNumOfHomesInArea;
    }
}
