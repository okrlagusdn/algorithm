import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ15683°¨½Ã {
	static int N, M, px, py, min = Integer.MAX_VALUE;
	static int[][] arr, carr;
	static public class SPoint{
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
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static ArrayList<SPoint> list = new ArrayList<>();
	static ArrayList<Integer> list2 = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		carr = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				carr[i][j] = arr[i][j];
				if(arr[i][j]>0 && arr[i][j]<6) {
					list.add(new SPoint(i, j, arr[i][j]));
				}
			}
		}
		dfs(list.size());
		System.out.println(min);
	}
	private static void dfs(int r) {
		if(r==0) {
			int cnt=0;
//			System.out.println(list2);
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					carr[i][j] = arr[i][j];
				}
			}
			for(int i=0;i<list2.size();i++) {
				px = list.get(i).x;
				py = list.get(i).y;
				if(list.get(i).num==1) {
					if(list2.get(i)==0) {
						solve(0);
					}else if(list2.get(i)==1) {
						solve(1);
					}else if(list2.get(i)==2) {
						solve(2);
					}else if(list2.get(i)==3) {
						solve(3);
					}
				}else if(list.get(i).num==2) {
					if(list2.get(i)==0) {
						solve(0);
						solve(1);
					}else if(list2.get(i)==1) {
						solve(1);
						solve(0);
					}else if(list2.get(i)==2) {
						solve(2);
						solve(3);
					}else if(list2.get(i)==3) {
						solve(3);
						solve(2);
					}
				}else if(list.get(i).num==3) {
					if(list2.get(i)==0) {
						solve(0);
						solve(3);
					}else if(list2.get(i)==1) {
						solve(1);
						solve(3);
					}else if(list2.get(i)==2) {
						solve(2);
						solve(1);
					}else if(list2.get(i)==3) {
						solve(2);
						solve(0);
					}
				}else if(list.get(i).num==4) {
					if(list2.get(i)==0) {
						solve(0);
						solve(2);
						solve(3);
					}else if(list2.get(i)==1) {
						solve(0);
						solve(1);
						solve(3);
					}else if(list2.get(i)==2) {
						solve(1);
						solve(2);
						solve(3);
					}else if(list2.get(i)==3) {
						solve(0);
						solve(1);
						solve(2);
					}
				}else if(list.get(i).num==5) {
					solve(0);
					solve(1);
					solve(2);
					solve(3);
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(carr[i][j]==0) {
						cnt++;
					}
				}
			}
			if(min>cnt) {
				min = cnt;
			}
			return;
		}
		for(int i=0;i<4;i++) {
			list2.add(i);
			dfs(r-1);
			list2.remove(list2.size()-1);
		}
	}
	private static void solve(int i) {
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(px, py, i));
		while(!q.isEmpty()) {
			SPoint p = q.poll();
			int tx = p.x+dx[i];
			int ty = p.y+dy[i];
			if(tx<0 || ty<0 || tx>=N || ty>=M) {
				continue;
			}
			if(carr[tx][ty]==6) {
				continue;
			}
			q.add(new SPoint(tx, ty, i));
			carr[tx][ty] = 10;
		}
	}
}
