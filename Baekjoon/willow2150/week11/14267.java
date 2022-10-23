import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Relationship {
        int underling;
        Relationship another;

        public Relationship(int underling, Relationship another) {
            this.underling = underling;
            this.another = another;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        StringBuilder output = new StringBuilder();
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        Relationship[] underlingList = new Relationship[n + 1];
        int[] praiseScores = new int[n + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        tokenizer.nextToken();
        for (int underling = 2; underling <= n; underling++) {
            int superior = Integer.parseInt(tokenizer.nextToken());
            underlingList[superior] = new Relationship(underling, underlingList[superior]);
        }

        for (int praiseIdx = 0; praiseIdx < m; praiseIdx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int employee = Integer.parseInt(tokenizer.nextToken());
            int praiseScore = Integer.parseInt(tokenizer.nextToken());
            praiseScores[employee] += praiseScore;
        }

        praise(1, underlingList, praiseScores);

        for (int employee = 1; employee <= n; employee++)
            output.append(praiseScores[employee]).append(' ');
        System.out.print(output);
    }

    public static void praise(int superior, Relationship[] underlingList, int[] praiseScores) {
        Relationship relationship = underlingList[superior];
        while (relationship != null) {
            praiseScores[relationship.underling] += praiseScores[superior];
            praise(relationship.underling, underlingList, praiseScores);
            relationship = relationship.another;
        }
    }
}
