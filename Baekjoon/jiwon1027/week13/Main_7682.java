package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_7682 {
    static char[][] board;
    static int x_cnt, o_cnt,point_cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String str = br.readLine();

            if (str.equals("end"))
                break;
            x_cnt = 0;
            o_cnt = 0;
            point_cnt = 0;

            board = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = str.charAt((i*3)+j);
                    if (board[i][j] == 'X')
                        x_cnt++;
                    else if (board[i][j] == 'O')
                        o_cnt++;
                    else if (board[i][j] == '.')
                        point_cnt++;
                }
            }

            if (check())
                System.out.println("valid");
            else
                System.out.println("invalid");




        }
    }

    /*
     * 이겼을때가 아니라 "최종" 상태를 판단하는 것
     * 게임 진행하다가 나타날 수 있는 중간상태도 모두 invalid 이다
     * 따라서 남은 point_cnt가 핵심이 되야한다
     * */


    public static boolean bingo(char c){
        //가로
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == c && board[i][1] == c && board[i][2] == c )
                return true;
        }

        //세로
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == c && board[1][i] == c && board[2][i] == c )
                return true;
        }

        //대각선
        if (board[0][0] == c && board[1][1] == c && board[2][2] == c )
            return true;

        if (board[2][0] == c && board[1][1] == c && board[0][2] == c )
            return true;

        return false;
    }

    public static boolean check(){
        if (x_cnt < o_cnt)
            return false;

        if (x_cnt == o_cnt+1 && x_cnt + o_cnt == 9){
            if (!bingo('X') && bingo('O'))
                return false;
            else if (bingo('X') && bingo('O'))
                return false;
            return true;
        }
        else{
            //point_cnt가 남았다는건 둘 중 하나가 무조껀 이겨야 끝난다는 소리
            // X win : x_cnt = o_cnt+1
            // O win : x_cnt == o_cnt
            if (bingo('X') && !bingo('O') && x_cnt == o_cnt+1)
                return true;
            if (bingo('O') && !bingo('X') && x_cnt == o_cnt)
                return true;
            return false;
        }
    }

}
