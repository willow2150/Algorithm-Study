package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 *  N*M board에서 최대한 많은 별똥별을 튕기는 최적의 자리에 L*L 트램펄린을 배치했을 때 몇개가 튕길까
 *  => board에서 L*L를 탐색할 때 가장 많이 찾은애는 몇개일까?
 *
 * 1. 반복문으로 모든 부분을 탐색하는건 O(500,000^2) 안됨
 * 2. 별똥별이 주어진 부분을 가지고 어느부분에 몰려있는지 군집도를 검사하면 될까?(좌표압축?)
 * 3. 별똥별을 기준으로 만들 수 있는 모든 조합을 본다
 *      => 2개를 고르면 2개를 기준으로 사각형을 만들어보고 그 안에 별똥별이 몇개 있는지 하나씩 모두 검사하면서 최댓값 갱신
 */


public class Main_14658 {
    static int N, M, L, K;
    static int[][] data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        data = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        int res = 0;

        for (int i = 0; i < K; i++) {
            int[] star1 = data[i];
            for (int j = 0; j < K; j++) {
                int[] star2 = data[j];

                int cnt = 0;
                for (int k = 0; k < K; k++) {
                    int[] star = data[k];
                    if (star1[0] <= star[0] && star[0] <=star1[0]+L && star2[1] <= star[1] && star[1] <=star2[1]+L)
                        cnt++;
                }
                res = Math.max(res, cnt);
            }
        }
        System.out.println(K-res);

    }
}
