package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;


/* 걸린시간 : 1시간 30분
 * 
 * 문자열로 된 식을 파싱하는 과정이 개인적으로 너무 힘들었다.
 * 천천히 차근차근 필요한 것들을 구현하다보니 풀 수 있었다.
 * 
 * replace로 공백을 지워주면 원하는 식이 나올텐데 List를 이용해서 연산을 해주었다.
 * 
 * */


public class Main_7490 {
	static char[] operator = {' ','+','-'};
	static int[] data;
	static int n;
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			n = Integer.parseInt(br.readLine());
			data = new int[n];
			
			for (int j = 0; j < n; j++) {
				data[j] = j+1;
			}

			char[] oper_list = new char[n-1];
			dfs(0,oper_list);
			System.out.println();
						
		}
		
		
		
	}
	
	
	public static void dfs(int depth, char[] oper_list) {
		if (depth == n-1) {
			if (check_sum(oper_list) == 0) {
				for (int i = 0; i < n-1; i++) {
					System.out.print(data[i]);
					System.out.print(oper_list[i]);
				}
				System.out.println(data[n-1]);
			}
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			oper_list[depth] = operator[i];
			dfs(depth+1,oper_list);
		}	
	}
	
	public static int check_sum(char[] oper_list) {
		
		
		String str = "";
		
		for (int i = 0; i < n-1; i++) {
			str += Integer.toString(data[i]);
			str += Character.toString(oper_list[i]);
		}
		str += Integer.toString(data[n-1]);
		
		str = str.replace(" ", "");


		
		LinkedList<Integer> numList = new LinkedList<Integer>();
        LinkedList<Character> opList = new LinkedList<Character>(); 
		
		String num = ""; 
        
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i); 
            
            if(ch == '+' || ch =='-') {
                numList.add(Integer.parseInt(num)); 
                opList.add(ch);
                num = "";                 
            }
            else
	            num += ch; 
        }
        numList.add(Integer.parseInt(num)); 

        while(!opList.isEmpty()) { 
            int prevNum = numList.poll();
            int nextNum = numList.poll();
            char op = opList.poll();
            
            if(op == '+') {
                numList.addFirst(prevNum + nextNum); 
            } else if(op == '-') {
                numList.addFirst(prevNum - nextNum);
            } 
        }
        
        
		return numList.poll();
	}

}
