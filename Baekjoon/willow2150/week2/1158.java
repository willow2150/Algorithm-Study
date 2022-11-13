import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /* 연결 리스트 **/
    static class List {
        /* head와 tail은 연결 리스트의 첫 노드와 마지막 노드를 가리킬 변수 **/
        Node head, tail;
        /* pointer는 제거할 노드의 직전 노드를 가리킬 변수 **/
        Node pointer;
        /* 연결 리스트의 크기 **/
        int size;

        /**
         * 연결 리스트에 1~numOfNodes를 값으로 갖는 노드를 차례대로 추가하는 메서드
         * @param numOfNodes : 마지막 노드의 번호
         */
        void connectNodes(int numOfNodes) {
            for (int item = 1; item <= numOfNodes; item++)
                add(new Node(item));
            /* 마지막 노드의 next가 첫 노드를 가리키도록 설정 **/
            tail.next = head;
        }

        /**
         * 연결 리스트에 노드를 추가하는 메서드
         * @param node : 연결 리스트에 추가할 노드
         */
        void add(Node node) {
            /* tail이 null이면 빈 연결 리스트 **/
            if (tail == null) {
                head = tail = node;
                /* 노드를 처음 제거할 때와 이후 노드를 제거할 때 **/
                /* 같은 코드를 적용할 수 있도록 처음에는 pointer가 **/
                /* 빈 노드를 가리키고, 빈 노드의 next가 head를 가리키도록 설정 **/
                pointer = new Node(0);
                pointer.next = head;
            } else {
                tail.next = node;
                tail = tail.next;
            }
            size++;
        }

        /**
         * 제거할 노드를 찾아 반환하는 메서드
         * @param inFrontOfK : 제거된 노드와 다음 제거할 노드 간 거리
         * @return : 제거할 노드
         */
        Node pull(int inFrontOfK) {
            /* 비어있는 연결 리스트라면 null 반환 **/
            if (size == 0)
                return null;
            /* pointer가 제거할 노드의 직전 노드를 가리키도록 **/
            /* inFrontOfK 값을 1 차감 **/
            /* 연결 리스트를 1바퀴 이상 탐색하지 않도록 **/
            /* inFrontOfK 값을 size로 나눈 나머지로 변경 **/
            /* 노드를 하나 제거하므로 size 값을 1 차감 **/
            inFrontOfK = --inFrontOfK % size--;
            for (int i = 0; i < inFrontOfK; i++)
                pointer = pointer.next;
            /* pointer가 가리키는 노드의 다음 노드를 반환 값으로 두고 **/
            Node returnNode = pointer.next;
            /* 반환할 노드를 연결 리스트에서 제거 **/
            pointer.next = returnNode.next;
            return returnNode;
        }
    }

    /* 연결 리스트를 구성할 노드 **/
    static class Node {
        int item;
        Node next;

        Node(int item) {
            this.item = item;
        }

        public int getItem() {
            return item;
        }
    }

    private static int N, K;

    public static void main(String[] args) {
        /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
        if (!input()) return;
        StringBuilder output = new StringBuilder();
        /* 연결 리스트 **/
        List list = new List();
        /* 제거된 노드를 가리킬 변수 **/
        Node removedNode;

        /* 연결 리스트에 1~N을 값으로 갖는 노드를 차례대로 추가 **/
        list.connectNodes(N);

        /* 노드를 꺼내고 출력 문자열에 기록 **/
        output.append('<');
        while ((removedNode = list.pull(K)) != null)
            output.append(removedNode.getItem()).append(',').append(' ');
        output.delete(output.length() - 2, output.length());
        output.append('>');

        /* 출력 **/
        System.out.print(output);
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력 처리에 성공하면 true, 처리 중 예외가 발생하면 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
