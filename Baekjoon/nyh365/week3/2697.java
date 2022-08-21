import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for(int test_case = 1; test_case <= T; test_case++) { 
    	  String input = br.readLine();
    
    	  int i = input.length() - 1;
    	  while(i > 0 && input.charAt(i - 1) >= input.charAt(i)) --i;
    	  if(i == 0) sb.append("BIGGEST");
    	  else {
    		  sb.append(input.substring(0,i -1));
    		  int[] num = new int[10];
        	  for(int index = i - 1; index < input.length(); index++){
        		  num[input.charAt(index) -'0']++;
        	  }
        	  
        	  for(int index = 0; index < num.length; index++){
        		  if(index > input.charAt(i-1)-'0') {
        			  if(num[index] > 0) {
        				  num[index]--;
        				  sb.append(index);
        				  break;
        			  }
        		  }
        	  }
        	  
        	  for(int index = 0; index < num.length; index++){
        		   if(num[index] > 0) {
        			  for(int k = 0; k < num[index]; k++) {
        				  sb.append(index);
        			  }
        		  }
        	  }
    	  }
    	  
    	  sb.append("\n");
      }
      System.out.println(sb);
    }
}
