import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MainBOJ1941소문난칠공주 {
	static char[][] carr;
	static int cnt=0;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[] visited;
	static boolean[][] map;
	static boolean[][] chk;
	static ArrayList<Integer> list = new ArrayList<>();
	static class SPoint {
		int x;
		int y;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static Queue<SPoint> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		carr = new char[5][5];
		visited = new boolean[25];
		for(int i=0;i<5;i++) {
			String s = br.readLine();
			for(int j=0;j<5;j++) {
				carr[i][j] = s.charAt(j);
			}
		}
		dfs(0,7);
		System.out.println(cnt);
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			//			System.out.println(list);
			int result=0;
			for(int i=0;i<list.size();i++) {
				int x = list.get(i)/5;
				int y = list.get(i)%5;
				if(carr[x][y]=='S') {
					result++;
				}
				if(result>=4) {
					//서로 연결되있는지 확인해서 연결되있으면 cnt증가
					solve();
					break;
				}
			}
			return;
		}
		for(int i=start;i<25;i++) {
			if(!visited[i]) {
				visited[i] = true;
				list.add(i);
				dfs(i+1, r-1);
				list.remove(list.size()-1);
				visited[i] = false;
			}
		}
	}
	private static void solve() {
		map = new boolean[5][5];
		chk = new boolean[5][5];
		int x=0;
		int y=0;
		for(int i=0;i<25;i++) {
			if(visited[i]) {
				x = i/5;
				y = i%5;
				map[x][y] = true;
			}
		}
		chk[x][y] = true;
		q.add(new SPoint(x, y));
		bfs();
	}
	private static void bfs() {
		int num=1;
		while(!q.isEmpty()) {
			SPoint p = q.poll();
			for(int i=0;i<4;i++) {
				int tx = p.x+dx[i];
				int ty = p.y+dy[i];
//				System.out.println("무한반복");
				if(tx>=0 && ty>=0 && tx<5 && ty<5 && map[tx][ty] && !chk[tx][ty]) {
					chk[tx][ty] = true;
					q.add(new SPoint(tx, ty));
					num++;
				}
			}
		}
		if(num==7) {
			cnt++;
		}
	}
}