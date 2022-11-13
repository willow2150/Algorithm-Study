import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /* 톱니바퀴 수 **/
    private static final int NUM_OF_COG_WHEELS = 4;
    /* 톱니바퀴의 12시 방향(위쪽) 톱니를 가리킬 인덱스를 가리키는 인덱스 **/
    private static final int U_COG_IDX = 0;
    /* 톱니바퀴의 3시 방향(오른쪽) 톱니를 가리킬 인덱스를 가리키는 인덱스 **/
    private static final int R_COG_IDX = 1;
    /* 톱니바퀴의 9시 방향(왼쪽) 톱니를 가리킬 인덱스를 가리키는 인덱스 **/
    private static final int L_COG_IDX = 2;
    /* S극 문자 **/
    private static final char S_POLE = '1';
    /* 회전 정보를 저장한 배열 **/
    private static int[][] rotationsInfo;

    public static void main(String[] args) {
        /* 각 톱니바퀴의 위쪽, 오른쪽, 왼쪽 톱니의 인덱스를 저장한 배열 **/
        /* Ex1: indexesOfCog[1] => 1번(가장 왼쪽의) 톱니바퀴 상태 **/
        /* Ex2: indexesOfCog[1][U_COG_IDX] => 1번 톱니바퀴에서 12시(위쪽) 방향 톱니 (S or N) **/
        /* Ex3: indexesOfCog[1][R_COG_IDX] => 1번 톱니바퀴에서 3시(오른쪽) 방향 톱니 (S or N) **/
        /* Ex4: indexesOfCog[1][L_COG_IDX] => 1번 톱니바퀴에서 9시(왼쪽) 방향 톱니 (S or N) **/
        int[][] indexesOfCog = {{}, {0, 2, 6}, {0, 2, 6}, {0, 2, 6}, {0, 2, 6}};
        /* 톱니바퀴들의 상태를 저장할 배열 **/
        String[] cogWheels = new String[NUM_OF_COG_WHEELS + 1];
        /* 최종 점수를 저장할 변수 **/
        int totalScore = 0;
        /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
        if (!input(cogWheels)) return;

        /* 모든 회전에 대하여 **/
        for (int[] rotationInfo: rotationsInfo) {
            /* 지목된 톱니바퀴의 번호 **/
            int cogWheelNum = rotationInfo[0];
            /* 회전할 방향(1은 시계 방향, -1은 반시계 방향) **/
            int dir = rotationInfo[1];
            /* 왼쪽(9시 방향) 톱니에 적힌 문자 **/
            char lPole = cogWheels[cogWheelNum].charAt(indexesOfCog[cogWheelNum][L_COG_IDX]);
            /* 오른쪽(3시 방향) 톱니에 적힌 문자 **/
            char rPole = cogWheels[cogWheelNum].charAt(indexesOfCog[cogWheelNum][R_COG_IDX]);
            /* 지목된 톱니바퀴의 왼쪽에 톱니바퀴가 존재하며 **/
            /* 지목된 톱니바퀴와 그 왼쪽 톱니바퀴가 서로 다른 극으로 맞닿아 있다면 **/
            if (1 < cogWheelNum
                    && lPole != cogWheels[cogWheelNum - 1]
                    .charAt(indexesOfCog[cogWheelNum - 1][R_COG_IDX])) {
                /* 왼쪽 톱니바퀴를 지목된 톱니바퀴와 반대 방향으로 먼저 회전 **/
                rotateCogWheel(cogWheels, indexesOfCog, cogWheelNum - 1, dir * -1, true);
            }
            /* 지목된 톱니바퀴의 오른쪽에 톱니바퀴가 존재하며 **/
            /* 지목된 톱니바퀴와 그 오른쪽 톱니바퀴가 서로 다른 극으로 맞닿아 있다면 **/
            if (cogWheelNum < NUM_OF_COG_WHEELS
                    && rPole != cogWheels[cogWheelNum + 1]
                    .charAt(indexesOfCog[cogWheelNum + 1][L_COG_IDX])) {
                /* 오른쪽 톱니바퀴를 지목된 톱니바퀴와 반대 방향으로 먼저 회전 **/
                rotateCogWheel(cogWheels, indexesOfCog, cogWheelNum + 1, dir * -1, false);
            }

            /* 회전에 따른 인덱스 변경 적용(즉, 톱니바퀴 회전 적용) **/
            /* 현재 인덱스에 8을 더하고, 회전하는 방향에 따라 1을 빼거나 더한 다음 **/
            /* 8로 나눈 나머지를 구하면? **/
            /* 인덱스가 범위를 벗어나지 않게 "적절히" 조정 **/
            /* 여기서 8은 톱니 수를 의미. 각 톱니에 해당하는 인덱스 범위는 0~7이므로 **/
            /* 8로 나눈 나머지는 반드시 0~7 **/
            indexesOfCog[cogWheelNum][U_COG_IDX]
                    = (indexesOfCog[cogWheelNum][U_COG_IDX] + 8 - dir) % 8;
            indexesOfCog[cogWheelNum][R_COG_IDX]
                    = (indexesOfCog[cogWheelNum][R_COG_IDX] + 8 - dir) % 8;
            indexesOfCog[cogWheelNum][L_COG_IDX]
                    = (indexesOfCog[cogWheelNum][L_COG_IDX] + 8 - dir) % 8;
        }

        /* 최종 회전 후 각 톱니바퀴를 조회하여 점수를 계산, 출력 **/
        for (int cogWheelNum = 1; cogWheelNum <= NUM_OF_COG_WHEELS; cogWheelNum++)
            if (cogWheels[cogWheelNum].charAt(indexesOfCog[cogWheelNum][0]) == S_POLE)
                totalScore += 1 << (cogWheelNum - 1);
        System.out.print(totalScore);
    }

    /**
     * 입력을 처리하는 메서드
     * @param cogWheels : 톱니바퀴들의 상태를 저장할 배열
     * @return : 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input(String[] cogWheels) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int cogWheelNum = 1; cogWheelNum <= NUM_OF_COG_WHEELS; cogWheelNum++)
                cogWheels[cogWheelNum] = reader.readLine();
            int K = Integer.parseInt(reader.readLine());
            rotationsInfo = new int[K][2];
            for (int[] rotationInfo: rotationsInfo) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                rotationInfo[0] = Integer.parseInt(tokenizer.nextToken());
                rotationInfo[1] = Integer.parseInt(tokenizer.nextToken());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 톱니바퀴를 회전시키는 메서드
     * 입력에 따라 특정 톱니바퀴를 지목하여 회전시킬 때, 그 톱니바퀴의 좌우 톱니바퀴가 회전할지는
     * 지목된 톱니바퀴의 회전 이전 상태(정지된 상태)에 달렸으므로,
     * main에서 지목된 톱니바퀴와 그 톱니바퀴의 좌측, 우측 톱니바퀴를 확인한다.
     * 이러한 과정을 main에서 수행하고, 만약 좌측 톱니바퀴 혹은 우측 톱니바퀴의 회전이 가능하다면
     * 좌측 톱니바퀴의 좌측 톱니바퀴가 회전 가능한지, 우측 톱니바퀴의 우측 톱니바퀴가 회전 가능한지를
     * 재귀적으로 파악하여 회전할 수 있는 좌측 끝, 회전할 수 있는 우측 끝의 톱니바퀴부터 회전시킨다.
     * 메서드 내부의 코드는 main의 내용과 크게 다르지 않다.
     * @param cogWheels : 톱니바퀴들의 상태를 저장한 배열
     * @param indexesOfCog : 각 톱니바퀴의 위쪽, 오른쪽, 왼쪽 톱니의 인덱스를 저장한 배열
     * @param cogWheelNum : 현재 확인하는 톱니바퀴의 번호
     * @param dir : 톱니바퀴가 회전할 방향(1은 시계 방향, -1은 반시계 방향)
     * @param isLeftCogWheel : 확인하는 톱니바퀴가 지목된 톱니바퀴의 왼쪽에 있는 것인지를 판별하는 변수
     */
    public static void rotateCogWheel(String[] cogWheels, int[][] indexesOfCog,
                                      int cogWheelNum, int dir, boolean isLeftCogWheel) {
        if (isLeftCogWheel) {
            char lPole = cogWheels[cogWheelNum].charAt(indexesOfCog[cogWheelNum][L_COG_IDX]);
            if (1 < cogWheelNum
                    && lPole != cogWheels[cogWheelNum - 1]
                    .charAt(indexesOfCog[cogWheelNum - 1][R_COG_IDX])) {
                rotateCogWheel(cogWheels, indexesOfCog, cogWheelNum - 1, dir * -1, true);
            }
        } else {
            char rPole = cogWheels[cogWheelNum].charAt(indexesOfCog[cogWheelNum][R_COG_IDX]);
            if (cogWheelNum < NUM_OF_COG_WHEELS
                    && rPole != cogWheels[cogWheelNum + 1]
                    .charAt(indexesOfCog[cogWheelNum + 1][L_COG_IDX])) {
                rotateCogWheel(cogWheels, indexesOfCog, cogWheelNum + 1, dir * -1, false);
            }
        }

        indexesOfCog[cogWheelNum][U_COG_IDX]
                = (indexesOfCog[cogWheelNum][U_COG_IDX] + 8 - dir) % 8;
        indexesOfCog[cogWheelNum][R_COG_IDX]
                = (indexesOfCog[cogWheelNum][R_COG_IDX] + 8 - dir) % 8;
        indexesOfCog[cogWheelNum][L_COG_IDX]
                = (indexesOfCog[cogWheelNum][L_COG_IDX] + 8 - dir) % 8;
    }
}
