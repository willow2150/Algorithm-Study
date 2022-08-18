import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /* 주유소 클래스 **/
    static class GasStation implements Comparable<GasStation> {
        /* 주유소의 좌표 **/
        int coordinate;
        /* 주요소에서 채울 수 있는 연료의 양 **/
        int fuelVolume;

        public GasStation(int coordinate, int fuelVolume) {
            this.coordinate = coordinate;
            this.fuelVolume = fuelVolume;
        }

        @Override
        public int compareTo(GasStation gasStation) {
            return this.coordinate < gasStation.coordinate ? -1 : 1;
        }
    }

    private static GasStation[] gasStations;
    private static int N, P;

    public static void main(String[] args) {
        if (!input()) return;
        System.out.print(findMinNumOfStops());
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력 처리 성공 시 true 반환, 예외 발생 시 false 반환
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer;
            N = Integer.parseInt(reader.readLine());
            gasStations = new GasStation[N + 1];
            for (int gasStationIdx = 0; gasStationIdx < N; gasStationIdx++) {
                tokenizer = new StringTokenizer(reader.readLine());
                gasStations[gasStationIdx] = new GasStation(
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken())
                );
            }
            tokenizer = new StringTokenizer(reader.readLine());
            int L = Integer.parseInt(tokenizer.nextToken());
            P = Integer.parseInt(tokenizer.nextToken());
            /* 도착 지점도 주유소 목록에 추가(주유 가능한 연료량은 0) **/
            gasStations[N] = new GasStation(L, 0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 목적지에 도착하기 위해 정차해야 할 최소 주유소의 수를 찾는 메서드
     * @return : 목적지에 도착하기 위해 정차해야 할 최소 주유소의 수
     */
    public static int findMinNumOfStops() {
        /* 이미 통과한 주유소를 채울 수 있는 연료의 양에 따라 내림차순으로 정렬하는 우선순위 큐 **/
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        /* 보유한 연료로 이동 가능한 좌표 중 가장 오른쪽 좌표 **/
        int maxMoveCoordinate = P;

        /* 주유소를 위치에 따라 오름차순으로 정렬 **/
        Arrays.sort(gasStations);
        for (GasStation gasStation: gasStations) {
            /* 현재 연료의 양으로 다음 주유소까지 이동이 불가하다면 **/
            while (maxMoveCoordinate < gasStation.coordinate) {
                /* 지나온 주유소 중, 주유 가능한 주유소가 남아있지 않다면 **/
                if (pq.isEmpty())
                    return -1;
                /* 지나온 주유소 중, 주유 가능한 주유소가 있다면 정차하고 주유한 것으로 설정 **/
                maxMoveCoordinate += pq.poll();
            }
            /* 현재 주유소를 통과한 주유소 목록에 추가 **/
            pq.add(gasStation.fuelVolume);
        }
        /* 전체 주유소 중 정차하지 않은 주유소의 수를 감하여 반환 **/
        /* 도착 지점은 제외해야 하므로, 1을 추가하여 반환 **/
        return N - pq.size() + 1;
    }
}
