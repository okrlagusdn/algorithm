import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MainBOJ17135Ä³½½µðÆæ½º {
	static int N, M, D,result, max = Integer.MIN_VALUE;
	static int[][] arr, carr;
	static boolean[] visited;
	static ArrayList<SPoint> elist2;
	static ArrayList<SPoint> dlist;
	static ArrayList<Integer> glist = new ArrayList<>();
	static class SPoint implements Comparable<SPoint>{
		int x;
		int y;
		int dis;
		public SPoint(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(SPoint o) {
			if(this.dis>o.dis) {
				return 1;
			}else if(this.dis==o.dis) {
				return this.y>o.y? 1:-1;
			}else {
				return -1;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		visited = new boolean[M];
		arr = new int[N][M];
		carr = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		elist2 = new ArrayList<SPoint>();
		dlist = new ArrayList<SPoint>();
		dfs(0, 3);
		System.out.println(max);
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			result=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					carr[i][j] = arr[i][j];
				}
			}
			while(true) {
				int flag=0;
				outer : for(int i=0;i<N;i++) {
					for(int j=0;j<M;j++) {
						if(carr[i][j]==1) {
							flag=1;
							break outer;
						}
					}
				}
				if(flag==0) {
					break;
				}
				for(int i=0;i<glist.size();i++) {
					for(int j=0;j<N;j++) {
						for(int k=0;k<M;k++) {
							if(carr[j][k]==1) {
								int dist = Math.abs(N-j)+Math.abs(glist.get(i)-k);
								if(dist>D) {
									continue;
								}
								elist2.add(new SPoint(j, k, dist));
							}
						}
					}
					if(elist2.size()>0) {
						Collections.sort(elist2);						
						for(int j=0;j<elist2.size();j++) {
							dlist.add(new SPoint(elist2.get(j).x, elist2.get(j).y));
							elist2.clear();
							break;
						}
					}
				}
				if(dlist.size()>0) {
					for(int i=0;i<dlist.size();i++) {
						if(carr[dlist.get(i).x][dlist.get(i).y]==1) {
							carr[dlist.get(i).x][dlist.get(i).y] = 0;
							result++;							
						}
					}					
					dlist.clear();
				}
				for(int i=N-1;i>=0;i--) {
					for(int j=0;j<M;j++) {
						if(carr[i][j]==1) {
							if(i==N-1) {
								carr[i][j]=0;
								continue;
							}
							carr[i][j]=0;
							carr[i+1][j]=1;
						}
					}
				}
			}
			if(max<result) {
				max = result;
			}
		}
		for(int i=start;i<M;i++) {
			if(!visited[i]) {
				visited[i] = true;
				glist.add(i);
				dfs(i+1, r-1);
				glist.remove(glist.size()-1);
				visited[i]=false;
			}
		}
	}
}