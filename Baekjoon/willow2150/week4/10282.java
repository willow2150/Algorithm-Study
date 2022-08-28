import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /* 의존성을 표현한 사용자 정의 클래스 **/
    static class Connection implements Comparable<Connection> {
        int affectedComputer;
        int timeTaken;
        Connection another;

        Connection() {}

        Connection(int affectedComputer, int timeTaken, Connection another) {
            this.affectedComputer = affectedComputer;
            this.timeTaken = timeTaken;
            this.another = another;
        }

        void init(int arrComputer, int timeTaken, Connection another) {
            this.affectedComputer = arrComputer;
            this.timeTaken = timeTaken;
            this.another = another;
        }

        @Override
        public int compareTo(Connection connection) {
            return this.timeTaken <= connection.timeTaken ? -1 : 1;
        }
    }

    /* 최대 컴퓨터의 수 **/
    private static final int MAX_NUM_OF_COMPUTERS = 10_000;
    /* 최대 의존성의 수 **/
    private static final int MAX_NUM_OF_DEPENDENCIES = 100_000;
    /* n: 컴퓨터의 개수, c: 해킹당한 컴퓨터 번호 **/
    private static int n, c;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        /* 테스트케이스 수 **/
        int numOfTC = Integer.parseInt(reader.readLine());

        /* 컴퓨터 배열(의존성 리스트의 각 head를 저장) **/
        Connection[] computers = new Connection[MAX_NUM_OF_COMPUTERS + 1];
        /* 의존성을 꺼내올 풀(재사용 목적) **/
        Connection[] dependencies = new Connection[MAX_NUM_OF_DEPENDENCIES];
        /* 각 컴퓨터의 최소 감염 시간을 저장한 배열 **/
        int[] minInfectionTime = new int[MAX_NUM_OF_COMPUTERS + 1];

        for (int tC = 1; tC <= numOfTC; tC++) {
            /* 각 컴퓨터의 의존성 초기화 **/
            for (int index = 1; index <= n; index++)
                computers[index] = null;
            /* 입력 처리 중 예외 발생 시 프로그램 종료 **/
            if (!input(reader, computers, dependencies)) return;
            /* 감염되는 컴퓨터 수와 마지막 컴퓨터가 감염되기까지 걸리는 시간을 **/
            /* 출력 문자열에 저장하는 메서드 **/
            infectComputers(computers, minInfectionTime, output);
        }
        /* 결과 출력 **/
        System.out.print(output);
    }

    /**
     * 입력을 처리하는 메서드
     * @param reader : BufferedReader
     * @param computers : 컴퓨터 배열(의존성 리스트의 각 head를 저장)
     * @param dependencies : 의존성을 꺼내올 풀(재사용 목적)
     * @return : 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input(BufferedReader reader,
                                Connection[] computers, Connection[] dependencies) {
        try {
            int index = 0;
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            /* n: 컴퓨터의 수 **/
            n = Integer.parseInt(tokenizer.nextToken());
            /* d: 의존성 수 **/
            int d = Integer.parseInt(tokenizer.nextToken());
            /* c: 해킹당한 컴퓨터 번호 **/
            c = Integer.parseInt(tokenizer.nextToken());
            for (int i = 0; i < d; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                /* 감염에 소요되는 시간 **/
                int s = Integer.parseInt(tokenizer.nextToken());
                /* 의존성을 풀에서 가져와 적절히 연결 **/
                if (dependencies[index] == null)
                    dependencies[index] = new Connection();
                Connection dependency = dependencies[index++];
                dependency.init(a, s, computers[b]);
                computers[b] = dependency;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 감염되는 컴퓨터 수, 마지막 컴퓨터가 감염되기까지 걸리는 시간을
     * 출력 문자열에 저장하는 메서드
     * @param computers : 각 컴퓨터, 그리고 그 의존성을 연결한 리스트
     * @param minInfectionTime : 각 컴퓨터의 최소 감염 시간을 저장한 배열
     * @param output : 결과를 저장할 출력 문자열
     */
    public static void infectComputers(Connection[] computers, int[] minInfectionTime,
                                       StringBuilder output) {
        Queue<Connection> queue = new PriorityQueue<>();
        int numOfInfectedComputers = 0;
        int timeOfLastInfection = 0;

        /* 각 컴퓨터의 최소 감염 시간을 큰 값으로 초기화 **/
        Arrays.fill(minInfectionTime, 1, n + 1, Integer.MAX_VALUE);
        queue.add(new Connection(c, 0, null));
        while (!queue.isEmpty()) {
            Connection conn = queue.poll();
            if (minInfectionTime[conn.affectedComputer] <= conn.timeTaken)
                continue;
            minInfectionTime[conn.affectedComputer] = conn.timeTaken;
            numOfInfectedComputers++;
            timeOfLastInfection = conn.timeTaken;
            Connection anotherConn = computers[conn.affectedComputer];
            while (anotherConn != null) {
                int infectionTime = conn.timeTaken + anotherConn.timeTaken;
                if (minInfectionTime[anotherConn.affectedComputer] >= infectionTime) {
                    queue.add(
                            new Connection(anotherConn.affectedComputer, infectionTime, null)
                    );
                }
                anotherConn = anotherConn.another;
            }
        }
        output.append(numOfInfectedComputers)
                .append(' ')
                .append(timeOfLastInfection)
                .append('\n');
    }
}
