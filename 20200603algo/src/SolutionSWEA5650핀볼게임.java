import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SolutionSWEA5650«…∫º∞‘¿” {
	static int T,N,max, sys=0;
	static int[][] arr, flag;
	static boolean[] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class SPoint{
		int x;
		int y;
		int num;
		public SPoint(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	static ArrayList<SPoint> slist;
	static ArrayList<SPoint> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			max = Integer.MIN_VALUE;
			slist = new ArrayList<>();
			list = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j]>5) {
						list.add(new SPoint(i, j, arr[i][j]));
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					for(int k=0;k<4;k++) {
						if(arr[i][j]==0) {
							sys=0;
							visited = new boolean[11];
							if(i==3 && j==8 && k==1) {
								sys=1;
							}
							solve(i,j,k);											
						}						
					}
				}
			}
			System.out.println("#"+tc+" "+max);
		}
	}
	private static void solve(int sx, int sy, int dir) {
		int curx = sx;
		int cury = sy;
		int score = 0;
		while(true) {
			curx+=dx[dir];
			cury+=dy[dir];
//			if(sys==1) {
//				System.out.println(curx+" "+cury);
//			}
			if(curx>=0 && cury>=0 && curx<N && cury<N && ((curx==sx && cury==sy) || arr[curx][cury]==-1)) {
				break;
			}
			if(curx<0 || cury<0 || curx>=N || cury>=N) {
				dir = rotate(5, dir);
				score++;
				curx += dx[dir];
				cury += dy[dir];
				if(arr[curx][cury]==-1 || (curx==sx&&cury==sy)) {
					break;
				}
				if(arr[curx][cury]>0 && arr[curx][cury]<=5) {
					dir = rotate(arr[curx][cury], dir);
					score++;
					continue;
				}
				if(arr[curx][cury]>=6) {
					for(int i=0;i<list.size();i++) {
						if((curx!=list.get(i).x||cury!=list.get(i).y)&&arr[curx][cury]==list.get(i).num) {
							visited[arr[curx][cury]]= true;
							curx = list.get(i).x;
							cury = list.get(i).y;
							break;
						}
					}
					continue;
				}
				continue;
			}
			if(arr[curx][cury]>0 && arr[curx][cury]<=5) {
				dir = rotate(arr[curx][cury], dir);
				score++;
				continue;
			}
			if(arr[curx][cury]>=6) {
				for(int i=0;i<list.size();i++) {
					if((curx!=list.get(i).x||cury!=list.get(i).y)&&arr[curx][cury]==list.get(i).num) {
						visited[arr[curx][cury]]= true;
						curx = list.get(i).x;
						cury = list.get(i).y;
						break;
					}
				}
				continue;
			}
		}
		if(max<score) {
			max = score;
		}
	}
	private static int rotate(int num, int dir) {
		if(num==1) {
			if(dir==0) {
				return 1;
			}else if(dir==1) {
				return 3;
			}else if(dir==2) {
				return 0;
			}else {
				return 2;
			}
		}else if(num==2) {
			if(dir==0) {
				return 3;
			}else if(dir==1) {
				return 0;
			}else if(dir==2) {
				return 1;
			}else {
				return 2;
			}
		}else if(num==3) {
			if(dir==0) {
				return 2;
			}else if(dir==1) {
				return 0;
			}else if(dir==2) {
				return 3;
			}else {
				return 1;
			}
		}else if(num==4) {
			if(dir==0) {
				return 1;
			}else if(dir==1) {
				return 2;
			}else if(dir==2) {
				return 3;
			}else {
				return 0;
			}
		}else {
			if(dir==0) {
				return 1;
			}else if(dir==1) {
				return 0;
			}else if(dir==2) {
				return 3;
			}else {
				return 2;
			}
		}
	}
}