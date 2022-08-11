import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    /* 회의 일정을 저장한 배열 **/
    private static int[][] meetings;

    public static void main(String[] args) {
        /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
        if (!input()) return;
        /* 회의 종료 시각을 저장할 PriorityQueue : 회의 종료 시각이 이른 것을 우선으로 **/
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (endTimeA, endTimeB) -> endTimeA < endTimeB ? -1 : 1
        );
        /* 필요한 회의실의 수 **/
        int numOfMeetingRooms = 0;

        /* 회의 목록 정렬: 1) 시작 시각이 빠른 순, 2) 종료 시각이 빠른 순 **/
        Arrays.sort(meetings, (meetingA, meetingB) -> {
            if (meetingA[0] == meetingB[0])
                return meetingA[1] < meetingB[1] ? -1 : 1;
            return meetingA[0] < meetingB[0] ? -1 : 1;
        });

        pq.add(Integer.MAX_VALUE);
        for (int[] meeting: meetings) {
            /* 큐에서 가장 이른 회의 종료 시각이 현재 미팅 시작 시각보다 뒤라면 **/
            if (pq.peek() > meeting[0]) {
                /* 회의실 수 추가 **/
                numOfMeetingRooms++;
            } else {
                /* 큐에서 가장 이른 회의 종료 시각이 현재 미팅 시작 시각보다 앞이거나 같다면 **/
                /* 큐에서 가장 이른 회의 종료 시각을 poll **/
                pq.poll();
            }
            /* 현재 회의 종료 시각을 큐에 저장 **/
            pq.add(meeting[1]);
        }
        /* 필요한 회의실 수 출력 **/
        System.out.print(numOfMeetingRooms);
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력 처리 성공 시 true, 예외 발생 시 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine());
            meetings = new int[N][2];
            for (int meetingIndex = 0; meetingIndex < N; meetingIndex++) {
                String[] lineInputSplits = reader.readLine().split(" ");
                meetings[meetingIndex][0] = Integer.parseInt(lineInputSplits[0]);
                meetings[meetingIndex][1] = Integer.parseInt(lineInputSplits[1]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
