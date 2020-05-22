import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ17837새로운게임2 {
	static int N, K, x, y, dir, turn;
	static int[][] arr;
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	static public class SPoint{
		int dir;
		int num;
		public SPoint(int dir, int num) {
			super();
			this.dir = dir;
			this.num = num;
		}
	}
	static ArrayList<SPoint> list[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		list = new ArrayList[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				list[i][j] = new ArrayList<SPoint>();
			}
		}
		for(int i=1;i<=K;i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			list[x][y].add(new SPoint(dir, i));
		}
		while(true) {
			turn++;
			for(int k=1;k<=K;k++) {
				outer2 : for(int i=1;i<=N;i++) {
					for(int j=1;j<=N;j++) {
						if(list[i][j].size()==0) {
							continue;
						}
						for(int s=0;s<list[i][j].size();s++) {
							if(list[i][j].get(s).num==k) {
								int tx = i+dx[list[i][j].get(s).dir];
								int ty = j+dy[list[i][j].get(s).dir];
								if(tx<1 || ty<1 || tx>N || ty>N || arr[tx][ty]==2) {//파란색이거나 벗어났을경우
									list[i][j].get(s).dir = rotate(list[i][j].get(s).dir);//방향 반대로 바꿈
									tx = i+dx[list[i][j].get(s).dir];
									ty = j+dy[list[i][j].get(s).dir];
									if(tx<1 || ty<1 || tx>N || ty>N || arr[tx][ty]==2) {//방향 반대로 바꾸고 한칸이동 실패시 가만히 있는다.
										break outer2;
									}
								}
								if(arr[tx][ty]==0) {
									ArrayList<SPoint> xlist = new ArrayList<>();
									int id = s;
									int size = list[i][j].size();
									for(int idx=s;idx<size;idx++) {
										xlist.add(list[i][j].get(id));
										list[i][j].remove(id);
									}
									for(int m=0;m<xlist.size();m++) {
										list[tx][ty].add(xlist.get(m));
									}
								}
								else if(arr[tx][ty]==1) {
									int size = list[i][j].size();
									for(int idx=s;idx<size;idx++) {
										list[tx][ty].add(list[i][j].get(list[i][j].size()-1));
										list[i][j].remove(list[i][j].size()-1);
									}
								}
								for(int ii=1;ii<=N;ii++) {
									for(int jj=1;jj<=N;jj++) {
										if(list[ii][jj].size()>=4) {
											System.out.println(turn);
											System.exit(0);
										}
									}
								}
								break outer2;
							}
						}
					}
				}				
			}
			if(turn>1000) {
				System.out.println(-1);
				System.exit(0);
			}
		}
	}
	private static int rotate(int d) {
		if(d==1) {
			return 2;
		}else if(d==2) {
			return 1;
		}else if(d==3) {
			return 4;
		}else {
			return 3;
		}
	}
}