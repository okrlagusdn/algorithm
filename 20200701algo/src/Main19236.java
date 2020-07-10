import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main19236 {
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,-1,-1,-1,0,1,1,1};
	static int max = Integer.MIN_VALUE;
	static int flag = 0;
//	static class SPoint {
//		int x;
//		int y;
//		int n;
//		int dir;
//		public SPoint(int n, int dir) {
//			super();
//			this.n = n;
//			this.dir = dir;
//		}
//		public SPoint(int x, int y, int n, int dir) {
//			super();
//			this.x = x;
//			this.y = y;
//			this.n = n;
//			this.dir = dir;
//		}
//	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		SPoint sp[][] = new SPoint[4][4];
		int[][] map = new int[4][4];
		int[][] dir = new int[4][4];
		int num1 = 0;
		int num2 = 0;
		int nu = 0;
		int di = 0;
		for(int i=0;i<4;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<8;j++) {
				if(j%2==0) {
					map[i][num2] = Integer.parseInt(st.nextToken());
				}
				if(j%2==1) {
					dir[i][num2] = Integer.parseInt(st.nextToken())-1;
					num2++;
				}
			}
			num2=0;
		}
		int r = map[0][0];
		map[0][0] = 0;
		dfs(map, dir, 0, 0, dir[0][0], r);
		System.out.println(max);
	}
	private static void dfs(int[][] map, int[][] dir, int sx, int sy, int sd, int result) {
		if(max<result) {
			max = result;
		}
		int[][][] ret = move(map, dir, sx, sy);
		int[][] cmap = ret[0];
		int[][] cdir = ret[1];
		for(int i=1;i<4;i++) {
			int tx = sx+(dx[sd]*i);
			int ty = sy+(dy[sd]*i);
			if(tx>=0 && ty>=0 && tx<4 && ty<4) {
				int nn = cmap[tx][ty];
				if(nn!=0) {
					cmap[tx][ty] = 0;
					dfs(cmap, cdir, tx, ty, cdir[tx][ty], result+nn);
					cmap[tx][ty] = nn;
				}
			}
		}
	}
	private static int[][][] move(int[][] map, int[][] dir, int sx, int sy) {
		int[][][] ret = new int[2][4][4];
		for(int i=0;i<4;i++) {
			ret[0][i] = map[i].clone();
			ret[1][i] = dir[i].clone();
		}
		for(int s=1;s<=16;s++) {
			outer : for(int i=0;i<4;i++) {//물고기 이동
				for(int j=0;j<4;j++) {
					if(s==ret[0][i][j]) {
						int d = ret[1][i][j];
						for(int k=0;k<8;k++) {
							int tx = i+dx[(d+k)%8];
							int ty = j+dy[(d+k)%8];
							if(tx>=0 && ty>=0 && tx<4 && ty<4 && !(tx==sx && ty==sy)) {
								if(ret[0][tx][ty]!=0) {
									int temp1 = ret[0][tx][ty];
									ret[0][tx][ty] = ret[0][i][j];
									ret[0][i][j] = temp1;
									int temp2 = ret[1][tx][ty];
									ret[1][tx][ty] = ret[1][i][j];
									ret[1][i][j] = temp2;
								}else {
									ret[0][tx][ty] = ret[0][i][j];
									ret[1][tx][ty] = ret[1][i][j];
									ret[0][i][j]=0;
									ret[1][i][j]=0;
								}
								ret[1][tx][ty] = (d+k)%8;
								break outer;
							}
						}
					}
				}
			}
		}
		return ret;
	}
}