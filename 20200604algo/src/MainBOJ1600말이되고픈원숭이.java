import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ1600말이되고픈원숭이 {
	static int K,W,H;
	static int[] dx = {-1,1,0,0,-1,-2,-2,-1,1,2,2,1};
	static int[] dy = {0,0,-1,1,-2,-1,1,2,-2,-1,1,2};
	static int[][] arr;
	static int[][][] visited;
	static class SPoint{
		int x;
		int y;
		int status;
		public SPoint(int x, int y, int status) {
			super();
			this.x = x;
			this.y = y;
			this.status = status;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		visited = new int[H][W][K+1];
		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				for(int k=0;k<K+1;k++) {
					visited[i][j][k] = Integer.MAX_VALUE;					
				}
			}
		}
		bfs();
		System.out.println(-1);
	}
	private static void bfs() {
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(0, 0, K));
		visited[0][0][0] = 0;
		int cnt=0;
		while(!q.isEmpty()) {
			int size = q.size();
//			System.out.println((cnt+1)+"번째 턴");
			for(int s=0;s<size;s++) {
				SPoint p = q.poll();
				if(p.x==H-1 && p.y==W-1) {
					System.out.println(cnt);
					System.exit(0);
				}
				for(int i=0;i<4;i++) {
					int tx = p.x+dx[i];
					int ty = p.y+dy[i];
					if(tx<0 || ty<0 || tx>=H || ty>=W) {
						continue;
					}
					if(arr[tx][ty]==1) {
						continue;
					}
					if(visited[tx][ty][p.status]<=cnt+1) {
						continue;
					}
//					System.out.println(tx+" "+ty);
					visited[tx][ty][p.status] = cnt+1;
					q.add(new SPoint(tx, ty, p.status));
				}
				if(p.status>0) {
					for(int i=4;i<12;i++) {
						int tx = p.x+dx[i];
						int ty = p.y+dy[i];
						if(tx<0 || ty<0 || tx>=H || ty>=W) {
							continue;
						}
						if(arr[tx][ty]==1) {
							continue;
						}
						if(visited[tx][ty][p.status-1]<=cnt+1) {
							continue;
						}
//						System.out.println("말처럼 이동 : "+tx+" "+ty);
						visited[tx][ty][p.status-1] = cnt+1;
						q.add(new SPoint(tx, ty, p.status-1));
					}
				}
			}
			cnt++;
		}
	}
}