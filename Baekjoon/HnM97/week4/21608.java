import java.io.*;
import java.util.*;
public class Main {
	public static class Student{
		int num;
		int[] like;
		public Student(int num, int[] like) {
			this.num = num;
			this.like = like;
		}
		@Override
		public String toString() {
			return "Student [num=" + num + ", like=" + Arrays.toString(like) + "]";
		}

	}
	public static class Point implements Comparable<Point>{
		int x,y;
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}

		@Override
		public int compareTo(Point o) {
			if(this.x==o.x ) {
				return this.y - o.y;
			} else {
				return this.x - o.x;
			}
		}

	}

	static Student[] students;
	static int[] order;
	static int[][] map;
	static int N;
	static int[] dx = {-1,0,0,1}; //상 우 좌 하
	static int[] dy = {0,1,-1,0}; //상 우 좌 하
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		students = new Student[N*N+1];
		
		map = new int[N+1][N+1];
		order = new int[N*N+1];
		for(int i=1;i<=N*N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int std = Integer.parseInt(st.nextToken());
			students[std] = new Student(std, new int[4]);
			order[i] = std;
			for(int j=0;j<4;j++) {
				students[std].like[j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1;i<=N*N;i++) {
			placeStd(students[order[i]].num);
		}
		
		System.out.println(calc());
		
//		System.err.println("=======================");
//		for(int i=1;i<=N;i++) {
//			for(int j=1;j<=N;j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

	}
	public static int calc() {
		int sum =0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				switch(checkLike(i,j,map[i][j])) {
				case 0:
					sum += 0;
					break;
				case 1:
					sum += 1;
					break;
				case 2:
					sum +=10;
					break;
				case 3:
					sum += 100;
					break;
				case 4:
					sum += 1000;
				}
			}
		}
		return sum;
	}
	
	public static void placeStd(int std) {
		ArrayList<Point>[] likeList = new ArrayList[5];
		ArrayList<Point> likeCandidate = null;
		for(int i=0;i<=4;i++) {
			likeList[i] = new ArrayList<>();
		}
		// 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(map[i][j] == 0) {
					likeList[checkLike(i,j,std)].add(new Point(i,j));
				}
			}
		}
		for(int i=4;i>=0;i--) {
			if(likeList[i].size() > 0) {
				likeCandidate = likeList[i];
				break;
			}
		}
		// 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
		int maxEmptyCnt = 0;
		ArrayList<Point> emptyCandidate = new ArrayList<>();
		for(Point p : likeCandidate) {
			int tempEmptyCnt = checkEmpty(p.x, p.y);
			if(maxEmptyCnt < tempEmptyCnt) {
				maxEmptyCnt = tempEmptyCnt;
				emptyCandidate.clear();
				emptyCandidate.add(p);
			} else if(maxEmptyCnt==tempEmptyCnt) {
				emptyCandidate.add(p);
			}
		}
		PriorityQueue<Point> pq = new PriorityQueue<Main.Point>();
		// 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
		for(Point p:emptyCandidate) {
			pq.add(p);
		}
		Point place = pq.poll();
		map[place.x][place.y]= std; 

	}

	public static int checkLike(int x, int y,int std) {
		int cnt =0;
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx>=1 && ny>=1 && nx<=N && ny <= N && map[nx][ny] != 0) {
				for(int k=0;k<4;k++) {
					if(students[std].like[k] == map[nx][ny]) {
						cnt ++;
					}
				}
			}
		}
		return cnt;
	}
	public static int checkEmpty(int x, int y) {
		int cnt =0;
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx>=1 && ny>=1 && nx<=N && ny <= N) {
				if(map[nx][ny] == 0) cnt ++;
			}
		}
		return cnt;
	}

}