import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    /* 가로등 위치를 저장한 배열 **/
    private static int[] streetLightLocations;
    private static int N, M;

    public static void main(String[] args) {
        /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
        if (!input()) return;
        /* left: 가능한 작은 가로등의 높이(1) **/
        /* right: 가능한 큰 가로등의 높이(구간의 길이) **/
        int left = 1, right = N;
        
        while (left <= right) {
            /* 이번 회차의 가로등 높이 **/
            int mid = (left + right) >> 1;
            /* 이번 회차의 가로등 높이로 모든 구간을 비출 수 있는지 판별하는 변수 **/
            boolean illuminable = true;
            /* 첫 번째 가로등이 구간 시작점을 비출 수 없거나 **/
            /* 마지막 가로등이 구간 끝점을 비출 수 없다면 **/
            /* 최소 가로등 높이를 현재 가로등 높이 + 1로 설정하고 다음 회차로 이동 **/
            if (streetLightLocations[0] > mid 
                    || N - streetLightLocations[M - 1] > mid) {
                left = mid + 1;
                continue;
            }
            /* 이전 가로등이 비추는 오른쪽 끝 지점과 현재 바라보는 가로등이 비추는 왼쪽 끝 지점 사이에 **/
            /* 빛이 닿지 않는 공백 구간이 있다면 이번 회차 가로등 높이로는 부족함을 표시하고 **/
            /* 최소 가로등 높이를 현재 가로등 높이 + 1로 설정하고 break **/
            for (int index = 1; index < M; index++) {
                if (streetLightLocations[index - 1] + mid 
                        < streetLightLocations[index] - mid) {
                    left = mid + 1;
                    illuminable = false;
                    break;
                }
            }
            /* 바로 위의 반복문에서 break 발생이 없다면 **/
            /* 즉, 현재 가로등 높이로 모든 구간을 비출 수 있다면 **/
            /* 최대 가로등 높이를 현재 가로등 높이 - 1로 설정 **/
            if (illuminable)
                right = mid - 1;
        }
        /* 모든 구간을 비출 수 있는 가로등 높이 중, 가장 낮은 가로등 높이 출력 **/
        System.out.println(left);
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력 처리 성공 시 true, 예외 발생 시 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            M = Integer.parseInt(reader.readLine());
            streetLightLocations = new int[M];
            String[] lineInputSplits = reader.readLine().split(" ");
            for (int index = 0; index < M; index++)
                streetLightLocations[index] = Integer.parseInt(lineInputSplits[index]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
