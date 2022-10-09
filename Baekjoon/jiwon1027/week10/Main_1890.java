package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/* 걸린시간 : 20분
 * 시작점에서 오른쪽, 아래로 가는 모든 경우의 수를 볼 수 있다
 * 하지만 N이 100이고 경로의 개수는 최대 2^63-1인걸 보니 DP를 사용하라는 냄새가 난다
 * 일단 규칙을 찾아보고 정 안되면 dfs해보자(근데 시간초과 날 거같음)
 *
 *
 * <Point>
 * 1. 항상 조건에 맞지 않으면 continue를 남발하는 습관이 있는데
 * continue 아래에 고려해야할 조건이 있음에도 불구하고 생각없이 continue를 쓰는 습관을 고쳐야겠다
 *
 * 2. 왜 굳이 for문 안에 마지막 조건을 줘서 종료 시켜야할까? 2중 for 끝나고 밖에서 호출시키면 답이 다르던데?
 * 문제에서 도착지점의 값은 무조껀 0인데 우리가 dp 점화식을 짤 때 dp[i+value][j] += dp[i][j]로 했기 때문에 원하지 않은 값이 저장되기 때문!
 *
 * 3. 경로의 개수는 최대 2^63-1이기 때문에 dp의 Type은 long
 *
 *
 * */
public class Main_1890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][N];
        long[][] dp = new long[N][N]; //i,j로 올 수 있는 총 경로의 수
        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (i==N-1 && j==N-1){
                    System.out.println(dp[i][j]);
                    System.exit(0);
                }
                int value = board[i][j];

                if (0<=i+value &&  i+value <N)
                    dp[i+value][j] += dp[i][j];

                if (0<=j+value &&  j+value <N)
                    dp[i][j+value] += dp[i][j];
            }
        }



        //System.out.println(dp[N-1][N-1]);





    }
}