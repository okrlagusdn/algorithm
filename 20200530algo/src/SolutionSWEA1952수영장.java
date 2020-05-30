import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionSWEA1952¼ö¿µÀå {
	static int T, min, result;
	static int[] arr, month;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		arr = new int[4];
		month = new int[13];
		for(int tc=1;tc<=T;tc++) {
			result = 0;
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=12;i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			dfs(1);
			System.out.println("#"+tc+" "+min);
		}
	}
	private static void dfs(int r) {
		if(r>12) {
//			System.out.println(result);
			if(min>result) {
				min = result;
			}
			return;
		}
		if(month[r]==0) {
			dfs(r+1);
		}
		for(int i=0;i<4;i++) {
			if(i==0) {
				result+=(arr[0]*month[r]);
				dfs(r+1);
				result-=(arr[0]*month[r]);
			}else if(i==1) {
				result+=arr[1];
				dfs(r+1);
				result-=arr[1];
			}else if(i==2) {
				result+=arr[2];
				dfs(r+3);
				result-=arr[2];
			}else if(i==3) {
				result+=arr[3];
				dfs(r+12);
				result-=arr[3];
			}
		}
	}
}