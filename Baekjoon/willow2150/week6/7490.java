import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        final int MAX_LAST_NUMBER = 9;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        char[] mathExp = new char[(MAX_LAST_NUMBER << 1) - 1];
        int numOfTC = Integer.parseInt(reader.readLine());

        mathExp[0] = '1';
        for (int tC = 1; tC <= numOfTC; tC++) {
            int lastNumber = Integer.parseInt(reader.readLine());
            makeMathExp(output, mathExp, 1, 2, lastNumber);
            output.append('\n');
        }
        System.out.print(output);
    }

    public static void makeMathExp(StringBuilder output, char[] mathExp,
                                   int top, int currentNumber, int lastNumber) {
        if (currentNumber > lastNumber) {
            int expLen = (lastNumber << 1) - 1;
            if (isZero(mathExp, expLen)) {
                for (int index = 0; index < expLen; index++)
                    output.append(mathExp[index]);
                output.append('\n');
            }
            return;
        }
        mathExp[top + 1] = (char) (currentNumber++ + '0');

        mathExp[top] = ' ';
        makeMathExp(output, mathExp, top + 2, currentNumber, lastNumber);
        mathExp[top] = '+';
        makeMathExp(output, mathExp, top + 2, currentNumber, lastNumber);
        mathExp[top] = '-';
        makeMathExp(output, mathExp, top + 2, currentNumber, lastNumber);
    }

    public static boolean isZero(char[] mathExp, int expLen) {
        int resultNumber = 0;
        int number = 0;
        char operator = '+';
        for (int index = 0; index < expLen; index++) {
            if (mathExp[index] > '0') {
                number += mathExp[index] - '0';
            } else if (mathExp[index] == ' ') {
                number *= 10;
            } else {
                if (operator == '+')
                    resultNumber += number;
                else
                    resultNumber -= number;
                number = 0;
                operator = mathExp[index];
            }
        }
        if (operator == '+')
            resultNumber += number;
        else
            resultNumber -= number;
        return resultNumber == 0;
    }
}
