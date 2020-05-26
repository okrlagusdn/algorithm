import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ13460구슬탈출2 {
	static int N, M,rx,ry,bx,by;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static char[][] arr;
	static public class SPoint{
		int x;
		int y;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static Queue<SPoint> rq = new LinkedList<>();
	static Queue<SPoint> bq = new LinkedList<>();	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				arr[i][j] = s.charAt(j);
				if(arr[i][j]=='R') {
					rq.add(new SPoint(i, j));
				}
				if(arr[i][j]=='B') {
					bq.add(new SPoint(i, j));
				}
			}
		}
		bfs();
	}
	private static void bfs() {
		int result=1;
		while(true) {
			if(result>=10) {
				System.out.println(-1);
				System.exit(0);
			}
			int size = rq.size();
			for(int k=0;k<size;k++) {
				SPoint rp = rq.poll();
				SPoint bp = bq.poll();
				for(int i=0;i<4;i++) {
					int flag=0;
					//빨, 파 둘다 진행
					int idx=1;
					while(true) {
						int tx = rp.x+dx[i]*idx;
						int ty = rp.y+dy[i]*idx;
						if(tx==bp.x && ty==bp.y) {
							flag=1;
							break;
						}
						if(arr[tx][ty]=='#') {
							break;
						}
						idx++;
					}
					idx = 1;
					while(true) {
						int tx = bp.x+dx[i]*idx;
						int ty = bp.y+dy[i]*idx;
						if(tx==rp.x && ty==rp.y) {
							flag=2;
							break;
						}
						if(arr[tx][ty]=='#') {
							break;
						}
						idx++;
					}
					if(flag==0) {
						int flag2=0;
						idx = 1;
						while(true) {
							rx = rp.x+dx[i]*idx;
							ry = rp.y+dy[i]*idx;
							if(arr[rx][ry]=='#') {
								flag2=1;
								break;
							}
							if(arr[rx][ry]=='O') {
								flag2=0;
								break;
							}
							idx++;
						}
						idx = 1;
						while(true) {
							bx = bp.x+dx[i]*idx;
							by = bp.y+dy[i]*idx;
							if(arr[bx][by]=='#') {
								if(flag2==0) {//빨간색 구멍에 들어가고, 파란색 안들어가면 게임끝 
									flag2=2;//게임 끝
								}else {
									flag2=3;//둘다 집어넣는다.
								}
								break;
							}
							if(arr[bx][by]=='O') {
								if(flag2==0) {
									flag2=4;//둘다 들어갔기 떄문에 둘다 안 넣음.
								}else {
									flag2=5;//빨간색 안들어가고, 파란색만 들어갔으므로 실패, 둘다 안 넣는다.
								}
								break;
							}
							idx++;
						}
						if(flag2==2) {
							System.out.println(result);
							System.exit(0);
						}
						if(flag2==3) {
							rq.add(new SPoint(rx-dx[i], ry-dy[i]));
							bq.add(new SPoint(bx-dx[i], by-dy[i]));
						}
						if(flag2==4 || flag2==5) {
							continue;
						}
					}else if(flag==1) {
						int flag2=0;
						idx = 1;
						while(true) {
							rx = rp.x+dx[i]*idx;
							ry = rp.y+dy[i]*idx;
							if(arr[rx][ry]=='#') {
								flag2=1;
								break;
							}
							if(arr[rx][ry]=='O') {
								flag2=0;
								break;
							}
							idx++;
						}
						idx = 1;
						while(true) {
							bx = bp.x+dx[i]*idx;
							by = bp.y+dy[i]*idx;
							if(arr[bx][by]=='#') {
								if(flag2==0) {//빨간색 구멍에 들어가고, 파란색 안들어가면 게임끝 
									flag2=2;//게임 끝
								}else {
									flag2=3;//둘다 집어넣는다.
								}
								break;
							}
							if(arr[bx][by]=='O') {
								if(flag2==0) {
									flag2=4;//둘다 들어갔기 떄문에 둘다 안 넣음.
								}else {
									flag2=5;//빨간색 안들어가고, 파란색만 들어갔으므로 실패, 둘다 안 넣는다.
								}
								break;
							}
							idx++;
						}
						if(flag2==2) {
							System.out.println(result);
							System.exit(0);
						}
						if(flag2==3) {
							rq.add(new SPoint(rx-dx[i]*2, ry-dy[i]*2));
							bq.add(new SPoint(bx-dx[i], by-dy[i]));
						}
						if(flag2==4 || flag2==5) {
							continue;
						}
					}else {
						int flag2=0;
						idx = 1;
						while(true) {
							rx = rp.x+dx[i]*idx;
							ry = rp.y+dy[i]*idx;
							if(arr[rx][ry]=='#') {
								flag2=1;
								break;
							}
							if(arr[rx][ry]=='O') {
								flag2=0;
								break;
							}
							idx++;
						}
						idx = 1;
						while(true) {
							bx = bp.x+dx[i]*idx;
							by = bp.y+dy[i]*idx;
							if(arr[bx][by]=='#') {
								if(flag2==0) {//빨간색 구멍에 들어가고, 파란색 안들어가면 게임끝 
									flag2=2;//게임 끝
								}else {
									flag2=3;//둘다 집어넣는다.
								}
								break;
							}
							if(arr[bx][by]=='O') {
								if(flag2==0) {
									flag2=4;//둘다 들어갔기 떄문에 둘다 안 넣음.
								}else {
									flag2=5;//빨간색 안들어가고, 파란색만 들어갔으므로 실패, 둘다 안 넣는다.
								}
								break;
							}
							idx++;
						}
						if(flag2==2) {
							System.out.println(result);
							System.exit(0);
						}
						if(flag2==3) {
							rq.add(new SPoint(rx-dx[i], ry-dy[i]));
							bq.add(new SPoint(bx-dx[i]*2, by-dy[i]*2));
						}
						if(flag2==4 || flag2==5) {
							continue;
						}
					}
//					System.out.println(flag);
//					System.out.println(rx+", "+ry);
//					System.out.println(bx+", "+by);
				}
			}
			result++;
		}
	}
}