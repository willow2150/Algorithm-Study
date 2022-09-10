package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



/* 걸린시간 : 30분
 * 
 * 문제는 쉬웠는데 어떻게 접근할지 선택장애와서 낭비되는 시간(?)이 많았던 문제
 * 
 * 
 * 기울기 그림을 그려보니 대충은 이해했지만 완벽하게 이해는 못함
 * 빗물이 고일 수 밖에 없는 상황에 대해 생각해봄
 * 
 * 1. start를 정하고 deque에 계속 넣은다음 last값 보다 큰값이 나오면 다 더해주기? 반례가 너무많음
 * 
 * 
 * 2. 내가 있는 index에서 고일 수 있는 빗물은 어느정도일까?
 * -> 최종적으로는 왼쪽 , 오른쪽 다 탐색해보고 그 중 작은애의 차이만큼 빗물이 고이지 않나? 
 * -> 근데 만약에 왼쪽, 오른쪽 한곳이라도 내가 있는 곳보다 낮은 위치라면 흘러 내릴 수 있으니까 거기는 못채움
 * 
 * 3. 2차원 행렬이라고 준 이유가 뭘까. 그냥 단순히 숫자로만 줘도 되지않나. 혹시 2차원으로도 풀 수 있는 hint가 있어서 준건가?
 * row로 탐색을 하면서 1001101 이렇게 있으면 1과 1사이에 0이 있으면 그 0부분이 빗물로 채울 수 있는 부분이다!
 * -> 1이 나오면 flag 스위치 on 해버리고 on이라면 더해주고 다시 flag off 하는 되는식으로 하면 된다
 * 
 * */
public class Main_14719 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
/*		
		String[] input = br.readLine().split(" ");
		int H = Integer.parseInt(input[0]);
		int W = Integer.parseInt(input[1]);

		int[] data = new int[W];
		
		input = br.readLine().split(" ");
		for (int i = 0; i < W; i++) 
			data[i] = Integer.parseInt(input[i]);
		
		int res = 0;

		
		int start = data[0];

		for (int i = 1; i < W; i++) {
			
			int left = 0;
			int right = 0;
			
			for (int j = i-1; j >=0 ; j--) {
				left = Math.max(left, data[j]);
			}
			for (int j = i+1; j < W; j++) {
				right = Math.max(right, data[j]);
			}
			
			//빗물 못채우는 경우
			if (left > data[i] && right > data[i] ) {
				int std = Math.min(left, right);
				res += std - data[i];
			}
			
		}	
*/		
		//2. 2차원 배열의 row로 탐색하며 풀기
		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
     
        boolean[][] data = new boolean[H][W];
        
        int res = 0;
        
        for (int i=0; i<W; i++) {
            int value = Integer.parseInt(st.nextToken());
            for (int j=0; j<value; j++) 
            	data[H-j-1][i] = true;
        }
		
        for (int i = 0; i < H; i++) {
        	int std = 0;
            boolean flag = false;
			for (int j = 0; j < W; j++) {
				if(data[i][j]) {
                    if (flag) {
                    	res += j-std-1;
                    	//2번째 true를 만난순간부터 다음 스위치는 on인 상태니까 flag=false를 할 필요가 없음!
                    }
                    else 
                    	flag = true;
               
                    std = j;
				}
				}
			
			}
		System.out.println(res);

		}
	
	}


