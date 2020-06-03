import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SolutionSWEA2112보호필름 {
	static int T,D,W,K,min, flag, end;
	static int[][] arr, carr;
	static ArrayList<Integer> list;
	static ArrayList<Integer> list2;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			end=0;
			min = Integer.MAX_VALUE;
			list = new ArrayList<>();
			list2 = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[D][W];
			carr = new int[D][W];
			for(int i=0;i<D;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					carr[i][j] = arr[i][j];
				}
			}
			test();
			if(end==0) {
				for(int i=1;i<=K;i++) {
					visited = new boolean[D];
					dfs1(0, i);
					if(end==1) {
						break;
					}
				}				
			}
			System.out.println("#"+tc+" "+min);
		}
	}
	private static void dfs1(int start, int r) {
		if(end==1) {
			return;
		}
		if(r==0) {
//			System.out.println(list.size());
			dfs2(list.size());
			for(int i=0;i<D;i++) {
				for(int j=0;j<W;j++) {
					carr[i][j] = arr[i][j];
				}
			}
			return;
		}
		for(int i=start;i<D;i++) {
			if(!visited[i]) {
				visited[i] = true;
				list.add(i);
				dfs1(i+1, r-1);
				list.remove(list.size()-1);
				visited[i] = false;
			}
		}
	}
	private static void dfs2(int r) {
		if(r==0) {
			if(end==1) {
				return;
			}
			for(int i=0;i<list.size();i++) {
				int num1 = list.get(i);
				int num2 = list2.get(i);
				for(int j=0;j<W;j++) {
					carr[num1][j] = num2;
				}
			}
			test();
			//검사
			return;
		}
		for(int i=0;i<2;i++) {
			list2.add(i);
			dfs2(r-1);
			list2.remove(list2.size()-1);
		}
	}
	private static void test() {
		flag = 0;
		for(int j=0;j<W;j++) {	
			int num = carr[0][j];
			int cnt = 0;
			for(int i=0;i<D;i++) {
				if(num==carr[i][j]) {
					cnt++;
				}else {
					num = carr[i][j];
					cnt=1;
				}
				if(cnt>=K) {
					flag++;
					break;
				}
			}
		}
		if(flag==W) {
			end=1;
			if(min>list2.size()) {
				min = list2.size();
			}
		}
	}
}