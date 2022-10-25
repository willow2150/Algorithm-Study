import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 이겼을때가 아니라 "최종" 상태를 판단하는 것
 * 게임 진행하다가 나타날 수 있는 중간상태도 모두 invalid 이다
 * 따라서 남은 point_cnt가 핵심이 되야한다
 *
 * if point_cnt 없을때(모든칸이 꽉찼고 X먼저 두니까 x가 1 많아야함)
 *      if x_cnt = 5, o_cnt = 4
 *          if X빙고 && O빙고
 *              false
 *          else if !X빙고 && O빙고
 *              false
 *          true
 *      else
 *          false
 * else point_cnt 없을때(게임중간에 누군가 이겨서 멈췄다는 거니까)
 *      if X가 이길 때(x_cnt = o_cnt+1)
 *          X빙고 && !O빙고
 *      if O가 이길 때(x_cnt = o_cnt)
 *          !X빙고 && O빙고
 * */
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
            if (bingo('X') && !bingo('O') && x_cnt == o_cnt+1)
                return true;
            if (bingo('O') && !bingo('X') && x_cnt == o_cnt)
                return true;
            return false;
        }
    }

}
