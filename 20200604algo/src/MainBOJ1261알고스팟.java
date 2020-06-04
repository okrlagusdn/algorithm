import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainBOJ1261¾Ë°í½ºÆÌ {
	static int N,M;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][][] visited;
	static class SPoint implements Comparable<SPoint>{
		int x;
		int y;
		int cnt;
		public SPoint(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(SPoint o) {
			return this.cnt-o.cnt;
		}
	}
	static char[][] carr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		visited = new int[N][M][1];
		carr = new char[N][M];
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				carr[i][j] = s.charAt(j);
				visited[i][j][0] = Integer.MAX_VALUE;
			}
		}
		bfs();
	}
	private static void bfs() {
		PriorityQueue<SPoint> pq = new PriorityQueue<>();
		pq.add(new SPoint(0, 0, 0));
		visited[0][0][0] = 0;
		int c = 0;
		while(!pq.isEmpty()) {
			int size = pq.size();
			for(int s=0;s<size;s++) {
				SPoint p = pq.poll();
//				System.out.println(p.x+" "+p.y);
				if(p.x==N-1 && p.y==M-1) {
					System.out.println(p.cnt);
					System.exit(0);
				}
				for(int i=0;i<4;i++) {
					int tx = p.x+dx[i];
					int ty = p.y+dy[i];
					if(tx<0 || ty<0 || tx>=N || ty>=M) {
						continue;
					}
					if(visited[tx][ty][0]<=c+1) {
						continue;
					}
					if(carr[tx][ty]=='0') {
						pq.add(new SPoint(tx, ty, p.cnt));
						visited[tx][ty][0] = c+1;
					}
					if(carr[tx][ty]=='1') {
						pq.add(new SPoint(tx, ty, p.cnt+1));
						visited[tx][ty][0] = c+1;
					}
				}
			}
			c++;
		}
	}
}