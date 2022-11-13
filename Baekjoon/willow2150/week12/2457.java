import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Flower implements Comparable<Flower> {
        int bloom;
        int fall;

        public Flower(int bloom, int fall) {
            this.bloom = bloom;
            this.fall = fall;
        }

        @Override
        public int compareTo(Flower flower) {
            if (bloom == flower.bloom)
                return Integer.compare(flower.fall, fall);
            return Integer.compare(bloom, flower.bloom);
        }
    }

    public static void main(String[] args) {
        final int DECEMBER = 1201;
        Flower[] flowers = input();
        int fallA = 100;
        int fallB = 301;
        int answer = 0;

        if (flowers == null) return;

        Arrays.sort(flowers);
        for (Flower flower: flowers) {
            if (fallB < flower.bloom || DECEMBER <= fallB) break;
            if (flower.fall <= fallB) continue;
            if (fallA < flower.bloom) {
                fallA = fallB;
                answer++;
            }
            fallB = flower.fall;
        }
        System.out.print(DECEMBER <= fallB ? answer : 0);
    }

    public static Flower[] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine());
            Flower[] flowers = new Flower[N];
            for (int flowerIdx = 0; flowerIdx < N; flowerIdx++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int bloomMonth = Integer.parseInt(tokenizer.nextToken());
                int bloomDay = Integer.parseInt(tokenizer.nextToken());
                int fallMonth = Integer.parseInt(tokenizer.nextToken());
                int fallDay = Integer.parseInt(tokenizer.nextToken());
                flowers[flowerIdx] = new Flower(
                        bloomMonth * 100 + bloomDay, fallMonth * 100 + fallDay
                );
            }
            return flowers;
        } catch (Exception e) {
            return null;
        }
    }
}
