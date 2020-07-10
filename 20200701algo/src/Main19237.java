import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main19237 {
	static int N,M,K,result=0, sec=1, flag2=1;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class SPoint {
		int num;
		int k;
		public SPoint(int num, int k) {
			super();
			this.num = num;
			this.k = k;
		}
	}
	static class SPoint2{
		int x;
		int y;
		int num;
		int dir;
		public SPoint2(int x, int y, int num, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
	static ArrayList<SPoint2> list = new ArrayList<>();
	static SPoint[][] sp;
	static int[][][] dir;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dir = new int[M+1][4][4];
		sp = new SPoint[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n>0) {
					sp[i][j] = new SPoint(n, K);
				}else {
					sp[i][j] = new SPoint(0, 0);
				}
			}
		}
		st = new StringTokenizer(br.readLine());//각 상어의 방향
		for(int i=1;i<M+1;i++) {
			int d = Integer.parseInt(st.nextToken())-1;
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					if(i==sp[j][k].num) {
						list.add(new SPoint2(j, k, i, d));
					}
				}
			}
		}
		for(int i=1;i<M+1;i++) {
			for(int j=0;j<4;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<4;k++) {
					dir[i][j][k] = (Integer.parseInt(st.nextToken())-1);
				}
			}
		}
		solve();
		if(flag2==0) {
			System.out.println(sec);
		}else {
			System.out.println(-1);
		}
	}
	private static void solve() {
		while(sec<=1000) {
			for(int i=0;i<list.size();i++) {
				int flag=0;
				int ax = list.get(i).x;
				int ay = list.get(i).y;
				int nu = list.get(i).num;
				int di = list.get(i).dir;
				for(int k=0;k<4;k++) {
					int tx = ax+dx[dir[nu][di][k]];
					int ty = ay+dy[dir[nu][di][k]];
					if(tx>=0 && ty>=0 && tx<N && ty<N) {
						if(sp[tx][ty].num==0) {//범위 안에 있고, 아직 냄새 뿌려지지 않은 칸이라면
							list.get(i).x=tx;
							list.get(i).y=ty;
							list.get(i).dir = dir[nu][di][k];
							break;
						}
					}
					flag++;
				}
				if(flag==4) {
//					System.out.println("자기냄새로 감");
					for(int k=0;k<4;k++) {
						int tx = ax+dx[dir[nu][di][k]];
						int ty = ay+dy[dir[nu][di][k]];
						if(tx>=0 && ty>=0 && tx<N && ty<N) {
							if(sp[tx][ty].num==nu) {
								list.get(i).x=tx;
								list.get(i).y=ty;
								list.get(i).dir=dir[nu][di][k];
								break;
							}
						}
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(sp[i][j].k==0) {
						continue;
					}
					sp[i][j].k--;
				}
			}
//			Collections.sort(list);
			for(int i=0;i<list.size();i++) {
				int xx = list.get(i).x;
				int yy = list.get(i).y;
				int nu = list.get(i).num;
				if(sp[xx][yy].num==0 || sp[xx][yy].num>=nu) {
					sp[xx][yy] = new SPoint(nu, K);					
				}else {
					list.remove(i);
					i--;
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(sp[i][j].k==0) {
						sp[i][j] = new SPoint(0, 0);
					}
				}
			}
			if(list.size()>1 && sec>1000) {
				flag2=1;
				break;
			}
			if(list.size()==1 && list.get(0).num==1 && sec<=1000) {
				flag2=0;
				break;
			}
			sec++;
		}
	}
}