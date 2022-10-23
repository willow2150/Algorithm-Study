import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int[] lis = new int[N];
        int lisLen = 0;

        Arrays.fill(lis, Integer.MAX_VALUE);
        for (int order = 0; order < N; order++) {
            int number = Integer.parseInt(reader.readLine());
            int index = binSearch(number, lis, lisLen);
            lis[index] = number;
            if (lisLen == index)
                lisLen++;
        }
        System.out.print(N - lisLen);
    }

    public static int binSearch(int value, int[] array, int arrayLen) {
        int left = 0;
        int right = arrayLen;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (array[mid] < value)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
}
