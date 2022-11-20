import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static class Route {
        int dep, arr;

        public Route() {}

        void init(int dep, int arr) {
            this.dep = dep;
            this.arr = arr;
        }
    }

    public static void main(String[] args) throws Exception {
        final int NUM_OF_ROOMS = 400;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        Queue<Route> routePool = new ArrayDeque<>();
        Queue<Route> routes = new ArrayDeque<>();
        int[] counter = new int[NUM_OF_ROOMS + 1];

        int T = Integer.parseInt(reader.readLine());
        for (int tC = 1; tC <= T; tC++) {
            int necessaryTime = 0;
            inputRoutes(reader, routePool, routes);
            for (Route route: routes) {
                int dep = route.dep;
                int arr = route.arr;
                for (int room = dep; room <= arr; room++) {
                    counter[room]++;
                    if (necessaryTime < counter[room])
                        necessaryTime = counter[room];
                }
            }
            routePool.addAll(routes);
            routes.clear();
            for (int room = 0; room <= NUM_OF_ROOMS; room++)
                counter[room] = 0;
            output.append('#').append(tC).append(' ').append(necessaryTime).append('\n');
        }
        System.out.print(output);
    }

    private static void inputRoutes(BufferedReader reader, Queue<Route> routePool,
                                    Queue<Route> routes) throws Exception {
        int N = Integer.parseInt(reader.readLine());
        for (int studentIdx = 0; studentIdx < N; studentIdx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            Route route = routePool.isEmpty() ? new Route() : routePool.poll();
            int dep = Integer.parseInt(tokenizer.nextToken());
            int arr = Integer.parseInt(tokenizer.nextToken());
            if (dep > arr) {
                dep ^= arr;
                arr ^= dep;
                dep ^= arr;
            }
            if ((dep & 1) == 0)
                dep--;
            if ((arr & 1) == 1)
                arr++;
            route.init(dep, arr);
            routes.add(route);
        }
    }
}
