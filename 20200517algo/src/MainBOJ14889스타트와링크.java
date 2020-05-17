import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ14889스타트와링크 {
static int N;
static int[][] arr;
static boolean[] visited;
static ArrayList<Integer> list1;
static ArrayList<Integer> list2;
static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, N/2);
		System.out.println(min);
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			list1 = new ArrayList<>();
			list2 = new ArrayList<>();
			for(int i=0;i<N;i++) {
				if(visited[i]) {
//					System.out.print(i+" ");
					list1.add(i);
				}else {
					list2.add(i);
				}
			}
			int sum1=0;
			int sum2=0;
			for(int i=0;i<N/2-1;i++) {
				for(int j=i+1;j<N/2;j++) {
					sum1+=arr[list1.get(i)][list1.get(j)];
					sum1+=arr[list1.get(j)][list1.get(i)];
				}
			}
			
			for(int i=0;i<N/2-1;i++) {
				for(int j=i+1;j<N/2;j++) {
					sum2+=arr[list2.get(i)][list2.get(j)];
					sum2+=arr[list2.get(j)][list2.get(i)];
				}
			}
			
			int result = Math.abs(sum2-sum1);
			if(min>result) {
				min = result;
			}
			return;
		}
		for(int i=start;i<N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, r-1);
				visited[i] = false;
			}
		}
	}
}
