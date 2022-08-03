import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		char[] data = br.readLine().toCharArray();
		
		
		for (int i=0, back =n-1; i < n/2; i++, back--) {
			if (data[i] == '?') {
				if (data[back] == '?') {
					data[i] = 'a';
					data[back]='a';
				}
				
				else
					data[i] = data[back];
			}
			else {
				if (data[back]=='?') 
					data[back] = data[i];
				
			}

		}
		if (n%2==1) {
			if (data[n/2] == '?')
				data[n/2] = 'a';
		}
			
		
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]);
		}
		

		
		
	}

}
