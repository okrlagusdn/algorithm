import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SolutionSWEA5653줄기세포배양 {
	static int T,N,M,K,cnt;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] arr;
	static class SPoint implements Comparable<SPoint>{
		int x;
		int y;
		int status;
		int lifet;
		int wt;
		public SPoint(int x, int y, int status,int lifet, int wt) {
			super();
			this.x = x;
			this.y = y;
			this.status = status;
			this.lifet = lifet;
			this.wt = wt;
		}
		@Override
		public int compareTo(SPoint o) {
			return this.lifet-o.lifet;
		}
	}
	static SPoint[][] p;
	static ArrayList<SPoint> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			arr = new int[660][660];
			p = new SPoint[660][660];
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for(int i=305;i<305+N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=305;j<305+M;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j]>0) {
						//						p[i][j] = new SPoint(2, arr[i][j], arr[i][j], 0);		
						list.add(new SPoint(i, j, 2, arr[i][j], 0));
					}
				}
			}
			solve();
			System.out.println("#"+tc+" "+cnt);
		}
	}
	private static void solve() {
		cnt=0;
		for(int s=0;s<=K;s++) {
			int size = list.size();
			for(int i=0;i<size;i++) {
				if(list.get(i).status==1) {
					for(int k=0;k<4;k++) {
						int tx = list.get(i).x+dx[k];
						int ty = list.get(i).y+dy[k];
						if(arr[tx][ty]>0) {
							continue;
						}
						list.add(new SPoint(tx, ty, 2, arr[list.get(i).x][list.get(i).y], s));
						arr[tx][ty] = arr[list.get(i).x][list.get(i).y];
					}
				}
				if(s-list.get(i).wt==list.get(i).lifet) {
					list.get(i).status=1;
				}
				if(s-list.get(i).wt==list.get(i).lifet*2) {
					list.get(i).status=0;
				}
			}
		}
		for(int i=0;i<list.size();i++) {
			if(list.get(i).status>0) {
				cnt++;
			}
		}
	}
}