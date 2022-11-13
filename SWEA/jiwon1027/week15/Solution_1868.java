package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 주위에 지뢰가 있는곳을 클릭하면 그 지역만 보이게 된다.
 * 따라서, 최소의 버튼으로 클릭을 하려면 주변에 폭탄이 없는(0) 부분을 먼저 없앤다음 나머지 .부분(주변에 폭탄이 있는 부분)을 더하면 된다
 * 이래야 많은 .들이 없어질테니까!
 *
 * Point1. 0을 클릭하면 어떻게 되야할까?
 * 8방 탐색으로 BFS 돌리면서 더이상 나아갈 수 없을 때 까지(주변에 폭탄이 있을 때) 탐색
 *
 * Point2.값이 0인 애들이 여러개 있는데 이걸 클릭하는 순서가 과연 중요할까?
 * BFS 돌리기 때문에 인접해있는 애들은 다같이 한 Queue에서 탐색될꺼니까 한 Team이라고 생각하면 여러 Team들이 생길거고
 * 각각의 Team들은 서로에게 영향을 끼치지 않는다. 따라서 그냥 순차적으로 탐색하면됨.
 *
 * */

public class Solution_1868 {

    static int N,res;
    static char[][] board;
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            N = Integer.parseInt(br.readLine());
            board = new char[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                board[i] = br.readLine().toCharArray();
            }

            res = Integer.MAX_VALUE;

            int temp = 0;
            //0인 부분 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j]=='.' && find_bomb(i,j) == 0){ // 주변에 폭탄이 없으면 bfs로 연쇄로 터지기
                        bfs(i,j);
                        temp++; //0을 찾았으니 클릭 한번은 해야하니까
                    }
                }
            }
            //나머지 0이 아닌 애들 더하기
            temp += find_remain();

            res = Math.min(res, temp);

            System.out.println("#"+tc+" "+res);



        }
    }

    public static int find_remain(){
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '.')
                    cnt++;
            }
        }
        return cnt;
    }
    public static void bfs(int i, int j){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i,j});
        board[i][j] = '0';

        while (!queue.isEmpty()){
            int[] poll_data = queue.poll();
            int x = poll_data[0];
            int y = poll_data[1];

            for (int k = 0; k < 8; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx<0 || ny<0 || nx>=N || ny>= N || visited[nx][ny])
                    continue;
                if (board[nx][ny]=='.' && find_bomb(nx,ny) == 0){ //클릭했을 때 주변에 폭탄이 없는 경우
                    board[nx][ny] = '0';
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx,ny});
                }
                else if(board[nx][ny] == '.'){ //0은 아니지만 그냥 클릭할 수 있는 경우
                    board[nx][ny] = (char)('0' + find_bomb(nx,ny));
                    visited[nx][ny] = true;
                }
            }

        }

    }
    //주변 8방향에 폭탄이 몇개있나 counting
    public static int find_bomb(int x, int y){
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx<0 || ny<0 || nx>=N || ny>= N)
                continue;
            if (board[nx][ny] == '*')
                cnt++;
        }

        return cnt;
    }


}
