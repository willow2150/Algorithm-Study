import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        final int MAX_N = 100_000;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int[][] peakTimes = new int[MAX_N][3];
        int T = Integer.parseInt(reader.readLine());

        for (int tC = 1; tC <= T; tC++) {
            int L = Integer.parseInt(reader.readLine());
            int N = Integer.parseInt(reader.readLine());
            for (int peakTimeIdx = 0; peakTimeIdx < N; peakTimeIdx++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int[] peakTime = peakTimes[peakTimeIdx];
                peakTime[0] = Integer.parseInt(tokenizer.nextToken());
                peakTime[1] = Integer.parseInt(tokenizer.nextToken());
                peakTime[2] = peakTime[1] - peakTime[0];
            }

            int maxAdvTime = 0;
            int rightPointer = 0;
            int leftTime = 0, rightTime = 0;
            int advTime = 0;
            for (int leftPointer = 0; leftPointer < N && rightPointer < N; leftPointer++) {
                int advStartTime = peakTimes[leftPointer][0];
                int advEndTime = advStartTime + L;
                if (advEndTime <= peakTimes[leftPointer][1]) {
                    maxAdvTime = L;
                    break;
                }
                advTime -= (leftTime + rightTime);
                leftTime = peakTimes[leftPointer][2];
                for (; rightPointer < N; rightPointer++) {
                    if (advEndTime < peakTimes[rightPointer][0]) {
                        rightTime = 0;
                        break;
                    } else if (advEndTime < peakTimes[rightPointer][1]) {
                        rightTime = advEndTime - peakTimes[rightPointer][0];
                        advTime += rightTime;
                        break;
                    } else {
                        advTime += peakTimes[rightPointer][2];
                    }
                }
                if (maxAdvTime < advTime)
                    maxAdvTime = advTime;
            }
            output.append('#').append(tC).append(' ').append(maxAdvTime).append('\n');
        }
        System.out.print(output);
    }
}
