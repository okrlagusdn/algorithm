import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class MainBOJ16235나무재테크 {
	static int N,M,K;//배열크기, 나무수, 세월
	static int[][] A;//양분 배열, 겨울에 더해지는 양분
	static int[][] arr;
	static int[] dx = {-1,1,0,0,1,-1,1,-1};
	static int[] dy = {0,0,-1,1,1,1,-1,-1};
	static class SPoint implements Comparable<SPoint>{
		int x;
		int y;
		int age;
		public SPoint(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}
		public SPoint(int age) {
			super();
			this.age = age;
		}
		@Override
		public int compareTo(SPoint o) {
			return this.age - o.age;
		}
	}
	static ArrayList<SPoint>[][] tlist;
	static ArrayList<SPoint> dlist;//죽은 나무리스트
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N+1][N+1];
		arr = new int[N+1][N+1];
		tlist = new ArrayList[N+1][N+1];
		dlist = new ArrayList<>();
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = 5;
				tlist[i][j] = new ArrayList<>();
			}
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			tlist[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(new SPoint(Integer.parseInt(st.nextToken())));
		}
		while(K-->0) {
			//봄
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(tlist[i][j].size()>1) {
						Collections.sort(tlist[i][j]);
						for(int s=0;s<tlist[i][j].size();s++) {
							int ag = tlist[i][j].get(s).age;
							//							System.out.println(ag);
							if(arr[i][j]>=ag) {
								arr[i][j]-=ag;
								tlist[i][j].get(s).age++;
							}else {
								dlist.add(new SPoint(i,j,ag));
								tlist[i][j].remove(s);
								s--;
							}
						}
					}else if(tlist[i][j].size()==1) {
						int ag = tlist[i][j].get(0).age;
						//						System.out.println(ag);
						if(arr[i][j]>=ag) {
							arr[i][j]-=ag;
							tlist[i][j].get(0).age++;
						}else {
							dlist.add(new SPoint(i,j,ag));
							tlist[i][j].remove(0);
						}
					}
				}
			}

			//여름
			Iterator<SPoint> it = dlist.iterator();
			while(it.hasNext()) {
				SPoint p = it.next();
				arr[p.x][p.y]+=(p.age/2);
				//				System.out.println(p.x+" "+p.y+" "+p.age/2);
			}
			dlist.clear();

			//가을
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					for(int s=0;s<tlist[i][j].size();s++) {
						if(tlist[i][j].get(s).age%5==0) {
							for(int k=0;k<8;k++) {
								int tx = i+dx[k];
								int ty = j+dy[k];
								if(tx<1 || ty<1 || tx>N || ty>N) {
									continue;
								}
								tlist[tx][ty].add(new SPoint(1));
							}
						}
					}
				}
			}

			//겨울
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					arr[i][j]+=A[i][j];
				}
			}
		}
		int result = 0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				result+=tlist[i][j].size();
			}
		}
		System.out.println(result);
	}
}