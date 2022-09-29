package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/* 걸린시간 : 40분

 * ???줄에서 나올 수 있는 모든 경우의 수로 해볼까? 2^25 경우나 나와서 안됨
 * 위에서 내려온거랑 아래에서 올라온거는 사다리 1행의 영향을 받았기 때문에 두 애들은 그대로거나 1칸만 차이 나야한다

 * */


public class Main_2469 {
    static int k,n;
    static char [][] board;
    static int lind_index;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        char [] start = new char[k];
        for (int i = 0; i < k; i++) {
        	start[i] = (char)('A' + i);
        }

        char [] end_data = br.readLine().toCharArray();

        board = new char[n][k - 1];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();

            if (input.charAt(0) == '?') {
                lind_index = i;
                continue;
            }
            board[i] = input.toCharArray();
        }

        for (int i = 0; i < lind_index; i++) {
            for (int j = 0; j < k - 1; j++) {
                if (board[i][j] == '-') {
                    char temp = start[j];
                    start[j] = start[j + 1];
                    start[j + 1] = temp;
                }
            }
        }
        
        for (int i = n-1; i > lind_index; i--) {
            for (int j = 0; j < k - 1; j++) {
                if (board[i][j] == '-') {
                    char temp = end_data[j];
                    end_data[j] = end_data[j + 1];
                    end_data[j + 1] = temp;
                }
            }
        }        
        
        //System.out.println(Arrays.toString(start));
        //System.out.println(Arrays.toString(end_data));
        
        char[] res = new char[k-1];
        
        for (int i = 0; i < k-1; i++) {
			if (start[i] == end_data[i])
				res[i] = '*';
			else if(start[i] == end_data[i+1] || start[i+1] == end_data[i]) {
				res[i] = '-';
                char temp = start[i];
                start[i] = start[i + 1];
                start[i + 1] = temp;
			}
			else {
				for (int j = 0; j < k-1; j++) {
					res[j] = 'x';
				}
				break;	
			}
		}
        
        for (int i = 0; i < k-1; i++) {
        	System.out.print(res[i]);
		}
        
        

        
		
        
        
        
        
		
	}
}
