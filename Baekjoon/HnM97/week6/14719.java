package b14719;
import java.io.*;
import java.util.*;
public class Main {
	public static int sum=0;
	public static int[] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		map = new int[W];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<W;i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;

		while(start < W) {
			// 시작 위치 찾기
			for(int i=start;i<W;i++) {
				if(map[i] > 0 ) {
					start = i;
					break;
				}

			}
			// 1. 시작 위치의 높이부터 1씩 감소하면서 고인 물이 생기는지 확인
			// 2. flag는 고인 물이 있었다는 뜻
			// 3. flag == false 이면 못찾았다는 뜻이므로 curHeight을 1 감소
			// 4. 모든 높이를 확인했는데도 flag가 false이면 현재 위치(start) 이후로 고일 수 없다는 뜻
			// 5. 4번의 경우 start++ 를 통해 다음 위치 확인, 끝에 다다르면 while을 빠져나가게 됨
			
			int curHeight = map[start];
			boolean flag = false;
			while(curHeight >= 0) {
				for(int i=start+1;i<W;i++) {
					if(map[i] >= curHeight) {
						calc(start,i,curHeight);
						start = i;
						flag = true;
						break;
					}
				}
				if(flag) break;
				else curHeight--;
			}
			if(!flag) start++;

		}
		System.out.println(sum);

	}
	
	// start로부터 end 까지 height만큼의 웅덩이가 얼마나 생기는지 계산 
	public static void calc(int start, int end,int height) {
		int rains=0;
		for(int i=start+1;i<end;i++) {
			if(height > map[i])
				rains += height - map[i];
		}
		sum += rains;
	}

}
