import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static String[] ladderLines;
    private static int[] after;
    private static int k, n;

    public static void main(String[] args) {
        if (!input()) return;
        System.out.print(findHiddenLine(ladderLines));
    }

    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            k = Integer.parseInt(reader.readLine());
            n = Integer.parseInt(reader.readLine());
            String resultOrder = reader.readLine();
            after = new int[k];
            for (int person = 0; person < k; person++)
                after[person] = resultOrder.charAt(person);
            ladderLines = new String[n];
            for (int lineIndex = 0; lineIndex < n; lineIndex++)
                ladderLines[lineIndex] = reader.readLine();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String findHiddenLine(String[] ladderLines) {
        final char HORIZONTAL_LINE = '-';
        final char EMPTY = '*';
        final char HIDDEN = '?';
        StringBuilder answer = new StringBuilder();
        int[] before = new int[k];
        int lastSection = k - 2;
        int hiddenLine = 0;

        for (int person = 0; person < k; person++)
            before[person] = 'A' + person;

        for (int upperLine = 0; upperLine < n; upperLine++) {
            String ladderLine = ladderLines[upperLine];
            if (ladderLine.charAt(0) == HIDDEN) {
                hiddenLine = upperLine;
                break;
            }
            for (int section = 0; section <= lastSection;) {
                if (ladderLine.charAt(section) == EMPTY) {
                    section++;
                } else if (ladderLine.charAt(section) == HORIZONTAL_LINE) {
                    before[section] ^= before[section + 1];
                    before[section + 1] ^= before[section];
                    before[section] ^= before[section + 1];
                    section += 2;
                }
            }
        }

        for (int underLine = n - 1; underLine > hiddenLine; underLine--) {
            String ladderLine = ladderLines[underLine];
            for (int section = 0; section <= lastSection;) {
                if (ladderLine.charAt(section) == EMPTY) {
                    section++;
                } else if (ladderLine.charAt(section) == HORIZONTAL_LINE) {
                    after[section] ^= after[section + 1];
                    after[section + 1] ^= after[section];
                    after[section] ^= after[section + 1];
                    section += 2;
                }
            }
        }

        for (int section = 0; section <= lastSection;) {
            if (before[section] == after[section]) {
                answer.append(EMPTY);
                section++;
            } else if (before[section] == after[section + 1]
                    && before[section + 1] == after[section]) {
                answer.append(HORIZONTAL_LINE);
                if (section != lastSection)
                    answer.append(EMPTY);
                section += 2;
            } else {
                answer = new StringBuilder();
                // answer.append("x".repeat(lastSection + 1));
                for (int sc = 0; sc <= lastSection; sc++)
                    answer.append('x');
                break;
            }
        }
        return answer.toString();
    }
}
