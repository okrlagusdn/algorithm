import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionSWEA1953Å»ÁÖ¹ü°Ë°Å {
	static int T,N,M,R,C,L,space;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] arr;
	static boolean[][] visited;
	static class SPoint{
		int x;
		int y;
		int num;
		public SPoint(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());	
			arr = new int[N][M];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[N][M];
			bfs(R, C);
			System.out.println("#"+tc+" "+space);
		}
	}
	private static void bfs(int sx, int sy) {
		Queue<SPoint> q = new LinkedList<>();
		space=1;
		visited[sx][sy]=true;
		q.add(new SPoint(sx, sy, arr[sx][sy]));
		for(int s=1;s<L;s++) {
			int size = q.size();
			for(int k=0;k<size;k++) {
				SPoint p = q.poll();
				solve(p.num, q, p);
			}
		}
	}
	private static void solve(int num, Queue<SPoint> q, SPoint p) {
		for(int i=0;i<4;i++) {
			if(num==2) {
				if(i==2 || i==3) {
					continue;
				}
			}else if(num==3) {
				if(i==0 || i==1) {
					continue;
				}
			}else if(num==4) {
				if(i==1 || i==2) {
					continue;
				}
			}else if(num==5) {
				if(i==0 || i==2) {
					continue;
				}
			}else if(num==6) {
				if(i==0 || i==3) {
					continue;
				}
			}else if(num==7) {
				if(i==1 || i==3) {
					continue;
				}
			}
			int tx = p.x+dx[i];
			int ty = p.y+dy[i];
			if(tx<0 || ty<0 || tx>=N || ty>=M) {
				continue;
			}
			if(arr[tx][ty]==0) {
				continue;
			}
			if(visited[tx][ty]) {
				continue;
			}
			if(!gop(i, arr[tx][ty])) {
				continue;
			}
			visited[tx][ty] = true;
			q.add(new SPoint(tx, ty, arr[tx][ty]));
			space++;
		}
	}
	private static boolean gop(int i, int j) {
		if(i==0) {
			if(j==3 || j==4 || j==7) {
				return false;
			}
		}else if(i==1) {
			if(j==3 || j==5 || j==6) {
				return false;
			}
		}else if(i==2) {
			if(j==2 || j==6 || j==7) {
				return false;
			}
		}else if(i==3) {
			if(j==2 || j==4 || j==5) {
				return false;
			}
		}
		return true;
	}
}