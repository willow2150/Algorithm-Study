import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static long k;
    private static int N;

    public static void main(String[] args) {
        int[] permutation = input();
        if (k == 0 && permutation == null) return;
        long[] factorial = getFactorialArray();
        System.out.print(
                k == 0 ?
                        getAnswerOfProblem(factorial, permutation)
                        : getAnswerOfProblem(factorial)
        );
    }

    public static int[] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            int[] permutation = null;
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int problemType = Integer.parseInt(tokenizer.nextToken());
            if (problemType == 1) {
                k = Long.parseLong(tokenizer.nextToken());
            } else {
                permutation = new int[N];
                for (int order = 0; order < N; order++)
                    permutation[order] = Integer.parseInt(tokenizer.nextToken());
            }
            return permutation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long[] getFactorialArray() {
        long[] factorial = new long[N];
        factorial[0] = 1L;
        for (int number = 1; number < N; number++)
            factorial[number] = factorial[number - 1] * number;
        return factorial;
    }

    public static StringBuilder getAnswerOfProblem(long[] factorial) {
        StringBuilder answer = new StringBuilder();
        int factorialArg = N;
        int isSelected = (1 << (factorialArg + 1)) - 1;

        while (isSelected != 1) {
            int coefficient = 0;
            factorialArg--;
            for (int number = 1; number <= N; number++) {
                if (((1 << number) & isSelected) == 0)
                    continue;
                if ((coefficient + 1) * factorial[factorialArg] >= k) {
                    answer.append(number).append(' ');
                    isSelected ^= (1 << number);
                    k -= coefficient * factorial[factorialArg];
                    break;
                }
                coefficient++;
            }
        }
        return answer;
    }

    public static long getAnswerOfProblem(long[] factorial, int[] permutation) {
        long answer = 1;
        int factorialArg = N;
        int isSelected = (1 << (factorialArg + 1)) - 1;

        for (int numberInPermutation: permutation) {
            int coefficient = 0;
            factorialArg--;
            for (int number = 1; number <= N; number++) {
                if (((1 << number) & isSelected) == 0)
                    continue;
                if (number == numberInPermutation) {
                    isSelected ^= (1 << number);
                    answer += coefficient * factorial[factorialArg];
                }
                coefficient++;
            }
        }
        return answer;
    }
}
