import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SolutionSWEA4008숫자만들기 {
	static int T, N, min, max;
	static ArrayList<Integer> nlist;
	static ArrayList<Integer> list;
	static int[] arr;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			nlist = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			arr = new int[4];
			for(int i=0;i<4;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				nlist.add(Integer.parseInt(st.nextToken()));
			}
			list = new ArrayList<>();
			int num = nlist.get(0);
			dfs(num, arr[0], arr[1], arr[2], arr[3], 1);
			System.out.println("#"+tc+" "+(max-min));
		}
	}
	private static void dfs(int num, int a1, int a2, int a3, int a4, int idx) {
		if(idx==N) {
			if(min>num) {
				min = num;
			}
			if(max<num) {
				max = num;
			}
			System.out.println(max);
			return;
		}
		for(int i=0;i<4;i++) {
			if(i==0) {
				if(a1>0) {
					dfs(num+nlist.get(idx), a1-1, a2, a3, a4, idx+1);
				}
			}
			if(i==1) {
				if(a2>0) {
					dfs(num-nlist.get(idx), a1, a2-1, a3, a4, idx+1);
				}
			}
			if(i==2) {
				if(a3>0) {
					dfs(num*nlist.get(idx), a1, a2, a3-1, a4, idx+1);
				}
			}
			if(i==3) {
				if(a4>0) {
					dfs(num/nlist.get(idx), a1, a2, a3, a4-1, idx+1);
				}
			}
		}
	}
}