package s4408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution1 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            int[] sList = new int[402];
            int maxRoom = 0;
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                sList[start] = end;
                sList[end] = start;
                maxRoom = Math.max(maxRoom,start);
                maxRoom = Math.max(maxRoom,end);
            }
            maxRoom = roomToCor(maxRoom);
//            System.out.println(Arrays.toString(sList));
            int returned = 0;
            int time = 0;
            while(returned < N){
                int cur = 1;
                int start = 1;
                int end = 1;
                boolean[] corridor = new boolean[201];

                while(cur<=maxRoom){
                    boolean flag = false;
                    int odd = cur*2 - 1;
                    int even = cur*2;
                    if(sList[odd] != 0){
                        // 오른쪽으로 가는 경우
                        if(sList[odd] > odd){
                            end = roomToCor(sList[odd]);
                            if(check(cur, end, corridor)) {
                                for (int i = cur; i <= end; i++) {
                                    corridor[i] = true;
                                }
                                returned ++;
                                System.out.println(odd + " "+ sList[odd]);
                                sList[sList[odd]] = 0;
                                sList[odd] = 0;
                                cur = end + 1;
                                continue;
                            } else{
                                flag = true;
                            }
                        }
                        // 왼쪽으로 가는 경우
                        else {
                            start = roomToCor(sList[odd]);
                            if(check(start, cur, corridor)) {
                                for (int i = start; i <= cur; i++) {
                                    corridor[i] = true;
                                }
                                returned ++;
                                System.out.println(odd + " "+ sList[odd]);
                                sList[sList[odd]] = 0;
                                sList[odd] = 0;
                                cur ++ ;
                                continue;
                            } else{
                                flag = true;
                            }
                        }
                    }
                    if(sList[even] != 0) {
                        // 오른쪽으로 가는 경우
                        if (sList[even] > even) {
                            end = roomToCor(sList[even]);
                            if (check(cur, end, corridor)) {
                                for (int i = cur; i <= end; i++) {
                                    corridor[i] = true;
                                }
                                returned++;
                                System.out.println(even+ " "+ sList[even]);
                                sList[sList[even]] = 0;
                                sList[even] = 0;

                                if(flag==true) flag = false;
                                cur = end + 1;
                            } else{
                                flag = true;
                            }
                        }
                        // 왼쪽으로 가는 경우
                        else {
                            start = roomToCor(sList[even]);
                            if (check(start, cur, corridor)) {
                                for (int i = start; i <= cur; i++) {
                                    corridor[i] = true;
                                }
                                returned ++;
                                System.out.println(even+ " "+ sList[even]);
                                sList[sList[even]] = 0;
                                sList[even] = 0;
                                if(flag==true) flag = false;
                                cur ++ ;
                            } else{
                                flag = true;
                            }
                        }
                    }
                    else{
                        cur ++;
                    }
                    if(flag==true){
                        cur++;
                    }
                }
                time ++;
                System.out.println("--------------");
            }
            sb.append("#" + t).append(" "+ time).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean check(int start, int end, boolean[] corridor) {
//        int rs = roomToCor(start);
//        int re = roomToCor(end);
        for(int i=start;i<=end;i++){
            if(corridor[i]==true) return false;
        }
        return true;
    }

    private static int roomToCor(int roomNumber){
        if(roomNumber % 2 ==0 ){
            return roomNumber / 2;
        } else{
            return roomNumber / 2+1;
        }
    }

}
