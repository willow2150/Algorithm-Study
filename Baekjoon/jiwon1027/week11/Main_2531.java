package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 그냥 2중 for문 돌리면서 체크해주면 시간초과(N <= 3,000,000)
 * 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격
 * => Queue나 슬라이딩 윈도우 느낌난다
 * 일단 회전 초밥 벨트가 원형이기 때문에 arr[N+K]로 하면 k의 window로 탐색했을 때 모든 경우 가능
 *
 * */


public class Main_2531 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());


        int[] belt = new int[N+K];
        int[] sushi = new int[D+1]; //이걸로 중복체크 할꺼임
        Queue<Integer> window = new ArrayDeque<>();


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            belt[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            belt[i+N] = belt[i];
        }

        //System.out.println(Arrays.toString(belt));

        int res = 0;
        int cnt = 0;
        for (int i = 0; i < N+K; i++) {
            int temp = belt[i];

            window.add(temp);
            sushi[temp] += 1;
            if (sushi[temp] == 1)
                cnt++;

            if (i < K-1)
                continue;
            //System.out.println(window.toString());
            if (sushi[C] == 0)
                res = Math.max(res, cnt+1);
            else
                res = Math.max(res,cnt);

            int poll_temp = window.poll();
            sushi[poll_temp] -= 1;

            if (sushi[poll_temp] == 0)
                cnt--;

        }

        System.out.println(res);





    }
}
