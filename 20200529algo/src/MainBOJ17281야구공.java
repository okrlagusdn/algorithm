import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ17281¾ß±¸°ø {
	static int N, max = Integer.MIN_VALUE;
	static int[][] arr;
	static int[] ru;
	static ArrayList<Integer> list = new ArrayList<>();
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][9];
		visited = new boolean[9];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(9);
		System.out.println(max);
	}
	private static void solve(int r) {
		if(r==0) {
			ru = new int[4];
			int result=0;
			if(list.get(3)!=0) {
				return;
			}
			int turn = 0;
			int outc = 0;
			int idx = 0;
			while(true) {
				ru[0] = 1;
				if(turn>=N) {
					break;
				}
				if(arr[turn][list.get(idx)]==0) {
					outc++;
					idx++;
				}else if(arr[turn][list.get(idx)]==1 || arr[turn][list.get(idx)]==2 || arr[turn][list.get(idx)]==3 || arr[turn][list.get(idx)]==4) {
					for(int i=3;i>=0;i--) {
						if(ru[i]==1) {
							int tx = i+arr[turn][list.get(idx)];
							if(tx>3) {
								result++;
								ru[i] = 0;
							}else {
								ru[tx]=1;
								ru[i]=0;								
							}
						}
					}
					idx++;
				}
				if(idx>8) {
					idx=0;
				}
				if(outc==3) {
					turn++;
					outc = 0;
					ru = new int[4];
				}
			}
			if(max<result) {
				max = result;
			}
			return;
		}
		for(int i=0;i<9;i++) {
			if(!visited[i]) {
				visited[i] = true;
				list.add(i);
				solve(r-1);
				list.remove(list.size()-1);
				visited[i] = false;
			}
		}
	}
}