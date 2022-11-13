import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int T = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int[][] diamondPoints = new int[T][];
        for (int pointIndex = 0; pointIndex < T; pointIndex++) {
            tokenizer = new StringTokenizer(reader.readLine());
            diamondPoints[pointIndex] = new int[] {
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())
            };
        }
        System.out.print(findBestSquare(diamondPoints, N, M, T, K));
    }

    public static String findBestSquare(int[][] diamondPoints,
                                        int N, int M, int T, int K) {
        int maxNumOfDiamonds = 0;
        int bestX = 0;
        int bestY = 0;
        for (int pointA = 0; pointA < T; pointA++) {
            for (int pointB = 0; pointB < T; pointB++) {
                int x1 = Math.min(diamondPoints[pointA][0], diamondPoints[pointB][0]);
                int y1 = Math.min(diamondPoints[pointA][1], diamondPoints[pointB][1]);
                int x2 = x1 + K;
                int y2 = y1 + K;
                if (x2 > N) {
                    x1 = N - K;
                    x2 = N;
                }
                if (y2 > M) {
                    y1 = M - K;
                    y2 = M;
                }
                int numOfDiamonds = 0;
                for (int[] diamondPoint: diamondPoints) {
                    if (x1 <= diamondPoint[0] && diamondPoint[0] <= x2
                            && y1 <= diamondPoint[1] && diamondPoint[1] <= y2) {
                        numOfDiamonds++;
                    }
                }
                if (maxNumOfDiamonds < numOfDiamonds) {
                    maxNumOfDiamonds = numOfDiamonds;
                    bestX = x1;
                    bestY = y2;
                }
            }
        }
        return bestX + " " + bestY + "\n" + maxNumOfDiamonds;
    }
}
