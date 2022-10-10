import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int[] timeTakenArray = input();
        if (timeTakenArray == null) return;
        int longestTimeTaken = 0;
        for (int timeTaken: timeTakenArray)
            if (longestTimeTaken < timeTaken)
                longestTimeTaken = timeTaken;
        System.out.print(longestTimeTaken);
    }

    public static int[] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine());
            int[] timeTakenArray = new int[N + 1];
            for (int taskNumber = 1; taskNumber <= N; taskNumber++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                timeTakenArray[taskNumber] = Integer.parseInt(tokenizer.nextToken());
                int numOfPredecessors = Integer.parseInt(tokenizer.nextToken());
                int taskStartTime = 0;
                for (int count = 0; count < numOfPredecessors; count++) {
                    int prevTaskNumber = Integer.parseInt(tokenizer.nextToken());
                    if (taskStartTime < timeTakenArray[prevTaskNumber])
                        taskStartTime = timeTakenArray[prevTaskNumber];
                }
                timeTakenArray[taskNumber] += taskStartTime;
            }
            return timeTakenArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
