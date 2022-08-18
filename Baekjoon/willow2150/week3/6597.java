import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder output;
    /* 입력: 프리오더 **/
    private static String preorder;
    /* 입력: 인오더 **/
    private static String inorder;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        output = new StringBuilder();
        try {
            while (true) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                preorder = tokenizer.nextToken();
                inorder = tokenizer.nextToken();
                writeToOutputPostorder(0, preorder.length() - 1, 0, inorder.length() - 1);
                output.append('\n');
            }
        } catch (Exception ignored) {}
        System.out.print(output);
    }

    /**
     * 포스트오더 결과를 출력 문자열에 저장하는 메서드
     * @param preL : 프리오더 범위 중 가장 왼쪽 인덱스
     * @param preR : 프리오더 범위 중 가장 오른쪽 인덱스
     * @param inL : 인오더 범위 중 가장 왼쪽 인덱스
     * @param inR : 인오더 범위 중 가장 오른쪽 인덱스
     */
    public static void writeToOutputPostorder(int preL, int preR, int inL, int inR) {
        if (preL > preR)
            return;
        char root = preorder.charAt(preL);
        int rootIdxInInorder;
        for (rootIdxInInorder = inL; rootIdxInInorder <= inR; rootIdxInInorder++)
            if (inorder.charAt(rootIdxInInorder) == root)
                break;
        int nextPreR = preL + rootIdxInInorder - inL;
        writeToOutputPostorder(preL + 1, nextPreR, inL, rootIdxInInorder - 1);
        writeToOutputPostorder(nextPreR + 1, preR, rootIdxInInorder + 1, inR);
        output.append(root);
    }
}
