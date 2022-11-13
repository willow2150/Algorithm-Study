import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    /* 뱀의 좌표를 나타낼 사용자 정의 클래스 **/
    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int DIR_CHANGE_TIME_LIMIT = 10_000;
    private static final char LEFT_TURN = 'L';
    /* 사과 **/
    private static final char APPLE = 'A';
    /* 비어있는 좌표 **/
    private static final char EMPTY = '\0';
    /* 뱀 **/
    private static final char SNAKE = 'S';
    private static char[][] board;
    /* 인덱스: 시간, 값: 90도 회전할 방향 **/
    private static char[] dirChangeInfo;
    private static int N;

    public static void main(String[] args) {
        dirChangeInfo = new char[DIR_CHANGE_TIME_LIMIT + 1];
        if (!input()) return;
        System.out.print(playDummy());
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력 처리 성공 시 true, 예외 발생 시 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] lineInputSplits;

            N = Integer.parseInt(reader.readLine());
            board = new char[N + 1][N + 1];

            /* 사과의 수 **/
            int numOfApples = Integer.parseInt(reader.readLine());
            for (int appleIdx = 0; appleIdx < numOfApples; appleIdx++) {
                lineInputSplits = reader.readLine().split(" ");
                int row = Integer.parseInt(lineInputSplits[0]);
                int col = Integer.parseInt(lineInputSplits[1]);
                board[row][col] = APPLE;
            }

            /* 방향 전환 횟수 **/
            int numOfDirChanges = Integer.parseInt(reader.readLine());
            for (int dirChangeIdx = 0; dirChangeIdx < numOfDirChanges; dirChangeIdx++) {
                lineInputSplits = reader.readLine().split(" ");
                int time = Integer.parseInt(lineInputSplits[0]);
                dirChangeInfo[time] = lineInputSplits[1].charAt(0);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Dummy 게임을 수행하는 메서드
     * @return : 플레이 시간
     */
    public static int playDummy() {
        /* 뱀의 좌표를 저장할 덱 **/
        Deque<Point> snakeBodyDeque = new ArrayDeque<>();
        Point startPoint = new Point(1, 1);
        int time = 0;
        int dir = 1;

        snakeBodyDeque.addFirst(startPoint);
        board[1][1] = SNAKE;
        while (true) {
            /* 시작과 동시에 게임이 끝나지 않으며, 이동 후 방향 전환이 발생하므로 **/
            /* 시간을 먼저 증가 **/
            time++;
            /* 뱀의 머리 좌표를 저장(덱에서 제거 X) **/
            Point snakeHead = snakeBodyDeque.peekFirst();
            /* 뱀의 머리가 이동할 좌표 **/
            int nr = snakeHead.row + DELTAS[dir][0];
            int nc = snakeHead.col + DELTAS[dir][1];
            /* 이동할 좌표가 범위를 벗어났거나, 뱀의 신체 부위 중 일부라면 break **/
            if (nr == 0 || nc == 0 || nr > N || nc > N || board[nr][nc] == SNAKE)
                break;
            /* 이동활 좌표가 비어있다면 **/
            if (board[nr][nc] == EMPTY) {
                /* 덱에서 끝 좌표(꼬리)를 제거하고, 해당 좌표가 비어있도록 설정 **/
                Point snakeTail = snakeBodyDeque.pollLast();
                board[snakeTail.row][snakeTail.col] = EMPTY;
            }
            /* 이동할 좌표에 뱀이 있음을 표시하고, 뱀의 머리로 덱에 추가 **/
            board[nr][nc] = SNAKE;
            snakeBodyDeque.addFirst(new Point(nr, nc));
            /* 이동이 완료된 후, 방향 전환을 하는 시간이라면 방향 전환 수행 **/
            if (dirChangeInfo[time] != EMPTY) {
                if (dirChangeInfo[time] == LEFT_TURN) {
                    dir = (dir + 3) & 3;
                } else {
                    dir = (dir + 1) & 3;
                }
            }
        }
        return time;
    }
}
