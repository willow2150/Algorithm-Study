import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    private static final int NUM_OF_PEOPLE = 4;
    private static final int ALL_JOINERS = 1 << NUM_OF_PEOPLE;
    private static final int DIV = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        final int MAX_DAYS = 10_000;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        long[][] dp = new long[MAX_DAYS][ALL_JOINERS];
        int T = Integer.parseInt(reader.readLine());

        for (int tC = 1; tC <= T; tC++) {
            String responsible = reader.readLine();
            int days = responsible.length();
            int admin = 1 | 1 << (responsible.charAt(0) - 'A');

            for (int joiners = 1; joiners < ALL_JOINERS; joiners++)
                if ((admin & joiners) == admin)
                    dp[0][joiners] = 1;

            for (int day = 1; day < days; day++) {
                admin = 1 << (responsible.charAt(day) - 'A');
                for (int yJoiner = 1; yJoiner < ALL_JOINERS; yJoiner++) {
                    if (dp[day - 1][yJoiner] == 0) continue;
                    for (int tJoiner = 1; tJoiner < ALL_JOINERS; tJoiner++) {
                        if ((tJoiner & admin) == admin && (yJoiner & tJoiner) != 0) {
                            dp[day][tJoiner] += dp[day - 1][yJoiner];
                            dp[day][tJoiner] %= DIV;
                        }
                    }
                }
            }

            output.append('#').append(tC).append(' ')
                    .append(countNumOfCases(dp, days)).append('\n');
            initDp(dp, days);
        }
        System.out.print(output);
    }

    public static long countNumOfCases(long[][] dp, int days) {
        long[] allCases = dp[days - 1];
        long numOfCases = 0;
        for (int joiner = 1; joiner < ALL_JOINERS; joiner++) {
            numOfCases += allCases[joiner];
            numOfCases %= DIV;
        }
        return numOfCases;
    }

    public static void initDp(long[][] dp, int days) {
        for (int day = 0; day < days; day++)
            for (int joiner = 1; joiner < ALL_JOINERS; joiner++)
                dp[day][joiner] = 0;
    }
}
