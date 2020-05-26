import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ17142연구소3 {
	static int N,M,min = Integer.MAX_VALUE;
	static int[][] arr, carr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited1;
	static boolean[] visited2;
	static Queue<SPoint> q;
	static public class SPoint{
		int x;
		int y;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<SPoint> list = new ArrayList<>();
	static ArrayList<Integer> blist = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited1 = new boolean[N][N];
		arr = new int[N][N];
		carr = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==2) {
					list.add(new SPoint(i, j));
				}
			}
		}
		visited2 = new boolean[list.size()];
		dfs(0, M);
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);			
		}
	}
	private static void dfs(int start, int r) {
		if(r==0) {
//			System.out.println(blist);
			visited1 = new boolean[N][N];
			q = new LinkedList<>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					carr[i][j] = arr[i][j];
				}
			}
			for(int i=0;i<blist.size();i++) {
				carr[list.get(blist.get(i)).x][list.get(blist.get(i)).y] = 3;
				visited1[list.get(blist.get(i)).x][list.get(blist.get(i)).y]= true; 
				q.add(new SPoint(list.get(blist.get(i)).x, list.get(blist.get(i)).y));
			}
			solve();
			return;
		}
		for(int i=start;i<list.size();i++) {
			if(!visited2[i]) {
				visited2[i] = true;
				blist.add(i);
				dfs(i+1, r-1);
				visited2[i] = false;
				blist.remove(blist.size()-1);
			}
		}
	}
	private static void solve() {
		int result = 0;
		while(!q.isEmpty()) {
			int flag=0;
			outer : for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(carr[i][j]==0) {
						flag=1;
						break outer;
					}
				}
			}
			if(flag==0) {
				break;
			}
			int size = q.size();
			for(int s=0;s<size;s++) {
				SPoint p = q.poll();
				for(int i=0;i<4;i++) {
					int tx = p.x+dx[i];
					int ty = p.y+dy[i];
					if(tx<0 || ty<0 || tx>=N || ty>=N) {
						continue;
					}
					if(carr[tx][ty]==1) {
						continue;
					}
					if(visited1[tx][ty]) {
						continue;
					}
					q.add(new SPoint(tx, ty));
					visited1[tx][ty] = true;
					carr[tx][ty]=3;
				}
			}
			result++;
		}
		for(int i=0;i<N;i++) {//다 돌았는데 바이러스 못퍼트린경우는 최소값계산에서 제외
			for(int j=0;j<N;j++) {
				if(carr[i][j]==0) {
					return;
				}
			}
		}
		if(min>result) {
			min = result;
		}
	}
}