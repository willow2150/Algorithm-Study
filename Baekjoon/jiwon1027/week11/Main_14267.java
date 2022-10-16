package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14267 {
    static int N,M;
    static int[] relation,data;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        relation = new int[N+1];
        list = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            relation[i] = Integer.parseInt(st.nextToken());
        }


        //상사 -> 부하 연결
        for (int i = 1; i < N+1; i++) {
            if (relation[i] != -1)
                list[relation[i]].add(i);
        }

        data = new int[N+1];

        
        //사원들 칭찬 데이터 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()); //부하
            int value = Integer.parseInt(st.nextToken()); //데이터

            data[idx] += value;
        }

        dfs(1,0);


        for (int i = 1; i < N+1; i++) {
            System.out.print(data[i]+" ");
        }
    }

    public static void dfs(int depth, int value){

        data[depth] += value;

        for(Integer idx : list[depth])
            dfs(idx, data[depth]);

    }
    
    
}
