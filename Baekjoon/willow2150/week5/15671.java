import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int BOARD_SIZE = 6;
    private static final char BLACK = 'B';
    private static final char WHITE = 'W';
    private static final char EMPTY = '.';
    private static int[][] logArray;

    public static void main(String[] args) {
        if (!input()) return;
        StringBuilder output = new StringBuilder();
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

        for (int row = 0; row < BOARD_SIZE; row++)
            for (int col = 0; col < BOARD_SIZE; col++)
                board[row][col] = EMPTY;
        board[2][2] = board[3][3] = WHITE;
        board[2][3] = board[3][2] = BLACK;

        runGame(board, output);
        output.append(pickWinner(board));

        System.out.print(output);
    }

    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            logArray = new int[n][2];
            for (int logIdx = 0; logIdx < n; logIdx++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                logArray[logIdx][0] = Integer.parseInt(tokenizer.nextToken());
                logArray[logIdx][1] = Integer.parseInt(tokenizer.nextToken());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void runGame(char[][] board, StringBuilder output) {
        int[][] deltas = {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}
        };
        boolean blackTurn = true;
        for (int[] log: logArray) {
            int row = log[0] - 1;
            int col = log[1] - 1;
            char stone, enemyStone;
            if (blackTurn) {
                stone = BLACK;
                enemyStone = WHITE;
            } else {
                stone = WHITE;
                enemyStone = BLACK;
            }
            board[row][col] = stone;
            for (int[] delta: deltas) {
                int nr = row + delta[0];
                int nc = col + delta[1];
                while (nr >= 0 && nc >= 0
                        && nr < BOARD_SIZE && nc < BOARD_SIZE
                        && board[nr][nc] == enemyStone) {
                    nr += delta[0];
                    nc += delta[1];
                }
                if (nr >= 0 && nc >= 0 && nr < BOARD_SIZE && nc < BOARD_SIZE
                        && board[nr][nc] == stone) {
                    int numOfEnemyStones =
                            (row == nr ? Math.abs(col  - nc) : Math.abs(row - nr)) - 1;
                    while (numOfEnemyStones-- > 0) {
                        nr -= delta[0];
                        nc -= delta[1];
                        board[nr][nc] = stone;
                    }
                }
            }
            blackTurn = !blackTurn;
        }

        for (char[] line: board) {
            for (char grid: line)
                output.append(grid);
            output.append('\n');
        }
    }

    public static String pickWinner(char[][] board) {
        int numOfBlackStones = 0;
        int numOfWhiteStones = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == WHITE)
                    numOfWhiteStones++;
                else if (board[row][col] == BLACK)
                    numOfBlackStones++;
            }
        }
        return numOfBlackStones > numOfWhiteStones ? "Black" : "White";
    }
}
