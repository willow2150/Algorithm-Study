import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /* 수열을 저장할 배열 **/
    private static int[] sequence;
    /* 조건에 맞는 부분수열의 개수 **/
    private static int numOfSuitableSubsequences = 0;
    /* 입력받을 수열의 크기(길이) **/
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        /* 입력받을 수열의 크기(길이) **/
        N = Integer.parseInt(tokenizer.nextToken());
        /* 부분 수열의 요소를 모두 더한 값 **/
        int S = Integer.parseInt(tokenizer.nextToken());

        /* 수열 입력받기 **/
        tokenizer = new StringTokenizer(reader.readLine());
        sequence = new int[N];
        for (int index = 0; index < N; index++)
            sequence[index] = Integer.parseInt(tokenizer.nextToken());

        /* 조건에 맞는 부분수열의 개수 세어주는 메서드 호출(조합 찾기) **/
        countNumOfSubsequencesToFind(0, S);

        /*
         * 합(S)이 0이길 원하는 경우엔 1을 감합니다.
         * 왜냐하면, 아무런 원소도 선택하지 않는 경우가 있기 때문입니다.
         */
        if (S == 0)
            numOfSuitableSubsequences--;

        /* 정답 출력 **/
        System.out.println(numOfSuitableSubsequences);
    }

    /**
     * 조건에 들어맞는 부분 수열의 개수를 세어주는 메서드
     * 0으로 시작하여 최종적으로 합이 S인지를 판단해도 좋지만,
     * 저는 S로 시작하여 수열에서 선택한 수를 빼주는 방식으로 진행하였습니다.
     * 이러한 방식이라면, 최종적으로 값은 0이 되어야 합니다.
     * @param index : 현재 차례의 인덱스
     * @param difference : S - 현재까지 수열에서 선택했던 수들의 합
     */
    public static void countNumOfSubsequencesToFind(int index, int difference) {
        /* 더 조회할 수가 없는 경우(수열의 크기를 벗어난 경우) **/
        if (index == N) {
            /* 수열의 수를 선택하여 빼준 결과가 0이라면 **/
            if (difference == 0)
                numOfSuitableSubsequences++;
            return;
        }
        /* 현재 순서(index)의 숫자를 선택하지 않고 넘어가는 경우 **/
        countNumOfSubsequencesToFind(index + 1, difference);
        /* 현재 순서(index)의 숫자를 선택하여 빼는 경우 **/
        countNumOfSubsequencesToFind(index + 1, difference - sequence[index]);
    }
}
