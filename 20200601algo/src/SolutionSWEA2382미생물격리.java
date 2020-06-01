import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SolutionSWEA2382미생물격리 {
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,-1,1};
	static int T,N,M,K,result;
	static int[][] arr;
	static class SPoint implements Comparable<SPoint>{
		int n;
		int d;
		int t;
		public SPoint(int n, int d, int t) {
			super();
			this.n = n;
			this.d = d;
			this.t = t;
		}
		@Override
		public int compareTo(SPoint o) {
			return o.n-this.n;
		}
	}
	static ArrayList<SPoint>[][] list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			result = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					list[i][j] = new ArrayList<>();
				}
			}
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				list[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(new SPoint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1));
			}
			for(int time=1;time<=M;time++) {
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(!list[i][j].isEmpty() && list[i][j].get(0).t==time) {
//							System.out.println(i+" "+j);
//							System.out.println(list[i][j].get(0).n);
							int dir = list[i][j].get(0).d;
							int tx = i+dx[dir];
							int ty = j+dy[dir];
							if(tx==0 || ty==0 || tx==N-1 || ty==N-1) {
								list[i][j].get(0).d = rotate(dir);
								list[i][j].get(0).n/=2;
							}
							if(list[i][j].get(0).n==0) {
								list[i][j].remove(0);
								continue;
							}
							list[i][j].get(0).t+=1; 
							list[tx][ty].add(list[i][j].get(0));
							list[i][j].remove(0);
						}
					}
				}
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						int num = 0;
						if(!list[i][j].isEmpty()) {
							if(list[i][j].size()==1) {
								continue;
							}
							Collections.sort(list[i][j]);
							for(int k=0;k<list[i][j].size();k++) {
								num+=list[i][j].get(k).n;
							}
							int ti = list[i][j].get(0).t;
							int di = list[i][j].get(0).d;
							list[i][j].clear();
							list[i][j].add(new SPoint(num, di, ti));
//							System.out.println(i+" "+j);
//							System.out.println(list[i][j].get(0).n);
						}
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!list[i][j].isEmpty()) {
						result += list[i][j].get(0).n;
					}
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}
	private static int rotate(int dir) {
		if(dir==1) {
			return 2;
		}else if(dir==2) {
			return 1;
		}else if(dir==3) {
			return 4;
		}else{
			return 3;
		}
	}
}