import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MainBOJ11559puyopuyo {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int flag, result = 0;
	static char[][] carr;
	static boolean[][] visited;
	static ArrayList<SPoint> clist = new ArrayList<>();
	static ArrayList<Character> dlist;
	static class SPoint{
		int x;
		int y;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		visited = new boolean[12][6];
		carr = new char[12][6];
		for(int i=0;i<12;i++) {
			String s = br.readLine();
			for(int j=0;j<6;j++) {
				carr[i][j] = s.charAt(j);
			}
		}
		while(true) {
			flag = 0;
			visited = new boolean[12][6];
			for(int i=11;i>=0;i--) {
				for(int j=0;j<6;j++) {
					if(!visited[i][j] && carr[i][j]!='.') {
						solve(i, j);
					}
				}
			}		
			if(flag==0) {
				break;
			}
			for(int i=0;i<6;i++) {
				dlist = new ArrayList<>();
				for(int j=11;j>=0;j--) {
					if(carr[j][i]!='.') {
						dlist.add(carr[j][i]);
						carr[j][i] = '.';
					}
				}
				for(int j=11;j>11-dlist.size();j--) {
					carr[j][i] = dlist.get(11-j);
				}
			}
			dlist.clear();
			visited = new boolean[12][6];
			result++;
		}
		System.out.println(result);
	}
	private static void solve(int x, int y) {
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(x, y));
		clist.add(new SPoint(x, y));
		visited[x][y] = true;
		int cnt=1;
		while(!q.isEmpty()) {
			SPoint p = q.poll();
			for(int i=0;i<4;i++) {
				int tx = p.x + dx[i];
				int ty = p.y + dy[i];
				if(tx<0 || ty<0 || tx>=12 || ty>=6) {
					continue;
				}
				if(visited[tx][ty]) {
					continue;
				}
				if(carr[p.x][p.y]!=carr[tx][ty]) {
					continue;
				}
				q.add(new SPoint(tx, ty));
				clist.add(new SPoint(tx, ty));
				visited[tx][ty] = true;
				cnt++;
			}
		}
		//		System.out.println(cnt);
		if(cnt>=4) {
			flag=1;
			while(clist.size()>0) {
				carr[clist.get(clist.size()-1).x][clist.get(clist.size()-1).y] = '.';
				clist.remove(clist.size()-1);
			}
		}else {
			clist.clear();
		}
		//		for(int i=0;i<12;i++) {
		//			for(int j=0;j<6;j++) {
		//				System.out.print(carr[i][j]+" ");
		//			}System.out.println();
		//		}
	}
}