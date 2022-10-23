import java.lang.Math;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/*
* 번호 순대로 정렬을 하려는데 바꾸는 횟수를 최소한으로 하고싶다.
* 1. 완탐  : N<=200 이기 때문에 안됨
* 2. 그리디 : 뒤에서부터 탐색해서 지금값보다 큰 값이 나오면 나보다 뒤에 있어야하니까 걔를 뒤로 옮기는 방식(최소가 아님)
* 3. 부분 증가 배열이였나. 주어진 배열에서 최소로 움직이려면 가장 길게 오름차순 되어있는 애들은 유지한채 다른 애들을 옮겨야함
*   => 근데 문제에서 값의 이동경로가 아닌 단순 최소 이동횟수만 구한것이기 때문에 "N - 주어진 배열에서의 LIS값" 을 해주면 될 것 같음
* */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] data = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (data[i] > data[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max_value = 0;

        for (int i = 0; i < N; i++) {
            max_value = Math.max(max_value, dp[i]);
        }

        //System.out.println(Arrays.toString(dp));
        System.out.println(N - max_value);


    }
}
