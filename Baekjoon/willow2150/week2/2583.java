import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    /* 모눈종이를 표현할 2차원 배열 **/
    private static boolean[][] isGridPaperFilledIn;
    private static int M, N;

    public static void main(String[] args) {
        if (!input()) return;
        StringBuilder output = new StringBuilder();
        /* PriorityQueue를 사용하여 영역의 크기를 정렬 **/
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                /* 이미 채워진 좌표는 배제 **/
                if (isGridPaperFilledIn[row][col])
                    continue;
                pq.add(findSizeOfArea(row, col));
            }
        }

        /* PriorityQueue의 크기가 곧 영역의 수 **/
        output.append(pq.size()).append('\n');
        for (int i = pq.size(); i > 0; i--)
            output.append(pq.poll()).append(' ');
        System.out.print(output);
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            M = Integer.parseInt(tokenizer.nextToken());
            N = Integer.parseInt(tokenizer.nextToken());
            int k = Integer.parseInt(tokenizer.nextToken());
            /* 모눈종이를 시계 방향으로 90도 회전 **/
            /* 주어진 예제의 경우, 5 by 7 직사각형으로 변화 **/
            isGridPaperFilledIn = new boolean[N][M];
            for (int rectangleIndex = 0; rectangleIndex < k; rectangleIndex++) {
                tokenizer = new StringTokenizer(reader.readLine());
                /* 예제에서 그려지는 직사각형의 좌표는 x1 y1 x2 y2의 형태로 주어지지만 **/
                /* 모눈종이 직사각형을 90도 회전했으므로, r1 c1 r2 c2로 적용 **/
                /* r1 c1 r2 c2에서 r1 c1은 왼쪽 위 좌표, r2 c2는 오른쪽 아래 좌표 **/
                int upperLeftRow = Integer.parseInt(tokenizer.nextToken());
                int upperLeftCol = Integer.parseInt(tokenizer.nextToken());
                int rightBelowRow = Integer.parseInt(tokenizer.nextToken());
                int rightBelowCol = Integer.parseInt(tokenizer.nextToken());
                for (int row = upperLeftRow; row < rightBelowRow; row++)
                    for (int col = upperLeftCol; col < rightBelowCol; col++)
                        isGridPaperFilledIn[row][col] = true;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 채워지지 않은 영역의 크기를 구하는 메서드
     * @param row : 현재 탐색 중인 행
     * @param col : 현재 탐색 중인 열
     * @return : 사방으로 이어진 한 영역의 크기
     */
    public static int findSizeOfArea(int row, int col) {
        /* 영역의 크기를 1로 설정(현재 좌표) **/
        int sizeOfArea = 1;
        /* 이후 현재 좌표를 영역의 크기에 더하지 않도록 반영 **/
        isGridPaperFilledIn[row][col] = true;
        for (int[] delta: DELTAS) {
            int nr = row + delta[0];
            int nc = col + delta[1];
            /* 인근(사방) 좌표가 범위를 벗어났거나, 이미 채워진 지역이면 continue **/
            if (nr < 0 || nc < 0 || nr == N || nc == M || isGridPaperFilledIn[nr][nc])
                continue;
            sizeOfArea += findSizeOfArea(nr, nc);
        }
        return sizeOfArea;
    }
}
