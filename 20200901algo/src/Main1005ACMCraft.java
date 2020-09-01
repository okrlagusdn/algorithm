import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1005ACMCraft {
	static int N,K,W,an=0;
	static int[] time;
	static int[] result;
	static int[] indegree;
	static ArrayList<Integer>[] list;
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for(int i=0;i<TC;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			time = new int[N+1];
			result = new int[N+1];
			indegree = new int[N+1];
			list = new ArrayList[N+1];
			for(int j=1;j<=N;j++) {
				list[j] = new ArrayList<Integer>();
			}
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				time[j] = Integer.parseInt(st.nextToken());
			}
			for(int j=0;j<K;j++) {
				st = new StringTokenizer(br.readLine());
				int f = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				indegree[s]++;
				list[f].add(s);
			}
			W = Integer.parseInt(br.readLine());
			for(int j=1;j<=N;j++) {
				result[j] = time[j];
				if(indegree[j]==0) {
					q.add(j);
				}
			}
			while(!q.isEmpty()) {
				int node = q.poll();
				for(int j=0;j<list[node].size();j++) {
					result[list[node].get(j)] = Math.max(result[list[node].get(j)], result[node]+time[list[node].get(j)]);
					indegree[list[node].get(j)]--;
					if(indegree[list[node].get(j)]==0) {
						q.add(list[node].get(j));
					}
				}
			}
			System.out.println(result[W]);
		}
	}
}