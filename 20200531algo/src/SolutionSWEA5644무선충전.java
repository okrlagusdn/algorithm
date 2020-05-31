import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionSWEA5644무선충전 {
	static int T, M, A,sx,sy,c,p;
	static int[] dx = {0,-1,0,1,0};//x,상,우,하,좌
	static int[] dy = {0,0,1,0,-1};
	static int[][] darr;
	static int[][] arr;
	static boolean[][] visited;
	static class SPoint{
		int num;
		int x;
		int y;
		int po;
		public SPoint(int num, int x, int y) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
		}
		public SPoint(int num, int po) {
			super();
			this.num = num;
			this.po = po;
		}
	}
	static ArrayList<SPoint>[][] plist = new ArrayList[11][11];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			darr = new int[2][M+1];
			arr = new int[11][11];
			for(int i=1;i<=10;i++) {
				for(int j=1;j<=10;j++) {
					plist[i][j] = new ArrayList<>();
					plist[i][j].add(new SPoint(0, 0));
				}
			}
			for(int i=0;i<2;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1;j<=M;j++) {
					darr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int idx=1;
			for(int i=0;i<A;i++) {
				st = new StringTokenizer(br.readLine());
				sy = Integer.parseInt(st.nextToken());
				sx = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				p = Integer.parseInt(st.nextToken());
				bfs(idx, sx,sy,c,p);
				idx++;
			}
			int result=0;
			int max = Integer.MIN_VALUE;
			if(plist[1][1].size()>0) {
				for(int i=0;i<plist[1][1].size();i++) {
					if(max<plist[1][1].get(i).po) {
						max = plist[1][1].get(i).po;
					}
				}
			}
			result+=max;
			max = Integer.MIN_VALUE;
			if(plist[10][10].size()>0) {
				for(int i=0;i<plist[10][10].size();i++) {
					if(max<plist[10][10].get(i).po) {
						max = plist[10][10].get(i).po;
					}
				}
			}
			result+=max;
			int tx1=1;
			int ty1=1;
			int tx2=10;
			int ty2=10;
			for(int i=1;i<=M;i++) {
				max = Integer.MIN_VALUE;
				tx1 +=dx[darr[0][i]]; 
				ty1 +=dy[darr[0][i]]; 
				tx2 +=dx[darr[1][i]]; 
				ty2 +=dy[darr[1][i]];
				for(int j=0;j<plist[tx1][ty1].size();j++) {
					for(int k=0;k<plist[tx2][ty2].size();k++) {
						int r = 0;
//						System.out.println(i+"초 "+plist[tx1][ty1].get(j).po);
//						System.out.println(i+"초 "+plist[tx2][ty2].get(k).po);
						if(plist[tx1][ty1].get(j).num==plist[tx2][ty2].get(k).num) {
							r+=plist[tx1][ty1].get(j).po;
						}else {
							r+=(plist[tx1][ty1].get(j).po+plist[tx2][ty2].get(k).po);
						}
//						System.out.println(r);
						if(max<r) {
							max = r;
						}
					}
				}
				result+=max;
			}
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void bfs(int num, int sx2, int sy2, int c2, int p2) {
		visited = new boolean[11][11];
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(num, sx2, sy2));
		plist[sx][sy].add(new SPoint(num, p2));
		for(int k=0;k<c2;k++) {
			int size = q.size();
			for(int s=0;s<size;s++) {
				SPoint p = q.poll();
				for(int i=1;i<5;i++) {
					int tx = p.x+dx[i];
					int ty = p.y+dy[i];
					if(tx<1 || ty<1 || tx>10 || ty>10) {
						continue;
					}
					if(visited[tx][ty]) {
						continue;
					}
					plist[tx][ty].add(new SPoint(p.num, p2));
					q.add(new SPoint(p.num, tx, ty));
					arr[tx][ty] = p.num;
					visited[tx][ty]=true;
				}
			}
		}
	}
}