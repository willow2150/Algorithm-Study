import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static String[] carNamesBeforePassing, carNamesAfterPassing;

    public static void main(String[] args) {
        if (!input()) return;
        Set<String> checkedCarNames = new HashSet<>();
        int carOrderBeforePassing = 0;
        int numOfCarsOvertaken = 0;

        for (String carNameAfterPassing: carNamesAfterPassing) {
            while (checkedCarNames.contains(carNamesBeforePassing[carOrderBeforePassing]))
                carOrderBeforePassing++;
            if (carNamesBeforePassing[carOrderBeforePassing].equals(carNameAfterPassing))
                carOrderBeforePassing++;
            else
                numOfCarsOvertaken++;
            checkedCarNames.add(carNameAfterPassing);
        }
        System.out.print(numOfCarsOvertaken);
    }

    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            carNamesBeforePassing = new String[n];
            for (int order = 0; order < n; order++)
                carNamesBeforePassing[order] = reader.readLine();
            carNamesAfterPassing = new String[n];
            for (int order = 0; order < n; order++)
                carNamesAfterPassing[order] = reader.readLine();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
