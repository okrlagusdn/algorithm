import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ17471게리맨더링 {
	static int N, min = Integer.MAX_VALUE;
	static int[] num, par;//사람수 담는 리스트
	static ArrayList<Integer>[] list;//각도시 연결관계 담고있는 배열리스트
	static ArrayList<Integer> alist = new ArrayList<>();//선거구 1
	static ArrayList<Integer> blist;//선거구2, 매번 초기화해줘야함.
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		num = new int[N+1];
		par = new int[N+1];
		list = new ArrayList[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
			for(int j=0;j<k;j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		for(int i=1;i<N;i++) {
			visited = new boolean[N+1];
			dfs(1,i);
		}
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			int result = 0;
			int flag=0;
			int a = 0;
			int b = 0;
			for(int i=1;i<=N;i++) {
				par[i] = i;
			}
//			System.out.println(alist);
			blist = new ArrayList<>();
			for(int i=1;i<=N;i++) {
				if(!visited[i]) {
					blist.add(i);
				}
			}
			for(int i=0;i<alist.size();i++) {
				for(int j=0;j<list[alist.get(i)].size();j++) {
					if(visited[list[alist.get(i)].get(j)]) {
						int u = alist.get(i);
						int v = list[alist.get(i)].get(j);
						UnionSet(u,v);
					}
				}
			}
			for(int i=0;i<blist.size();i++) {
				for(int j=0;j<list[blist.get(i)].size();j++) {
					if(!visited[list[blist.get(i)].get(j)]) {
						int u = blist.get(i);
						int v = list[blist.get(i)].get(j);
						UnionSet(u,v);
					}
				}
			}
//			System.out.println(alist);
//			System.out.print(par[alist.get(0)]+" ");
			for(int i=1;i<alist.size();i++) {
//				System.out.print(par[alist.get(i)]+" ");
				if(par[alist.get(0)]!=par[alist.get(i)]) {
					flag=1;
				}
			}
//			System.out.println();
//			System.out.println(blist);
//			System.out.print(par[blist.get(0)]+" ");
			for(int i=1;i<blist.size();i++) {
//				System.out.print(par[blist.get(i)]+" ");
				if(par[blist.get(0)]!=par[blist.get(i)]) {
					flag=1;
				}
			}
//			System.out.println();
			if(flag==0) {
				for(int i=0;i<alist.size();i++) {
					a+=num[alist.get(i)];
				}
				for(int i=0;i<blist.size();i++) {
					b+=num[blist.get(i)];
				}
				result = Math.abs(a-b);
				if(min>result) {
					min = result;
				}
			}
			return;
		}
		for(int i=start;i<=N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				alist.add(i);
				dfs(i+1, r-1);
				alist.remove(alist.size()-1);
				visited[i] = false;
			}
		}
	}
	private static void UnionSet(int u, int v) {
		u = findSet(u);
		v = findSet(v);
		if(u==v) {
			return;
		}
		par[u] = v;
	}
	private static int findSet(int v) {
		if(par[v] == v) {
			return v;
		}
		return par[v] = findSet(par[v]);
	}
}