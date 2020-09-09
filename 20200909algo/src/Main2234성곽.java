import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2234¼º°û {
	static int N,M, cnt=0;
	static int[][] arr;
	static int smax = Integer.MIN_VALUE;
	static int tmax = Integer.MIN_VALUE;
	static int[] dx = {0,0,-1,0,1};//¼­, ºÏ, µ¿, ³²
	static int[] dy = {0,-1,0,1,0};
	static boolean[][] visited;
	static class SPoint{
		int x;
		int y;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M][N];
		visited = new boolean[M][N];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					cnt++;
					bfs(i, j);
				}
			}
		}
		System.out.println(cnt);
		System.out.println(smax);
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				for(int k=1;k<=8;k <<= 1) {
					if((arr[i][j] & k) != 0) {
//						System.out.println(k);
//						System.out.println(i+" "+j);
						visited = new boolean[M][N];
						arr[i][j]-=k;
//						System.out.println(arr[i][j]);
						bfs(i,j);
						arr[i][j]+=k;
//						System.out.println(smax);
					}
				}
			}
		}
		System.out.println(smax);
	}
	private static void bfs(int ax, int ay) {
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(ax, ay));
		visited[ax][ay] = true;
		int scnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int j=0;j<size;j++) {
				scnt++;
				SPoint p = q.poll();
				int bit = 1;
				for(int i=1;i<=4;i++) {
					if((arr[p.x][p.y] & bit) == 0) {
						int tx = p.x+dx[i];
						int ty = p.y+dy[i];
						if(tx<0 || ty<0 || tx>=M || ty>=N) {
							bit = bit<<1;
							continue;
						}
						if(visited[tx][ty]) {
							bit = bit<<1;
							continue;
						}
						q.add(new SPoint(tx, ty));
						visited[tx][ty] = true;
					}
					bit = bit<<1;
				}
			}
		}
		if(smax<scnt) {
			smax = scnt;
		}
	}
}