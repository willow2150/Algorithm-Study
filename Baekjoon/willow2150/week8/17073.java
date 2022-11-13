import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int number;
        Node another;

        Node(int number) {
            this.number = number;
        }

        Node(int number, Node another) {
            this.number = number;
            this.another = another;
        }
    }

    private static final int ROOT = 1;
    private static Node[] graph;
    private static double[] percentage;
    private static int[] degree;
    private static int N, W;

    public static void main(String[] args) {
        if (!input()) return;
        System.out.print(calcAverageOfExpectedValues(graph));
    }

    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            W = Integer.parseInt(tokenizer.nextToken());
            graph = new Node[N + 1];
            percentage = new double[N + 1];
            degree = new int[N + 1];
            Arrays.fill(percentage, 1D);
            Arrays.fill(degree, -1);
            degree[ROOT] = 0;
            for (int edge = 1; edge < N; edge++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int nodeANumber = Integer.parseInt(tokenizer.nextToken());
                int nodeBNumber = Integer.parseInt(tokenizer.nextToken());
                graph[nodeANumber] = new Node(nodeBNumber, graph[nodeANumber]);
                graph[nodeBNumber] = new Node(nodeANumber, graph[nodeBNumber]);
                degree[nodeANumber]++;
                degree[nodeBNumber]++;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static double calcAverageOfExpectedValues(Node[] graph) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        double averageOfExpectedValues = 0;
        int numOfLeafNodes = 0;

        queue.add(new Node(1, null));
        visited[ROOT] = true;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                Node node = queue.poll();
                Node connectedNode = graph[node.number];
                while (connectedNode != null) {
                    if (!visited[connectedNode.number]) {
                        visited[connectedNode.number] = true;
                        percentage[connectedNode.number] =
                                percentage[node.number] / degree[node.number];
                        if (degree[connectedNode.number] == 0) {
                            averageOfExpectedValues += W * percentage[connectedNode.number];
                            numOfLeafNodes++;
                        } else {
                            queue.add(new Node(connectedNode.number));
                        }
                    }
                    connectedNode = connectedNode.another;
                }
            }
        }
        return averageOfExpectedValues / numOfLeafNodes;
    }
}
