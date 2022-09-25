package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/* 걸린 시간 : 1시간 30분
 * 
 * <틀린 풀이>
 * 처음에 가장 왼쪽에 있는 계란을 선택한다음에 이 계란이 깨질 때 까지 다른계란들이랑 치고
 * 깨지게 되면 오른쪽으로 이동한다라고 생각해서 최대한 기준이 되는 계란이 깨지면 안되겠다 해서 
 * 그리디(가장 무게가 작은 계란부터 치기)로 풀었는데 문제 파악이 잘못되었다.
 * 무조껀 3번째에서 오른쪽으로 한번 이동해야됨
 * 
 * <구현 순서>
 * 1. 가장 왼쪽 계란 선택
 * 
 * 2. 선택한 계란으로 다른 계란 중 하나를 친다
 * (if) 선택한 계란이 깨졌거나 or 깨지지 않은 다른 계란이 없으면(모두 깨졌으면) 치지않고 3번으로
 * 
 * 3. 가장 최근에 선택한 계란 한칸 오른쪽 계란을 손에 들고 2번을 다시 진행
 * (if) 가장 최근에 선택한 계란이 가장 오른쪽에 위치한 계란이면 프로그램 종료
 * 
 * 
 * 
 * 계란을 선택하고 어떤 순서로 계란을 처야 최댓값(max)가 되는지 그리디로 접근이 안되기 때문에 모든 경우를 봐야한다
 * 문제 조건이 많아서 헷갈렸는데 천천히 해보면 풀리는 문제였다.
 * */

public class Main_16987 {
	static int[][] data;
	static boolean[] visited;
	static int n,res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		data = new int[n][2];
		visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());;
			data[i][1] = Integer.parseInt(st.nextToken());;
		}
			
		dfs(0,0);
		
		System.out.println(res);
		
		
		
		
	}
	
	public static void dfs(int left_egg, int cnt) {
		if(left_egg == n) {
			res = Math.max(res, cnt);
			return;
		}
		
		if(data[left_egg][0] <= 0 || cnt == n-1) {
			dfs(left_egg + 1, cnt);
			return;
		}
		
		
		
		int temp_cnt = cnt;
		for (int i = 0; i < n; i++) {			
			if (i == left_egg)
				continue;

			int choose_egg = i;

			if (data[choose_egg][0]<=0)
				continue;
			
			crush(left_egg, choose_egg);
			
			if (data[left_egg][0] <= 0)
				cnt++;
			if (data[choose_egg][0] <= 0)
				cnt++;
			
			dfs(left_egg+1,cnt);
			recovery_crush(left_egg, choose_egg);
			cnt = temp_cnt;
		}
		
		
	}
	
	public static void crush(int left_egg, int choose_egg) {
		data[left_egg][0] -= data[choose_egg][1];
		data[choose_egg][0] -= data[left_egg][1];
	}
	
	
	static void recovery_crush(int left_egg, int choose_egg) {
		data[left_egg][0] += data[choose_egg][1];
		data[choose_egg][0] += data[left_egg][1];		
	}
	

}
