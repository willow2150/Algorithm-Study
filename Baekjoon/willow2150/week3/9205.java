import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /* 좌표를 저장하는 사용자 정의 클래스 **/
    static class Point {
        int x, y;

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int calcDistanceTo(Point point) {
            return Math.abs(this.x - point.x) + Math.abs(this.y - point.y);
        }
    }

    /* 페스티벌에 갈 수 있을 때 출력할 문자열 **/
    private static final String POSSIBLE_TO_GO = "happy";
    /* 페스티벌에 갈 수 없을 때 출력할 문자열 **/
    private static final String IMPOSSIBLE_TO_GO = "sad";
    /* 특정 장소에서 다른 장소로 이동할 때 이동할 수 있는 최대 거리 **/
    private static final int MOVING_DISTANCE = 1_000;
    /* 편의점의 최대 개수 **/
    private static final int MAX_NUM_OF_STORES = 100;
    private static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int numOfTC = Integer.parseInt(reader.readLine());

        /* 모든 장소의 수: 집(1), 편의점(100), 락 페스티벌 장소(1) **/
        int maxNumOfPoints = MAX_NUM_OF_STORES + 2;
        /* 장소의 방문 여부를 저장할 배열 **/
        boolean[] visited = new boolean[maxNumOfPoints];
        /* 모든 장소의 좌표를 저장할 배열 **/
        Point[] points = new Point[maxNumOfPoints];
        /* 특정 장소에서 다른 장소로 이동 가능한지를 저장할 배열 **/
        boolean[][] movable = new boolean[maxNumOfPoints][maxNumOfPoints];

        /* 각 장소를 저장할 Point 객체를 미리 생성 **/
        for (int pointIdx = 0; pointIdx < points.length; pointIdx++)
            points[pointIdx] = new Point();

        for (int tC = 1; tC <= numOfTC; tC++) {
            /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
            if (!input(reader, points)) return;
            /* 각 장소 간 이동 가능 여부를 조사 **/
            checkMovable(points, movable);
            /* 집에서 락 페스티벌 장소로 갈 수 있는지 조사하고 결과를 출력 문자열에 저장 **/
            output.append(
                    checkMovableFromDepToArr(movable, visited, 0)
                            ? POSSIBLE_TO_GO : IMPOSSIBLE_TO_GO
            ).append('\n');
            /* 다음 테스트케이스를 위한 초기화 작업 수행 **/
            for (int dep = 0; dep <= n; dep++) {
                visited[dep] = false;
                for (int arr = dep + 1; arr <= n; arr++) {
                    movable[dep][arr] = false;
                    movable[arr][dep] = false;
                }
            }
        }
        System.out.print(output);
    }

    /**
     * 입력을 처리하는 메서드
     * @param reader : BufferedReader
     * @param points : 각 장소의 좌표를 저장할 배열
     * @return 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input(BufferedReader reader, Point[] points) {
        try {
            n = Integer.parseInt(reader.readLine()) + 1;
            for (int pointIdx = 0; pointIdx <= n; pointIdx++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(tokenizer.nextToken());
                int y = Integer.parseInt(tokenizer.nextToken());
                points[pointIdx].setX(x);
                points[pointIdx].setY(y);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 특정 장소에서 다른 장소로 이동 가능한지를 확인하고 저장하는 메서드
     * @param points : 각 장소의 좌표를 저장한 배열
     * @param movable : 특정 장소에서 다른 장소로 이동 가능한지를 저장할 배열
     */
    public static void checkMovable(Point[] points, boolean[][] movable) {
        for (int depIdx = 0; depIdx <= n; depIdx++) {
            for (int arrIdx = depIdx + 1; arrIdx <= n; arrIdx++) {
                int distance = points[depIdx].calcDistanceTo(points[arrIdx]);
                if (distance <= MOVING_DISTANCE) {
                    movable[depIdx][arrIdx] = true;
                    movable[arrIdx][depIdx] = true;
                }
            }
        }
    }

    /**
     * 집에서 페스티벌 장소로의 이동이 가능한지를 판단하는 메서드
     * @param movable : 특정 장소에서 다른 장소로 이동 가능한지를 저장할 배열
     * @param visited : 이미 방문한 장소인지를 확인할 수 있는 배열
     * @param depIdx : 출발 장소 인덱스
     * @return 이동할 수 있으면 true, 이동 불가하면 false
     */
    public static boolean checkMovableFromDepToArr(boolean[][] movable,
                                                   boolean[] visited, int depIdx) {
        /* 이번 출발 장소 인덱스가 도착 장소의 인덱스와 같다면 true 반환 **/
        if (depIdx == n)
            return true;
        /* 이번 출발 장소가 이미 방문한 장소임을 표시 **/
        visited[depIdx] = true;
        for (int arrIdx = 0; arrIdx <= n; arrIdx++) {
            /* 이동할 장소가 이미 방문한 장소라면 continue **/
            if (visited[arrIdx])
                continue;
            /* 이동할 장소로의 이동이 가능하며, 하위 호출에서 true 반환이 있었다면 true 반환 **/
            if (movable[depIdx][arrIdx]
                    && checkMovableFromDepToArr(movable, visited, arrIdx))
                return true;
        }
        return false;
    }
}
