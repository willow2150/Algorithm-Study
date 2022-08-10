import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);

		int[][] data = new int[n][4];
		
		for (int i = 0; i < n; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < 4; j++) {
				data[i][j] = Integer.parseInt(input[j]);
			}
			
		}
		
		//System.out.println(Arrays.deepToString(data));

		Arrays.sort(data, new Comparator<int[]>() {
			
			@Override
			public int compare(int[] s1, int[] s2) {
				if (s1[1] == s2[1] && s1[2] == s2[2])
					return s2[3] - s1[3];
				else if (s1[1] == s2[1])
					return s2[2] - s1[2];
				else
					return s2[1] - s1[1];
				
			}
		});
		
		//System.out.println(Arrays.deepToString(data));
		
		int idx = 0;
		
		for (int i = 0; i < n; i++) {
			if (data[i][0] == k)
				idx = i;
		}
		
		
		for (int i = 0; i < n; i++) {
			if (Arrays.equals(Arrays.copyOfRange(data[idx],1,4),
					Arrays.copyOfRange(data[i],1,4))) {
				System.out.println(i+1);
				break;
			}
				
		}
	}

}
