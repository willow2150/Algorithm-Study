import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder output;
    /* 입력으로 주어진 문자들을 담은 배열 **/
    private static char[] alphabetArray;
    /* 암호 추출 및 검증에 사용할 배열 **/
    private static char[] password;
    /* 자/모음 문자를 구별하기 위한 boolean 배열 **/
    private static boolean[] isVowel;
    /* 암호의 길이, 입력으로 주어질 문자의 수 **/
    private static int L, C;

    public static void main(String[] args) {
        /* 입력 중 예외가 발생했다면 프로그램 종료 **/
        if (!input())
            return;

        /* 추출된 암호 문자열을 저장할 StringBuilder **/
        output = new StringBuilder();
        /* 문자를 담을 암호 문자 배열 **/
        password = new char[L];
        /* 입력된 각 문자가 모음인지 판별한 정보를 저장할 boolean 배열 **/
        isVowel = new boolean[C];

        /* 문제 조건에 맞게 입력된 문자를 정렬 **/
        Arrays.sort(alphabetArray);

        /* 정렬된 문자 배열의 문자 중, 모음인 index가 있다면 isVowel[index] = true **/
        for (int index = 0; index < C; index++) {
            if (alphabetArray[index] == 'a'
                    || alphabetArray[index] == 'e'
                    || alphabetArray[index] == 'i'
                    || alphabetArray[index] == 'o'
                    || alphabetArray[index] == 'u')
                isVowel[index] = true;
        }

        /* 암호를 추출하는 조합 메서드를 호출 **/
        extractPasswords(0, 0, 0, 0);

        /* 출력 수행 **/
        System.out.print(output);
    }

    /**
     * 조합 메서드: 정렬된 문자 배열에서 문자를 추출하여 문제 조건에 맞을 경우, 출력 문자열에 저장하는 메서드
     * @param index : 현재 문자를 채워넣어야 할 인덱스(조회 대상: alphabetArray, 채워 넣을 대상: password)
     * @param startIdx : 문자 배열의 문자 조회를 시작할 인덱스
     * @param numOfConsonants : 선택된 자음의 수(암호 성립 조건: 둘 이상)
     * @param numOfVowels : 선택된 모음의 수(암호 성립 조건: 하나 이상)
     */
    public static void extractPasswords(int index, int startIdx,
                                        int numOfConsonants, int numOfVowels) {
        /* 원하는 password의 길이만큼 문자가 추출됐다면 **/
        if (index == L) {
            /* 자음이 둘 이상이고(and) 모음이 하나 이상일 경우 출력 문자열에 암호를 저장 **/
            if (numOfConsonants >= 2 && numOfVowels >= 1)
                output.append(String.valueOf(password)).append('\n');
            /* 더 이상 진행할 수 없으므로 return **/
            return;
        }
        /* 문자 배열의 startIdx부터 마지막 인덱스까지 문자를 조회 **/
        for (int idx = startIdx; idx < C; idx++) {
            /* 현재 조회한 문자(alphabetArray[idx])를 암호에 기록 **/
            password[index] = alphabetArray[idx];
            /* 모음일 경우 모음의 개수를 늘린 채로 재귀 호출, 자음일 겨우 자음의 개수를 늘린 채로 재귀 호출 **/
            if (isVowel[idx])
                extractPasswords(index + 1, idx + 1, numOfConsonants, numOfVowels + 1);
            else
                extractPasswords(index + 1, idx + 1, numOfConsonants + 1, numOfVowels);
        }
    }

    /* 입력을 처리하는 메서드 **/
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            /* 암호의 길이 **/
            L = Integer.parseInt(tokenizer.nextToken());
            /* 입력으로 주어질 문자의 수 **/
            C = Integer.parseInt(tokenizer.nextToken());
            /* 주어진 문자열 **/
            String inputString = reader.readLine();
            /* 문자열을 문자 배열로 바꾸는 과정 **/
            alphabetArray = new char[C];
            for (int index = 0; index < C; index++)
                alphabetArray[index] = inputString.charAt(index << 1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
