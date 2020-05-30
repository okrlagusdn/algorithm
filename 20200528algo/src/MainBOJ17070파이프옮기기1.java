import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ17070파이프옮기기1 {
	static int N;
	static int[][] arr;
	static int[][][] dp;
	static int[] dx = {0,1,1};
	static int[] dy = {1,0,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		dp = new int[N+1][N+1][3];
		for(int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[1][2][0] = 1;
		solve();
	}
	private static void solve() {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				//가로
				if(dp[i][j-1][0]>0 && arr[i][j]!=1) {
					dp[i][j][0]+=dp[i][j-1][0];
				}
				if(dp[i-1][j-1][0]>0 && arr[i][j-1]!=1 && arr[i-1][j]!=1 && arr[i][j]!=1) {
					dp[i][j][2]+=dp[i-1][j-1][0];
				}
				//세로
				if(dp[i-1][j][1]>0 && arr[i][j]!=1) {
					dp[i][j][1]+=dp[i-1][j][1];
				}
				if(dp[i-1][j-1][1]>0 && arr[i][j-1]!=1 && arr[i-1][j]!=1 && arr[i][j]!=1) {
					dp[i][j][2]+=dp[i-1][j-1][1];
				}
				//대각
				if(dp[i][j-1][2]>0 && arr[i][j]!=1) {
					dp[i][j][0]+=dp[i][j-1][2];
				}
				if(dp[i-1][j][2]>0 && arr[i][j]!=1) {
					dp[i][j][1]+=dp[i-1][j][2];
				}
				if(dp[i-1][j-1][2]>0 && arr[i][j-1]!=1 && arr[i-1][j]!=1 && arr[i][j]!=1) {
					dp[i][j][2]+=dp[i-1][j-1][2];
				}
			}
		}
		System.out.println(dp[N][N][0]+dp[N][N][1]+dp[N][N][2]);
	}
}