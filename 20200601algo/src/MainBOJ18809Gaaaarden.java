import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class MainBOJ18809Gaaaarden {
	static int N,M,G,R,max=Integer.MIN_VALUE;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] arr;
	static boolean[] visited;
	static ArrayList<Integer> glist = new ArrayList<>();
	static ArrayList<Integer> rlist = new ArrayList<>();
	static ArrayList<SPoint> ylist = new ArrayList<>();
	static SPoint[][] list2;
	static Queue<SPoint> q;
	static class SPoint{
		int x;
		int y;
		int t;
		char c;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public SPoint(int x, int y, int t, char c) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
			this.c = c;
		}
		public SPoint(int t, char c) {
			super();
			this.t = t;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==2) {
					ylist.add(new SPoint(i, j));
				}
			}
		}
		visited = new boolean[ylist.size()];
		dfs(0, G);
		System.out.println(max);
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			dfs2(0, R);
			return;
		}
		for(int i=start;i<ylist.size();i++) {
			if(!visited[i]) {
				visited[i] = true;
				glist.add(i);
				dfs(i+1, r-1);
				glist.remove(glist.size()-1);
				visited[i] = false;
			}
		}
	}
	private static void dfs2(int start, int r) {
		if(r==0) {
			list2 = new SPoint[N][M];
			for(int i=0;i<glist.size();i++) {
				int x = ylist.get(glist.get(i)).x;
				int y = ylist.get(glist.get(i)).y;
				list2[x][y] = new SPoint(0, 'G');
			}
			for(int i=0;i<rlist.size();i++) {
				int x = ylist.get(rlist.get(i)).x;
				int y = ylist.get(rlist.get(i)).y;
				list2[x][y] = new SPoint(0, 'R');
			}
			bfs();
			return;
		}
		for(int i=start;i<ylist.size();i++) {
			if(!visited[i]) {
				visited[i] = true;
				rlist.add(i);
				dfs2(i+1, r-1);
				rlist.remove(rlist.size()-1);
				visited[i] = false;
			}
		}
	}
	private static void bfs() {
		q = new LinkedList<>();
		int result = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(list2[i][j]!=null) {
					q.add(new SPoint(i, j, list2[i][j].t, list2[i][j].c));
				}
			}
		}
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0;s<size;s++) {
				SPoint p = q.poll();
				if(list2[p.x][p.y].c=='F') {
					continue;
				}
				for(int i=0;i<4;i++) {
					int tx = p.x+dx[i];
					int ty = p.y+dy[i];
					if(tx<0 || ty<0 || tx>=N || ty>=M || arr[tx][ty]==0) {
						continue;
					}
					if(list2[tx][ty]!=null) {
						if(list2[tx][ty].c==list2[p.x][p.y].c) {
							continue;
						}
						if(list2[tx][ty].c=='F') {
							continue;
						}
						if(list2[tx][ty].t==list2[p.x][p.y].t+1 && (list2[tx][ty].c=='R' && list2[p.x][p.y].c=='G' || list2[tx][ty].c=='G' && list2[p.x][p.y].c=='R')) {
							list2[tx][ty] = new SPoint(0, 'F');
							result++;
							continue;
						}
					}else {
						list2[tx][ty] = new SPoint(list2[p.x][p.y].t+1, list2[p.x][p.y].c);
						q.add(new SPoint(tx, ty, list2[p.x][p.y].t+1, list2[p.x][p.y].c));						
					}
				}
			}
		}
		if(max<result) {
			max = result;
		}
	}
}