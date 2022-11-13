import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        final int MAX_NUM_OF_MATCHSTICKS = 100;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        long[] dp = getDpArray(MAX_NUM_OF_MATCHSTICKS);
        int numOfTC = Integer.parseInt(reader.readLine());

        for (int tC = 1; tC <= numOfTC; tC++) {
            int numOfMatchsticks = Integer.parseInt(reader.readLine());
            output.append(dp[numOfMatchsticks]).append(' ');
            if ((numOfMatchsticks & 1) != 0) {
                output.append('7');
                numOfMatchsticks -= 3;
            }
            for (int i = numOfMatchsticks >> 1; i > 0; i--)
                output.append('1');
            output.append('\n');
        }
        System.out.print(output);
    }

    public static long[] getDpArray(int maxNumOfMatchsticks) {
        long[] dp = new long[maxNumOfMatchsticks + 1];
        int[] matchsticksTable = {0, 0, 1, 7, 4, 2, 0, 8};

        Arrays.fill(dp, Long.MAX_VALUE);
        dp[2] = 1L; dp[3] = 7L; dp[4] = 4L; dp[5] = 2L; dp[6] = 6L; dp[7] = 8L; dp[8] = 10L;
        for (int matchsticks = 9; matchsticks <= maxNumOfMatchsticks; matchsticks++) {
            for (int i = 2; i <= 7; i++) {
                dp[matchsticks] = Math.min(
                        dp[matchsticks - i] * 10 + matchsticksTable[i],
                        dp[matchsticks]
                );
            }
        }
        return dp;
    }
}
