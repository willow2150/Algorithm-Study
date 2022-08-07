import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final int[][] DELTAS = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int INF = Integer.MAX_VALUE;
    private static final int BOARD_SIZE = 3;
    private static final char BLACK = '*';
    private static final char WHITE = '.';
    private static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        /* 테스트케이스의 수 **/
        int numOfTC = Integer.parseInt(reader.readLine());
        board = new char[BOARD_SIZE][BOARD_SIZE];

        for (int tC = 1; tC <= numOfTC; tC++) {
            /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
            if (!input(reader)) return;
            /* 최소 클릭 횟수를 찾아 출력에 저장 **/
            output.append(calcMinNumOfClicks(0, 0, countBlackSpaces(), 0)).append('\n');
        }
        /* 출력 **/
        System.out.print(output);
    }

    /**
     * 검은색 칸을 모두 지울 수 있는 최소 클릭 횟수를 찾아주는 메서드
     * 단, 검은색 칸을 모두 지우지 못한 경우, INF 반환(해당 경우를 배제)
     * @param row : 현재 행
     * @param col : 현재 열
     * @param remainingNumOfBlackSpaces : 남은 검은색 칸의 수
     * @param numOfClicks : 클릭 횟수
     * @return : 클릭했을 경우와 안 했을 경우 중, 더 적은 클릭 횟수
     *           (단, 검은색 칸을 모두 지우지 못한 경우 INF)
     */
    public static int calcMinNumOfClicks(int row, int col,
                                         int remainingNumOfBlackSpaces, int numOfClicks) {
        /* 현재 남아있는 검은색 칸의 수가 0이면, 클릭 횟수를 반환 **/
        if (remainingNumOfBlackSpaces == 0)
            return numOfClicks;
        /* 열이 범위를 벗어나면, 다음 행의 0번째 열로 좌표 이동 **/
        if (col == BOARD_SIZE) {
            row++;
            col = 0;
        }
        /* 행이 범위를 벗어나고(탐색이 끝나고), 검은색 칸이 남아있을 경우 INF 반환 **/
        if (row == BOARD_SIZE)
            return INF;
        
        /* 클릭 적용 **/
        for (int[] delta: DELTAS) {
            int nr = row + delta[0];
            int nc = col + delta[1];
            if (nr < 0 || nc < 0 || nr == BOARD_SIZE || nc == BOARD_SIZE)
                continue;
            if (board[nr][nc] == WHITE) {
                board[nr][nc] = BLACK;
                remainingNumOfBlackSpaces++;
            } else {
                board[nr][nc] = WHITE;
                remainingNumOfBlackSpaces--;
            }
        }
        /* 현재 좌표를 클릭하고 다음 좌표로 넘어갔을 때의 최소 클릭 횟수 **/
        int numOfClicksCaseB =
                calcMinNumOfClicks(row, col + 1, remainingNumOfBlackSpaces, numOfClicks + 1);
        
        /* 클릭 전으로 되돌리기 **/
        for (int[] delta: DELTAS) {
            int nr = row + delta[0];
            int nc = col + delta[1];
            if (nr < 0 || nc < 0 || nr == BOARD_SIZE || nc == BOARD_SIZE)
                continue;
            if (board[nr][nc] == WHITE) {
                board[nr][nc] = BLACK;
                remainingNumOfBlackSpaces++;
            } else {
                board[nr][nc] = WHITE;
                remainingNumOfBlackSpaces--;
            }
        }
        /* 현재 좌표를 클릭하지 않고 다음 좌표로 넘어갔을 때의 최소 클릭 횟수 **/
        int numOfClicksCaseA =
                calcMinNumOfClicks(row, col + 1, remainingNumOfBlackSpaces, numOfClicks);
        
        /* 두 경우 중, 더 적은 클릭 횟수를 반환 **/
        return Math.min(numOfClicksCaseA, numOfClicksCaseB);
    }

    /**
     * 보드를 탐색하여 검은색 칸의 수를 세어주는 메서드
     * @return 보드 내 검은색 칸의 수
     */
    public static int countBlackSpaces() {
        int numOfBlackSpaces = 0;
        for (int row = 0; row < BOARD_SIZE; row++)
            for (int col = 0; col < BOARD_SIZE; col++)
                if (board[row][col] == BLACK)
                    numOfBlackSpaces++;
        return numOfBlackSpaces;
    }

    /**
     * 각 테스트케이스의 입력을 처리하는 메서드
     * @return : 입력이 정상 처리되면 true, 예외가 발생하면 false
     */
    public static boolean input(BufferedReader reader) {
        try {
            /* 입력으로 주어지는 보드의 상태를 저장 **/
            for (int row = 0; row < BOARD_SIZE; row++) {
                String line = reader.readLine();
                for (int col = 0; col < BOARD_SIZE; col++)
                    board[row][col] = line.charAt(col);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
