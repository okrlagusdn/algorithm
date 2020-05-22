import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainBOJ14500테트로미노 {
	static int N, M, max = Integer.MIN_VALUE;
	static int[][] arr;
	static int[][] dx = {{1,2,2},{1,1,1},{0,1,2},{0,0,1},{1,2,2},{1,1,1},{0,1,2},{0,0,1},
			{0,0,1},{1,2,1},{0,0,-1},{1,2,1},{0,0,0},{1,2,3},{1,1,2},{0,1,1},{1,1,2},{0,1,1},{0,1,1}};
	static int[][] dy = {{0,0,1},{0,-1,-2},{1,1,1},{-1,-2,-2},{0,0,-1},{0,1,2},{-1,-1,-1},{1,2,2},
			{1,2,1},{0,0,1},{1,2,1},{0,0,-1},{1,2,3},{0,0,0},{0,1,1},{-1,-1,-2},{0,-1,-1},{1,1,2},{1,0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				for(int k=0;k<19;k++) {
					solve(i ,j, k);					
				}
			}
		}
		System.out.println(max);
	}

	private static void solve(int sx, int sy, int k) {
		int result=0;
		result+=arr[sx][sy];
		for(int i=0;i<3;i++) {
			int tx = sx+dx[k][i];
			int ty = sy+dy[k][i];
			if(tx<0 || ty<0 || tx>=N || ty>=M) {
				break;
			}
			result+=arr[tx][ty];
		}
		if(max<result) {
			max = result;
		}
	}
}
