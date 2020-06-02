import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionSWEA1949등산로조성 {
	static int T,N,K,max, hm;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] arr;
	static boolean[][] visited;
	static class SPoint{
		int x;
		int y;
		int len;
		int status;
		public SPoint(int x, int y, int len, int status) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
			this.status = status;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			max = Integer.MIN_VALUE;
			hm = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j]>hm) {
						hm = arr[i][j];
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(arr[i][j]==hm) {
						visited = new boolean[N][N];
						solve(i, j, 1, 1);
					}
				}
			}
			System.out.println("#"+tc+" "+max);
		}
	}
	private static void solve(int x, int y, int cnt, int status) {
		visited[x][y] = true;
		int sarr = arr[x][y];
		for(int i=0;i<4;i++) {
			int tx = x+dx[i];
			int ty = y+dy[i];
			if(tx<0 || ty<0 || tx>=N || ty>=N || visited[tx][ty]) {
				continue;
			}
			int aarr = arr[tx][ty];
			if(aarr<sarr) {
				if(max<cnt+1) {
					max = cnt+1;
				}
				visited[tx][ty] = true;
				solve(tx, ty, cnt+1, status);
				visited[tx][ty] = false;
			}
			else if(status==1 && (aarr-sarr)<K) {
				if(max<(cnt+1)) {
					max = cnt+1;
				}
				arr[tx][ty] = sarr-1;
				visited[tx][ty] = true;
				solve(tx, ty, cnt+1, 0);
				visited[tx][ty] = false;
				arr[tx][ty] = aarr;
			}
		}
		return;
	}
}