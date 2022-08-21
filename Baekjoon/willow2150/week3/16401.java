import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /* 과자 길이를 입력받을 배열 **/
    private static int[] snackLenArray;
    private static int M, N;

    public static void main(String[] args) {
        /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
        if (!input()) return;
        /* 과자 길이를 오름차순으로 정렬 **/
        Arrays.sort(snackLenArray);

        /* 가장 짧은 과자의 길이: 1 **/
        int shortestSnackLen = 1;
        /* 가장 긴 과자의 길이: 마지막 과자의 길이 **/
        int longestSnackLen = snackLenArray[N - 1];

        /* 적정 과자 길이를 이진 탐색으로 찾기 **/
        while (shortestSnackLen <= longestSnackLen) {
            int snackLen = (shortestSnackLen + longestSnackLen) >> 1;
            int snackIdx = findSnackIdxOfGreaterOrEqualLen(snackLen);
            int numOfSnacks = 0;
            /* 과자의 개수를 세는 과정(원하는 과자 길이보다 2배 이상 길다면 여러 개 획득 가능 **/
            for (int snackIndex = snackIdx; snackIndex < N; snackIndex++)
                numOfSnacks += snackLenArray[snackIndex] / snackLen;
            /* 과자의 개수가 부족하여 조카들에게 나눠줄 수 없다면 과자 길이를 줄이기 **/
            /* 과자의 개수가 충분하여 조카들에게 나눠줄 수 있다면 과자 길이를 늘이기 **/
            if (numOfSnacks < M)
                longestSnackLen = snackLen - 1;
            else
                shortestSnackLen = snackLen + 1;
        }
        System.out.print(longestSnackLen);
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            M = Integer.parseInt(tokenizer.nextToken());
            N = Integer.parseInt(tokenizer.nextToken());
            snackLenArray = new int[N];
            tokenizer = new StringTokenizer(reader.readLine());
            for (int index = 0; index < N; index++)
                snackLenArray[index] = Integer.parseInt(tokenizer.nextToken());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 파라미터로 주어진 과자의 길이보다 길거나 같은 것 중 가장 왼쪽에 있는 인덱스를 반환하는 메서드
     * @param snackLen : 과자의 길이
     * @return : 파라미터로 주어진 과자의 길이보다 길거나 같은 것 중 가장 왼쪽에 있는 인덱스
     */
    public static int findSnackIdxOfGreaterOrEqualLen(int snackLen) {
        int leftIdx = 0;
        int rightIdx = N - 1;
        while (leftIdx <= rightIdx) {
            int midIdx = (leftIdx + rightIdx) >> 1;
            if (snackLen <= snackLenArray[midIdx])
                rightIdx = midIdx - 1;
            else
                leftIdx = midIdx + 1;
        }
        return leftIdx;
    }
}
