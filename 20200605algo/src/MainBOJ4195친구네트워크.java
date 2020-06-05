import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainBOJ4195模备匙飘况农 {
	static int T,F;
	static int[] arr, rel;
	static HashMap<String, Integer> hash;
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		while(T-->0) {
			hash = new HashMap<>();
			arr = new int[200001];
			rel = new int[200001];
			F = Integer.parseInt(br.readLine());
			for(int i=1;i<=200000;i++) {
				arr[i] = i;
				rel[i] = 1;
			}
			int idx = 1;
			for(int i=0;i<F;i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				if(!hash.containsKey(a)) {
					hash.put(a, idx++);
				}
				if(!hash.containsKey(b)) {
					hash.put(b, idx++);
				}
				int n1 = hash.get(a);
				int n2 = hash.get(b);				
				unionSet(n1, n2);
			}
		}
	}
	private static void unionSet(int u, int v) {
		u = findSet(u);
		v = findSet(v);
		if(u==v) {
			System.out.println(rel[u]);
			return;
		}
		arr[u] = v;
		rel[v]+=rel[u];
		System.out.println(rel[v]);
	}
	private static int findSet(int u) {
		if(u==arr[u]) {
			return u;
		}
		return arr[u] = findSet(arr[u]);
	}
}
