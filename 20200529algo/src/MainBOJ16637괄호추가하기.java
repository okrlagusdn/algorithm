import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainBOJ16637괄호추가하기 {
	static int N, sum, max = Integer.MIN_VALUE;
	static boolean[] visited;
	static ArrayList<Integer> list = new ArrayList<>();
	static ArrayList<Integer> nlist = new ArrayList<>();
	static ArrayList<Character> clist = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		for(int i=0;i<N;i++) {
			if(i%2==0) {
				nlist.add(s.charAt(i)-'0');
			}else {
				clist.add(s.charAt(i));
			}
		}
		visited = new boolean[clist.size()];
		for(int i=0;i<=10;i++) {
			dfs(0,i);
		}
		System.out.println(max);
	}
	private static void dfs(int start, int r) {
		if(r==0) {
//			System.out.println(list);
			ArrayList<Integer> slist = new ArrayList<>();
			ArrayList<Character> clist2 = new ArrayList<>();
			for(int i=0;i<nlist.size();i++) {
				slist.add(nlist.get(i));
			}
			for(int i=0;i<clist.size();i++) {
				clist2.add(clist.get(i));
			}
			for(int i=list.size()-1;i>=0;i--) {
				int idx = list.get(i);
				if(clist.get(idx)=='+') {
					int result = slist.get(idx)+slist.get(idx+1);
//					System.out.println(result);
					slist.set(idx, result);
//					System.out.println("더하기 연산후"+slist.get(idx));
					slist.remove(idx+1);
					clist2.remove(idx);
				}else if(clist.get(idx)=='-') {
					int result = slist.get(idx)-slist.get(idx+1);
//					System.out.println(result);
					slist.set(idx, result);
//					System.out.println("빼기 연산후"+slist.get(idx));
					slist.remove(idx+1);
					clist2.remove(idx);
				}else if(clist.get(idx)=='*') {
					int result = slist.get(idx)*slist.get(idx+1);
//					System.out.println(result);
					slist.set(idx, result);
//					System.out.println("곱하기 연산후"+slist.get(idx));
					slist.remove(idx+1);
					clist2.remove(idx);
				}
			}
			sum = 0;
//			System.out.println(clist2.size());
			int size = clist2.size();
			for(int i=0;i<size;i++) {
				if(clist2.get(0).equals('+')) {
					sum = slist.get(0)+slist.get(1);
					slist.remove(1);
					slist.set(0, sum);
					clist2.remove(0);
				}else if(clist2.get(0).equals('-')) {
					sum = slist.get(0)-slist.get(1);
					slist.remove(1);
					slist.set(0, sum);
					clist2.remove(0);
				}else if(clist2.get(0).equals('*')) {
					sum = slist.get(0)*slist.get(1);
					slist.remove(1);
					slist.set(0, sum);
					clist2.remove(0);
				}
//				System.out.println(slist.get(0));
			}
//			System.out.println(slist.get(0));
			if(max<slist.get(0)) {
				max = slist.get(0);
			}
			return;
		}
		for(int i=start;i<clist.size();i++) {
			if(!visited[i]) {
				visited[i] = true;
				list.add(i);
				dfs(i+2,r-1);
				list.remove(list.size()-1);
				visited[i] = false;
			}
		}
	}
}