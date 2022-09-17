package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/* 걸린 시간 : 1시간 40분
 * 
 * n의 범위가 20까지 주어진 것을 보고 미리 순열을 구해놓은 다음에 몇번째인지 하나하나씩 탐색하면서 하는건 절때 아닌 문제구나 라는 걸 파악했다
 * 
 * 그럼 순열에 대해서 어떤 규칙이 있다는 뜻이다
 * 
 * 예를들어 예제처럼 n이 4라고 해보면 어떤 x개의 주기만큼 순열의 결과에서 맨 앞자리가 변한다는지 이런 규칙을 찾아봐야 될 것 같다!
 * ==> dp[i] = dp[i-1] * i라는 규칙을 찾았고 이것을 이용해서 각 순열의 순서를 구해주면 된다
 * 
 * 특정 숫자에 대해서 각 자리수가 변경된다는건 알았는데 막상 그걸 구현하는 과정에서 엄청 고전했다.
 * 뭘 해야될진 알겠는데 막상 코드로 어떻게 옮겨야될지 전 굽다가 시간을 엄청 오래썼다.
 * 
 * <중요 포인트>
 * 최대 20!까지 나오기때문에 그냥 int로 하면 overflow생기기 때문에 long으로 선언해줘야하고
 * str to long을 하기위해선 Long.parseLong(str) 해줘야한다
 * 안하면 NumberformatException 뜬다
 * 
 * <1번 구현>
 * 1. 각 자리수 모두 하나씩 봐야하니까 for n번
 * 2. for n번 안에서 1,2,...,n에 대해서 검사를 해줘야하는데 사전순으로 1,2,...,n로 탐색 해야되기 때문에 for 1~n
 * 
 * => 따라서 2중 반복문 안에서 n칸의 각 자리만큼 1~n까지 이 자리에 이 숫자가 들어가도 되는지 확인을 해주면 된다.
 * 
 * visited 방문 배열 하나둬서 숫자를 썼는지 안썻는지 판단해주고
 * dp배열은 뒤에서 부터 탐색해야 순열의 첫 자리부터 탐색하기 때문에 dp[n-i-1]로 검사한다.
 * 
 * if(dp[n-i-1] < k)라는 말은 현재 2번째 for문의 index(순열에 넣을값)가 아닌 다른 숫자가 들어가야된다는 의미임(그림 그려보니까 이해됨)
 * ex) dp[3] = 6이고 k가 8이면 첫번째 자리에 1가 들어가는게 아닌 다른 숫자가 들어가야됨
 * k -= dp[n-i-1]를 하면서 dp[n-i-1] 순서만큼 건너뛴다
 * 
 * if(dp[n-i-1] > k)가 되면 숫자를 넣을 수 있다는 뜻이므로 2번째 for문의 index(순열의 넣을값)을 넣어주고 방문처리해준다
 * 
 * <2번 구현>
 * 예제 1 3 2 4를 보면
 * 
 * 첫번째 자리에 1을 사용했다는건 <1번 구현> 에서 k -= dp[n-i-1]라는 과정이 없이 이루어졌다는 뜻이고
 * 두번째 자리에 3을 사용했다는건 1은 방문처리가 되있으니 넘어가고 2을 사용하려는 과정에서 k -= dp[n-i-1]를 했기 때문에 <2번 구현>에 대한 답은 k += dp[n-i-1] 가 되야한다
 * 나머지 2,4는 순차적으로 방문을 해주기 때문에 k += dp[n-i-1]라는 과정이 이루어지지 않았다.
 * 
 * 
 * 결국 dp배열에 대해서 얼마나 잘 이해하고 활용할 수 있었는지 판단하는 문제였다. 
 * 
 * 
 * */


public class Main_1722 {
	static int n;
	static int[] data;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n+1];
		data = new int[n];

		long[] dp = new long[n+1];
		dp[0] = 1;
		for (int i = 1; i < n+1; i++) {
			dp[i] = dp[i-1] * i;
		}
		
		
		String[] input = br.readLine().split(" ");
		int kind = Integer.parseInt(input[0]);
		
		if (kind==1) {
			long k = Long.parseLong(input[1]);
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < n+1; j++) {
					if (visited[j])
						continue;
					if(dp[n-i-1] < k)
						k -= dp[n-i-1];
					else {
						data[i] = j;
						visited[j] = true;
						break;
					}
				}
			}
			
			
			for (int i = 0; i < n; i++) {
				System.out.print(data[i]+" ");
			}
		}
		else {
			for (int i = 0; i < n; i++) {
				data[i] = Integer.parseInt(input[i+1]);
			}
			
			long res = 1;
			
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < data[i]; j++) {
					if(visited[j])
						continue;
					res += dp[n-i-1];
				}
				visited[data[i]] = true;
			}
			System.out.println(res);
		}
	
	}

}
