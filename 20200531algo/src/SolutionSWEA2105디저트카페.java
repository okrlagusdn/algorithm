import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SolutionSWEA2105디저트카페 {
	static int T,N, max;
	static int[][] arr;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	static boolean[] visited;
	static ArrayList<Integer> list = null;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			max = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					visited = new boolean[101];
					list = new ArrayList<>();
					list.add(arr[i][j]);
					dfs(i, j, 1, i, j, 0, 0);
				}
			}
			if(max==Integer.MIN_VALUE) {
				System.out.println("#"+tc+" "+-1);
			}else {
				System.out.println("#"+tc+" "+(max-1));
			}
		}
	}
	private static void dfs(int sx, int sy, int dir, int px, int py, int d, int dn) {
		if(d>4) {
			return;
		}
		if(sx==px && sy==py && d==4 ) {
			if(max<dn) {
				max = dn;
			}	
			return;
		}
		for(int i=0;i<4;i++) {
			int tx = px+dx[i];
			int ty = py+dy[i];
			if(tx<0 || ty<0 || tx>=N || ty>=N || visited[arr[tx][ty]]) {
				continue;
			}
			int dd=d;
			if(dir!=i) {
				dd = d+1;
			}
			visited[arr[tx][ty]] = true;
			dfs(sx, sy, i, tx, ty, dd, dn+1);
			visited[arr[tx][ty]] = false;
		}
	}
}