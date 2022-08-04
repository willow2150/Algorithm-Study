import java.io.*;
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String st = br.readLine();
		char[] ch = new char[n];
		char[] originCh = new char[n];
		
		for(int i=0;i<n;i++) {
			originCh[i] = st.charAt(i);
		}
		for(int i=0;i<n;i++) {
			char c = originCh[i];
			if(c != '?') {
				ch[i] = c;
				ch[n-i-1] = c;
				originCh[n-i-1] = c;
			} else {
				ch[i] = 'a';
			}

		}
		for(int j=0;j<n;j++) {
			System.out.print(ch[j]);
		}
	}

}
