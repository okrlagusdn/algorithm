import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ2252줄세우기 {
	static int N,M;
	static int[] arr, result;//진입차수 체크용 배열
	static ArrayList<Integer>[] list;
	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		arr = new int[N+1];
		result = new int[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
			arr[b]++;
		}
		for(int i=1;i<=N;i++) {
			if(arr[i]==0) {
				q.add(i);
			}
		}
		for(int i=1;i<=N;i++) {
			if(q.isEmpty()) {
				return;
			}
			int x = q.peek();
			q.poll();
			result[i] = x;
			for(int j=0;j<list[x].size();j++) {
				arr[list[x].get(j)]--;
				if(arr[list[x].get(j)]==0) {
					q.add(list[x].get(j));
				}
			}
		}
		for(int i=1;i<=N;i++) {
			System.out.print(result[i]+" ");
		}
	}
}