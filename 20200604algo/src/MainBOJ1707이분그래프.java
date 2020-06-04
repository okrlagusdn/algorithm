import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ1707이분그래프 {
	static int T,V,E;
	static ArrayList<Integer>[] list;
	static int[] co;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			co = new int[V+1];
			list = new ArrayList[V+1];
			for(int i=1;i<=V;i++) {
				list[i] = new ArrayList<>();
				co[i] = 0;
			}
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			}
			for(int i=1;i<=V;i++) {
				if(co[i]==0) {
					dfs(i,1);
				}
			}
			int flag = 1;
			for(int i=1;i<=V;i++) {
				for(int j=0;j<list[i].size();j++) {
					if(co[i]==co[list[i].get(j)]) {
						flag=0;
					}
				}
			}
			if(flag==1) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}
	private static void dfs(int a, int b) {
		co[a] = b;
		for(int i=0;i<list[a].size();i++) {
			if(co[list[a].get(i)]==0) {
				dfs(list[a].get(i),3-b);
			}
		}
	}
}