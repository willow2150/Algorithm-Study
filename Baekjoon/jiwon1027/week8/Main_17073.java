package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_17073 {

	/* 걸린시간 : 10분
	 * 
	 * '더 이상 물이 움직이지 않을 때' 라는건 리프노드에 물이 도착했다는 의미라서 
	 * 물을 리프노드로 나눈 값이 정답
	 * 
	 * 리프노드 찾는법 : 리프노드는 물이 1번만 떨어지는 것을 캐치
	 * 다른풀이법 : dfs, bfs로 돌리면서 리프노드 개수를 찾기
	 * 
  	 * https://www.acmicpc.net/problem/1068
	 * 문제 풀어보면 도움 많이 됨
	 * */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int w = Integer.parseInt(input[1]);
        int res = 0;
        int[] left_node = new int[n+1];

        for(int i=0; i< n-1; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            left_node[a]++;
            left_node[b]++;
        }
        System.out.println(Arrays.toString(left_node));

        for(int i=2; i<=n; i++) {
            if(left_node[i]==1)
                res++;
        }

        System.out.println((double)w/res);
	}

}
