package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1941 {
    static char[][] board = new char[5][5];
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        int[] isSelected = new int[7];

        combi(0,0,isSelected);


        System.out.println(res);

    }


    public static boolean check_seven_princess(int[] isSelected){
        int cnt = 0;
        for (int i = 0; i < 7; i++) {
            int x = (isSelected[i]) / 5;
            int y = (isSelected[i]) % 5;
            if (board[x][y] == 'S')
                cnt++;
        }
        if (cnt >=4){
            return true;
        }
        else
            return false;
    }

    public static boolean check(int[] isSelected){
        boolean[] visited = new boolean[7];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[0] = true;
        queue.add(isSelected[0]);

        while(!queue.isEmpty()){
            int temp = queue.poll();
            int x = (temp) / 5;
            int y = (temp) % 5;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx<0 || nx>=5 | ny<0 || ny>=5)
                    continue;
                int idx = (nx*5) + (ny);

                for (int j = 0; j < 7; j++) {
                    if (isSelected[j] == idx){
                        if (!visited[j]){
                            queue.add(idx);
                            visited[j] = true;
                        }
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            if (!visited[i])
                return false;
        }
        //System.out.println(Arrays.toString(isSelected));

        return true;



    }

    public static void combi(int depth, int start, int[] isSelected){
        if (depth == 7){
            if (check_seven_princess(isSelected)){
                if (check(isSelected))
                    res++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            isSelected[depth] = i;
            combi(depth+1,i+1,isSelected);
        }
    }

}
