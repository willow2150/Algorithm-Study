package b7490;
import java.io.*;
import java.util.*;
public class Main {
	public static PriorityQueue<String> ansList= new PriorityQueue<String>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		// 테스트 케이스마다 백트래킹 실행
		for( int i=0;i<T;i++) {
			int N = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			// N 개의 숫자 사이에 들어갈 N-1 개의 연산자 배열
			char[] operators = new char[N-1];

			// 백트래킹
			bt(N,0,operators);

			// 정답을 우선순위 큐에 저장하여 아스키 순서로 정렬하고 poll 하면서 출력 
			while(!ansList.isEmpty()) {
				System.out.println(ansList.poll());
			}
			// 정답 출력을 위한 예외 처리
			if(i != T-1) System.out.println();
		}


	}
	/**
	 * 
	 * @param n 숫자의 개수
	 * @param n-1  결정할 연산자의 수 
	 * @param r 결정된 연산자의 수
	 * @param operators 연산자의 순서를 저장할 배열
	 * 연산자 배열을 계속 deep copy 하여
	 * 연산자를 +,-," " 순으로 백트래킹 하는 과정에서
	 * 수정되는 일이 없도록 함
	 */
	public static void bt(int n, int r, char[] operators) {
		// 연산자를 모두 결정하면 정답인지 판단
		if(r == n-1) {
			calc(n, operators);
			return;
		}

		// 현재 위치의 연산자가 '+' 인 경우
		operators[r] = 'p';
		bt(n,r+1, operators.clone());

		// 현재 위치의 연산자가 '-' 인 경우		
		operators[r] = 'm';
		bt(n,r+1,operators.clone());

		// 현재 위치의 연산자가 ' ' 인 경우
		operators[r] = 'a';
		bt(n,r+1,operators.clone());
	}
	/**
	 * 1. 결정된 연산자들을 거꾸로 확인
	 * 2. +, - 가 나오면 현재 숫자(cur)를 결과(sum)에 더하거나 뺌
	 * 3. " " 가 나오면 cur을 자릿수에 맞게 수정
	 */
	public static void calc(int n, char[] operators ){
		int sum =0;
		int cur = n;
		StringBuilder sb = new StringBuilder();
		for(int i=n-2;i>=0;i--) {
			char op =operators[i]; 
			if(op == 'p') {
				sum += cur;
				cur = i+1;
			} else if(op=='m') {
				sum -= cur;
				cur = i+1;
			} else {
				cur  += Math.pow(10, Integer.toString(cur).length()) * (i+1);

			}
		}
		// 연산자가 " "만 존재하는 경우
		// sum이 0으로 초기화되어 정답으로 판별되는 경우 예외 처리
		sum += cur;

		// 정답이면 출력형식에 맞게 수정 후 우선순위큐에 저장
		if(sum==0) {
			sb.append(1);
			for( int i=2;i<=n;i++) {
				if(operators[i-2]=='p') sb.append('+');
				else if(operators[i-2]=='m') sb.append('-');
				else sb.append(' ');
				sb.append(i);
			}
			ansList.add(sb.toString());

		}

	}

}
