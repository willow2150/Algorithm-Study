import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int[] blockHeightArray = input();
        if (blockHeightArray == null) return;
        int[] queue = new int[blockHeightArray.length];
        int qHead = 0, qTail = 0;
        int rainwater = 0;
        int highest = -1;
        for (int blockHeight: blockHeightArray) {
            if (blockHeight <= highest) {
                queue[qTail++] = blockHeight;
            } else {
                while (qHead < qTail)
                    rainwater += highest - queue[qHead++];
                highest = blockHeight;
            }
        }
        
        highest = -1;
        for (int index = qTail - 1; index >= qHead; index--) {
            if (queue[index] > highest)
                highest = queue[index];
            else if (queue[index] < highest)
                rainwater += highest - queue[index];
        }
        System.out.print(rainwater);
    }

    public static int[] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            tokenizer.nextToken();
            int W = Integer.parseInt(tokenizer.nextToken());
            int[] blockHeightArray = new int[W];
            tokenizer = new StringTokenizer(reader.readLine());
            for (int blockIndex = 0; blockIndex < W; blockIndex++)
                blockHeightArray[blockIndex] = Integer.parseInt(tokenizer.nextToken());
            return blockHeightArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
