import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ17822원판돌리기 {
	static int N, M, T, flag;
	static int[][] arr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static long result = 0;
	static public class SPoint{
		int x;
		int d;
		int k;
		int y;
		public SPoint(int x, int d, int k) {
			super();
			this.x = x;
			this.d = d;
			this.k = k;
		}
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<SPoint> list = new ArrayList<>();
	static ArrayList<SPoint> dlist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new SPoint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		for(int i=0;i<list.size();i++) {
//			for(int l=1;l<=N;l++) {
//				for(int j=1;j<=M;j++) {
//					System.out.print(arr[l][j]+" ");
//				}System.out.println();
//			}
			int xx = list.get(i).x;
			int dd = list.get(i).d;
			int kk = list.get(i).k;
			for(int j=1;j<=N;j++) {
				if(j%xx==0) {
					for(int k=0;k<kk;k++) {
						rotate(arr, j, dd);
					}
				}
			}
			flag=0;
			dlist = new ArrayList<>();
			for(int j=1;j<=N;j++) {
				for(int k=1;k<=M;k++) {
					if(arr[j][k]==0) {
						continue;
					}
					erase(arr, j, k);
				}
			}
			for(int m=0;m<dlist.size();m++) {
				arr[dlist.get(m).x][dlist.get(m).y] = 0;
			}
			if(flag==0) {
				float avg = 0;
				int sum=0;
				for(int j=1;j<=N;j++) {
					for(int k=1;k<=M;k++) {
						avg+=arr[j][k];
						if(arr[j][k]>0) {
							sum++;							
						}
					}
				}
				avg /= sum;
				for(int j=1;j<=N;j++) {
					for(int k=1;k<=M;k++) {
						if(arr[j][k]==0) {
							continue;
						}
						if(arr[j][k]>avg) {
							arr[j][k]--;
						}else if(arr[j][k]<avg) {
							arr[j][k]++;
						}
					}
				}
			}
		}
//		for(int i=1;i<=N;i++) {
//			for(int j=1;j<=M;j++) {
//				System.out.print(arr[i][j]+" ");
//			}System.out.println();
//		}
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				result+=arr[i][j];
			}
		}
		System.out.println(result);
	}

	private static void erase(int[][] arr2, int px, int py) {
//		ArrayList<SPoint> dlist = new ArrayList<>();
		for(int i=0;i<4;i++) {
			int tx = px+dx[i];
			int ty = py+dy[i];
			if(tx<1) {
				continue;
			}
			if(ty<1) {
				ty=M;
			}
			if(tx>N) {
				continue;
			}
			if(ty>M) {
				ty=1;
			}
			if(arr2[tx][ty]!=arr2[px][py]) {
				continue;
			}
			dlist.add(new SPoint(tx, ty));
			dlist.add(new SPoint(px, py));
			flag=1;
		}
	}
	private static void rotate(int[][] a, int num, int dir) {
		if(dir==0) {
			int temp = a[num][M];
			for(int i=M;i>1;i--) {
				a[num][i] = a[num][i-1];
			}
			a[num][1] = temp;
		}else {
			int temp2 = a[num][1];
			for(int i=0;i<M;i++) {
				a[num][i] = a[num][i+1];
			}
			a[num][M] = temp2;
		}
	}
}
