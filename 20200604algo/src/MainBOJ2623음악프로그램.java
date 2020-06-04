import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ2623음악프로그램 {
	static int N,M, k;
	static int[] result, arr;
	static ArrayList<Integer>[] list;
	static ArrayList<Integer> slist = new ArrayList<>();
	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		result = new int[N+1];
		arr = new int[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			for(int j=0;j<k;j++) {
				slist.add(Integer.parseInt(st.nextToken()));
			}
			for(int j=0;j<slist.size()-1;j++) {
				int a = slist.get(j);
				int b = slist.get(j+1);
				list[a].add(b);
				list[b].add(a);
				arr[b]++;
			}
			slist.clear();
		}
		for(int i=1;i<=N;i++) {
			if(arr[i]==0) {
				q.add(i);
			}
		}
		for(int i=1;i<=N;i++) {
			if(q.isEmpty()) {
				System.out.println(0);
				break;
			}
			int x = q.poll();
			result[i] = x;
			for(int j=0;j<list[x].size();j++) {
				arr[list[x].get(j)]--;
				if(arr[list[x].get(j)]==0) {
					q.add(list[x].get(j));
				}
			}
		}
		for(int i=1;i<=N;i++) {
			System.out.println(result[i]);
		}
	}
}