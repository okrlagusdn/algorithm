import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainBOJ14501��� {
static int N;
static int[][] arr;
static int[] dp;//
static int[] dp2;//
static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[16][2];
		dp = new int[16];
//		dp2 = new int[17];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
//			if(i+arr[i][0]>N) {
//				dp[i]=0;
//			}else {
//				dp[i] = arr[i][1];
//			}
		}
		
		for(int i = 0 ; i < 16 ; i++)
			dp[i] = -1;
		
//		dp2[N-1] = dp[N-1];
//		for(int i=N-2;i>=0;i--) {
//			if(arr[i][0]+i-1 < N) {//���� ���̶��
//				dp2[i] = Math.max(dp2[arr[i][0]+i]+dp[i], dp2[i+1]);
//			}else {
//				dp2[i] = dp[i];
//			}
//		}
//		for(int i=0;i<dp2.length;i++) {
//			if(max<dp2[i]) {
//				max = dp2[i];
//			}
////			System.out.print(dp2[i]+" ");
//		}
		
		
		System.out.println(solve(0));
	}
	
	static public int solve(int d)
	{
		if(d == N) return 0;
		if(dp[d] != -1) 
			return dp[d];
		
		int profit = 0;
		// �� �޴°��
		if(d + arr[d][0] - 1 < N)
			profit = solve(d + arr[d][0]) + arr[d][1];
		
		// �� �ȹ޴°��
		profit = Math.max(profit, solve(d + 1));
		
		return dp[d] = profit;
	}
	//���� �ϴ� ���� ���ϴ� ���� ������ ������ ����ؼ� �������ش�.
}