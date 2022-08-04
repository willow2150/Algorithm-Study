import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    /* 소문자 알파벳 a **/
    private static final char LOWERCASE_A = 'a';
    /* 와일드카드 문자 **/
    private static final char WILD_CARD = '?';
    /* 입력받는 문자열(문자 배열) **/
    private static char[] str;
    /* 입력받은 문자열의 길이 **/
    private static int strLen;

    public static void main(String[] args) {
        /* 입력에서 예외가 발생했다면 프로그램을 종료합니다. **/
        if (!input())
            return;
        StringBuilder output = new StringBuilder();
        /* 문자열 길이를 2로 나눈 몫 **/
        int strLenHalf = strLen >> 1;

        /* 중간 지점까지 문자열을 대칭 비교: 진행 방향은 왼쪽 -> 중간 지점 **/
        for (int idx = 0; idx < strLenHalf; idx++) {
            if (str[idx] == WILD_CARD) {
                if (str[strLen - idx - 1] == WILD_CARD) {
                    str[idx] = LOWERCASE_A;
                } else {
                    str[idx] = str[strLen - idx - 1];
                }
            }
            output.append(str[idx]);
        }

        /* 문자열의 길이가 홀수이면? => 중앙 문자가 '?'일 경우 'a'를, 아닐 경우 그대로 출력에 저장 **/
        if ((strLen & 1) == 1)
            output.append(str[strLenHalf] == WILD_CARD ? LOWERCASE_A : str[strLenHalf]);

        /* 나머지 부분을 출력에 저장: 중간 지점 -> 왼쪽 **/
        for (int idx = strLenHalf - 1; idx >= 0; idx--)
            output.append(str[idx]);

        /* 결과 출력 **/
        System.out.print(output);
    }

    /**
     * 입력을 수행하는 메서드
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            strLen = Integer.parseInt(reader.readLine());
            str = reader.readLine().toCharArray();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
