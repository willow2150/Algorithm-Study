import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Order {
        int nextSinger;
        Order another;

        Order(int nextSinger, Order another) {
            this.nextSinger = nextSinger;
            this.another = another;
        }
    }

    private static Order[] orderGraph;
    private static int[] inDegree;
    private static int N;

    public static void main(String[] args) {
        if (!input()) return;
        StringBuilder output = new StringBuilder();
        int[] queue = new int[N];
        int head = 0, tail = 0;

        for (int singer = 1; singer <= N; singer++)
            if (inDegree[singer] == 0)
                queue[tail++] = singer;

        while (head < tail) {
            int singer = queue[head++];
            Order order = orderGraph[singer];
            output.append(singer).append('\n');
            while (order != null) {
                if (--inDegree[order.nextSinger] == 0)
                    queue[tail++] = order.nextSinger;
                order = order.another;
            }
        }
        System.out.print(head == N ? output : 0);
    }

    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            orderGraph = new Order[N + 1];
            inDegree = new int[N + 1];
            for (int pd = 1; pd <= M; pd++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int numOfSingers = Integer.parseInt(tokenizer.nextToken());
                if (numOfSingers == 0)
                    continue;
                int prevSinger = Integer.parseInt(tokenizer.nextToken());
                for (int singer = 2; singer <= numOfSingers; singer++) {
                    int nextSinger = Integer.parseInt(tokenizer.nextToken());
                    orderGraph[prevSinger] = new Order(nextSinger, orderGraph[prevSinger]);
                    inDegree[nextSinger]++;
                    prevSinger = nextSinger;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
