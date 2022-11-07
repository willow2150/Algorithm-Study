package pratice;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main_1891 {
    static long resultx=0;
    static long resulty=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int d=sc.nextInt();
        String N=sc.nextLine().trim();
        long x=sc.nextLong();//양수 오른쪽
        long y=sc.nextLong();//양수 위쪽

        go(0,(long)Math.pow(2,d)-1,0,d,N,(long) Math.pow(2,d));//N이 현재의 위치를 구한다
        // System.out.print(resultx+","+resulty+"\n");
        if(resultx-y<0||resulty+x<0||resultx-y>=Math.pow(2,d)||resulty+x>=Math.pow(2,d)) {
            System.out.print(-1);
        }else {
            System.out.print(find(resultx-y,resulty+x,(long)Math.pow(2,d),""));//인덱스를 이용하여 그위치의 숫자를 구한다.
        }
    }

    private static String find(long i,long j,long size,String ans) {
        if(size==1) {
            return ans;
        }
        if(i>=size/2) {//3or4사분면
            if(j<size/2) {//3사분면
                ans+='3';
                return find(i-size/2,j,size/2,ans);
            }else{//4사분면
                ans+='4';
                return find(i-size/2,j-size/2,size/2,ans);
            }
        }else {//1or2사분면
            if(j<size/2) {//2사분면
                ans+='2';
                return find(i,j,size/2,ans);
            }else {
                ans+='1';
                return find(i,j-size/2,size/2,ans);
            }
        }
    }

    private static void go(long startx,long starty,int i,long d, String n,long size) {

        if(i>=d) {//현재 인덱스가 마지막 인덱스를 넘어갈때
            resultx=startx;
            resulty=starty;
            return;
        }
        if(n.charAt(i)=='1') {
            go(startx,starty,i+1,d,n,size/2);
        }
        else if(n.charAt(i)=='2') {
            go(startx,starty-size/2,i+1,d,n,size/2);
        }
        else if(n.charAt(i)=='3') {
            go(startx+size/2,starty-size/2,i+1,d,n,size/2);
        }
        else{//4
            go(startx+size/2,starty,i+1,d,n,size/2);
        }



    }


}