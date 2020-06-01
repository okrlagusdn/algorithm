import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SolutionSWEA2383점심식사시간 {
	static int T,N,min;
	static int[][] arr;
	static int[][] stair;
	static ArrayList<SPoint> plist;
	static ArrayList<SPoint> stair1;
	static ArrayList<SPoint> stair2;
	static ArrayList<SPoint> wait;
	static class SPoint{
		int x;
		int y;
		int t;
		int num;
		public SPoint(int t, int num) {
			super();
			this.t = t;
			this.num = num;
		}
		public SPoint(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
	static ArrayList<Integer> list1;
	static ArrayList<Integer> list2;
	static SPoint[][] p;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			wait = new ArrayList<>();
			min = Integer.MAX_VALUE;
			list1 = new ArrayList<>();
			list2 = new ArrayList<>();
			stair1 = new ArrayList<>();
			stair2 = new ArrayList<>();
			stair = new int[3][2];//계산 좌표 저장
			plist = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			p = new SPoint[N][N];
			int idx = 1;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j]==1) {
						plist.add(new SPoint(i, j, 0));
					}
					if(arr[i][j]>=2) {
						stair[idx][0] = i;
						stair[idx][1] = j;
						idx++;
					}
				}
			}
			visited = new boolean[plist.size()];
			for(int i=0;i<=plist.size();i++) {
				dfs(0, i);
			}
			System.out.println("#"+tc+" "+min);
		}
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			int complet = 0;
			list2 = new ArrayList<>();
			//			System.out.println(list1);
			for(int i=0;i<plist.size();i++) {
				if(!visited[i]) {
					list2.add(i);
				}
			}
			//			System.out.println(list2);
			for(int i=0;i<list1.size();i++) {
				int x = plist.get(list1.get(i)).x;
				int y = plist.get(list1.get(i)).y;
				int dis = Math.abs(x-stair[1][0])+Math.abs(y-stair[1][1]);
				p[x][y] = new SPoint(dis, 1);
				//				stair1.add(new SPoint(x, y, dis));
			}
			for(int i=0;i<list2.size();i++) {
				int x = plist.get(list2.get(i)).x;
				int y = plist.get(list2.get(i)).y;
				int dis = Math.abs(x-stair[2][0])+Math.abs(y-stair[2][1]);
				p[x][y] = new SPoint(dis, 2);
				//				stair2.add(new SPoint(x, y, dis));
			}
			int time = 0;
			while(true) {
				for(int i=0;i<stair1.size();i++) {
					stair1.get(i).t--;
					if(stair1.get(i).t==0) {
						complet++;
						stair1.remove(i);
						i--;
					}
				}
				for(int i=0;i<stair2.size();i++) {
					stair2.get(i).t--;
					if(stair2.get(i).t==0) {
						complet++;
						stair2.remove(i);
						i--;
					}
				}
				for(int i=0;i<wait.size();i++) {
					if(wait.get(i).num==1) {
						if(stair1.size()<3) {
							stair1.add(wait.get(i));
							wait.remove(i);
							i--;
						}
					}else {
						if(stair2.size()<3) {
							stair2.add(wait.get(i));
							wait.remove(i);
							i--;
						}
					}
				}
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(p[i][j]!=null) {
							if(p[i][j].t==0) {
								if(p[i][j].num==1) {
									p[i][j].t = arr[stair[1][0]][stair[1][1]];
									wait.add(p[i][j]);
									p[i][j] = null;
//									if(stair1.size()<3) {
//										stair1.add(p[i][j]);
//										p[i][j] = null;
//									}
								}else {
									p[i][j].t = arr[stair[2][0]][stair[2][1]];
									wait.add(p[i][j]);
									p[i][j]=null;
//									if(stair2.size()<3) {
//										stair2.add(p[i][j]);
//										p[i][j] = null;
//									}
								}
							}else {
								p[i][j].t--;
							}
						}
					}
				}
				if(complet==plist.size()) {
					break;
				}
				time++;
			}
			if(min>time) {
				min = time;
			}
			return;
		}
		for(int i=start;i<plist.size();i++) {
			if(!visited[i]) {
				visited[i] = true;
				list1.add(i);
				dfs(i+1, r-1);
				list1.remove(list1.size()-1);
				visited[i] = false;
			}
		}
	}
}