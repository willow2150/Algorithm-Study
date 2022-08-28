import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_NUM_OF_APPLICANTS = 100_000;
    private static int numOfApplicants;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        /* 지원자들의 성적을 저장할 배열: [지원자 번호][과목 번호] **/
        int[][] applicantGrades = new int[MAX_NUM_OF_APPLICANTS][2];
        /* 테스트케이스 수 **/
        int numOfTC = Integer.parseInt(reader.readLine());

        /* 지원자 성적을 정렬하기 위한 Comparator: 면접 결과가 좋을수록 우선순위 **/
        Comparator<int[]> comparatorForApplicantGrades = new Comparator<int[]>() {
            @Override
            public int compare(int[] arrayA, int[] arrayB) {
                return arrayA[1] <= arrayB[1] ? -1 : 1;
            }
        };

        for (int tC = 1; tC <= numOfTC; tC++) {
            /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
            if (!input(reader, applicantGrades)) return;
            /* 합격자의 수 **/
            int numOfSuccessfulApplicants = 0;
            /* 현재까지 서류 심사 결과가 가장 좋은 지원자의 서류 심사 결과 등수 **/
            int bestDocumentRank = numOfApplicants + 1;
            /* 지원자 성적 목록을 면접 결과가 좋은 순으로 정렬 **/
            Arrays.sort(applicantGrades, 0, numOfApplicants, comparatorForApplicantGrades);
            for (int applicant = 0; applicant < numOfApplicants; applicant++) {
                /* 합격자 중 서류 심사 결과가 가장 좋은 지원자보다 **/
                /* 현재 지원자의 서류 심사 결과가 더 좋다면 합격자로 등록 **/
                if (bestDocumentRank > applicantGrades[applicant][0]) {
                    bestDocumentRank = applicantGrades[applicant][0];
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
    public static boolean input(BufferedReader reader, int[][] applicantGrades) {
        try {
            StringTokenizer tokenizer;
            numOfApplicants = Integer.parseInt(reader.readLine());
            for (int applicant = 0; applicant < numOfApplicants; applicant++) {
                tokenizer = new StringTokenizer(reader.readLine());
                applicantGrades[applicant][0] = Integer.parseInt(tokenizer.nextToken());
                applicantGrades[applicant][1] = Integer.parseInt(tokenizer.nextToken());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
