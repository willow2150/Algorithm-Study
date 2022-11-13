import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final int NINE = 9;
    private static final int THREE = 3;
    private static final int ONE = 1;
    /* 스도쿠를 저장하는 배열 **/
    private static int[][] sudoku;
    private static int[][] bitSumArrayForSquare;
    private static int[] bitSumArrayForRow, bitSumArrayForCol;

    public static void main(String[] args) {
        /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
        if (!input()) return;
        /*
         * 아래 3개의 배열은 비트 마스킹을 적용할 배열입니다.
         * 아래는 예시입니다.
         * 1 2 3 4 5 6 7 8 0
         * 9 0 0 0 0 0 0 0 0
         * 8 0 0 0 0 0 0 0 0
         * 7 0 0 0 0 0 0 0 0
         * 6 0 0 0 0 0 0 0 0
         * 5 0 0 0 0 0 0 0 0
         * 4 0 0 0 0 0 0 0 0
         * 3 0 0 0 0 0 0 0 0
         * 0 0 0 0 0 0 0 0 0
         *
         * 가장 맨 위의 행(0번 행)은 1, 2, 3, 4, 5, 6, 7, 8
         * 로 구성되어 있습니다.
         *
         * 이 경우, bitSumArrayForRow[0]은 처음에 0을 저장하고 있었지만,
         * 아래처럼 OR 연산을 적용할 수 있습니다.
         *
         * bitSumArrayForRow[0] |= (1 << 1);
         * bitSumArrayForRow[0] |= (1 << 2);
         *              ...
         * bitSumArrayForRow[0] |= (1 << 8);
         *
         * 참고로, (1 << n) => 2의 n제곱 입니다.
         * 비트 연산으로 모든 비트를 왼쪽으로 1회 이동시키는 연산은 그 수에 2를 곱하는 것과 같습니다.
         *
         * 위의 결과를 이진수로 나타내면 다음과 같습니다. 0 1   1 1 1 1   1 1 1 0
         * 2의 0승, 그러니까 맨 오른쪽 비트는 이 문제에서 사용하지 않습니다. 그러므로 비워둡니다.
         *
         * 즉, 각 비트 자리에 1이 존재한다는 것은 해당 비트에 대응하는 수가
         * 해당 행에 사용되었다는 의미입니다.
         *
         * OR 연산의 결과는 피연산자 중 하나라도 1이면 1, 피연산자가 모두 0이면 0입니다.
         * AND 연산의 결과는 피연산자 모두 1일 때 1, 피연산자 중 하나라도 0이면 0입니다.
         * XOR 연산의 결과는 두 피연산자가 같으면 0, 다르면 1입니다.
         * 이 문제에서 OR 연산은 "비트에 대응하는 수를 이번 좌표에서 사용한다."고 표시하는 것과 같습니다.
         * 0 OR 1 = 1
         * 이 문제에서 AND 연산은 "비트에 대응하는 수가 이미 사용된 적이 있는지"를 확인하는 것과 같습니다.
         * 0 AND 1 = 0, 1 AND 1 = 1
         * 이 문제에서 XOR 연산은 "비트에 대응하는 수의 사용을 취소한다."고 표시하는 것과 같습니다.
         * 1 XOR 1 = 0
         *
         * 열 또한 bitSumArrayForCol 을 사용해 같은 방식을 적용합니다.
         *
         * 사각형의 경우, 조금 특별합니다.
         * 0, 1, 2 는 3으로 나눈 몫이 0입니다.
         * 3, 4, 5 는 3으로 나눈 몫이 1입니다.
         * 6, 7, 8 은 3으로 나눈 몫이 2입니다.
         *
         * 즉, 0행 0열, 0행 1열, 0행 2열
         *     1행 0열, 1행 1열, 1행 2열
         *     2행 0열, 2행 1열, 2행 2열
         *
         * 의 각 행과 열을 3으로 나누면, 모두 0행 0열로 변환됩니다.
         * 따라서 위 9개 칸은 bitSumArrayForSquare[0][0] 에 비트 연산을 적용하게 됩니다.
         * 즉, 9 x 9 배열 중 각 사각형(각 9칸)은 행과 열을 3으로 나눈 몫으로
         * 3 x 3 배열에 대응시킵니다.
         * 3 x 3 배열에 대응된 위치에 OR 연산을 적용, 사용한 수에 대응하는 비트를 저장합니다.
         */
        bitSumArrayForSquare = new int[THREE][THREE];
        bitSumArrayForRow = new int[NINE];
        bitSumArrayForCol = new int[NINE];
        /* 초기 스도쿠 상태를 위의 비트 합 배열들에 반영 **/
        initBitSumArrays();
        /* 스도쿠를 완성하는 메서드 호출 **/
        completeSudoku(0, 0);
        /* 완성된 스도쿠를 출력 **/
        printSudoku();
    }

    /**
     * 입력을 처리하는 메서드
     * @return 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            sudoku = new int[NINE][NINE];
            for (int row = 0; row < NINE; row++) {
                String inputLine = reader.readLine();
                for (int col = 0; col < NINE; col++)
                    sudoku[row][col] = inputLine.charAt(col) - '0';
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     */
    public static void initBitSumArrays() {
        for (int row = 0; row < NINE; row++) {
            for (int col = 0; col < NINE; col++) {
                /* 수가 0인 칸은 아직 비어있는 칸이므로, 비트 연산을 적용하지 않습니다. **/
                if (sudoku[row][col] == 0)
                    continue;
                /* 각 행, 각 열, 각 사각형에 비트 연산을 적용합니다. **/
                int bit = 1 << sudoku[row][col];
                bitSumArrayForSquare[row / THREE][col / THREE] |= bit;
                bitSumArrayForRow[row] |= bit;
                bitSumArrayForCol[col] |= bit;
            }
        }
    }

    /**
     * 스도쿠를 채워나가는 메서드
     * @param row : 현재 행
     * @param col : 현재 열
     * @return : 스도쿠가 완성되면 true, 완성되지 못하면 false
     */
    public static boolean completeSudoku(int row, int col) {
        /* 현재 행 번호가 9라면 완성된 것이므로, true 반환 **/
        if (row == NINE)
            return true;
        /* 현재 열 번호가 9라면 다음 행으로 이동 **/
        if (col == NINE)
            return completeSudoku(row + 1, 0);
        /* 현재 칸의 번호가 0보다 크다면 초기에 정해진 것이므로 다음 열로 이동 **/
        if (sudoku[row][col] > 0)
            return completeSudoku(row, col + 1);

        for (int number = ONE; number <= NINE; number++) {
            /* 수를 비트로 변환(대응 비트) **/
            int bit = 1 << number;
            /* 변환된 비트가 현재 행 비트 합에 이미 존재한다면 continue **/
            if ((bitSumArrayForRow[row] & bit) != 0)
                continue;
            /* 변환된 비트가 현재 열 비트 합에 이미 존재한다면 continue **/
            if ((bitSumArrayForCol[col] & bit) != 0)
                continue;
            /* 변환된 비트가 현재 사각형 비트 합에 이미 존재한다면 continue **/
            if ((bitSumArrayForSquare[row / THREE][col / THREE] & bit) != 0)
                continue;
            /* 스도쿠에 수를 대입 **/
            sudoku[row][col] = number;
            /* 현재 행, 열, 사각형의 비트 합에 OR 연산을 적용,  **/
            bitSumArrayForSquare[row / THREE][col / THREE] |= bit;
            bitSumArrayForRow[row] |= bit;
            bitSumArrayForCol[col] |= bit;
            /* 하위 호출에서 스도쿠가 완성되었다면, 더 진행하지 않고 true 반환 **/
            if (completeSudoku(row, col + 1))
                return true;
            /* 하위 호출에서 스도쿠가 완성되지 못했다면, 현재 칸을 0으로 초기화 **/
            sudoku[row][col] = 0;
            /* 현재 행, 열, 사각형의 비트 합을 OR 연산 이전으로 변경(XOR 연산 적용) **/
            bitSumArrayForSquare[row / THREE][col / THREE] ^= bit;
            bitSumArrayForRow[row] ^= bit;
            bitSumArrayForCol[col] ^= bit;
        }
        /* 모든 수를 대입해도 스도쿠를 완성하지 못한 것이므로 false 반환 **/
        return false;
    }

    /**
     * 완성된 스도쿠를 출력하는 메서드
     */
    public static void printSudoku() {
        StringBuilder output = new StringBuilder();
        for (int row = 0; row < NINE; row++) {
            for (int col = 0; col < NINE; col++)
                output.append(sudoku[row][col]);
            output.append('\n');
        }
        System.out.print(output);
    }
}
