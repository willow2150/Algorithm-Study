package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 걸린시간 : 1시간 30분
 * 
 * 문제자체는 어렵지 않았는데 어떤 자료구조를 써야되는지 확실하게 정하지 못하고 고민을 너무 많이 하다가 시간을 많이 썼다.
 * 원형 돌리기를 만나면 자꾸 python의 deque.rotate()를 먼저 생각하는 버릇이 있어서 Java에서 Collections.rotate()를
 * 쓰려는 버릇이 있는데 고쳐야겠다고 생각이 들었다.
 * 
 * ----유의해야할점----
 * robot은 n-1에서 무조껀 빠지기 때문에 robot을 관리하는 배열의 길이는 n까지면 된다
 * 
 * 회전을 시킬 때 index를 앞에서가 아니라 뒤에서 부터 접근해야한다
 * why?)
 * 우리는 belt,robot을 시계방향으로 돌릴껀데 for문도 시계방향(0 to 2*n)으로 돌리면 문제가 생김
 * ex) i=0에 있는 로봇을 i=1로 옮김(이 로봇은 더이상 움직이면 안됨)
 * 		근데 다음 for문의 index가 i=1이라서 i=0 to i=1로 이동한 로봇에 대해서 또 움직이려함
 * so, for문을 뒤에서 부터 돌려 회전 시켜야됨 ==> 맨날 rotate만 쓰다보니 이 부분 구현하는데 시간을 오래씀
 * */


public class Main_20055 {
	static int N,K;
	static int[] belt;
	static boolean[] robot;
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new int[2*N];
		robot = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2*N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int res = 1;
		
		
		
		while (true) {
			//1.회전
			rotate();

			//2.이동
			move();

			//3.로봇 올리기
			if (!robot[0] && belt[0]>0) {
				robot[0] = true;
				belt[0]--;
			}
			
			//4.내구도 0개 검사
			if (endCheck()) {
				System.out.println(res);
				break;
			}
			res++;


			
		}
		
		
		
	}
	
	public static void rotate() {
		int temp = belt[belt.length-1];
		for (int i = belt.length-2; i > -1; i--) {
			belt[i+1] = belt[i];
		}
		belt[0] = temp;
		
		
		for (int i = N-2; i > -1; i--) {
			robot[i+1] = robot[i];
		}
		robot[0] = false;
	}
	
	public static void move() {
		//내리는 위치는 그냥 바로 내려야됨
		if(robot[N-1])
			robot[N-1] = false;
		
		//벨트랑 로봇 이동
		//이거 i=0부터 하면 0->1 정상적으로 이동했는데 다음 for문에서 i=1에 있는애 또 이동시키려고 하니까 뒤에서부터함
		for (int i = N-2; i > 0; i--) {
			if(robot[i] && !robot[i+1] && belt[i+1]>0) {
				robot[i] = false;
				robot[i+1] = true;
				belt[i+1]--;
			}
		}
	}
	
	public static boolean endCheck() {
		int cnt = 0;
		for (int i = 0; i < belt.length; i++) {
			if(belt[i] == 0)
				cnt++;
			if (cnt==K)
				return true;
		}
		return false;
		
		
	}

}
