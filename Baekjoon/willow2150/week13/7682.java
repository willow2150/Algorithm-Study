import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        final String VALID = "valid";
        final String INVALID = "invalid";
        final String END_INPUT = "end";
        final int BOARD_AREA = 9;
        final char X = 'X';
        final char O = 'O';
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int[][] lines = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
                {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}
        };

        while (true) {
            String input = reader.readLine();
            int xCount = 0, oCount = 0;
            if (input.equals(END_INPUT)) break;
            for (int index = 0; index < BOARD_AREA; index++) {
                if (input.charAt(index) == X) xCount++;
                else if (input.charAt(index) == O) oCount++;
            }
            if (xCount < 3 || xCount < oCount || xCount - oCount >= 2) {
                output.append(INVALID).append('\n');
                continue;
            }
            boolean xWin = isVictorious(X, lines, input);
            boolean oWin = isVictorious(O, lines, input);
            if (xCount == oCount) {
                output.append(!xWin && oWin ? VALID : INVALID).append('\n');
            } else if (xCount == 5) {
                output.append(oWin ? INVALID : VALID).append('\n');
            } else {
                output.append(xWin && !oWin ? VALID : INVALID).append('\n');
            }
        }
        System.out.print(output);
    }

    public static boolean isVictorious(char player, int[][] lines, String board) {
        for (int[] line: lines) {
            if (board.charAt(line[0]) != player) continue;
            if (board.charAt(line[0]) == board.charAt(line[1])
                    && board.charAt(line[1]) == board.charAt(line[2])) {
                return true;
            }
        }
        return false;
    }
}
