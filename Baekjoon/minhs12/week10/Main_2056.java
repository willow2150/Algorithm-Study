import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, wcnt, minTime;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        // 위상정렬에 사용할 진입차수 저장 배열
        int[] edgeCount = new int[N + 1];     // 인덱스 1부터 사용
        // 위상정렬에 사용할 그래프 2차원 리스트로 구현
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int n = 0; n <= N; n++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            time[n] = Integer.parseInt(st.nextToken());
            wcnt = Integer.parseInt(st.nextToken());

            for (int w = 0; w < wcnt; w++) {
                int prev = Integer.parseInt(st.nextToken());
                adj.get(prev).add(n);   // 이전작업(prev)가 해야하는 다음작업(n)을 추가
                edgeCount[n]++;
            }
        }

        topologySort(adj, edgeCount);

        System.out.println(minTime);
    }

    private static void topologySort(ArrayList<ArrayList<Integer>> adj, int[] edgeCount) {
        int[] result = new int[N + 1];
        // 위상정렬에 사용하는 큐
        Queue<Integer> q = new ArrayDeque<>();

        // 진입차수가 0인 값 큐에 추가
        for (int i = 1; i <= N; i++) {
            result[i] = time[i];
            if (edgeCount[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int work = q.poll();                // 큐에서 노드 꺼내기

            for (int next : adj.get(work)) {    // 꺼낸 노드의 인접한 노드 찾기
                edgeCount[next]--;              // 인접한 노드 진입차수 갱신

                result[next] = Math.max(result[next], result[work] + time[next]);

                // 갱신된 노드의 진입차수가 0이면 큐에 노드 추가가
                if (edgeCount[next] == 0)
                    q.add(next);
            }
        }

        minTime = 0;
        for (int time : result)
            minTime = Math.max(minTime, time);
   }
}
