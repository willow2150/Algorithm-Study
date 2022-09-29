import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> pq = new PriorityQueue<>();
        int[][] times = input();
        int maxNumOfMosquitoes = 0;
        int entryTime = 0, exitTime = 0;

        if (times == null) return;

        Arrays.sort(times, new Comparator<int[]>() {
            @Override
            public int compare(int[] timeA, int[] timeB) {
                if (timeA[0] != timeB[0])
                    return timeA[0] <= timeB[0] ? -1 : 1;
                return timeA[1] <= timeB[1] ? -1 : 1;
            }
        });

        for (int[] time: times) {
            int prevExitTime = 0;
            while (!pq.isEmpty() && pq.peek() <= time[0])
                prevExitTime = pq.poll();
            pq.add(time[1]);
            if (maxNumOfMosquitoes == pq.size() && time[0] == prevExitTime) {
                exitTime = pq.peek();
            } else if (maxNumOfMosquitoes < pq.size()) {
                maxNumOfMosquitoes = pq.size();
                entryTime = time[0];
                exitTime = pq.peek();
            }
        }
        System.out.println(maxNumOfMosquitoes);
        System.out.print(entryTime + " " + exitTime);
    }

    public static int[][] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer;
            int N = Integer.parseInt(reader.readLine());
            int[][] times = new int[N][];
            for (int mosquitoIndex = 0; mosquitoIndex < N; mosquitoIndex++) {
                tokenizer = new StringTokenizer(reader.readLine());
                times[mosquitoIndex] = new int[] {
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken())
                };
            }
            return times;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
