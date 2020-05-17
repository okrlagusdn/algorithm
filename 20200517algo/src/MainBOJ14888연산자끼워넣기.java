import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ14888연산자끼워넣기 {
	static int N;
	static int[] arr;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static boolean[] visited;
	static ArrayList<Integer> list = new ArrayList<>();
	static ArrayList<Integer> list2 = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			int num = Integer.parseInt(st.nextToken());
			if(i==0) {
				for(int j=0;j<num;j++) {
					list.add(1);
				}
			}else if(i==1) {
				for(int j=0;j<num;j++) {
					list.add(2);
				}
			}else if(i==2) {
				for(int j=0;j<num;j++) {
					list.add(3);
				}
			}else if(i==3) {
				for(int j=0;j<num;j++) {
					list.add(4);
				}
			}
		}
		visited = new boolean[list.size()];
		dfs(0, list.size());
		System.out.println(max);
		System.out.println(min);
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			int result = arr[0];
			for(int i=0;i<list2.size();i++) {
				if(list2.get(i)==1) {// +
					result += arr[i+1];
				}else if(list2.get(i)==2) {// -
					result -= arr[i+1];
				}else if(list2.get(i)==3) {// *
					result *= arr[i+1];
				}else if(list2.get(i)==4) {// /
					result /= arr[i+1];
				}
			}
			if(min>result) {
				min = result;
			}
			if(max<result) {
				max = result;
			}
			return;
		}
		for(int i=start;i<list.size();i++) {
			if(!visited[i]) {
				visited[i] = true;
				list2.add(list.get(i));
				dfs(start, r-1);
				visited[i] = false;
				list2.remove(list2.size()-1);
			}
		}
	}
}