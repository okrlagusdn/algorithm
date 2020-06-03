import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ16987계란으로계란치기 {
	static ArrayList<Info> list = new ArrayList<>();
	static class Info{
		int h;
		int w;
		public Info(int h, int w) {
			super();
			this.h = h;
			this.w = w;
		}
	}
	static int N,result, max=Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		dfs(0);
		if(max==Integer.MIN_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(max);			
		}
	}
	private static void dfs(int r) {
		cal();
		if(r==list.size()) {
			return;
		}
		if(list.get(r).h>0) {
			for(int i=0;i<list.size();i++) {
				if(r!=i && list.get(i).h>0) {
					list.get(i).h-=list.get(r).w;
					list.get(r).h-=list.get(i).w;
					dfs(r+1);
					list.get(i).h+=list.get(r).w;
					list.get(r).h+=list.get(i).w;
				}
			}
		}else {
			dfs(r+1);
		}
	}
	private static void cal() {
		result = 0;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).h<=0) {
				result++;
			}
		}
		if(max<result) {
			max = result;
		}
	}
}