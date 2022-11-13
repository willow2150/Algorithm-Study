import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    /* 테스트케이스에서 주어질 수 있는 가장 큰 수 **/
    private static final int MAX_INPUT_NUMBER = 1_000;
    /* 세 삼각수의 합으로 표현이 가능한 경우 **/
    private static final char POSSIBLE = '1';
    /* 세 삼각수의 합으로 표현할 수 없는 경우 **/
    private static final char IMPOSSIBLE = '0';
    /* 각 테스트케이스에서 주어진 수를 저장한 배열 **/
    private static int[] inputNumbers;
    /* 삼각수를 저장할 배열 **/
    private static int[] triangularNumbers;

    public static void main(String[] args) {
        /* 입력 처리 중 예외가 발생하면 프로그램 종료 **/
        if (!input()) return;
        StringBuilder output = new StringBuilder();
        /* 테스트케이스에서 주어질 수 있는 가장 큰 수보다 작은 삼각수들을 찾아서 배열에 저장 **/
        findTriangularNumbers(MAX_INPUT_NUMBER);
        /* 각 테스트케이스에서 주어진 수를 세 삼각수의 합으로 표현할 수 있다면? **/
        /* POSSIBLE(1), 불가능하면 IMPOSSIBLE(0)을 출력 문자열에 저장 **/
        for (int inputNumber: inputNumbers) {
            output.append(
                    isAbleToExpress(inputNumber, 1, 0) ? POSSIBLE : IMPOSSIBLE
            ).append('\n');
        }
        /* 출력 **/
        System.out.print(output);
    }

    /**
     * 주어진 수를 삼각수의 합으로 표현할 수 있는지를 판별해주는 메서드
     * @param number : 처음 주어진 수 - 선택된 삼각수의 합
     * @param index : 삼각수 배열에서 선택할 수의 인덱스
     * @param numOfSelected : 현재까지 선택된 삼각수의 수
     * @return : 삼각수로 표현할 수 있는지를 반환
     */
    public static boolean isAbleToExpress(int number, int index, int numOfSelected) {
        /* 선택한 삼각수의 개수가 3개일 때, 세 삼각수의 합이 처음 주어진 수와 같은지를 반환하는 메서드 **/
        if (numOfSelected == 3)
            return number == 0;
        /* 선택할 수 있는 삼각수가 아직 있는 경우 **/
        /* 선택하고 다음 삼각수를 고려하는 경우 || 선택하지 않고 다른 삼각수를 고려하는 경우를 반환 **/
        if (index + 1 < triangularNumbers.length)
            return isAbleToExpress(number - triangularNumbers[index], 1, numOfSelected + 1)
                    || isAbleToExpress(number, index + 1, numOfSelected);
        /* 선택할 수 있는 삼각수가 더 이상 없으므로, false 반환 **/
        return false;
    }

    /**
     * 사용할 삼각수를 찾아 저장하는 메서드
     * @param number : 사용할 삼각수 중, 마지막 삼각수를 찾기 위해 사용할 수
     */
    public static void findTriangularNumbers(int number) {
        /* 삼각수 **/
        int triangularNumber = 0;
        /* 항 번호 **/
        int term = 0;
        /* 사용할 삼각수 수열의 마지막 항을 찾는 과정 **/
        for (int addends = 1; triangularNumber < number; addends++) {
            triangularNumber += addends;
            term++;
        }

        /* 삼각수를 저장할 배열을 생성 및 할당 **/
        triangularNumbers = new int[term];
        /* 각 항에 알맞은 삼각수를 저장 **/
        for (int addends = 1; addends < term; addends++)
            triangularNumbers[addends] = triangularNumbers[addends - 1] + addends;
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력이 정상 처리되면 true, 예외가 발생하면 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            /* 테스트케이스의 수 **/
            int numOfTC = Integer.parseInt(reader.readLine());
            /* 테스트케이스에서 주어지는 수를 저장할 배열 생성 및 할당 **/
            inputNumbers = new int[numOfTC];
            /* 각 테스트케이스에서 주어지는 수 저장 **/
            for (int tC = 0; tC < numOfTC; tC++)
                inputNumbers[tC] = Integer.parseInt(reader.readLine());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
