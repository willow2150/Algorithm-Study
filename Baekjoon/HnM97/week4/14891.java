import java.io.*;
import java.util.*;
public class Main {
	static int[][] gears = new int[5][8];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=1;i<=4;i++) {
			String line = br.readLine();
			for(int j=0;j<8;j++) {
				if(line.charAt(j)=='1') gears[i][j]=1;
				else gears[i][j]=0;
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		for(int i=0;i<K;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			rotateAll(gearNum, direction);
		}
		System.out.println(calc());

	}
	public static class Pair{
		int num;
		int direction;
		public Pair(int num, int direction) {
			this.num = num;
			this.direction = direction;
		}
	}
	
	//상:0, 우:2, 하:4, 좌: 6  
	//10101111
	public static void rotateAll(int gearNum, int direction) {
		ArrayList<Pair> pairList = new ArrayList<>();
		pairList.add(new Pair(gearNum,direction));
		// 왼쪽
		int leftDir = direction;
		for(int i=gearNum;i>1;i--) {
			if((gears[i][6] ^ gears[i-1][2]) == 1) {
//				leftDir = ~leftDir;
				leftDir = toggleDir(leftDir);
				pairList.add(new Pair(i-1,leftDir));
			} else {
				break;
			}
		}
		
		//오른쪽
		int rightDir = direction;
		for(int i=gearNum;i<4;i++) {
			if((gears[i+1][6] ^ gears[i][2]) == 1) {
				rightDir = toggleDir(rightDir);
				pairList.add(new Pair(i+1,rightDir));
			} else {
				break;
			}
		}
		// 동시에 돌리기
		for(Pair p:pairList) {
			rotate(p.num, p.direction);
		} 
	}
	public static void rotate(int gearNum, int direction) {
		int temp =0;
		if(direction==1) {
				temp = gears[gearNum][0];
				gears[gearNum][0] =gears[gearNum][7];
				for(int i=6;i>=0;i--) {
					gears[gearNum][i+1] = gears[gearNum][i];
				}
				gears[gearNum][1] = temp;
		}
		else {
			temp = gears[gearNum][7];
			gears[gearNum][7] = gears[gearNum][0];
			for(int i=0;i<7;i++) {
				gears[gearNum][i] =gears[gearNum][i+1]; 
			}
			gears[gearNum][6] = temp;
		}
			
	}
	
	public static int calc() {
		int ans=0;
		if(gears[1][0]==1) {
			ans += 1;
		}
		if(gears[2][0]==1) {
			ans += 2;
		}
		if(gears[3][0]==1) {
			ans += 4;
		}
		if(gears[4][0]==1) {
			ans += 8;
		}
		return ans;
	}
	public static int toggleDir(int dir) {
		if(dir==1) return -1;
		else return 1;
	}

}
