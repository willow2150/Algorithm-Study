import java.io.*;
import java.util.*;
public class Main {
	public static class Egg{
		int durability, weight;

		public Egg(int durability, int weight) {
			super();
			this.durability = durability;
			this.weight = weight;
		}
		
	}
	public static int N,maxEgg;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Egg[] eggs = new Egg[N];
		maxEgg = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
//		for(Egg e: eggs) {
//			System.out.println(e.durability + " " + e.weight);
//		}
		bt(eggs,0);
		System.out.println(maxEgg);

	}
	
	public static void bt(Egg[] eggs, int cur) {
		int cnt = 0;
		for(Egg e: eggs) {
			if(e.durability <= 0) cnt ++;
		}
		
		if(cur == N || cnt == N-1) {
			maxEgg = Math.max(maxEgg, cnt);
			return;	
		} else if(eggs[cur].durability <= 0) {
			bt(eggs,cur+1);
			return;
		}
		
		Egg[] newEggs = new Egg[N];
		for(int i=0;i<N;i++) {
			newEggs[i] = new Egg(eggs[i].durability, eggs[i].weight);
		}
		
		
		for(int i=0;i<N;i++) {
			if(i==cur || eggs[i].durability<=0) continue;
			// 깨지든 안깨지든 한 번 치면 다음 계란
			Egg e1 = newEggs[cur];
			Egg e2 = newEggs[i];
			e1.durability -= e2.weight;
			e2.durability -= e1.weight;
			bt(newEggs, cur+1);
			for(int j=0;j<N;j++) {
				newEggs[j] = new Egg(eggs[j].durability, eggs[j].weight);
			}
		}
	}

}
