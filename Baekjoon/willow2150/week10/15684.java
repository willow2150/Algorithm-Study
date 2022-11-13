import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int M;

    public static void main(String[] args) {
        boolean[][] isHLine = input();
        if (isHLine == null) return;
        System.out.print(findMinNumOfHLines(isHLine));
    }

    public static boolean[][] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());
            int H = Integer.parseInt(tokenizer.nextToken());
            boolean[][] isHLine = new boolean[H + 1][N + 1];
            for (int hLineIdx = 0; hLineIdx < M; hLineIdx++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int row = Integer.parseInt(tokenizer.nextToken());
                int col = Integer.parseInt(tokenizer.nextToken());
                isHLine[row][col] = true;
            }
            return isHLine;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int findMinNumOfHLines(boolean[][] isHLine) {
        int height = isHLine.length;
        int width = isHLine[0].length - 1;
        for (int added = 0; added <= 3; added++) {
            if (((M + added) & 1) == 1) continue;
            List<int[][]> combinations = new ArrayList<>();
            findCombinations(
                    combinations, new int[added][2], isHLine, 0, 1, 1, height, width
            );
            for (int[][] combination: combinations) {
                for (int[] point: combination)
                    isHLine[point[0]][point[1]] = true;
                if (checkAll(isHLine, height, width))
                    return added;
                for (int[] point: combination)
                    isHLine[point[0]][point[1]] = false;
            }
        }
        return -1;
    }

    public static void findCombinations(List<int[][]> combinations, int[][] temp,
                                        boolean[][] isHLine, int index,
                                        int row, int col, int height, int width) {
        if (index == temp.length) {
            int[][] combination = new int[index][2];
            for (int pointIdx = 0; pointIdx < index; pointIdx++)
                combination[pointIdx] = Arrays.copyOf(temp[pointIdx], 2);
            combinations.add(combination);
            return;
        }
        if (col >= width) {
            col = 1;
            row++;
        }
        if (row == height)
            return;
        if (isHLine[row][col]) {
            findCombinations(
                    combinations, temp, isHLine, index, row, col + 2, height, width
            );
        } else if (col + 1 == width) {
            temp[index][0] = row;
            temp[index][1] = col;
            findCombinations(
                    combinations, temp, isHLine, index + 1, row + 1, 1, height, width
            );
            findCombinations(
                    combinations, temp, isHLine, index, row + 1, 1, height, width
            );
        } else if (isHLine[row][col + 1]) {
            findCombinations(
                    combinations, temp, isHLine, index, row, col + 3, height, width
            );
        } else {
            temp[index][0] = row;
            temp[index][1] = col;
            findCombinations(
                    combinations, temp, isHLine, index + 1, row, col + 2, height, width
            );
            findCombinations(
                    combinations, temp, isHLine, index, row, col + 1, height, width
            );
        }
    }

    public static boolean checkAll(boolean[][] isHLine, int height, int width) {
        for (int col = 1; col < width; col++) {
            int vLine = col;
            for (int row = 1; row < height; row++) {
                if (isHLine[row][vLine - 1]) vLine--;
                else if (isHLine[row][vLine]) vLine++;
            }
            if (vLine != col) return false;
        }
        return true;
    }
}
