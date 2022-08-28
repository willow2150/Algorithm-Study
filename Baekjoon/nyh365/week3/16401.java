import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken());
      int[] snack = new int[N];
      int result = 0;
      st = new StringTokenizer(br.readLine());
      for(int i = 0; i < N; i++) {
    	  snack[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(snack);
      int start = 1;
      int end = snack[N - 1];
      
      while(start <= end) {  
    	  int mid = (start + end) / 2;
    	  if(isOk(snack, M, mid)) {
    		  start = mid + 1;
    		  result = Math.max(result, mid);
    	  }
    	  else {
    		 end = mid - 1;
    	  } 
      }
      
      System.out.println(result);
    }
    public static boolean isOk(int[] snack, int M, int target) {
    	int count = 0;
    	for(int i = 0; i < snack.length; i++) {
    		count += snack[i] / target;
    	}
    	if(count >= M) return true;
    	else return false;
    }
}
