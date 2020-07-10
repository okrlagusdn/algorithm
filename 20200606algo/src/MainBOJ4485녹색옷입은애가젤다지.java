import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainBOJ4485≥Ïªˆø ¿‘¿∫æ÷∞°¡©¥Ÿ¡ˆ {
	static int N;
	static int[][] arr, visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class SPoint implements Comparable<SPoint>{
		int x;
		int y;
		int rup;
		public SPoint(int x, int y, int rup) {
			super();
			this.x = x;
			this.y = y;
			this.rup = rup;
		}
		@Override
		public int compareTo(SPoint o) {
			return this.rup-o.rup;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int idx = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0) {
				break;
			}
			arr = new int[N][N];
			visited = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			System.out.println("Problem "+(idx++)+": "+bfs());
		}
	}
	private static int bfs() {
		PriorityQueue<SPoint> pq = new PriorityQueue<>();
		pq.add(new SPoint(0, 0, arr[0][0]));
		visited[0][0] = arr[0][0];
		while(!pq.isEmpty()) {
			SPoint p = pq.poll();
			if(p.x==N-1 && p.y==N-1) {
				return p.rup;
			}
			for(int i=0;i<4;i++) {
				int tx = p.x+dx[i];
				int ty = p.y+dy[i];
				if(tx<0 || ty<0 || tx>=N || ty>=N) {
					continue;
				}
				if(visited[tx][ty]<=p.rup+arr[tx][ty]) {
					continue;
				}
				visited[tx][ty] = p.rup+arr[tx][ty];
				pq.add(new SPoint(tx, ty, p.rup+arr[tx][ty]));
			}
		}
		return 0;
	}
}