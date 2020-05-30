import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainBOJ17136색종이붙이기 {
	static int[][] arr;
	static int[] plist;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		arr = new int[10][10];
		plist = new int[6];
		for(int i=5;i>0;i--) {
			plist[i] = 5;
		}
		for(int i=0;i<10;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<10;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,0);
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}
	private static void dfs(int idx, int cnt) {
		if(idx==100) {
			if(min>cnt) {
				min = cnt;
			}
			return;
		}
		if(min<=cnt){
			return;
		}
		int x = idx/10;
		int y = idx%10;
		if(arr[x][y]==1) {
			for(int i=5;i>0;i--) {
				if(check(x,y,i) && plist[i]>0) {
					for(int j=x;j<(x+i);j++) {
						for(int k=y;k<(y+i);k++) {
							arr[j][k]=0;
						}
					}
					plist[i]--;
					dfs(idx+1, cnt+1);
					for(int j=x;j<(x+i);j++) {
						for(int k=y;k<(y+i);k++) {
							arr[j][k]=1;
						}
					}
					plist[i]++;
				}
			}			
		}else {
			dfs(idx+1, cnt);
		}
	}
	private static boolean check(int x, int y, int cnt) {
		if(x+cnt>10 || y+cnt>10) {
			return false;
		}
		for(int i=x;i<(x+cnt);i++) {
			for(int j=y;j<(y+cnt);j++) {
				if(arr[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}
}