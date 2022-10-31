import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        tokenizer.nextToken();
        tokenizer.nextToken();
        int L = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[][] points = new int[K][];
        for (int pointIdx = 0; pointIdx < K; pointIdx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            points[pointIdx] = new int[] {
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())
            };
        }
        System.out.print(findMinNumOfFallenStars(points, L));
    }

    public static int findMinNumOfFallenStars(int[][] points, int L) {
        int minNumOfFallenStars = Integer.MAX_VALUE;
        int numOfStars = points.length;
        int rowA, colA, rowB, colB;
        for (int[] pointA: points) {
            rowA = pointA[1]; rowB = rowA + L;
            for (int[] pointB: points) {
                colA = pointB[0]; colB = colA + L;
                int numOfBlockedStars = 0;
                for (int[] pointC: points) {
                    if (rowA <= pointC[1] && pointC[1] <= rowB
                            && colA <= pointC[0] && pointC[0] <= colB) {
                        numOfBlockedStars++;
                    }
                }
                minNumOfFallenStars = Math.min(
                        minNumOfFallenStars,
                        numOfStars - numOfBlockedStars
                );
            }
        }
        return minNumOfFallenStars;
    }
}
