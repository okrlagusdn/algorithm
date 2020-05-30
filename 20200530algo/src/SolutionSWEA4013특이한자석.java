import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SolutionSWEA4013특이한자석 {
	static int T, K;
	static int[][] arr;
	static class SPoint{
		int num;
		int dir;
		public SPoint(int num, int dir) {
			super();
			this.num = num;
			this.dir = dir;
		}
	}
	static ArrayList<SPoint> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int tc=1;tc<=T;tc++) {
			int result = 0;
			list = new ArrayList<>();
			arr = new int[5][8];
			K = Integer.parseInt(br.readLine());
			for(int i=1;i<=4;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<8;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				list.add(new SPoint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			for(int i=0;i<list.size();i++) {
				dfs1(list.get(i).num, list.get(i).dir);
				dfs2(list.get(i).num, list.get(i).dir);
				rotate(list.get(i).num, list.get(i).dir);
			}
			for(int k=1;k<=4;k++) {
				if(arr[k][0]==1) {
					result+=(1<<(k-1));
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void dfs1(int num, int dir) {
		if(num+1<=4) {
			if(arr[num][2]!=arr[num+1][6]) {
				dfs1(num+1, dir*-1);
				rotate(num+1, dir*-1);
			}else {
				return;
			}
		}
	}
	private static void dfs2(int num, int dir) {
		if(num-1>=1) {
			if(arr[num][6]!=arr[num-1][2]) {
				dfs2(num-1, dir*-1);
				rotate(num-1, dir*-1);
			}else {
				return;
			}
		}
	}
	private static void rotate(int num, int dir) {
			if(dir==-1) {
				int temp = arr[num][0];
				for(int j=0;j<7;j++) {
					arr[num][j] = arr[num][j+1];
				}
				arr[num][7] = temp;
			}else {
				int temp = arr[num][7];
				for(int j=7;j>0;j--) {
					arr[num][j] = arr[num][j-1];
				}
				arr[num][0] = temp;
			}
	}
}