import java.io.*;
import java.util.*;

public class Main {
	static int[] result = new int[26], dictionary = new int[26], puzzle = new int[26];
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = "";
		List<String> inputs = new ArrayList<>();
		
		while(!(input = br.readLine()).equals("-")) {
			inputs.add(input);
		}
		
		Arrays.fill(result, -1);
		while(!(input = br.readLine()).equals("#")) {
			for(int i = 0; i < input.length(); i++) {
				puzzle[input.charAt(i) - 'A']++;
				result[input.charAt(i) - 'A'] = 0;
			}
			for(int i = 0; i < inputs.size(); i++) {
				char[] tmp = inputs.get(i).toCharArray();
				for(int j = 0; j < tmp.length; j++) {
					dictionary[tmp[j] - 'A']++;
				}
				if(vaild(tmp)) {
					Set<Character> set = new HashSet<>();
					for(int j = 0; j < tmp.length; j++) {
						set.add(tmp[j]);
					}
					for(char c : set) {
						if(puzzle[c - 'A'] > 0)
							result[c -'A']++;
					}
				}
				dictionaryInit();
			}
			puzzleInit();
			
			int[] tmp = new int[26];
			for(int i = 0; i < 26; i++) {
				tmp[i] = result[i];
			}
			Arrays.sort(tmp);
			int min_count = -1;
			for(int i = 0; i < 26; i++) {
				if(tmp[i] != -1) {
					min_count = tmp[i];
					break;
				}
			}
			int max_count = tmp[25];
			
			for(int i = 0; i < 26; i++) {
				if(result[i] == min_count) {
					sb.append((char)(i+'A'));
				}
			}
			sb.append(" ").append(min_count).append(" ");
			
			for(int i = 0; i < 26; i++) {
				if(result[i] == max_count) {
					sb.append((char)(i+'A'));
				}
			}
			sb.append(" ").append(max_count).append("\n");
			resultInit();
		}
		
		System.out.println(sb);
	}
  //주어진 단어가 만들 수 있는 단어인지 판별
	public static boolean vaild(char[] tmp) {
		for(int i = 0; i < tmp.length; i++) {
			if(dictionary[tmp[i] - 'A'] > puzzle[tmp[i] - 'A']) {
				return false;
			}
		}
		return true;
	}
  //dictionary 배열 초기화
	public static void dictionaryInit() {
		for(int i = 0; i < 26; i++) {
			dictionary[i] = 0;
		}
	}
  //puzzle배열 초기화
	public static void puzzleInit() {
		for(int i = 0; i < 26; i++) {
			puzzle[i] = 0;
		}
	}
  //result배열 
	public static void resultInit() {
		Arrays.fill(result, -1);
	}
}
