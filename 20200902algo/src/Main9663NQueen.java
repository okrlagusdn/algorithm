import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9663NQueen {
	static int N, result=0;
	static int[] col;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i=1;i<=N;i++) {
			col = new int[15];
			col[1] = i;
			dfs(1);
		}
		System.out.println(result);
	}
	
	private static void dfs(int n) {
		if(n==N) {
			++result;
		}else {
			for(int i=1;i<=N;i++) {
				col[n+1] = i;
				if(isPb(n+1)) {//퀸을 놓을 수 있으면
					dfs(n+1);
				}else {//퀸을 놓을 수 없으면
					col[n+1] = 0;
				}
			}
		}
		col[n] = 0;
	}
	
	private static boolean isPb(int r) {
		for(int i=1;i<r;i++) {
			if(col[i]==col[r]) {
				return false;
			}
			if(Math.abs(i-r)==Math.abs(col[i]-col[r])) {
				return false;
			}
		}
		return true;
	}
}