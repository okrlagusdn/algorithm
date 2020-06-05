import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionSWEA1824혁진이의프로그램검증 {
	static int T,R,C,memo, px, py, dir;
	static char[][] carr;
	static String result = "";
	static boolean find1;
	static boolean find2;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][][][] visited;
	static class SPoint{
		int x;
		int y;
		int dir;
		public SPoint(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T= Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			find1 = false;
			find2 = false;
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			visited = new boolean[21][21][4][16];
			carr = new char[R][C];
			for(int i=0;i<R;i++) {
				String s = br.readLine();
				for(int j=0;j<C;j++) {
					carr[i][j] = s.charAt(j);
					if(carr[i][j]=='@') {
						find1 = true;
					}
				}
			}
			if(find1) {
				memo = 0;
				solve(0,0,1,0);				
			}
			System.out.println("#"+tc+" "+ (find2 ? "YES":"NO"));
		}
	}
	private static void solve(int px, int py, int dir, int memo) {
//		System.out.println(px+" "+py);
		if(find2) {
			return;
		}
		if(carr[px][py] == '@') {
			find2 =  true;
			return;
		}
		if(visited[px][py][dir][memo]) {
			return;
		}
		visited[px][py][dir][memo] = true;

		int nd = dir;
		int nm = memo;
		if(carr[px][py]=='v' || carr[px][py]=='<' || carr[px][py]=='>' || carr[px][py]=='^') {
			nd = rotate(carr[px][py]);
		}
		if(carr[px][py]=='_') {
			if(memo==0) {
				nd=rotate('>');
			}else {
				nd=rotate('<');
			}
		}
		if(carr[px][py]>='0' && carr[px][py]<='9') {
			nm = carr[px][py]-'0';
		}
		if(carr[px][py]=='+') {
			if(memo==15) {
				nm = 0;
			}else {
				nm++;
			}
		}
		if(carr[px][py]=='-') {
			if(memo==0) {
				nm = 15;
			}else {
				nm--;
			}
		}
		if(carr[px][py]=='|') {
			if(memo==0) {
				nd=rotate('v');
			}else {
				nd=rotate('^');
			}
		}
		//		System.out.println(nm);
		if(carr[px][py]=='?') {
			for(int i=0;i<4;i++) {
				int tx = px+dx[i];
				int ty = py+dy[i];
				if(tx<0) {
					tx = R-1;
					//					solve(tx, ty, i, nm);
				}
				else if(ty<0) {
					ty = C-1;
					//					solve(tx, ty, i, nm);
				}
				else if(tx>=R) {
					tx = 0;
					//					solve(tx, ty, i, nm);
				}
				else if(ty>=C) {
					ty=0;
					//					solve(tx, ty, i, nm);
				}
				solve(tx, ty, i, nm);

			}
		}else {
			int tx = px+dx[nd];
			int ty = py+dy[nd];
			if(tx<0) {
				tx = R-1;
			}
			if(ty<0) {
				ty = C-1;
			}
			if(tx>=R) {
				tx = 0;
			}
			if(ty>=C) {
				ty=0;
			}
			solve(tx, ty, nd, nm);
		}
	}
	private static int rotate(char d) {
		if(d=='v') {
			return 2;
		}else if(d=='^') {
			return 0;
		}else if(d=='<') {
			return 3;
		}else {
			return 1;
		}
	}
}