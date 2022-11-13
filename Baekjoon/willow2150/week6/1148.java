import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringBuilder output = new StringBuilder();
        List<int[]> wordCounters = new ArrayList<>();
        List<int[]> puzzleWordCounters = new ArrayList<>();
        int[] counter = new int[26];
        if (!input(wordCounters, puzzleWordCounters)) return;

        for (int[] puzzleWordCounter: puzzleWordCounters) {
            int min = Integer.MAX_VALUE, max = 0;
            for (int index = 0; index < 26; index++)
                counter[index] = puzzleWordCounter[index] == 0 ? -1 : 0;
            for (int[] wordCounter: wordCounters)
                if (canBeMade(puzzleWordCounter, wordCounter))
                    for (int index = 0; index < 26; index++)
                        if (wordCounter[index] > 0)
                            counter[index]++;
            for (int index = 0; index < 26; index++) {
                if (counter[index] < 0)
                    continue;
                if (counter[index] < min)
                    min = counter[index];
                if (max < counter[index])
                    max = counter[index];
            }
            for (int index = 0; index < 26; index++)
                if (counter[index] == min)
                    output.append((char) (index + 'A'));
            output.append(' ').append(min).append(' ');
            for (int index = 0; index < 26; index++)
                if (counter[index] == max)
                    output.append((char) (index + 'A'));
            output.append(' ').append(max).append('\n');
        }
        System.out.print(output);
    }

    public static boolean input(List<int[]> wordCounters, List<int[]> puzzleWordCounters) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String word = reader.readLine();
                if (word.charAt(0) == '-')
                    break;
                int[] wordCounter = new int[26];
                int wordLength = word.length();
                for (int index = 0; index < wordLength; index++)
                    wordCounter[word.charAt(index) - 'A']++;
                wordCounters.add(wordCounter);
            }
            while (true) {
                String puzzle = reader.readLine();
                if (puzzle.charAt(0) == '#')
                    break;
                int[] puzzleWordCounter = new int[26];
                for (int index = 0; index < 9; index++)
                    puzzleWordCounter[puzzle.charAt(index) - 'A']++;
                puzzleWordCounters.add(puzzleWordCounter);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean canBeMade(int[] puzzleWordCounter, int[] wordCounter) {
        for (int index = 0; index < 26; index++)
            if (puzzleWordCounter[index] < wordCounter[index])
                return false;
        return true;
    }
}
