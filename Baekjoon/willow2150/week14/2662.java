import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] investment = new int[N + 1][M + 1];
        int[][] dp = new int[N + 1][M + 1];
        int[][][] investmentTable = new int[2][N + 1][M + 1];
        int[][] prevTable = investmentTable[0];
        int[][] currentTable = investmentTable[1];
        for (int amount = 1; amount <= N; amount++) {
            tokenizer = new StringTokenizer(reader.readLine());
            tokenizer.nextToken();
            for (int enterprise = 1; enterprise <= M; enterprise++) {
                investment[amount][enterprise] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int enterprise = 1; enterprise <= M; enterprise++) {
            for (int amount = 1; amount <= N; amount++) {
                dp[amount][enterprise] = investment[amount][enterprise];
                for (int etp = 1; etp <= M; etp++)
                    currentTable[amount][etp] = 0;
                currentTable[amount][enterprise] = amount;
                for (int amt = 0; amt < amount; amt++) {
                    int tmp = dp[amount - amt][enterprise - 1] + investment[amt][enterprise];
                    if (dp[amount][enterprise] < tmp) {
                        dp[amount][enterprise] = tmp;
                        for (int etp = 1; etp <= M; etp++)
                            currentTable[amount][etp] = prevTable[amount - amt][etp];
                        currentTable[amount][enterprise] = amt;
                    }
                }
            }
            int[][] temp = prevTable;
            prevTable = currentTable;
            currentTable = temp;
        }
        output.append(dp[N][M]).append('\n');
        for (int enterprise = 1; enterprise <= M; enterprise++)
            output.append(prevTable[N][enterprise]).append(' ');
        System.out.println(output);
    }
}
