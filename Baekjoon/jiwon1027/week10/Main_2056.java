package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 걸린시간 : 1시간
 * 한번에 바로 정답을 도출해내기엔 너무 어려웠던 문제였는데 되게 재밌는 문제였다.
 *
 *
 * 선행 관계라는 단어가 보자마자 위상 정렬이 떠올랐다.
 * 보니까 그냥 위상정렬 개념에다가 가중치를 고려해서 최소 시간을 구하라는 것 같음
 *
 * 이거 그림 그려보니까 위상정렬 틀대로 한다음에 해도 되고
 * 그냥 n번 작업이랑 연결된 여러 작업들 중 최댓값을 구하고 n번 작업시간이랑 더하면 그게 최종적으로 n번 작업 시간임.
 * why?)
 * 문제보면 반드시 해결되야되는 작업이기 때문에 1번 작업이 2,4번이랑 연결되있다고 치면
 * 2번 : 5초, 4번 : 10초 걸리면 무조껀 10초를 택해야됨. 그래야 2,4번 모두 완료할 수 있으니까.
 *
 * dp배열에 각 작업 완료 시간 저장할껀데 from -> to 관계에서
 * dp[from] = dp[from] + max(여러개의 dp[to])
 *
 *
 * 최종적으로 dp들 중 가장 시간이 많은게 문제에서 모든 작업을 완료하는 최소 시간임
 * why?)
 * dp배열이 각 작업마다 완료하는 시간을 모두 더한건데 모든 작업을 완료한걸 구하려면 그중에서 가장 큰 녀석을 고르는게 모든 작업을 완료한 시간이 아닐까?
 * 그리고 가장 큰 녀석은 이미 수많은 경우 중 가장 작은 녀석으로 미리 완성된 녀석인거지
 *
 *
 * */


public class Main_2056 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[N+1];

        List<Integer>[] list = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());

            dp[i] = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                int to = Integer.parseInt(st.nextToken());
                list[i].add(to);
            }
        }

        for (int i = 1; i < N+1; i++) {
            int max_time = 0;
            for (int value:list[i]) {
                if (max_time <= dp[value])
                    max_time = dp[value];
            }
            dp[i] += max_time;
        }

        int res = 0;
        for (int i = 1; i < N+1; i++) {
            if (res <= dp[i])
                res = dp[i];
        }

        System.out.println(res);





    }
}
