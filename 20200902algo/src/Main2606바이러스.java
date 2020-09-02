import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2606바이러스 {
	static int N, M, result=0;
	static boolean[] visit;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		visit = new boolean[N+1];
		for(int i=1;i<N+1;i++) {
			list[i] = new ArrayList<Integer>();
		}
		for(int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			list[f].add(s);
			list[s].add(f);
		}
		visit[1] = true;
		dfs(1);
		System.out.println(result);
	}
	private static void dfs(int num) {
		for(int i=0;i<list[num].size();i++) {
			if(!visit[list[num].get(i)]) {
				result++;
				visit[list[num].get(i)] = true;
				dfs(list[num].get(i));
			}
		}
	}
}