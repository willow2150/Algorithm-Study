import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int d = Integer.parseInt(tokenizer.nextToken());
        String numbers = tokenizer.nextToken();
        tokenizer = new StringTokenizer(reader.readLine());
        long x = Long.parseLong(tokenizer.nextToken());
        long y = Long.parseLong(tokenizer.nextToken());

        long[] coordinate = findCoordinate(numbers, d);
        if (coordinate == null) {
            System.out.print(-1);
        } else {
            coordinate[0] -= y;
            coordinate[1] += x;
            if (0 < coordinate[0] && coordinate[0] <= (1L << d)
                    && 0 < coordinate[1] && coordinate[1] <= (1L << d)) {
                System.out.print(findNumbers(coordinate, d));
            } else {
                System.out.print(-1);
            }
        }
    }

    public static long[] findCoordinate(String numbers, int d) {
        long boardSize = 1L << d;
        long y = 1;
        long x = boardSize;

        for (int index = 0; index < d; index++) {
            char number = numbers.charAt(index);
            if (number == '2') {
                x -= boardSize >> 1;
            } else if (number == '3') {
                y += boardSize >> 1;
                x -= boardSize >> 1;
            } else if (number == '4') {
                y += boardSize >> 1;
            }
            if (y <= 0 || (1L << d) < y || x <= 0 || (1L << d) < x)
                return null;
            boardSize >>= 1;
        }
        return new long[] {y, x};
    }

    public static String findNumbers(long[] coordinate, int d) {
        StringBuilder numbers = new StringBuilder();
        long boardSize = 1L << d;
        for (int i = 0; i < d; i++) {
            boardSize >>= 1;
            boolean lessThanHalfRow = coordinate[0] <= boardSize;
            boolean lessThanHalfCol = coordinate[1] <= boardSize;
            if (lessThanHalfRow && lessThanHalfCol) {
                numbers.append(2);
            } else if (lessThanHalfRow) {
                numbers.append(1);
                coordinate[1] -= boardSize;
            } else if (lessThanHalfCol) {
                numbers.append(3);
                coordinate[0] -= boardSize;
            } else {
                numbers.append(4);
                coordinate[0] -= boardSize;
                coordinate[1] -= boardSize;
            }
        }
        return numbers.toString();
    }
}
