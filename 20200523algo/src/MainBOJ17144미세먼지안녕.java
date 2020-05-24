import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ17144미세먼지안녕 {
	static int R, C, T, result;
	static int[][] arr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static public class SPoint{
		int x;
		int y;
		int du;
		public SPoint(int x, int y, int du) {
			super();
			this.x = x;
			this.y = y;
			this.du = du;
		}
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<SPoint> list;
	static ArrayList<SPoint> blist = new ArrayList<>();
	static Queue<SPoint> qlist = new LinkedList<>();
	static Queue<SPoint> qlist2 = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==-1) {
					blist.add(new SPoint(i, j));
				}
			}
		}
		for(int i=0;i<T;i++) {
			list = new ArrayList<>();
			for(int j=0;j<R;j++) {
				for(int k=0;k<C;k++) {
					if(arr[j][k]>0) {
//						list.add(new SPoint(j, k, arr[j][k]));
						qlist.add(new SPoint(j, k, arr[j][k]));
						solve();
					}
				}
			}
			int size = qlist2.size();
			for(int j=0;j<size;j++) {
				SPoint p = qlist2.poll();
				arr[p.x][p.y]+=p.du;
			}
//			int size = list.size();
//			for(int j=0;j<size;j++) {
//				SPoint p = list.get(0);
//				list.remove(0);
//				int cnt=0;
//				for(int k=0;k<4;k++) {
//					int tx = p.x+dx[k];
//					int ty = p.y+dy[k];
//					if(tx<0 || ty<0 || tx>=R || ty>=C) {
//						continue;
//					}
//					if(arr[tx][ty]==-1) {
//						continue;
//					}
//					list.add(new SPoint(tx, ty, arr[p.x][p.y]/5));
//					cnt++;
//				}
//				list.add(new SPoint(p.x, p.y, arr[p.x][p.y]-((arr[p.x][p.y]/5)*cnt)));
//			}
			
//			arr = new int[R][C];
//			for(int j=0;j<list.size();j++) {
//				SPoint p = list.get(j);
//				arr[p.x][p.y] += p.du; 
//			}
//			arr[blist.get(0).x][blist.get(0).y] = -1;
//			arr[blist.get(1).x][blist.get(1).y] = -1;
			//공기청정기 돌리기
			int dx = blist.get(0).x;
			int dy = blist.get(0).y;
			int temp = arr[dx][C-1];
			for(int idx=C-1;idx>1;idx--) {
				arr[dx][idx] = arr[dx][idx-1];
			}
			arr[dx][1] = 0;
			int temp2 = arr[0][C-1];
			for(int idx = 0;idx<dx-1;idx++) {
				arr[idx][C-1] = arr[idx+1][C-1];
			}
			arr[dx-1][C-1] = temp;
			int temp3 = arr[0][0];
			for(int idx=0;idx<C-2;idx++) {
				arr[0][idx] = arr[0][idx+1];
			}
			arr[0][C-2] = temp2;
			for(int idx=dx-1;idx>1;idx--) {
				arr[idx][0]=arr[idx-1][0];
			}
			arr[1][0] = temp3;
			
			//공기청정기 돌리기2
			dx = blist.get(1).x;
			dy = blist.get(1).y;
			
			temp = arr[dx][C-1];
			for(int idx=C-1;idx>1;idx--) {
				arr[dx][idx]=arr[dx][idx-1];
			}
			arr[dx][1]=0;
			temp2 = arr[R-1][C-1];
			for(int idx=R-1;idx>dx+1;idx--) {
				arr[idx][C-1]=arr[idx-1][C-1];
			}
			arr[dx+1][C-1] = temp;
			temp3 = arr[R-1][0];
			for(int idx=0;idx<C-2;idx++) {
				arr[R-1][idx]=arr[R-1][idx+1];
			}
			arr[R-1][C-2] = temp2;
			for(int idx=dx+1;idx<R-2;idx++) {
				arr[idx][0]=arr[idx+1][0];
			}
			arr[R-2][0] = temp3;
		}
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(arr[i][j]>0) {
					result+=arr[i][j];
				}
			}
		}
		System.out.println(result);
	}
	private static void solve() {
		SPoint p = qlist.poll();
		int cnt=0;
		for(int i=0;i<4;i++) {
			int tx = p.x+dx[i];
			int ty = p.y+dy[i];
			if(tx<0 || ty<0 || tx>=R || ty>=C) {
				continue;
			}
			if(arr[tx][ty]==-1) {
				continue;
			}
			qlist2.add(new SPoint(tx, ty, arr[p.x][p.y]/5));
			cnt++;
		}
		arr[p.x][p.y]-=((arr[p.x][p.y]/5)*cnt);
	}
}
