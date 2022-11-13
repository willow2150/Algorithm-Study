import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    /* 입력으로 주어질 값과 그 절댓값을 저장할 수 있는 클래스 **/
    static class Int implements Comparable<Int> {
        /* 절댓값 **/
        int absolutValue;
        /* 입력으로 받은 값 **/
        int value;

        public Int(int value) {
            this.absolutValue = Math.abs(value);
            this.value = value;
        }

        /* 절댓값이 같으면 this 객체의 값이 음수일 때 작은 값으로 판단 **/
        /* 절댓값이 다르면 절댓값이 작은 쪽을 작은 값으로 판단 **/
        @Override
        public int compareTo(Int intObject) {
            if (this.absolutValue == intObject.absolutValue) {
                return this.value < 0 ? -1 : 1;
            } else {
                return this.absolutValue < intObject.absolutValue ? -1 : 1;
            }
        }
    }

    public static void main(String[] args) {
        StringBuilder output = new StringBuilder();
        /* 힙 **/
        Queue<Int> pq = new PriorityQueue<>();
        int[] operationInfoArray = input();

        /* 입력 처리 중 예외가 발생하면 프로그램 종료 **/
        if (operationInfoArray == null) return;

        for (int operationInfo: operationInfoArray) {
            /* 연산 정보가 0이 아니면 연산 정보 값을 힙에 저장 **/
            /* 힙이 비어있다면 0을 출력 문자열에 저장 */
            /* 위 두 경우가 아니면 힙에서 값을 가져와 출력 문자열에 저장 **/
            if (operationInfo != 0) {
                pq.add(new Int(operationInfo));
            } else if (pq.isEmpty()) {
                output.append(0).append('\n');
            } else {
                output.append(pq.poll().value).append('\n');
            }
        }
        System.out.print(output);
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력 처리 성공 시 입력받은 배열을 반환, 입력 처리 중 예외 발생 시 null 반환
     */
    public static int[] input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            /* 연산의 횟수 **/
            int numOfOperations = Integer.parseInt(reader.readLine());
            /* 연산 정보를 저장할 배열 **/
            int[] operationInfoArray = new int[numOfOperations];
            for (int operationIdx = 0; operationIdx < numOfOperations; operationIdx++)
                operationInfoArray[operationIdx] = Integer.parseInt(reader.readLine());
            return operationInfoArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
