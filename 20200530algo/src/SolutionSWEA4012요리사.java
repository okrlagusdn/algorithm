import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SolutionSWEA4012¿ä¸®»ç {
	static int T,N,min,A,B;
	static int[][] arr;
	static boolean[] visited;
	static boolean[] visited2;
	static boolean[] visited3;
	static ArrayList<Integer> list = new ArrayList<>();
	static ArrayList<Integer> list2 = new ArrayList<>();
	static ArrayList<Integer> flist = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1][N+1];
			for(int i=1;i<=N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1;j<=N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[N+1];
			dfs(1, N/2);
			System.out.println("#"+tc+" "+min);
		}
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			list2 = new ArrayList<>();
			for(int i=1;i<=N;i++) {
				if(!visited[i]) {
					list2.add(i);
				}
			}
			A = 0;
			B = 0;
			visited3 = new boolean[N/2+1];
			visited2 = new boolean[N/2+1];
			flist = new ArrayList<>();
			dfs3(list2, 0, 2);
			flist = new ArrayList<>();
			dfs2(list, 0, 2);
			int result = Math.abs((A-B));
			if(min>result) {
				min = result;
			}
			return;
		}
		for(int i=start;i<=N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				list.add(i);
				dfs(i+1, r-1);
				list.remove(list.size()-1);
				visited[i] = false;
			}
		}
	}
	private static void dfs2(ArrayList<Integer> listf2, int start, int r) {
		if(r==0) {
			A += arr[listf2.get(flist.get(1))][listf2.get(flist.get(0))]+arr[listf2.get(flist.get(0))][listf2.get(flist.get(1))];
			return;
		}
		for(int i=start;i<listf2.size();i++) {
			if(!visited2[i]) {
				visited2[i] = true;
				flist.add(i);
				dfs2(listf2, i+1, r-1);
				flist.remove(flist.size()-1);
				visited2[i] = false;
			}
		}
	}
	private static void dfs3(ArrayList<Integer> listf3, int start, int r) {
		if(r==0) {
			B += arr[listf3.get(flist.get(1))][listf3.get(flist.get(0))]+arr[listf3.get(flist.get(0))][listf3.get(flist.get(1))];
			return;
		}
		for(int i=start;i<listf3.size();i++) {
			if(!visited3[i]) {
				visited3[i] = true;
				flist.add(i);
				dfs3(listf3, i+1, r-1);
				flist.remove(flist.size()-1);
				visited3[i] = false;
			}
		}
	}
}