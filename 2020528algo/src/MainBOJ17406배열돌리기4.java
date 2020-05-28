import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ17406배열돌리기4 {
	static int N,M,K,min=Integer.MAX_VALUE;
	static int[][] arr, carr;
	static boolean[] visited;
	static ArrayList<Integer> list = new ArrayList<>();
	static class SPoint{
		int r;
		int c;
		int s;
		public SPoint(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	static ArrayList<SPoint> rlist = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[K];
		arr = new int[N+1][M+1];
		carr = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			rlist.add(new SPoint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		dfs(K);
		System.out.println(min);
	}
	private static void dfs(int r) {
		if(r==0) {
			int result = 0;
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {
					carr[i][j] = arr[i][j];
				}
			}
//			System.out.println(list);
			for(int i=0;i<list.size();i++) {
				int sr = rlist.get(list.get(i)).r;
				int sc = rlist.get(list.get(i)).c;
				int ss = rlist.get(list.get(i)).s;
				int rr1 = sr-ss;
				int cc1 = sc-ss;
				int rr2 = sr+ss;
				int cc2 = sc+ss;
				while(true) {
					solve(rr1, cc1, rr2, cc2);
					rr1++;
					cc1++;
					rr2--;
					cc2--;
					if(rr1>=rr2 || cc1>=cc2) {
						break;
					}
				}
			}
			for(int j=1;j<=N;j++) {
				result = 0;
				for(int k=1;k<=M;k++) {
					result+=carr[j][k];
				}
				if(min>result) {
					min = result;
				}
			}
			return;
		}
		for(int i=0;i<K;i++) {
			if(!visited[i]) {
				visited[i] = true;
				list.add(i);
				dfs(r-1);
				visited[i] = false;
				list.remove(list.size()-1);				
			}
		}
	}
	private static void solve(int r1, int c1, int r2, int c2) {
		int temp = carr[r1][c2];
		for(int i=c2;i>c1;i--) {
			carr[r1][i] = carr[r1][i-1];
		}
		int temp2 = carr[r2][c2];
		for(int i=r2;i>r1+1;i--) {
			carr[i][c2]=carr[i-1][c2];
		}
		carr[r1+1][c2]=temp;
		int temp3 = carr[r2][c1];
		for(int i=c1;i<c2-1;i++) {
			carr[r2][i]=carr[r2][i+1];
		}
		carr[r2][c2-1] =temp2;
		for(int i=r1;i<r2-1;i++) {
			carr[i][c1] = carr[i+1][c1];
		}
		carr[r2-1][c1] = temp3;//마지막
	}
}
