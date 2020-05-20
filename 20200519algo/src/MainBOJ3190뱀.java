import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainBOJ3190¹ì {
	static int N, K, L;
	static int[][] arr;
	static int[] dx = {-1,1,0,0};//»ó, ÇÏ, ÁÂ, ¿ì
	static int[] dy = {0,0,-1,1};	
	static ArrayList<SPoint> list = new ArrayList<>();
	static public class SPoint{
		int x;
		int y;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static HashMap<Integer, Character> hash = new HashMap<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 8;//»ç°ú
		}
		L = Integer.parseInt(br.readLine());
		for(int i=0;i<L;i++) {
			st = new StringTokenizer(br.readLine());
			hash.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		int dir = 3;
		list.add(new SPoint(1, 1));
		int result=0;
		arr[1][1] = 1;
		while(true) {
			result++;
			int tx = list.get(list.size()-1).x+dx[dir];
			int ty = list.get(list.size()-1).y+dy[dir];
			if(tx<1 || ty<1 || tx>N || ty>N || arr[tx][ty]==1) {
				break;
			}
			if(arr[tx][ty]==8) {
				list.add(new SPoint(tx, ty));
				arr[tx][ty] = 1;
			}
			if(arr[tx][ty]==0) {
				arr[tx][ty]=1;
				list.add(new SPoint(tx, ty));
				arr[list.get(0).x][list.get(0).y] = 0;
				list.remove(0);
			}
			if(hash.containsKey(result)) {
				dir = rotate(dir, hash.get(result));
			}
		}
		System.out.println(result);
	}
	private static int rotate(int d, char s) {
		if(s == 'L') {//¿ÞÂÊÀ¸·Î 90µµ
			if(d==0) {
				return 2;
			}else if(d==1) {
				return 3;
			}else if(d==2) {
				return 1;
			}else {
				return 0;
			}
		}else {
			if(d==0) {
				return 3;
			}else if(d==1) {
				return 2;
			}else if(d==2) {
				return 0;
			}else {
				return 1;
			}
		}
	}
}