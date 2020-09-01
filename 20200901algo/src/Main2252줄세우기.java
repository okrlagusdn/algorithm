import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2252줄세우기 {
	static int N, M;
	static int[] indegree;
	static ArrayList<Integer>[] list;
	static boolean[] chk;
	static Queue<Integer> q = new LinkedList<Integer>();
//	static Queue<Integer> result = new LinkedList<Integer>();
	static int[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N+1];
		list = new ArrayList[N+1];
		result = new int[N];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		chk = new boolean[N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			indegree[s]++;
			list[f].add(s);
		}
		for(int i=1;i<=N;i++) {
			if(indegree[i]==0) {
				q.add(i);
				chk[i] = true;
			}
		}
		int n = 0;
		while(!q.isEmpty()) {
			int num = q.poll();
//			result.add(num);
			result[n] = num;
			for(int i=0;i<list[num].size();i++) {
				indegree[list[num].get(i)]--;
			}
			for(int i=1;i<=N;i++) {
				if(!chk[i] && indegree[i]==0) {
					q.add(i);
					chk[i] = true;
				}
			}
			n++;
		}
		for(int i=0;i<N;i++) {
			System.out.print(result[i]+" ");
		}
	}
}