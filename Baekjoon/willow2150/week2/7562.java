import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] DELTAS = {
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}
    };
    /* 최대 체스판의 크기 **/
    private static final int MAX_BOARD_SIZE = 300;
    /* 입력받은 체스판의 크기 **/
    private static int boardSize;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        /* 나이트가 특정 좌표를 지나친 적이 있는지 기록하고 확인할 배열(체스판 역할) **/
        boolean[][] visited = new boolean[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
        /* dep: 출발 지점 좌표, arr: 목표 지점 좌표 **/
        int[] dep = new int[2];
        int[] arr = new int[2];
        /* 테스트케이스의 수 **/
        int numOfTC = Integer.parseInt(reader.readLine());

        for (int tC = 1; tC <= numOfTC; tC++) {
            /* 입력 처리 중 예외 발생 시, 프로그램 종료 **/
            if (!input(reader, dep, arr)) return;
            initBoard(visited);
            output.append(findMinNumOfMoves(visited, dep, arr)).append('\n');
        }
        /* 출력 **/
        System.out.print(output);
    }

    /**
     * 나이트가 목표 지점으로 이동하는 데 필요한 최소 이동 횟수를 찾아서 반환하는 메서드
     * @param visited : 나이트가 해당 좌표를 방문한 적이 있는지를 기록하는 배열(체스판)
     * @param dep : 나이트의 출발 좌표
     * @param arr : 나이트의 목표 좌표
     * @return : 나이트가 목표 지점으로 이동하는 데 필요한 최소 이동 횟수
     */
    public static int findMinNumOfMoves(boolean[][] visited, int[] dep, int[] arr) {
        /* BFS 수행을 위해 필요한 큐 **/
        Queue<int[]> queue = new ArrayDeque<>();
        /* 나이트가 목표 지점으로 이동하는 데 필요한 최소 이동 횟수 **/
        int minNumOfMoves = 0;

        /* 출발 좌표는 이미 방문한 것으로 처리 **/
        visited[dep[0]][dep[1]] = true;
        /* 출발 좌표를 큐에 add **/
        queue.add(dep);
        while (!queue.isEmpty()) {
            /* 현재 큐 크기만큼 poll 수행 **/
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] coordinate = queue.poll();
                /* 목표 지점의 좌표라면 minNumOfMoves 반환 **/
                if (coordinate[0] == arr[0] && coordinate[1] == arr[1])
                    return minNumOfMoves;
                /* 방문한 적 없고, 이동할 수 있는 위치를 찾아 큐에 add **/
                for (int[] delta: DELTAS) {
                    int nr = coordinate[0] + delta[0];
                    int nc = coordinate[1] + delta[1];
                    /* 체스판 범위를 벗어났다면 continue **/
                    if (nr < 0 || nc < 0 || nr >= boardSize || nc >= boardSize)
                        continue;
                    /* 다음 위치를 더 적은 이동 횟수로 방문한 적이 있다면 continue **/
                    if (visited[nr][nc])
                        continue;
                    /* 다음 위치를 방문한 것으로 처리하고 큐에 add **/
                    visited[nr][nc] = true;
                    queue.add(new int[] {nr, nc});
                }
            }
            minNumOfMoves++;
        }
        return minNumOfMoves;
    }

    /**
     * 입력을 처리하는 메서드
     * @param reader : BufferedReader
     * @return : 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input(BufferedReader reader, int[] dep, int[] arr) {
        try {
            StringTokenizer tokenizer;
            boardSize = Integer.parseInt(reader.readLine());
            tokenizer = new StringTokenizer(reader.readLine());
            dep[0] = Integer.parseInt(tokenizer.nextToken());
            dep[1] = Integer.parseInt(tokenizer.nextToken());
            tokenizer = new StringTokenizer(reader.readLine());
            arr[0] = Integer.parseInt(tokenizer.nextToken());
            arr[1] = Integer.parseInt(tokenizer.nextToken());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 체스판을 초기화하는 메서드
     * @param visited : 나이트가 해당 좌표를 방문한 적이 있는지를 기록하는 배열(체스판)
     */
    public static void initBoard(boolean[][] visited) {
        for (int row = 0; row < boardSize; row++)
            for (int col = 0; col < boardSize; col++)
                visited[row][col] = false;
    }
}
