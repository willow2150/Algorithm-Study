package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_2931 {
    static char[][] map;
    static int R,C;
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };
    static Map<Character, Integer> idx = new HashMap<>();
    static boolean[][] connection = { { true, true, false, false },
                                    { false, false, true, true },
                                    { true, true, true, true },
                                    { false, true, false, true },
                                    { true, false, false, true },
                                    { true, false, true, false },
                                    { false, true, true, false } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        idx.put('|', 0);
        idx.put('-', 1);
        idx.put('+', 2);
        idx.put('1', 3);
        idx.put('2', 4);
        idx.put('3', 5);
        idx.put('4', 6);

        for (int i = 0; i < R; i++) {
            String t = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = t.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') {
                    boolean[] visited = new boolean[4];
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && ny >= 0 && nx < R && ny < C ){
                                if(map[nx][ny] != '.' && map[nx][ny] != 'Z' && map[nx][ny] != 'M' ){
                                    if(connection[idx.get(map[nx][ny])][reverse_dir(k)]) {
                                        cnt++;
                                        visited[k] = true;
                                    }
                                }
                        }
                    }
                    if (cnt == 4) {
                        System.out.println((i + 1) + " " + (j + 1) + " +");
                        return;

                    } else if (cnt == 2) {
                        print(i + 1, j + 1, visited);
                        return;
                    }
                }
            }
        }
    }

    private static void print(int x, int y, boolean[] visited) {
        for (int i = 0; i < 7; i++) {
            if (i == 2)
                continue;
            boolean flag = true;
            for (int j = 0; j < 4; j++) {
                if (connection[i][j] != visited[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(x + " " + y + " " + pipe(i));
            }
        }

    }

    public static char pipe(int i) {
        switch (i) {
            case 0:
                return '|';
            case 1:
                return '-';
            case 2:
                return '+';
            case 3:
                return '1';
            case 4:
                return '2';
            case 5:
                return '3';
            case 6:
                return '4';
        }
        return 0;
    }

    public static int reverse_dir(int d) {
        switch (d) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
        }
        return d;
    }
}