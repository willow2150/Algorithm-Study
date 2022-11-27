import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    static class Virus {
        int row, col;
        int vitality;
        int timeOfBirth;
        int dyingTime;
        int breedingTime;

        public Virus(int row, int col) {
            this.row = row;
            this.col = col;
            this.timeOfBirth = Integer.MAX_VALUE;
        }

        public void init(int now, int vitality) {
            this.vitality = vitality;
            this.timeOfBirth = now;
            this.dyingTime = now + (vitality << 1);
            this.breedingTime = now + vitality + 1;
        }
    }

    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception {
        final int MAX_N = 50;
        final int MAX_K = 300;
        final int MAX_BOUNDARY = MAX_N + MAX_K;
        final int BASE_POINT = 150;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        Queue<Virus> virusQueue = new PriorityQueue<>(
                (virusA, virusB) -> virusA.breedingTime <= virusB.breedingTime ? -1 : 1
        );
        Queue<Virus> undeadVirus = new PriorityQueue<>(
                (virusA, virusB) -> virusA.dyingTime <= virusB.dyingTime ? -1 : 1
        );
        Virus[][] map = new Virus[MAX_BOUNDARY][MAX_BOUNDARY];
        int T = Integer.parseInt(reader.readLine());

        for (int row = 0; row < MAX_BOUNDARY; row++)
            for (int col = 0; col < MAX_BOUNDARY; col++)
                map[row][col] = new Virus(row, col);

        for (int tC = 1; tC <= T; tC++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            int K = Integer.parseInt(tokenizer.nextToken());
            int firstBoundaryRow = BASE_POINT + N;
            int firstBoundaryCol = BASE_POINT + M;
            for (int row = BASE_POINT; row < firstBoundaryRow; row++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int col = BASE_POINT; col < firstBoundaryCol; col++) {
                    int vitality = Integer.parseInt(tokenizer.nextToken());
                    if (vitality > 0) {
                        map[row][col].init(0, vitality);
                        virusQueue.add(map[row][col]);
                    }
                }
            }
            output.append('#')
                    .append(tC)
                    .append(' ')
                    .append(spreadVirus(virusQueue, undeadVirus, map, K))
                    .append('\n');
            virusQueue.clear();
            undeadVirus.clear();
        }
        System.out.print(output);
    }

    public static int spreadVirus(Queue<Virus> virusQueue, Queue<Virus> liveVirus,
                                  Virus[][] map, int K) {
        Queue<Virus> allVirus = new ArrayDeque<>(virusQueue);
        Queue<Virus> newVirusQueue = new ArrayDeque<>();
        int time = 0;
        while (time++ < K) {
            while (!virusQueue.isEmpty() && virusQueue.peek().breedingTime == time) {
                Virus virus = virusQueue.poll();
                if (virus.dyingTime > time)
                    liveVirus.add(virus);
                for (int[] delta: DELTAS) {
                    int nr = virus.row + delta[0];
                    int nc = virus.col + delta[1];
                    if (map[nr][nc].timeOfBirth == time) {
                        if (map[nr][nc].vitality < virus.vitality)
                            map[nr][nc].init(time, virus.vitality);
                    } else if (map[nr][nc].timeOfBirth > time) {
                        map[nr][nc].init(time, virus.vitality);
                        newVirusQueue.add(map[nr][nc]);
                    }
                }
            }
            while (!liveVirus.isEmpty() && liveVirus.peek().dyingTime <= time)
                liveVirus.poll();
            allVirus.addAll(newVirusQueue);
            virusQueue.addAll(newVirusQueue);
            newVirusQueue.clear();
        }
        for (Virus virus : allVirus)
            virus.timeOfBirth = Integer.MAX_VALUE;
        return liveVirus.size() + virusQueue.size();
    }
}
