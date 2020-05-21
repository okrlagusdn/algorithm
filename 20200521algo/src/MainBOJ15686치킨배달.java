import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ15686치킨배달 {
	static int N, M, dis;
	static int[][] arr;
	static int min = Integer.MAX_VALUE;
	static boolean[] visited;
	static public class SPoint{
		int x;
		int y;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<SPoint> hlist = new ArrayList<>();
	static ArrayList<SPoint> clist = new ArrayList<>();
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==1) {
					hlist.add(new SPoint(i, j));
				}else if(arr[i][j]==2) {
					clist.add(new SPoint(i, j));
				}
			}
		}
		for(int i=1;i<=M;i++) {
			visited = new boolean[clist.size()];
			list = new ArrayList<>();
			dfs(0,i);
		}
		System.out.println(min);
	}
	private static void dfs(int start, int r) {
		if(r==0) {
//			System.out.println(list);
			int cdis = 0;
			for(int i=0;i<hlist.size();i++) {
				int min2 = Integer.MAX_VALUE;
				dis = 0;
				for(int j=0;j<list.size();j++) {
					dis = Math.abs(hlist.get(i).x-clist.get(list.get(j)).x)+Math.abs(hlist.get(i).y-clist.get(list.get(j)).y);
					if(min2>dis) {
						min2 = dis;
					}
				}
				cdis+=min2;
			}
			if(min>cdis) {
				min=cdis;
			}
			return;
		}
		for(int i=start;i<clist.size();i++) {
			if(!visited[i]) {
				list.add(i);
				visited[i] = true;
				dfs(i+1, r-1);
				list.remove(list.size()-1);
				visited[i] = false;
			}
		}
	}
}
