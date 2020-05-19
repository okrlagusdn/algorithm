import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ14502¿¬±¸¼Ò {
	static int N, M;
	static int max = Integer.MIN_VALUE;
	static int[][] arr, carr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
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
	static ArrayList<Integer> wlist = new ArrayList<>();
	static boolean[] visited;
	static boolean[][] visited2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		carr = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==0) {
					list.add(new SPoint(i, j));
				}
			}
		}
		visited = new boolean[list.size()];
		dfs(0, 3);
		System.out.println(max);
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			visited2 = new boolean[N][M];
			int cnt=0;
//			System.out.println(wlist);
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					carr[i][j] = arr[i][j];
				}
			}
			for(int i=0;i<3;i++) {
				carr[list.get(wlist.get(i)).x][list.get(wlist.get(i)).y] = 1;
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(carr[i][j]==2 && !visited2[i][j]) {
						bfs(i, j);
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(carr[i][j]==0) {
						cnt++;
					}
				}
			}
			if(max<cnt) {
				max = cnt;
			}
			return;
		}
		for(int i=start;i<list.size();i++) {
			if(!visited[i]) {
				visited[i] = true;
				wlist.add(i);
				dfs(i+1,r-1);
				visited[i] = false;
				wlist.remove(wlist.size()-1);
			}
		}
	}
	private static void bfs(int sx, int sy) {
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(sx, sy));
		visited2[sx][sy]=true;
		while(!q.isEmpty()) {
			SPoint p = q.poll();
			for(int i=0;i<4;i++) {
				int tx = p.x+dx[i];
				int ty = p.y+dy[i];
				if(tx<0 || ty<0 || tx>=N || ty>=M) {
					continue;
				}
				if(visited2[tx][ty]) {
					continue;
				}
				if(carr[tx][ty]==1 || carr[tx][ty]==2) {
					continue;
				}
				q.add(new SPoint(tx, ty));
				carr[tx][ty]=2;
			}
		}
	}
}