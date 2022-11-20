import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static class Slice {
        int row, col;
        int taste;
        int lastCheckedDay;

        public Slice(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void setTaste(int taste) {
            this.taste = taste;
        }
    }

    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int MAX_N = 100;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        Slice[][] cheese = new Slice[MAX_N][MAX_N];
        int T = Integer.parseInt(reader.readLine());

        for (int row = 0; row < MAX_N; row++)
            for (int col = 0; col < MAX_N; col++)
                cheese[row][col] = new Slice(row, col);

        for (int tC = 1; tC <= T; tC++) {
            int N = Integer.parseInt(reader.readLine());
            for (int row = 0; row < N; row++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                for (int col = 0; col < N; col++) {
                    cheese[row][col].setTaste(Integer.parseInt(tokenizer.nextToken()));
                    cheese[row][col].lastCheckedDay = 0;
                }
            }
            output.append('#').append(tC).append(' ')
                    .append(findMaxNumOfChunks(N, cheese)).append('\n');
        }
        System.out.print(output);
    }

    public static int findMaxNumOfChunks(int N, Slice[][] cheese) {
        int maxNumOfChunks = 1;
        for (int taste = 1; taste < MAX_N; taste++) {
            int numOfChunks = 0;
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (cheese[row][col].taste > taste
                            && cheese[row][col].lastCheckedDay < taste) {
                        checkCheese(row, col, taste, N, cheese);
                        numOfChunks++;
                    }
                }
            }
            if (maxNumOfChunks < numOfChunks)
                maxNumOfChunks = numOfChunks;
        }
        return maxNumOfChunks;
    }

    public static void checkCheese(int row, int col, int taste, int N, Slice[][] cheese) {
        cheese[row][col].lastCheckedDay = taste;
        for (int[] delta: DELTAS) {
            int nr = row + delta[0];
            int nc = col + delta[1];
            if (nr < 0 || nc < 0 || nr == N || nc == N) continue;
            if (cheese[nr][nc].taste > taste && cheese[nr][nc].lastCheckedDay < taste)
                checkCheese(nr, nc, taste, N, cheese);
        }
    }
}
