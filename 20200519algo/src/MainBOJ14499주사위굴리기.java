import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainBOJ14499¡÷ªÁ¿ß±º∏Æ±‚ {
	static int N, M, K;
	static int[][] arr;
	static int[][] dice = {{0,0,0},{0,0,0},{0,0,0},{0,0,0}};
	static int sx, sy;
	static int[] dx = {0,0,0,-1,1};//µø, º≠, ∫œ, ≥≤
	static int[] dy = {0,1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			int d = Integer.parseInt(st.nextToken());
			rotate(sx, sy, d);
		}
	}
	private static void rotate(int sx2, int sy2, int d) {
		int tx = sx2+dx[d];
		int ty = sy2+dy[d];
		if(tx<0 || ty<0 || tx>=N || ty>=M) {
			return;
		}
		if(d==1) {
			int temp = dice[1][2];
			for(int i=2;i>0;i--) {
				dice[1][i] = dice[1][i-1];
			}
			dice[1][0] = temp;
			dice[1][0] = dice[3][1];
			dice[3][1] = temp;
//			System.out.println(dice[1][0]+" "+dice[3][1]);
		}else if(d==2) {
			int temp = dice[1][0];
			for(int i=0;i<2;i++) {
				dice[1][i] = dice[1][i+1];
			}
			dice[1][2] = temp;
			dice[1][2] = dice[3][1];
			dice[3][1] = temp;
		}else if(d==3) {
			int temp = dice[0][1];
			for(int i=0;i<3;i++) {
				dice[i][1] = dice[i+1][1];
			}
			dice[3][1] = temp;
		}else if(d==4) {
			int temp = dice[3][1];
			for(int i=3;i>0;i--) {
				dice[i][1] = dice[i-1][1];
			}
			dice[0][1] = temp;
		}
		if(arr[tx][ty]!=0) {
			dice[3][1] = arr[tx][ty];
			arr[tx][ty] = 0;
		}else if(arr[tx][ty]==0) {
			arr[tx][ty] = dice[3][1];
		}
		sx = tx;
		sy = ty;
		System.out.println(dice[1][1]);
	}
}