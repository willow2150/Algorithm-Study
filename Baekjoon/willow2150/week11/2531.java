import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int d = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());

        int[] counter = new int[d + 1];
        int[] queue = new int[N];
        int left = 0, right = 0;
        int numOfTypes = 0;

        for (int i = 0; i < k; i++) {
            int sushi = Integer.parseInt(reader.readLine());
            if (sushi != c && counter[sushi]++ == 0) numOfTypes++;
            queue[right++] = sushi;
        }

        int maxNumOfTypes = numOfTypes;

        for (int i = k; i < N; i++) {
            int poll = queue[left++];
            if (poll != c && --counter[poll] == 0) numOfTypes--;
            int sushi = Integer.parseInt(reader.readLine());
            if (sushi != c && counter[sushi]++ == 0) {
                numOfTypes++;
                maxNumOfTypes = Math.max(maxNumOfTypes, numOfTypes);
            }
            queue[right++] = sushi;
        }
        
        k--;

        for (int i = 0; i < k; i++) {
            int poll = queue[left++];
            if (poll != c && --counter[poll] == 0) numOfTypes--;
            int sushi = queue[i];
            if (sushi != c && counter[sushi]++ == 0) {
                numOfTypes++;
                maxNumOfTypes = Math.max(maxNumOfTypes, numOfTypes);
            }
        }
        System.out.print(maxNumOfTypes + 1);
    }
}
