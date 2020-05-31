import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionSWEA5656벽돌깨기 {
	static int T,N,W,H,min;
	static int[][] arr, carr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static ArrayList<Integer> list;
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
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			arr = new int[H][W];
			carr = new int[H][W];
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			list = new ArrayList<>();
			dfs(N);
			System.out.println("#"+tc+" "+min);
		}
	}
	private static void dfs(int r) {
		if(r==0) {
			if(min==0) {
				return;
			}
			int cnt = 0;
//			System.out.println(list);
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					carr[i][j] = arr[i][j];
				}
			}
			for(int i=0;i<list.size();i++) {
				int num = list.get(i);
				bfs(num);//깨는 부분
//				for(int k=0;k<H;k++) {
//					for(int j=0;j<W;j++) {
//						System.out.print(carr[k][j]+" ");
//					}System.out.println();
//				}
				//내리는 부분
				for(int j=0;j<W;j++) {
					ArrayList<Integer> dlist = new ArrayList<>();
					for(int k=H-1;k>=0;k--) {
						if(carr[k][j]>0) {
							dlist.add(carr[k][j]);
							carr[k][j] = 0;
						}
					}
					int idx=0;
					for(int k=H-1;k>H-1-dlist.size();k--) {
						carr[k][j] = dlist.get(idx);
						idx++;
					}
				}
//				for(int k=0;k<H;k++) {
//					for(int j=0;j<W;j++) {
//						System.out.print(carr[k][j]+" ");
//					}System.out.println();
//				}
			}

			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(carr[i][j]>0) {
						cnt++;
					}
				}
			}
			if(min>cnt) {
				min = cnt;
			}
			return;
		}
		for(int i=0;i<W;i++) {
			list.add(i);
			dfs(r-1);
			list.remove(list.size()-1);
		}
	}
	private static void bfs(int sy) {
		Queue<SPoint> q = new LinkedList<SPoint>();
		for(int i=0;i<H;i++) {
			if(carr[i][sy]>0) {
				q.add(new SPoint(i, sy, carr[i][sy]));
				break;
			}
		}
		while(!q.isEmpty()) {
			SPoint p = q.poll();
			carr[p.x][p.y]=0;
			if(p.num==1) {
				continue;
			}
			for(int i=0;i<4;i++) {
				for(int idx=1;idx<p.num;idx++) {
					int tx = p.x+dx[i]*idx;
					int ty = p.y+dy[i]*idx;
					if(tx<0 || ty<0 || tx>=H || ty>=W) {
						break;
					}
					if(carr[tx][ty]==0) {
						continue;
					}
					q.add(new SPoint(tx, ty, carr[tx][ty]));
				}				
			}
		}
	}
}