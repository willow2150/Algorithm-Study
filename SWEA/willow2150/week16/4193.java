import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int MAX_N = 15;
    private static final char OBSTACLE = '1';
    private static final char SWIRL = '2';

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer tokenizer;
        Queue<int[]> pq = new PriorityQueue<>(
                (stateA, stateB) -> stateA[2] <= stateB[2] ? -1 : 1
        );
        int[][] map = new int[MAX_N][MAX_N];
        int[][] minTimesArray = new int[MAX_N][MAX_N];
        int T = Integer.parseInt(reader.readLine());

        for (int tC = 1; tC <= T; tC++) {
            int N = Integer.parseInt(reader.readLine());
            for (int row = 0; row < N; row++) {
                String mapLine = reader.readLine();
                for (int col = 0; col < N; col++) {
                    map[row][col] = mapLine.charAt(col << 1);
                    minTimesArray[row][col] = Integer.MAX_VALUE;
                }
            }
            tokenizer = new StringTokenizer(reader.readLine());
            int[] dep = new int[] {
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())
            };
            tokenizer = new StringTokenizer(reader.readLine());
            int[] arr = new int[] {
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())
            };
            output.append('#').append(tC).append(' ')
                    .append(findMinTime(pq, map, minTimesArray, dep, arr, N)).append('\n');
            pq.clear();
        }
        System.out.print(output);
    }

    public static int findMinTime(Queue<int[]> pq, int[][] map, int[][] minTimesArray,
                                  int[] dep, int[] arr, int N) {
        pq.add(new int[] {dep[0], dep[1], 0});
        while (!pq.isEmpty()) {
            int[] point = pq.poll();
            int row = point[0], col = point[1], currentTime = point[2];
            for (int[] delta: DELTAS) {
                int nr = row + delta[0], nc = col + delta[1];
                int nextTime = currentTime + 1;
                if (nr < 0 || nc < 0 || nr == N || nc == N) continue;
                if (nr == arr[0] && nc == arr[1]) return nextTime;
                if (map[nr][nc] == OBSTACLE) continue;
                if (map[nr][nc] == SWIRL) {
                    if (nextTime % 3 == 1) nextTime += 2;
                    else if (nextTime % 3 == 2) nextTime++;
                }
                if (minTimesArray[nr][nc] <= nextTime) continue;
                minTimesArray[nr][nc] = nextTime;
                pq.add(new int[] {nr, nc, nextTime});
            }
        }
        return -1;
    }
}
