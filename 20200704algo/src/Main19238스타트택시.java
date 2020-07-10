import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main19238스타트택시 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int N,M,K,sx,sy, cnum=0, clear=0;
	static int[][] arr;
	static int[][][] visited;
	static ArrayList<SPoint>[][] list;
	static class SPoint implements Comparable<SPoint>{
		int x;
		int y;
		int flag;
		int dist;
		int num;
		int g;//연료량
		public SPoint(int x, int y, int g ,int dist) {
			super();
			this.x = x;
			this.y = y;
			this.g = g;
			this.dist = dist;
		}
		public SPoint(int x, int y, int g) {
			super();
			this.x = x;
			this.y = y;
			this.g = g;
		}
		public SPoint(int flag, int num) {
			super();
			this.flag = flag;
			this.num = num;
		}
		@Override
		public int compareTo(SPoint o) {
			if(this.dist==o.dist) {
				if(this.x==o.x) {
					return this.y-o.y;
				}
				return this.x-o.x;
			}
			return this.dist-o.dist;
		}
		@Override
		public String toString() {
			return "SPoint [x=" + x + ", y=" + y + ", dist=" + dist + ", g=" + g + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		visited = new int[N+1][N+1][1];
		list = new ArrayList[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());//0빈칸, 1벽
				list[i][j] = new ArrayList<>();
			}
		}
		st = new StringTokenizer(br.readLine());//택시 시작 위치
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			list[x1][y1].add(new SPoint(0, i+2));//출발
			list[x2][y2].add(new SPoint(1, i+2));//도착
		}
		for(int i=0;i<M;i++) {
//			sx=0;
//			sy=0;
			bfs1(sx, sy);//승객찾기
//			System.out.println(K);
			bfs2(sx, sy);//승객 데려다 주기
//			System.out.println(K);
		}
		if(clear==M) {
			System.out.println(K);
		}else {
			System.out.println(-1);
		}
	}
	private static void bfs2(int sx1, int sy1) {
		Queue<SPoint> q = new LinkedList<>();
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				visited[i][j][0] = Integer.MAX_VALUE;
			}
		}
		visited[sx1][sy1][0]=0;
		q.add(new SPoint(sx1, sy1, K));
		int dist = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0;s<size;s++) {
				SPoint sp = q.poll();
//				if(sp.g<0) {
//					System.out.println(-1);
//					System.exit(0);
//				}
				for(int i=0;i<list[sp.x][sp.y].size();i++) {
					if(list[sp.x][sp.y].get(i).num==cnum && list[sp.x][sp.y].get(i).flag==1 && sp.g>=0) {
						//고객 데려다주기 성공한 경우, 태우고 이동한 거리만큼 연료 충전, 이때가지 데려다준 고객 +1 시켜주기
						sx = sp.x;
						sy = sp.y;
						K=sp.g+(dist*2);
						clear++;
						list[sp.x][sp.y].remove(i);
					}
				}
				for(int i=0;i<4;i++) {
					int tx = sp.x+dx[i];
					int ty = sp.y+dy[i];
					if(tx<1 || ty<1 || tx>N || ty>N) {
						continue;
					}
					if(visited[tx][ty][0]<=dist+1) {
						continue;
					}
					if(arr[tx][ty]==1) {
						continue;
					}
					visited[tx][ty][0]=dist+1;
					q.add(new SPoint(tx, ty, sp.g-1));
				}
			}
			dist++;
		}
	}
	private static void bfs1(int sx1, int sy1) {
		int dist = 0;
		PriorityQueue<SPoint> pq = new PriorityQueue<>();
		//		Queue<SPoint> pq = new LinkedList<>();
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				visited[i][j][0] = Integer.MAX_VALUE;
			}
		}
		visited[sx1][sy1][0] = 0;
		pq.add(new SPoint(sx1, sy1, K, 0));
		outer : while(!pq.isEmpty()) {
			int size = pq.size();
//			System.out.println(pq.toString());
			//			System.out.println(size);
//			System.out.println("-------------");
			for(int s=0;s<size;s++) {
				SPoint sp = pq.poll();
//				if(sp.g<0) {
//					System.out.println(-1);
//					System.exit(0);
//				}
//				System.out.println(sp.x+", "+sp.y);
				for(int i=0;i<list[sp.x][sp.y].size();i++) {
					if(sp.g>=0 && list[sp.x][sp.y].get(i).flag==0) {
						//						System.out.println(sp.x+", "+sp.y);
//						System.out.println(list[sp.x][sp.y].get(i).num+"승객찾음!");
						K = sp.g;//현재 기름 갱신
						cnum = list[sp.x][sp.y].get(i).num;//현재 태운 고객 번호 갱신
						list[sp.x][sp.y].remove(i);
						sx = sp.x;
						sy = sp.y;
						//						System.out.println("고객  찾기까지 이동한 거리: "+dist);
						break outer;
					}
				}
				for(int i=0;i<4;i++) {
					int tx = sp.x+dx[i];
					int ty = sp.y+dy[i];
					//					System.out.println(tx+", "+ty);
					if(tx<1 || ty<1 || tx>N || ty>N) {
						continue;
					}
					if(visited[tx][ty][0]<=(dist+1)) {
						continue;
					}
					if(arr[tx][ty]==1) {
						continue;
					}
					pq.add(new SPoint(tx, ty, sp.g-1, sp.dist+1));
					visited[tx][ty][0]=dist+1;
				}
			}
			dist++;
		}
	}
}