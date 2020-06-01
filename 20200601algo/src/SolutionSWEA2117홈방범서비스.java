import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionSWEA2117홈방범서비스 {
	static int T,N,M,max;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] arr;
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
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int cnt=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(arr[i][j]==1) {
						cnt++;
					}
				}
			}
			int maxv = 10;
			int maxval = cnt*M;
			for(int i=1;i<=10;i++) {
				int mo = i*i+(i-1)*(i-1);
				if(mo>maxval) {
					maxv = i-1;
					break;
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					for(int k=1;k<=N+1;k++) {
						visited = new boolean[N][N];
						solve(i,j,k);						
					}
				}
			}
			System.out.println("#"+tc+" "+max);
		}
	}
	private static void solve(int sx, int sy, int ran) {
		int r = ran*ran+(ran-1)*(ran-1);
		int result = 0;
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(sx, sy));
		visited[sx][sy] = true;
		result += arr[sx][sy]*M;
		int cnt = 0;
		if(arr[sx][sy]==1) {
			cnt++;
		}
		for(int idx=1;idx<ran;idx++) {
			int size = q.size();
			for(int s=0;s<size;s++) {
				SPoint p = q.poll();
				for(int i=0;i<4;i++) {
					int tx = p.x+dx[i];
					int ty = p.y+dy[i];
					if(tx<0 || ty<0 || tx>=N || ty>=N) {
						continue;
					}
					if(visited[tx][ty]) {
						continue;
					}
					if(arr[tx][ty]==1) {
						result+=(arr[tx][ty]*M);
						cnt++;
					}
					visited[tx][ty] = true;
					q.add(new SPoint(tx, ty));
				}					
			}
		}
		if(result-r>=0) {
			if(max<cnt) {
				max = cnt;
			}	
		}
	}
}