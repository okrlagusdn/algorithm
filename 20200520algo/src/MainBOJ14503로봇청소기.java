import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ14503·Îº¿Ã»¼Ò±â {
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int N, M, r, c, d, result;
	static int[][] arr;
	static boolean[][] visited;
	static public class SPoint{
		int x;
		int y;
		int dir;
		public SPoint(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	//	ArrayList<SPoint> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = 0;
		
		outer : while(true) {
			if(arr[r][c] == 0) {
				arr[r][c] = -1;
				result++;
			}
			for(int i=0;i<4;i++) {
//				System.out.println(d);
				int tx = r+dx[lrotate(d)];
				int ty = c+dy[lrotate(d)];
				if(tx<0 || ty<0 || tx>=N || ty>=M || arr[tx][ty]!=0) {
					d = lrotate(d);
//					System.out.println(d);
					continue;
				}
				if(arr[tx][ty]==0) {
					d = lrotate(d);
					r = tx;
					c = ty;
					continue outer;
				}
			}
//			d = lrotate(d);
//			System.out.println(d);
			int tx = r+dx[brotate(d)];
			int ty = c+dy[brotate(d)];
			if(tx<0 || ty<0 || tx>=N || ty>=M || arr[tx][ty]==1) {
				break;
			}else if(arr[tx][ty]!=1) {
				r+=dx[brotate(d)];
				c+=dy[brotate(d)];
			}
		}
		System.out.println(result);
	}
	private static int brotate(int dir) {
		if(dir==0) {
			return 2;
		}else if(dir ==1) {
			return 3;
		}else if(dir ==2) {
			return 0;
		}else {
			return 1;
		}
	}
	private static int lrotate(int dir) {
		if(dir==0) {
			return 3;
		}else if(dir ==1) {
			return 0;
		}else if(dir ==2) {
			return 1;
		}else {
			return 2;
		}
	}
}