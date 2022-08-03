

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//list get add확실하게 배움
//pair해서 sort()했는데 compareto 저거 어케 하는지 모르겠음

public class Main{
	static List<List<Integer>> board = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());


		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			board.add(temp);
		}
		

		int res = 0;
		for (; res < 101; res++) {
			if ((r <= board.size()) && (c <= board.get(0).size()) && board.get(r-1).get(c-1) == k) {
				System.out.println(res);
				break;
			}

			if (board.size() >= board.get(0).size()) {
				row_sort();
				//System.out.println("1."+board.toString());
			}
			else {
				rotation_90();
				//System.out.println("2."+board.toString());
				row_sort();
				//System.out.println("3."+board.toString());
				rotation_reverse_90();
				//System.out.println("4."+board.toString());
			}
			
		}

		if (res > 100)
			System.out.println(-1);

	}

	public static void rotation_90() {
		int n = board.size();
		int m = board.get(0).size();
		List<List<Integer>> return_board = new ArrayList<>();

		for (int i = 0; i < m; i++) { 
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				temp.add(board.get(j).get(i));
			}
			return_board.add(temp);
		}
		
		board = return_board;
	}

	public static void rotation_reverse_90() {
		int n = board.size();
		int m = board.get(0).size();
		List<List<Integer>> return_board = new ArrayList<>();

		for (int i = 0; i < m; i++) { 
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				temp.add(board.get(j).get(i));
			}
			return_board.add(temp);
		}
		
		board = return_board;
	}
	
	
	public static void row_sort() {
		/* 1. row 읽어서 countint() 해야됨(0빼고)
		 * 2. [num, count(num)] 형태를 lambda(x[1],x[0])로 정렬
		 * 3. 정렬한 row를 새로운 1차원 list에 add(100개 넘어가면 break)
		 * 4. max_len = max(max_len,list.size())
		 * 
		 * for문 끝나면 max_len가지고 기존 board 확장
		 * */
		int max_len = 0;
		List<List<Integer>> temp = new ArrayList<>();

		for(List<Integer> row :board) {
			
			//1. dp배열 만들어서 counting 했음
			int[] dp = new int [101];
			for(int x:row) {
				dp[x] += 1;
			}
			List<Pair> pair = new ArrayList<>(); //tuple 자료형하고 싶어서 따로 Pair 클래스만듬
			for(int x=1;x<101;x++) {
				if (dp[x]>0)
					pair.add(new Pair(x,dp[x]));
			}
			//2. 이거 정렬해야됨
			Collections.sort(pair);
			
			List<Integer> tmp = new ArrayList<>();
			for (int i = 0; i < pair.size() && i<101; i++) {
				tmp.add(pair.get(i).num);
				tmp.add(pair.get(i).num_cnt);
			}
			
			max_len = Math.max(max_len, tmp.size());
			temp.add(tmp);
		}
		for (List<Integer> row:temp) {
			for (int i = row.size(); i < max_len; i++) {
				row.add(0);
			}
		}
		
		board = temp;
		
		
	}

}

class Pair implements Comparable<Pair>{
	int num;
	int num_cnt;
	
	public Pair(int num, int num_cnt) {
		super();
		this.num = num;
		this.num_cnt = num_cnt;
	}
	
	   @Override
	   public int compareTo(Pair p) {
	      if(this.num_cnt == p.num_cnt)
	         return Integer.compare(this.num, p.num);
	      else
	         return Integer.compare(this.num_cnt, p.num_cnt);
	   }

	

}
