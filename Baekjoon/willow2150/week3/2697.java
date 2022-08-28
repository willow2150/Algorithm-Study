import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    /* 각 테스트케이스에서 입력된 수를 문자열로 저장할 배열 **/
    private static String[] numbers;

    public static void main(String[] args) {
        /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
        if (!input()) return;
        /* 출력을 저장할 문자열 **/
        StringBuilder output = new StringBuilder();
        /* 숫자의 등장 횟수를 저장할 배열 **/
        int[] counter = new int[10];
        for (String number: numbers) {
            /* 다음 수를 찾아 입력 문자열에 저장 **/
            printNextNumber(number, counter, output);
            /* 카운터 배열 초기화 **/
            initCounter(counter);
        }
        /* 결과 출력 **/
        System.out.print(output);
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numOfNumbers = Integer.parseInt(reader.readLine());
            numbers = new String[numOfNumbers];
            for (int index = 0; index < numOfNumbers; index++)
                numbers[index] = reader.readLine();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 입력된 수의 다음 수를 찾아 출력 문자열에 저장하는 메서드
     * @param number : 입력된 수(문자열 형식)
     * @param counter : 숫자의 등장 횟수를 저장할 카운터 배열
     * @param output : 출력 문자열
     */
    public static void printNextNumber(String number, int[] counter, StringBuilder output) {
        /* 뒤에서부터 오름차순이 끊기는 지점을 찾으며 숫자를 카운트하는 과정 **/
        int idxToStop = number.length() - 2;
        while (idxToStop >= 0 && number.charAt(idxToStop) >= number.charAt(idxToStop + 1))
            counter[number.charAt(idxToStop--) - '0']++;
        if (idxToStop < 0) {
            /* 더 비교할 인덱스가 없다면 가장 큰 수. BIGGEST 출력 **/
            output.append("BIGGEST").append('\n');
        } else {
            /* 마지막 자리 숫자 카운트를 1 증가 **/
            counter[number.charAt(number.length() - 1) - '0']++;
            /* 오름차순이 끊긴 자리의 숫자 카운트를 1 증가 **/
            counter[number.charAt(idxToStop) - '0']++;
            /* 오름차순이 끊긴 자리 이전까지의 숫자를 그대로 출력 문자열에 저장 **/
            for (int idx = 0; idx < idxToStop; idx++)
                output.append(number.charAt(idx));
            /* 오름차순이 끊긴 자리의 숫자를 대신할 숫자(해당 수보다 큰 수중에서 가장 작은 숫자) **/
            /* 를 찾아 출력 문자열에 저장 **/
            for (int n = number.charAt(idxToStop) - '0' + 1; n <= 9; n++) {
                if (counter[n] > 0) {
                    output.append(n);
                    counter[n]--;
                    break;
                }
            }
            /* 이후 카운터 배열을 참고하여 오름차순으로 출력 문자열에 저장 **/
            for (int n = 0; n <= 9; n++)
                while (counter[n]-- > 0)
                    output.append(n);
            output.append('\n');
        }
    }

    /**
     * 숫자의 등장 횟수를 저장한 배열을 초기화하는 메서드
     * @param counter : 숫자의 등장 횟수를 저장한 배열
     */
    public static void initCounter(int[] counter) {
        for (int number = 0; number <= 9; number++)
            counter[number] = 0;
    }
}
