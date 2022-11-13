import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /* 마을을 잇는 도로로 사용할 사용자 정의 클래스 **/
    static class Road implements Comparable<Road> {
        /* 도로를 따라 걸었을 때 도착할 마을 **/
        int arrTown;
        /* 도로를 걷는 데 걸리는 시간 **/
        int time;
        /* 출발 마을에서 갈 수 있는 또 다른 도로 **/
        Road another;

        Road(int arrTown, int time, Road another) {
            this.arrTown = arrTown;
            this.time = time;
            this.another = another;
        }

        @Override
        public int compareTo(Road road) {
            return this.time <= road.time ? -1 : 1;
        }
    }

    private static int N, X;

    public static void main(String[] args) {
        final int INF = 10_000_000;
        /* 두 그래프를 저장할 그래프 배열 **/
        /* mapArray[0]: 정방향 그래프 **/
        /* mapArray[1]: 역방향 그래프 **/
        /* 역방향 그래프는 각 마을에서 파티장까지 가는 최단 거리를 찾기 위해 사용 **/
        /* 정방향 그래프는 파티장에서 각 마을까지 가는 최단 거리를 찾기 위해 사용 **/
        Road[][] graphArray = new Road[2][];
        /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
        if (!input(graphArray)) return;
        /* 각 마을에서 파티장으로 갈 때 걸리는 시간을 저장할 배열 **/
        int[] minTimesWhenToGo = new int[N + 1];
        /* 파티장에서 각 마을로 올 때 걸리는 시간을 저장할 배열 **/
        int[] minTimesWhenToCome = new int[N + 1];
        /* 두 배열의 각 요소를 큰 값으로 초기화 **/
        for (int town = 1; town <= N; town++) {
            minTimesWhenToCome[town] = INF;
            minTimesWhenToGo[town] = INF;
        }
        findOutMinTimes(graphArray[0], minTimesWhenToCome);
        findOutMinTimes(graphArray[1], minTimesWhenToGo);
        System.out.print(calcLongestTimeRequired(minTimesWhenToGo, minTimesWhenToCome));
    }

    /**
     * 입력을 처리하는 메서드
     * @param graphArray : 두 그래프를 저장할 그래프 배열
     * @return : 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input(Road[][] graphArray) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            X = Integer.parseInt(tokenizer.nextToken());
            Road[] map = graphArray[0] = new Road[N + 1];
            Road[] reversedMap = graphArray[1] = new Road[N + 1];
            for (int roadIdx = 0; roadIdx < m; roadIdx++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int depTown = Integer.parseInt(tokenizer.nextToken());
                int arrTown = Integer.parseInt(tokenizer.nextToken());
                int time = Integer.parseInt(tokenizer.nextToken());
                map[depTown] = new Road(arrTown, time, map[depTown]);
                reversedMap[arrTown] = new Road(depTown, time, reversedMap[arrTown]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 파티장이 있는 마을에서 각 마을까지의 최소 이동 시간을 찾는 메서드(정방향 그래프)
     * 혹은
     * 각 마을에서 파티장이 있는 마을까지의 최소 이동 시간을 찾는 메서드(역방향 그래프)
     * @param graph : 탐색할 그래프
     * @param minTimes : 최소 이동 시간을 저장할 배열
     */
    public static void findOutMinTimes(Road[] graph, int[] minTimes) {
        /* 다익스트라 알고리즘 적용을 위한 PriorityQueue **/
        Queue<Road> pq = new PriorityQueue<>();
        int numOfTowns = N;

        /* 파티장이 있는 마을은 소요 시간을 0으로 초기화 **/
        minTimes[X] = 0;
        pq.add(new Road(X, 0, null));
        while (numOfTowns > 1) {
            Road road = pq.poll();
            if (minTimes[road.arrTown] < road.time) continue;
            numOfTowns--;
            Road anotherRoad = graph[road.arrTown];
            while (anotherRoad != null) {
                int time = road.time + anotherRoad.time;
                if (time < minTimes[anotherRoad.arrTown]) {
                    minTimes[anotherRoad.arrTown] = time;
                    pq.add(new Road(anotherRoad.arrTown, time, null));
                }
                anotherRoad = anotherRoad.another;
            }
        }
    }

    /**
     * 각 마을에서 파티장을 왕복하는 데 걸리는 시간 중 최대 시간을 반환하는 메서드
     * @param minTimesWhenToGo : 각 마을에서 파티장으로 갈 때 걸리는 시간을 저장한 배열
     * @param minTimesWhenToCome : 파티장에서 각 마을로 올 때 걸리는 시간을 저장한 배열
     * @return : 마을에서 파티장을 왕복하는 데 걸리는 시간 중 최대 시간
     */
    public static int calcLongestTimeRequired(int[] minTimesWhenToGo,
                                              int[] minTimesWhenToCome) {
        int longestTimeRequired = 0;
        for (int town = 1; town <= N; town++) {
            int time = minTimesWhenToGo[town] + minTimesWhenToCome[town];
            if (time > longestTimeRequired)
                longestTimeRequired = time;
        }
        return longestTimeRequired;
    }
}
