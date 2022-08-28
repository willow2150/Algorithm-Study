import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_NUM_OF_APPLICANTS = 100_000;
    /* 지원자 수 **/
    private static int numOfApplicants;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        /* 지원자들의 성적을 저장할 배열 - index: 서류 심사 결과 등수, value: 면접 등수 **/
        int[] applicantGrades = new int[MAX_NUM_OF_APPLICANTS + 1];
        /* 테스트케이스 수 **/
        int numOfTC = Integer.parseInt(reader.readLine());

        for (int tC = 1; tC <= numOfTC; tC++) {
            /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
            if (!input(reader, applicantGrades)) return;
            /* 합격자 수 **/
            int numOfSuccessfulApplicants = 0;
            /* 현재까지 면접 결과가 가장 좋은 지원자의 면접 결과 등수 **/
            int bestInterviewRank = numOfApplicants + 1;

            for (int documentRank = 1; documentRank <= numOfApplicants; documentRank++) {
                /* 지원자 탐색 순서는 서류 심사 결과가 좋은 순서이므로 **/
                /* 이전 합격자 중 면접 결과가 가장 좋은 지원자보다 **/
                /* 현재 지원자의 면접 결과가 더 좋다면 합격자로 등록 **/
                if (applicantGrades[documentRank] < bestInterviewRank) {
                    bestInterviewRank = applicantGrades[documentRank];
                    numOfSuccessfulApplicants++;
                }
            }
            /* 합격자의 수를 출력 문자열에 저장 **/
            output.append(numOfSuccessfulApplicants).append('\n');
        }
        System.out.print(output);
    }

    /**
     * 입력을 처리하는 메서드
     * @param reader : BufferedReader
     * @param applicantGrades : 지원자들의 성적을 저장할 배열
     * @return : 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input(BufferedReader reader, int[] applicantGrades) {
        try {
            StringTokenizer tokenizer;
            numOfApplicants = Integer.parseInt(reader.readLine());
            for (int applicant = 1; applicant <= numOfApplicants; applicant++) {
                tokenizer = new StringTokenizer(reader.readLine());
                applicantGrades[Integer.parseInt(tokenizer.nextToken())]
                        = Integer.parseInt(tokenizer.nextToken());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
