import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    /* 집을 잇는 길로 사용할 사용자 정의 클래스 **/
    static class Road implements Comparable<Road> {
        int houseA, houseB;
        int cost;

        public Road(int houseA, int houseB, int cost) {
            this.houseA = houseA; this.houseB = houseB;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road road) {
            return this.cost <= road.cost ? -1 : 1;
        }
    }

    /* 최대 집의 수 **/
    private static final int MAX_NUM_OF_HOUSES = 200_000;
    /* 집의 수 **/
    private static int m;
    /* 모든 길의 비용 합 **/
    private static int totalCost;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        /* 길을 저장할 List **/
        List<Road> roadList = new ArrayList<>();
        /* Union-Find를 적용하고자 만든 parent 배열 **/
        int[] parentHouse = new int[MAX_NUM_OF_HOUSES];

        while (true) {
            /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
            if (!input(reader, roadList)) return;
            /* 프로그램 종료 조건 체크 **/
            if (m == 0) break;
            /* 길의 비용이 낮은 것을 우선으로 List를 정렬 **/
            Collections.sort(roadList);
            /* 각 집의 parent가 자기 자신이 되도록 설정 **/
            initParent(parentHouse);
            /* 필요한 최소 유지 비용 **/
            /* 전체 비용에서 길을 유지하는 최소 비용을 빼면 절약할 수 있는 최대 비용 **/
            int necessaryCost = 0;
            /* 필요한 길의 수는 집의 수 - 1 **/
            int numOfRoadsToKeep = m - 1;
            for (Road road : roadList) {
                /* 두 집의 parent가 같다면 continue **/
                if (findParentHouse(parentHouse, road.houseA)
                        == findParentHouse(parentHouse, road.houseB)) {
                    continue;
                }
                /* 다르면 두 집을 잇고 **/
                connect(road, parentHouse);
                /* 길의 비용을 유지 비용에 합산 **/
                necessaryCost += road.cost;
                /* 필요한 길의 수를 줄이고, 그 값이 0과 같다면 반복문 종료 **/
                if (--numOfRoadsToKeep == 0)
                    break;
            }
            /* 전체 비용에서 길을 유지하는 최소 비용 뺀 값(최대 절약 비용)을 출력 문자열에 저장 **/
            output.append(totalCost - necessaryCost).append('\n');
            /* 다음 테스트케이스를 위해 전체 비용을 0으로 초기화 **/
            totalCost = 0;
            /* 다음 테스트케이스를 위해 길 목록을 초기화 **/
            roadList.clear();
        }
        System.out.print(output);
    }

    /**
     * 입력을 처리하는 메서드
     * @param reader : BufferedReader
     * @param roadList : 길을 저장할 배열
     * @return : 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input(BufferedReader reader, List<Road> roadList) {
        try {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            m = Integer.parseInt(tokenizer.nextToken());
            int n = Integer.parseInt(tokenizer.nextToken());
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                Road road = new Road(
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken())
                );
                roadList.add(road);
                totalCost += road.cost;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 각 집의 parent를 자기 자신으로 초기화하는 메서드
     * @param parentHouse : Union-Find를 적용하고자 만든 parent 배열
     */
    public static void initParent(int[] parentHouse) {
        for (int house = 0; house < m; house++)
            parentHouse[house] = house;
    }

    /**
     * 집의 parent를 찾아 반환하는 메서드
     * parent를 찾는 과정에서 조회되는 모든 집을 최고 조상 집과 부모-자식으로 설정
     * @param parentHouse : Union-Find를 적용하고자 만든 parent 배열
     * @param house : parent를 찾을 집
     * @return : parent 집을 찾을 집의 최고 조상 집
     */
    public static int findParentHouse(int[] parentHouse, int house) {
        if (parentHouse[house] == house)
            return house;
        return parentHouse[house] = findParentHouse(parentHouse, parentHouse[house]);
    }

    /**
     * 두 집을 잇는 메서드
     * 두 집의 parent를 비교하여 번호가 낮은 쪽이 높은 쪽의 parent가 되도록 설정
     * @param road : 없애지 않고 유지할 길
     * @param parentHouse : Union-Find를 적용하고자 만든 parent 배열
     */
    public static void connect(Road road, int[] parentHouse) {
        int houseAParent = parentHouse[road.houseA];
        int houseBParent = parentHouse[road.houseB];
        if (houseAParent < houseBParent)
            parentHouse[houseBParent] = houseAParent;
        else
            parentHouse[houseAParent] = houseBParent;
    }
}
