import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
* 3.1 ~ 11.30까지 꽃이 계속 피어있게 하고 싶을 때 최소 꽃을 몇개 선택해야되나 범위가 100,000이고
* 약간 start, end 시간 느낌 보니까 회의실 배정 느낌이 나기 때문에 그리디적으로 접근 해보자
*
* 일단 꽃이 피는 month, day를 기준으로 정렬을 하고 시작해야될듯?
* 근데 이거 끝나는 날짜도 정렬해줘야 될거같은데?
* ==> month, day 나누니까 조건식 너무 힘들어서 그냥 숫자 하나로 묶어버리자.
*
* 암튼 정렬했다고 치면
* 현재 날짜를 기준으로 현재 날짜 보다 작거나 같은 꽃을 선택하고 가장 오래피어 있는 꽃을 선택해야됨(그래야 최소로 꽃을 고르지)
* 그 꽃을 선택하고 start 날짜 갱신한다음에 또 작거나 같은애를 선택하는 식으로 하면 될듯?
*
*
* */

public class Main {
    static int N;
    static int[][] flower;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        flower = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
                int start_month = Integer.parseInt(st.nextToken());
                int start_day = Integer.parseInt(st.nextToken());
                int end_month = Integer.parseInt(st.nextToken());
                int end_day = Integer.parseInt(st.nextToken());

            flower[i][0] = start_month * 100 + start_day;
            flower[i][1] = end_month * 100 + end_day;
        }

        Arrays.sort(flower, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        int end = 1201;
        int start = 301;
        int cnt = 0;
        int idx = 0;
        int temp = 0;

        while(start < end){
            boolean find = false;
            for (int i = idx; i < N; i++) {
                if (flower[i][0] > start)
                    break;

                if (temp < flower[i][1]){
                    find = true;
                    temp = flower[i][1];
                    idx = i+1; //어짜피 정렬 했고 다시 0부터 볼필요없으니까 idx 하나 만들어서 시간 줄이자
                }
            }

            if (find){
                start = temp;
                cnt++;
            }
            else{
                break;
            }

        }

        if(temp < end) {
            System.out.println(0);
        }
        else {
            System.out.println(cnt);
        }
    }
}