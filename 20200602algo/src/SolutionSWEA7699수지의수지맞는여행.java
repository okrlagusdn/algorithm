import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SolutionSWEA7699수지의수지맞는여행 {
	static int T,R,C, max;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static char[][] carr;
	static boolean[] visited;
	static boolean flag;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			flag = true;
			max = 1;
			visited = new boolean[26];
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			carr = new char[R][C];
			for(int i=0;i<R;i++) {
				String s = br.readLine();
				for(int j=0;j<C;j++) {
					carr[i][j] = s.charAt(j);
				}
			}
			visited[carr[0][0]-'A'] = true;
			dfs(0, 0, 1);
			System.out.println("#"+tc+" "+max);
		}
	}
	private static void dfs(int x, int y, int cnt) {
		if(max<cnt) {
			max = cnt;
		}
		if(max==26) {
			return;
		}
		for(int i=0;i<4;i++) {
			int tx = x+dx[i];
			int ty = y+dy[i];
			if(tx<0 || ty<0 || tx>=R || ty>=C) {
				continue;
			}
			if(visited[carr[tx][ty]-'A']) {
				continue;
			}
			visited[carr[tx][ty]-'A'] = true;
			dfs(tx, ty, cnt+1);
			visited[carr[tx][ty]-'A'] = false;
		}
	}
}