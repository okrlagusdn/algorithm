import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ17472다리만들기2 {
	static int N,M,num, min = Integer.MAX_VALUE;
	static int[][] arr;
	static int[] A;
	static boolean[] visit;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class SPoint implements Comparable<SPoint>{
		int x;
		int y;
		int dis;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public SPoint(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		@Override
		public String toString() {
			return "SPoint [x=" + x + ", y=" + y + ", dis=" + dis + "]";
		}
		@Override
		public int compareTo(SPoint o) {
			return this.dis-o.dis;
		}

	}
	static ArrayList<Integer> list = new ArrayList<>();
	static ArrayList<SPoint> blist = new ArrayList<>();//다리 리스트
	static HashSet<SPoint> bhash = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];
		arr = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int idx=1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!visited[i][j] && arr[i][j]>0) {
					numbering(idx, i, j);		
					idx++;
				}
			}
		}
		num = idx;
//		System.out.println(idx);
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(arr[i][j]>0) {
					bridge(i, j, arr[i][j]);
				}
			}
		}
		//		System.out.println("리스트");
		//		System.out.println(blist.toString());
		//		System.out.println("해시셋");
		//		System.out.println(bhash.toString());
		//		HashSet<SPoint> hash = new HashSet<SPoint>(blist);
		//		ArrayList<SPoint> list = new ArrayList<>(hash);
		Collections.sort(blist);
//		System.out.println(blist.toString());
//		System.out.println(blist.size());
		//		for(int i=0;i<blist.size();i++) {
		//			int a = blist.get(i).x;
		//			int b = blist.get(i).y;
		//			int c = blist.get(i).dis;
		//		}
		visit = new boolean[blist.size()];
		dfs(0, idx-2);		
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);		
		}
	}
	private static void dfs(int start, int r) {
		if(r==0) {
//			System.out.println(list);
			int result = 0;
			A = new int[num];
			for(int i=1;i<num;i++) {
				A[i] = i;
			}
			for(int i=0;i<list.size();i++) {
				unionSet(blist.get(list.get(i)).x, blist.get(list.get(i)).y);
				result+=blist.get(list.get(i)).dis;
			}
			for(int i=1;i<A.length;i++) {
				findSet(i);
			}
			int number = A[1];
			for(int i=2;i<A.length;i++) {
				if(number!=A[i]) {
					return;
				}
			}
			if(min>result) {
				min = result;
			}
			return;
		}
		for(int i=start;i<blist.size();i++) {
			if(!visit[i]) {
				visit[i]=true;
				list.add(i);
				dfs(i+1, r-1);
				list.remove(list.size()-1);
				visit[i]=false;
			}
		}
	}
	private static void unionSet(int u, int v) {
		u = findSet(u);
		v = findSet(v);
		if(u==v) {
			return;
		}
		A[v] = u;
	}
	private static int findSet(int v) {
		if(A[v]==v) {
			return v;
		}
		return A[v] = findSet(A[v]);
	}
	private static void bridge(int sx, int sy, int idx) {
		for(int i=0;i<4;i++) {
			int ind = 1;
			int len = 0;
			outer : while(true) {
				int tx = sx+dx[i]*ind;
				int ty = sy+dy[i]*ind;
				if(tx<0 || ty<0 || tx>=N || ty>=M) {
					break;
				}
				if(arr[tx][ty]==arr[sx][sy]) {
					break;
				}
				if(arr[tx][ty]>0 && arr[tx][ty]!=idx && len>=2) {
					if(idx<arr[tx][ty]) {
						for(int j=0;j<blist.size();j++) {
							if(blist.get(j).x==idx && blist.get(j).y==arr[tx][ty] && blist.get(j).dis==len) {
								break outer;
							}
						}
						blist.add(new SPoint(idx, arr[tx][ty], len));
					}else {
						for(int j=0;j<blist.size();j++) {
							if(blist.get(j).x==arr[tx][ty] && blist.get(j).y==idx && blist.get(j).dis==len) {
								break outer;
							}
						}
						blist.add(new SPoint(arr[tx][ty], idx, len));
					}
					break;
				}
				if(arr[tx][ty]==0) {
					len++;
					ind++;
					continue;
				}
				break;
			}
		}
	}
	private static void numbering(int idx, int sx, int sy) {
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(sx, sy));
		while(!q.isEmpty()) {
			SPoint p = q.poll();
			arr[sx][sy] = idx;
			visited[sx][sy] = true;
			for(int i=0;i<4;i++) {
				int tx = p.x+dx[i];
				int ty = p.y+dy[i];
				if(tx<0 || ty<0 || tx>=N || ty>=M) {
					continue;
				}
				if(visited[tx][ty]) {
					continue;
				}
				if(arr[tx][ty]==0) {
					continue;
				}
				visited[tx][ty] = true;
				arr[tx][ty] = idx;
				q.add(new SPoint(tx, ty));
			}
		}
	}
}
