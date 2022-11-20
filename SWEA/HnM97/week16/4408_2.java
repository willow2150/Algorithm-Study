package s4408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            int[] sList = new int[402];
            int[] corridor = new int[201];
            int maxRoom = 0;
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                start = roomToCor(start);
                end = roomToCor(end);
                if(start <= end){
                    for(int j=start;j<=end;j++){
                        corridor[j] ++;
                    }
                } else{
                    for(int j=end;j<=start;j++){
                        corridor[j] ++;
                    }
                }

            }
            int maxCor = 0;
            for(int i=1;i<=200;i++){
                maxCor = Math.max(maxCor, corridor[i]);
            }


            sb.append("#" + t).append(" "+ maxCor).append("\n");
        }
        System.out.println(sb.toString());
    }


    private static int roomToCor(int roomNumber){
        if(roomNumber % 2 ==0 ){
            return roomNumber / 2;
        } else{
            return roomNumber / 2+1;
        }
    }

}
