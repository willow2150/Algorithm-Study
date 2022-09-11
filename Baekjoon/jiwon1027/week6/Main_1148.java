package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/* 걸린시간 : 4시간
 * 
 * 문제 이해하는데 2시간 30분이나 쓴 문제였다.
 * 사실 이해하고 보니까 이렇게 한번에 이해할 수 있을까 두려웠다
 * 
 * '-' 이전에 나오는 단어들은 저 문제세계에서 존재하는 단어들인것이고
 * '#' 이전에 나오는 문자열들은 사전에 적을 퍼즐판인 것이다
 * 
 * 1. 퍼즐판에 대해서 위에 나온 단어들을 만들 수 있는지 체크
 * 2. 만들 수 있다면 단어를 만들기 위해 퍼즐판의 어떤 알파벳을 썼는지 count해주며 체크
 * 3. count에 따라 if count[x]==3 이라면 여러개의 단어중에서 x라는 알파벳을 무조껀 사용하는 단어(중앙에위치)는 count[x]개임
 * 4. count의 최대 최소를 구한다음 최대, 최소 똑같은 애들이랑 값을 묶어 출력해야됨
 * => 그냥 if count[x] !=0 으로 해버리면 되는 줄 알고 시간을 또 엄청 썼는데 puzzle_board[x]!=0으로 해야됨
 * => why?) 그냥 if count[x] !=0로 해버리면 애초에 퍼즐판에도 없는 알파벳들도 정답으로 가지고 오려고 하기 때문임
 * 
 * 
 * 
 * 파이썬으로 풀었다면 배열의 min,max,count()때문에 훨씬 빨리 풀 수 있을 것 같다. 
 * */


public class Main_1148 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] word = new int[200000][26];

		int k = 0;

		while (true) {
			String input = br.readLine();
			if (input.equals("-"))
				break;
			for (int j = 0; j < input.length(); j++) {
				word[k][input.charAt(j)-'A']++;
			}
			k++;
		}
		
		while (true) {
			int[] puzzle_board = new int[26];
			String input = br.readLine();
			if (input.equals("#"))
				break;
			for (int j = 0; j < input.length(); j++) {
				puzzle_board[input.charAt(j)-'A']++;
			}
			
            int[] count = new int[26];      
	        
	        /* 지금 퍼즐판으로 만들 수 있는 단어를 확인
	         * 
	         * [예제]에서는 BRILLIANT,LINT,TILL,TRILL 4개를 만들 수 있는데 각각 어떤 알파벳을 사용하는지 배열(temp)에 저장해서 체크 해줌
	         */
	        	        
	        for (int i = 0; i < k; i++) {
	            if (make_check(word[i], puzzle_board)) {
	            	//System.out.println(Arrays.toString(word[i]));
		            for (int j = 0; j < 26; j++) {
		                if (word[i][j] != 0)
		                	count[j]++;
		            }
	            }
	        }
	        
	        //System.out.println(Arrays.toString(count));
	        
	        
	        
	        //가장 작은 애들끼리, 큰 애들끼리 모아서 정답 출력하면 끝
	        int min_cnt = 200001;
	        int max_cnt = -1;
	        
	        
	        for (int i = 0; i < 26; i++) {
				/* 꼭 단어를 1개 이상 만들 필요는 없고 퍼즐판에 있는 알파펫을 이용해서 만들 수 있는 단어를 가장 적게 하는 것 이니까
				 * 0개도 된다는 의미(예제3)
				 * */
                if (puzzle_board[i] != 0) { 
	                if (min_cnt > count[i]) 
	                	min_cnt = count[i];
	                
	                if (max_cnt < count[i]) 
	                	max_cnt = count[i];
                }
            }
	        
            for (int i = 0; i < 26; i++) {
                if (count[i] == min_cnt && puzzle_board[i] != 0)
                	System.out.print((char)('A'+i));
            }
			System.out.print(" "+min_cnt+" ");
            for (int i = 0; i < 26; i++) {
            	if (count[i] == max_cnt && puzzle_board[i] != 0)
                	System.out.print((char)('A'+i));
            }
			System.out.println(" "+max_cnt);

		}

	}
	
	
    public static boolean make_check(int[] word, int[] puzzle_board) {
        for (int i = 0; i < 26; i++) {
            if (puzzle_board[i] < word[i]) 
            	return false;
        }
        return true;
    }

}
