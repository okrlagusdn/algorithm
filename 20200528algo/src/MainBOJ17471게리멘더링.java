import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ17471게리멘더링 {
	static int N,flag,sum1,sum2, min = Integer.MAX_VALUE;
	static boolean[] visited;
	static ArrayList<Integer> list2;
	static int[] array;
	static int[] arr;
	static ArrayList<Integer> alist[];
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		alist = new ArrayList[N+1];
		arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			if(sec!=0) {
				alist[i] = new ArrayList<>();
				for(int j=0;j<sec;j++) {
					alist[i].add(Integer.parseInt(st.nextToken()));
				}				
			}
		}
		for(int i=1;i<N;i++) {
			visited = new boolean[N+1];
			dfs(1, i);
		}
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}
	private static void dfs(int start, int r) {
		if(r==0) {
			flag=0;
//			System.out.println(list);//첫번째 그룹
			list2 = new ArrayList<>();
			for(int i=1;i<=N;i++) {
				if(!visited[i]) {
					list2.add(i);
				}
			}//두번째 그룹
			boolean[] visited2 = new boolean[N+1];
			for(int i=1;i<visited.length;i++) {
				visited2[i] = visited[i];
			}
//			System.out.println(list2);
			//각각의 그룹이 한 선거구인지 확인하고 최소값 갱신하는 로직
			solve(list,visited2);
			for(int i=1;i<visited.length;i++) {
				visited2[i] = !visited[i];
			}
			solve(list2,visited2);
			if(flag==2) {
				sum1=0;
				sum2=0;
				for(int i=0;i<list.size();i++) {
					sum1+=arr[list.get(i)];
				}
				for(int i=0;i<list2.size();i++) {
					sum2+=arr[list2.get(i)];
				}
				int result = Math.abs(sum1-sum2);
//				System.out.println(result);
				if(min>result) {
					min = result;
				}
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
	private static void solve(ArrayList<Integer> list, boolean[] visited) {
//		ArrayList<Integer> arrlist = new ArrayList<>();
		HashSet<Integer> hash = new HashSet<>();
//		arrlist.add(list.get(0));
		hash.add(list.get(0));
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(list.get(0));
		while(!q.isEmpty()) {
			int num = q.poll();
			if(alist[num]!=null) {
				for(int i=0;i<alist[num].size();i++) {
//				System.out.println("num:"+num);
//				System.out.println(alist[num].get(i));
					if(!visited[alist[num].get(i)]) {
						continue;
					}
					q.add(alist[num].get(i));
//				arrlist.add(alist[num].get(i));
					hash.add(alist[num].get(i));
					visited[alist[num].get(i)]=false;
				}
			}
		}
		if(list.size()==hash.size()) {
			Iterator iter = hash.iterator();
			int idx=0;
			while(iter.hasNext()) {
				int number = (int)iter.next();
//				System.out.println("sdkfjasdlfklajlsdfaj");
//				System.out.println(number);
//				if((int)iter.next()!=list.get(idx)) {
//					System.out.println("다르다");
//					return;
//				}
				idx++;
			}
			flag++;
		}
//		System.out.println("list : "+list);
//		System.out.println("hash : "+hash);
	}
}