import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SolutionSWEA2115¹ú²ÜÃ¤Ãë {
static int T,N,M,C,max, x,y, maxresult;
static int[][] arr;
static ArrayList<Integer> list;
static ArrayList<Integer> list2;
static boolean[] visited;
static boolean[] visited2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			list = new ArrayList<>();
			max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[N*N];
			dfs(0, 2);
			System.out.println("#"+tc+" "+max);
		}
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			int money = 0;
			for(int i=0;i<list.size();i++) {
				maxresult = 0;
				x = list.get(i)/N;
				y = list.get(i)%N;
				for(int j=0;j<M;j++) {
					if(y+j>N-1) {
						return;
					}
				}
				list2 = new ArrayList<>();
				visited2 = new boolean[M];
				for(int k=1;k<=M;k++) {
					dfs2(0, k);					
				}
				money+=maxresult;
			}
			if(max<money) {
				max = money;
			}
			return;
		}
		for(int i=start;i<N*N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				list.add(i);
				dfs(i+M, r-1);
				list.remove(list.size()-1);
				visited[i] = false;
			}
		}
	}
	private static void dfs2(int start, int r) {
		if(r==0) {
			int maxresult2 = 0;
			int sum = 0;
			for(int i=0;i<list2.size();i++) {
				if(y+list2.get(i)>N-1) {
					return;
				}
				sum+=arr[x][y+list2.get(i)];
				maxresult2+=arr[x][y+list2.get(i)]*arr[x][y+list2.get(i)];
			}
			if(sum>C) {
				return;
			}
			if(maxresult<maxresult2) {
				maxresult=maxresult2;
			}
			return;
		}
		for(int i=start;i<M;i++) {
			if(!visited2[i]) {
				visited2[i] = true;
				list2.add(i);
				dfs2(i+1, r-1);
				list2.remove(list2.size()-1);
				visited2[i] = false;
			}
		}
	}
}