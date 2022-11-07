package pratice;

import java.util.*;
import java.io.*;

/*
*  본사가 모든 지사와 직접 연결되어 있으므로, 각 지사노드는 고장나도 간접연결가능
* 따라서 본사와 직접연결을 제외하고, 각 지사를 최소비용 최소스패닝트리로 묶음
*
* */
public class Main_2406{
    static int[] parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]), m = Integer.parseInt(input[1]);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        parent = new int[n+1];
        for(int i=0; i<=n; i++) parent[i] = i;
        StringBuilder sb = new StringBuilder();
        int edgeCnt = 0;



        for(int i=0; i<m; i++){
            String[] directly = br.readLine().split(" ");
            int x = Integer.parseInt(directly[0]), y = Integer.parseInt(directly[1]);
            //UNION X, Y
            int xRoot = find(x), yRoot = find(y);
            if(xRoot!=yRoot) parent[xRoot] = y;
            edgeCnt++;
        }
        //간선을 정렬
        for(int i=1; i<=n; i++){
            String[] edgeReadLine = br.readLine().split(" ");
            for(int j=1; j<=n; j++){
                if(i==1 || j==1 || j<=i || find(i)==find(j)) continue;
                int t = Integer.parseInt(edgeReadLine[j-1]);
                if(t!=0) pq.add(new Edge(i, j, t));
            }
        }
        //크루스칼
        int sum = 0, cnt = 0;
        while(!pq.isEmpty()){
            Edge k = pq.poll();
            int x = k.start, y = k.end;
            //UNION X, Y
            int xRoot = find(x), yRoot = find(y);
            if(xRoot!=yRoot) {
                parent[xRoot] = y;
                sum += k.weight;
                sb.append(k.start);
                sb.append(" ");
                sb.append(k.end);
                sb.append("\n");
                cnt++;
                edgeCnt++;
            }
        }

        if(cnt==0) System.out.println("0 0");
        else{
            System.out.println(sum+" "+cnt);
            System.out.print(sb.toString());
        }

        pq.clear();

    }

    public static int find(int a){
        if(a==parent[a]) return a;
        parent[a] = find(parent[a]);
        return parent[a];
    }

    public static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        Edge(int s, int e, int w){
            this.start = s;
            this.end = e;
            this.weight = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}