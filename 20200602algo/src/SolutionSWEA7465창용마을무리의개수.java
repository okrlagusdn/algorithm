import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SolutionSWEA7465창용마을무리의개수 {
	static int T,N,M;
	static int[] arr;
	static HashSet<Integer> hash;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			hash = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N+1];
			for(int i=1;i<=N;i++) {
				arr[i] = i;
			}
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				uionSet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			for(int i=1;i<=N;i++) {
				findSet(i);
			}
			for(int i=1;i<=N;i++) {
				hash.add(arr[i]);
			}
			System.out.println("#"+tc+" "+hash.size());
		}
	}
	private static void uionSet(int u, int v) {
		u = findSet(u);
		v = findSet(v);
		if(u==v) {
			return;
		}
		arr[u] = v;
	}
	private static int findSet(int u) {
		if(u==arr[u]) {
			return u;
		}
		return arr[u] = findSet(arr[u]);
	}
}