package pratice;

import java.io.*;
import java.util.*;

public class Main_2492 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m; // n : 지도의 너비, m : 지도의 높이
    static int t, k; // t : 금강석의 개수, k : 정사각형의 크기

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Node[] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new Node[t];

        for(int i = 0; i < t; i++) {
            int a, b;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr[i] = new Node(a, b);
        }

        int ans = 0;
        int ansx = 0;
        int ansy = 0;
        int cnt = 0;

        for(int p = 0; p < t; p++) {
            for(int j = 0; j < t; j++) {
                int x, y;

                if(arr[p].x + k > n)
                    x = n - k;
                else
                    x = arr[p].x;

                if(arr[j].y + k > m)
                    y = m - k;
                else
                    y = arr[j].y;

                cnt = 0;
                for(int i = 0; i < t; i++) {
                    // 해당 사각형 범위 내에 있으면 캘 수 있는 금강석
                    if((x <= arr[i].x && arr[i].x <= x + k) && (y <= arr[i].y && arr[i].y <= y + k))
                        cnt++;
                }
                if (cnt > ans)
                {
                    ans = cnt;
                    ansx = x;
                    ansy = y + k;
                }
            }
        }
        System.out.println(ansx + " " + ansy);
        System.out.println(ans);

    }
}