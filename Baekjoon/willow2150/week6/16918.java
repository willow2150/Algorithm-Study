import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        final int BOUNDARY = 200;
        final char BOMB = 'O', EMPTY = '.';
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int R = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        if (N <= 1) {
            for (int row = 0; row < R; row++)
                output.append(reader.readLine()).append('\n');
        } else if ((N & 1) == 0) {
            for (int row = 0; row < R; row++) {
                // output.append(String.valueOf(BOMB).repeat(C));
                for (int col = 0; col < C; col++)
                    output.append(BOMB);
                output.append('\n');
            }
        } else {
            int[] queue = new int[R * C];
            char[][] board = new char[R][C];
            int qHead = 0, qTail = 0;
            for (int row = 0; row < R; row++) {
                String line = reader.readLine();
                for (int col = 0; col < C; col++) {
                    board[row][col] = BOMB;
                    if (line.charAt(col) == BOMB)
                        queue[qTail++] = BOUNDARY * row + col;
                }
            }
            while (qHead < qTail) {
                int element = queue[qHead++];
                int row = element / BOUNDARY;
                int col = element % BOUNDARY;
                board[row][col] = EMPTY;
                for (int[] delta: DELTAS) {
                    int nr = row + delta[0];
                    int nc = col + delta[1];
                    if (nr < 0 || nc < 0 || nr == R || nc == C || board[nr][nc] == EMPTY)
                        continue;
                    board[nr][nc] = EMPTY;
                }
            }

            if ((N & 3) == 1) {
                qHead = qTail = 0;
                for (int row = 0; row < R; row++) {
                    for (int col = 0; col < C; col++) {
                        if (board[row][col] == BOMB)
                            queue[qTail++] = BOUNDARY * row + col;
                        board[row][col] = BOMB;
                    }
                }
                while (qHead < qTail) {
                    int element = queue[qHead++];
                    int row = element / BOUNDARY;
                    int col = element % BOUNDARY;
                    board[row][col] = EMPTY;
                    for (int[] delta: DELTAS) {
                        int nr = row + delta[0];
                        int nc = col + delta[1];
                        if (nr < 0 || nc < 0 || nr == R || nc == C || board[nr][nc] == EMPTY)
                            continue;
                        board[nr][nc] = EMPTY;
                    }
                }
            }

            for (int row = 0; row < R; row++) {
                for (int col = 0; col < C; col++)
                    output.append(board[row][col]);
                output.append('\n');
            }
        }
        System.out.print(output);
    }
}
