import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    /* 노드 클래스 **/
    static class Node {
        char name;
        Node left, right;

        public Node(char name) {
            this.name = name;
        }
    }

    /* 루트 노드의 이름으로 사용할 문자 **/
    private static final char ROOT = 'A';
    /* null 노드의 이름으로 사용할 문자 **/
    private static final char NULL = '.';
    /* 생성한 노드를 저장할 배열 **/
    private static Node[] nodes;

    public static void main(String[] args) {
        StringBuilder output = new StringBuilder();
        nodes = new Node['Z' + 1];

        if (!input()) return;

        preorder(nodes[ROOT], output);
        output.append('\n');
        inorder(nodes[ROOT], output);
        output.append('\n');
        postorder(nodes[ROOT], output);
        System.out.print(output);
    }

    /**
     * 입력을 처리하는 메서드
     * @return : 입력 처리 성공 시 true, 입력 처리 중 예외 발생 시 false
     */
    public static boolean input() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numOfNodes = Integer.parseInt(reader.readLine());
            for (int nodeIndex = 0; nodeIndex < numOfNodes; nodeIndex++) {
                String inputLine = reader.readLine();
                char nodeName = inputLine.charAt(0);
                char lChildNodeName = inputLine.charAt(2);
                char rChildNodeName = inputLine.charAt(4);
                /* 부모 노드가 없다면 생성 **/
                if (nodes[nodeName] == null)
                    nodes[nodeName] = new Node(nodeName);
                /* 왼쪽 자식 노드가 없다면 생성 **/
                if (nodes[lChildNodeName] == null)
                    nodes[lChildNodeName] = new Node(lChildNodeName);
                /* 부모 노드와 왼쪽 자식 노드를 연결 **/
                nodes[nodeName].left = nodes[lChildNodeName];
                /* 오른쪽 자식 노드가 없다면 생성 **/
                if (nodes[rChildNodeName] == null)
                    nodes[rChildNodeName] = new Node(rChildNodeName);
                /* 부모 노드와 오른쪽 자식 노드를 연결 **/
                nodes[nodeName].right = nodes[rChildNodeName];
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 전위 순회
     * @param node : 현재 노드
     * @param output : 출력 문자열
     */
    public static void preorder(Node node, StringBuilder output) {
        if (node.name == NULL)
            return;
        output.append(node.name);
        preorder(node.left, output);
        preorder(node.right, output);
    }

    /**
     * 중위 순회
     * @param node : 현재 노드
     * @param output : 출력 문자열
     */
    public static void inorder(Node node, StringBuilder output) {
        if (node.name == NULL)
            return;
        inorder(node.left, output);
        output.append(node.name);
        inorder(node.right, output);
    }

    /**
     * 후위 순회
     * @param node : 현재 노드
     * @param output : 출력 문자열
     */
    public static void postorder(Node node, StringBuilder output) {
        if (node.name == NULL)
            return;
        postorder(node.left, output);
        postorder(node.right, output);
        output.append(node.name);
    }
}
