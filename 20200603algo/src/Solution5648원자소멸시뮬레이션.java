import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5648¿øÀÚ¼Ò¸ê½Ã¹Ä·¹ÀÌ¼Ç {
	static int T,N,result, flag2;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int[][] arr;
	static boolean[][] visited;
	static class SPoint{
		int x;
		int y;
		int dir;
		int k;
		public SPoint(int x, int y, int dir, int k) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}
	}
	//	static ArrayList<SPoint> list = new ArrayList<>();
	static SPoint[] p;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		arr = new int[4001][4001];
		visited = new boolean[4001][4001];
		for(int tc=1;tc<=T;tc++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			p = new SPoint[1001];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				//				list.add(new SPoint(i, tc, dir, k))
				p[i] = new SPoint((Integer.parseInt(st.nextToken())+1000)*2, (Integer.parseInt(st.nextToken())+1000)*2, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				arr[p[i].x][p[i].y]+=1;
			}
			while(true) {
				for(int i=0;i<N;i++) {
					if(p[i].k==0) {
						continue;
					}
					int sx = p[i].x;
					int sy = p[i].y;
					int d = p[i].dir;
					int tx = sx+dx[d];
					int ty = sy+dy[d];
					if(tx<0 || ty<0 || tx>4000 || ty>4000) {
						arr[sx][sy]-=1;
						p[i].k=0;
						continue;
					}
					arr[p[i].x][p[i].y]-=1;
					p[i].x = tx;
					p[i].y = ty;
					arr[p[i].x][p[i].y]+=1;
					if(arr[p[i].x][p[i].y]>1) {
						visited[p[i].x][p[i].y] = true;
					}
				}
				for(int i=0;i<N;i++) {
					if(p[i].k==0) {
						continue;
					}
					if(visited[p[i].x][p[i].y]) {
						if(arr[p[i].x][p[i].y]==1) {
							visited[p[i].x][p[i].y] = false;
						}
						arr[p[i].x][p[i].y]-=1;
						result+=p[i].k;
						p[i].k=0;
					}
				}
				int cnt=0;
				for(int i=0;i<N;i++) {
					if(p[i].k==0) {
						cnt++;
					}
				}
				if(cnt==N) {
					break;
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}
}