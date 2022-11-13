package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_10507 {
    static boolean[] data;
    static int N,P,max_value,res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            res = P+1;
            data = new boolean[1000001];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                data[x] = true;
                max_value = Math.max(max_value, x);
            }
            fun();
            System.out.println("#"+tc+" "+res);


        }
    }

    public static void fun() {
        int start = 1;
        int end = 1;
        int length = 0;

        while(end<max_value+1) {
            if(data[end]) {
                length++;
                end++;
                res=Math.max(res, length);
            }
            else {
                if(P==0) { //기회가 없어서 start 증가시키면서 나가야됨
                    res=Math.max(res, length);
                    if(!data[start])
                        P++;
                    start++;
                    length--;
                }
                else {
                    P--;
                    length++;
                    end++;
                }
            }
        }
    }

}
