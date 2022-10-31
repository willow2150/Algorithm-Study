import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] board = new char[3][3];
		
		//대각선 탐색
		
		while(true) {
			String line = br.readLine();

			if(line.equals("end")) break;

			int o_cnt = 0;
			int x_cnt = 0;
			boolean hasEmpty = false;
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					board[i][j] = line.charAt(i*3 + j);
					if(board[i][j]=='O') o_cnt++;
					else if(board[i][j]=='X') x_cnt++;
					else {
						hasEmpty = true;
					}
				}
			}

			// X 의 개수 = O의 개수 or O의 개수 + 1
			if(x_cnt != o_cnt && x_cnt != o_cnt+1) {
				System.out.println("invalid");
				continue;
			}

			// 빙고가 되기에 수가 부족한 경우
			if(x_cnt < 3 && o_cnt < 3) {
				System.out.println("invalid");
				continue;
			}

			boolean o_valid = false;
			boolean x_valid = false;
			char cur;
			boolean isValid;
			// 가로 체크
			for(int i=0;i<3;i++) {
				cur = board[i][0];
				 isValid = true;
				for(int j=1;j<3;j++) {
					if(cur != board[i][j]) {
						isValid = false;
						break;
					}
				}
				if(isValid) {
					if(cur=='O') o_valid = true;
					else if(cur=='X') x_valid = true;
				}
			}

			// 세로 체크
			for(int i=0;i<3;i++) {
				cur = board[0][i];
				 isValid = true;
				for(int j=1;j<3;j++) {
					if(cur != board[j][i]) {
						isValid = false;
						break;
					}
				}
				if(isValid) {
					if(cur=='O') o_valid = true;
					else if(cur=='X') x_valid = true;
				}
			}

			// 대각선 체크
			cur = board[0][0];
			isValid = true;
			for(int i=1;i<3;i++) {
				if(cur != board[i][i]) {
					isValid = false;
					break;
				}
			}
			
			if(isValid) {
				if(cur=='O') o_valid = true;
				else if(cur=='X') x_valid = true;
			}
			
			cur = board[2][0];
			isValid = true;
			for(int i=1;i<3;i++) {
				if(cur != board[2-i][i]) {
					isValid = false;
					break;
				}
			}
			
			if(isValid) {
				if(cur=='O') o_valid = true;
				else if(cur=='X') x_valid = true;
			}
			
			/**
			 * 1. X 와 O 모두 틱택토
			 * 2. O만 틱택토
			 * 3. X만 틱택토 
			 * 4. 틱택토 없음
			 * */
	
			if(x_valid && o_valid) {
				System.out.println("invalid");
			} else if(!x_valid && o_valid) {
				if(x_cnt != o_cnt) {
					System.out.println("invalid");
				} else {
					System.out.println("valid");
				}
			} else if(x_valid && !o_valid) {
				if(x_cnt != o_cnt +1) {
					System.out.println("invalid");
				} else {
					System.out.println("valid");
				}
			} else {
				if(hasEmpty) {
					System.out.println("invalid");
				} else {
					System.out.println("valid");
				}
			}
		}
	}
}