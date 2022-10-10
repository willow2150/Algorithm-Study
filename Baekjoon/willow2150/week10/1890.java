import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        int[][] board = input();
        if (board == null) return;
        long[][] dp = new long[board.length][board.length];
        int boundary = board.length - 1;
        dp[boundary][boundary] = 1;
        jump(board, dp, 0, 0, boundary);
        System.out.print(dp[0][0]);
    }

    public static int[][] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine());
            int[][] board = new int[N][N];
            for (int row = 0; row < N; row++) {
                String line = reader.readLine();
                for (int col = 0; col < N; col++)
                    board[row][col] = line.charAt(col << 1) - '0';
            }
            return board;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long jump(int[][] board, long[][] dp, int row, int col, int boundary) {
        if (boundary < row || boundary < col) return 0;
        if (dp[row][col] != 0) return dp[row][col];
        if (board[row][col] == 0) return 0;
        return dp[row][col]
                = jump(board, dp, row + board[row][col], col, boundary)
                + jump(board, dp, row, col + board[row][col], boundary);
    }
}
