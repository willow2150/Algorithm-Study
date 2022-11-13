import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] connA, int[] connB) {
                return connA[2] <= connB[2] ? -1 : 1;
            }
        });
        int[] parentCom = new int[n + 1];
        int numOfConnRequired = n - 2;

        for (int com = 2; com <= n; com++)
            parentCom[com] = com;

        for (int conn = 0; conn < m; conn++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int comA = Integer.parseInt(tokenizer.nextToken());
            int comB = Integer.parseInt(tokenizer.nextToken());
            if (findParentCom(parentCom, comA) == findParentCom(parentCom, comB))
                continue;
            unionCom(parentCom, comA, comB);
            numOfConnRequired--;
        }

        reader.readLine();
        for (int comA = 2; comA <= n; comA++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int comB = 1; comB <= comA; comB++)
                tokenizer.nextToken();
            for (int comB = comA + 1; comB <= n; comB++)
                pq.add(new int[] {comA, comB, Integer.parseInt(tokenizer.nextToken())});
        }

        printStableNetwork(pq, parentCom, numOfConnRequired);
    }

    public static int findParentCom(int[] parentCom, int com) {
        if (parentCom[com] == com)
            return com;
        return parentCom[com] = findParentCom(parentCom, parentCom[com]);
    }

    public static void unionCom(int[] parentCom, int comA, int comB) {
        comA = parentCom[comA];
        comB = parentCom[comB];
        if (comA <= comB)
            parentCom[comB] = comA;
        else
            parentCom[comA] = comB;
    }

    public static void printStableNetwork(Queue<int[]> pq, int[] parentCom,
                                          int numOfConnRequired) {
        StringBuilder connInfo = new StringBuilder();
        int cost = 0;
        int numOfConn = numOfConnRequired;
        while (!pq.isEmpty() && numOfConnRequired > 0) {
            int[] conn = pq.poll();
            if (findParentCom(parentCom, conn[0]) == findParentCom(parentCom, conn[1]))
                continue;
            unionCom(parentCom, conn[0], conn[1]);
            cost += conn[2];
            numOfConnRequired--;
            connInfo.append(conn[0]).append(' ').append(conn[1]).append('\n');
        }
        System.out.println(cost + " " + numOfConn);
        System.out.print(connInfo);
    }
}
