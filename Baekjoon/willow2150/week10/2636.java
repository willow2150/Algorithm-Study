import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int[][] board = input();
        if (board == null) return;
        System.out.print(meltCheese(board));
    }

    public static int[][] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int height = Integer.parseInt(tokenizer.nextToken());
            int width = Integer.parseInt(tokenizer.nextToken());
            int[][] board = new int[height][width];
            for (int row = 0; row < height; row++) {
                String line = reader.readLine();
                for (int col = 0; col < width; col++)
                    board[row][col] = line.charAt(col << 1) - '0';
            }
            return board;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String meltCheese(int[][] board) {
        Queue<int[]> queueA = new ArrayDeque<>();
        Queue<int[]> queueB = new ArrayDeque<>();
        int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int height = board.length;
        int width = board[0].length;
        int time = 0;
        int lastCheese = 0;
        int cheese = 1;
        int visited = 2;

        queueA.add(new int[] {0, 0});
        while (!queueA.isEmpty()) {
            time++;
            while (!queueA.isEmpty()) {
                int[] point = queueA.poll();
                int row = point[0];
                int col = point[1];
                for (int[] delta: deltas) {
                    int nr = row + delta[0];
                    int nc = col + delta[1];
                    if (nr < 0 || nc < 0 || nr == height || nc == width
                            || board[nr][nc] == visited) {
                        continue;
                    }
                    if (board[nr][nc] == cheese)
                        queueB.add(new int[] {nr, nc});
                    else
                        queueA.add(new int[] {nr, nc});
                    board[nr][nc] = visited;
                }
            }
            if (!queueB.isEmpty())
                lastCheese = queueB.size();
            else
                time--;
            Queue<int[]> temp = queueA;
            queueA = queueB;
            queueB = temp;
        }
        return time + "\n" + lastCheese;
    }
}
