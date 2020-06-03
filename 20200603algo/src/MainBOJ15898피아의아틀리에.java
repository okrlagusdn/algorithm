import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ15898피아의아틀리에 {
	static int N, result, max= Integer.MIN_VALUE;
	static int[][][] arr;
	static char[][][] color;
	static int[][] ga;
	static char[][] gc;
	static ArrayList<Integer> list1;
	static boolean[] visited1;
	static ArrayList<Integer> list2;
	static ArrayList<Integer> list3;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		list1 = new ArrayList<>();
		list2 = new ArrayList<>();
		list3 = new ArrayList<>();
		N = Integer.parseInt(br.readLine());
		visited1 = new boolean[N];
		arr = new int[N][4][4];
		color = new char[N][4][4];
		ga = new int[5][5];
		gc = new char[5][5];
		for(int t=0;t<N;t++) {
			for(int i=0;i<4;i++) {
				st = new StringTokenizer(br.readLine()); 
				for(int j=0;j<4;j++) {
					arr[t][i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<4;i++) {
				String s = br.readLine();
				String a = s.trim().replaceAll(" ", "");
				for(int j=0;j<4;j++) {
					color[t][i][j] = a.charAt(j);
				}
			}
		}
		dfs1(3);
		System.out.println(max);
	}
	private static void dfs1(int r) {
		if(r==0) {
			dfs2(3);
			return;
		}
		for(int i=0;i<N;i++) {
			if(!visited1[i]) {
				visited1[i] = true;
				list1.add(i);
				dfs1(r-1);
				list1.remove(list1.size()-1);
				visited1[i] = false;
			}
		}
	}
	private static void dfs2(int r) {
		if(r==0) {
			dfs3(3);
			return;
		}
		for(int i=0;i<4;i++) {
			list2.add(i);
			dfs2(r-1);
			list2.remove(list2.size()-1);
		}
	}
	private static void dfs3(int r) {
		if(r==0) {
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					ga[i][j] = 0;
					gc[i][j] = 'W';
				}
			}
			
			for(int s=0;s<list1.size();s++) {
				int num = list2.get(s);
				int idx = list1.get(s);
				int x = num/2;
				int y = num%2;
				int sx = x-1;
				int sy = y;
				if(list3.get(s)==0) {
					for(int i=0;i<4;i++) {
						sx++;
						for(int j=0;j<4;j++) {
							if(ga[sx][sy]+arr[idx][i][j]>9) {
								ga[sx][sy]=9;
								sy++;
								continue;
							}
							if(ga[sx][sy]+arr[idx][i][j]<0) {
								ga[sx][sy]=0;
								sy++;
								continue;
							}
							ga[sx][sy]+=arr[idx][i][j];
							sy++;
						}
						sy=y;
						for(int j=0;j<4;j++) {
							if(color[idx][i][j]=='W') {
								sy++;
								continue;
							}else {
								gc[sx][sy]=color[idx][i][j];
							}
							sy++;
						}
						sy=y;
					}
				}else if(list3.get(s)==1) {
					for(int j=3;j>=0;j--) {
						sx++;
						for(int i=0;i<4;i++) {
							if(ga[sx][sy]+arr[idx][i][j]>9) {
								ga[sx][sy]=9;
								sy++;
								continue;
							}
							if(ga[sx][sy]+arr[idx][i][j]<0) {
								ga[sx][sy]=0;
								sy++;
								continue;
							}
							ga[sx][sy]+=arr[idx][i][j];
							sy++;
						}
						sy=y;
						for(int i=0;i<4;i++) {
							if(color[idx][i][j]=='W') {
								sy++;
								continue;
							}else {
								gc[sx][sy]=color[idx][i][j];
							}
							sy++;
						}
						sy=y;
					}
				}else if(list3.get(s)==2) {
					for(int i=3;i>=0;i--) {
						sx++;
						for(int j=3;j>=0;j--) {
							if(ga[sx][sy]+arr[idx][i][j]>9) {
								ga[sx][sy]=9;
								sy++;
								continue;
							}
							if(ga[sx][sy]+arr[idx][i][j]<0) {
								ga[sx][sy]=0;
								sy++;
								continue;
							}
							ga[sx][sy]+=arr[idx][i][j];
							sy++;
						}
						sy=y;
						for(int j=3;j>=0;j--) {
							if(color[idx][i][j]=='W') {
								sy++;
								continue;
							}else {
								gc[sx][sy]=color[idx][i][j];
							}
							sy++;
						}
						sy=y;
					}
				}else if(list3.get(s)==3) {
					for(int j=0;j<4;j++) {
						sx++;
						for(int i=3;i>=0;i--) {
							if(ga[sx][sy]+arr[idx][i][j]>9) {
								ga[sx][sy]=9;
								sy++;
								continue;
							}
							if(ga[sx][sy]+arr[idx][i][j]<0) {
								ga[sx][sy]=0;
								sy++;
								continue;
							}
							ga[sx][sy]+=arr[idx][i][j];
							sy++;
						}
						sy=y;
						for(int i=3;i>=0;i--) {
							if(color[idx][i][j]=='W') {
								sy++;
								continue;
							}else {
								gc[sx][sy]=color[idx][i][j];
							}
							sy++;
						}
						sy=y;
					}
				}
			}
			result=0;
			int red = 0;
			int green = 0;
			int blue = 0;
			int yellow = 0;
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					if(gc[i][j]=='R') {
						red+=ga[i][j];
					}
					if(gc[i][j]=='G') {
						green+=ga[i][j];
					}
					if(gc[i][j]=='B') {
						blue+=ga[i][j];
					}
					if(gc[i][j]=='Y') {
						yellow+=ga[i][j];
					}
				}
			}
			result = red*7 + green*3 + blue*5 + yellow*2;
			if(max<result) {
				max=result;
			}
			return;
		}
		for(int i=0;i<4;i++) {
			list3.add(i);
			dfs3(r-1);
			list3.remove(list3.size()-1);
		}

	}
}