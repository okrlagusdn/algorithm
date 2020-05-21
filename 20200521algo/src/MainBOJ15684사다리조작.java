import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ15684사다리조작 {
	static int N,M,H,a,b,num,min=Integer.MAX_VALUE;
	static int[][] arr, carr;
	static boolean[] visited;
	static public class SPoint{
		int x;
		int y;
		int n;
		public SPoint(int x, int y, int n) {
			super();
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}
	static ArrayList<SPoint> list = new ArrayList<>();
	static ArrayList<Integer> blist;
	public static void main(String[] args) throws IOException {
		num=1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H+2][N+1];
		carr = new int[H+2][N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			arr[a][b] = num;
			arr[a][b+1] = num;
			carr[a][b] = arr[a][b];
			carr[a][b+1] = arr[a][b+1];
			num++;
		}
		for(int i=1;i<=H;i++) {
			for(int j=1;j<N;j++) {
				if(arr[i][j]==0 && arr[i][j+1]==0) {
					list.add(new SPoint(i, j, num));
					num++;
				}
			}
		}
		for(int i=0;i<=list.size();i++) {
			visited = new boolean[list.size()];
			blist = new ArrayList<>();
			dfs(0, i);
		}
		System.out.println(-1);
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			for(int i=1;i<=H;i++) {
				for(int j=1;j<=N;j++) {
					carr[i][j] = arr[i][j];
				}
			}
			if(blist.size()>3 && min==Integer.MAX_VALUE) {
				System.out.println(-1);
				System.exit(0);
			}
//						System.out.println(blist);
			if(blist.size()!=0) {
				for(int i=0;i<blist.size();i++) {
					if(carr[list.get(blist.get(i)).x][list.get(blist.get(i)).y] !=0 || carr[list.get(blist.get(i)).x][list.get(blist.get(i)).y+1] !=0) {
						return;
					}
					carr[list.get(blist.get(i)).x][list.get(blist.get(i)).y] = list.get(blist.get(i)).n;
					carr[list.get(blist.get(i)).x][list.get(blist.get(i)).y+1] = list.get(blist.get(i)).n;
				}				
			}
//			if(blist.get(0)==1 && blist.get(1)==8 && blist.get(1)==10) {
//				for(int i=1;i<)
//			}
//			System.out.println("33");
			int flag=0;
			for(int i=1;i<=N;i++) {
				int j=1;
				int xx = i;
				while(true) {
					if(carr[j][xx]==0) {
						j++;
					}else {
						if(xx+1<=N && carr[j][xx]==carr[j][xx+1]) {
							xx++;
							j++;
						}else if(xx-1>=1 && carr[j][xx]==carr[j][xx-1]) {
							xx--;
							j++;
						}
					}
					if(j>H) {
						break;
					}
				}
//				System.out.println(i+" "+xx);
				if(i!=xx) {
					flag=1;
				}
			}
			//성공했을때
			if(flag==0) {
				System.out.println(blist.size());
				System.exit(0);
			}
			return;
		}
		for(int i=start;i<list.size();i++) {
			if(!visited[i]) {
				visited[i] = true;
				blist.add(i);
				dfs(i,r-1);
				blist.remove(blist.size()-1);
				visited[i] = false;
			}
		}
	}
}