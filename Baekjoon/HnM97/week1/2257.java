import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ch = br.readLine();
        int[] stack = new int[100];
        int sum = 0;
        int temp=0;
        int openCnt = 0;
        
        for(int i=0;i<ch.length();i++) {
        	char c = ch.charAt(i);
        	if(c=='(') {
        		stack[++openCnt] = 0;
        	} else if(c=='H') {
        		temp = 1;
        		stack[openCnt] += 1;
        	} else if(c=='C') {
        		temp = 12;
        		stack[openCnt] += 12;
        	} else if(c=='O') {
        		temp = 16;
        		stack[openCnt] += 16;
        	} else if(c>'1' && c <='9') {
        		temp *= c - '1';
        		stack[openCnt] += temp;
        	} else if (c==')') {
        		temp = stack[openCnt--];
        		stack[openCnt] += temp;
        	}

        }

        System.out.println(stack[0]);
    }
}