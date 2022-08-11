import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] light = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < light.length; i++)
			light[i] = Integer.parseInt(st.nextToken());
		
    //터널의 시작부분~맨 처음 가로등 사이의 거리, 마지막 가로등~터널의 마지막 부분의 거리 중 큰 값을 저장
		int result = Math.max(light[0], N - light[M - 1]);
		
    // 가로등 간의 거리 중 차이가 가장 큰 것을 찾고 1/2을 한다.
		for(int i = 1; i < light.length; i++)
			result = Math.max(result, (light[i] - light[i-1] + 1) / 2);
		
		System.out.println(result);
	}
}
