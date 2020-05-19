import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ16234인구이동 {
	static int N, L, R, flag, flag2, idx;
	static int[][] A;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static ArrayList<SPoint> list[];
	static public class SPoint{
		int x;
		int y;
		int pnum;
		public SPoint(int x, int y, int pnum) {
			super();
			this.x = x;
			this.y = y;
			this.pnum = pnum;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt=0;
		while(true) {
			flag = 0;
			visited = new boolean[N][N];
			list = new ArrayList[2501];
			idx = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j]) {
						list[idx] = new ArrayList<>();
						bfs(i, j);//연합되는지 확인하고 flag가 안바뀌면 연합안되므로 
						if(flag2==1) {
							idx++;
							flag2=0;
						}else {
							list[idx].clear();
						}
					}
				}
			}
//			System.out.println(list[1].size());
			if(flag==0) {
				break;
			}else {
				//인구 나눠주고
				for(int i=0;i<idx;i++) {
					int sum = 0;
					for(int j=0;j<list[i].size();j++) {
						sum+=list[i].get(j).pnum;
					}
					sum/=list[i].size();
					for(int j=0;j<list[i].size();j++) {
						A[list[i].get(j).x][list[i].get(j).y] = sum;
					}
					list[i].clear();
				}
				idx=0;
				cnt++;
			}
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(A[i][j]+" ");
//				}System.out.println();
//			}
		}
		System.out.println(cnt);
	}
	private static void bfs(int a, int b) {
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(a, b, A[a][b]));
		visited[a][b] = true;
		list[idx].add(new SPoint(a, b, A[a][b]));
		while(!q.isEmpty()) {
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
				if(Math.abs(A[p.x][p.y]-A[tx][ty])<L || Math.abs(A[p.x][p.y]-A[tx][ty])>R) {
					continue;
				}
				q.add(new SPoint(tx, ty, A[tx][ty]));
				visited[tx][ty] = true;
				flag2=1;
				flag=1;
				list[idx].add(new SPoint(tx, ty, A[tx][ty]));
			}
		}
	}
}