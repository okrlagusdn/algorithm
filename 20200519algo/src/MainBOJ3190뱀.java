import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ3190¹ì {
	static int N, K, L;
	static int[][] arr;
	static int[] dx = {-1,1,0,0};//»ó, ÇÏ, ÁÂ, ¿ì
	static int[] dy = {0,0,-1,1};	
	static public class SPoint{
		int x;
		int y;
		int dir;
		String d;
		public SPoint(int x, int y, String d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
		public SPoint(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}
	static HashMap<Integer, String> hash = new HashMap<>();
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
			hash.put(Integer.parseInt(st.nextToken()), st.nextToken());
		}
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(1, 1, 3));
		arr[1][1] = 1;//¹ì:1
		int result=0;
		outer : while(true) {
			int size = q.size();
			int flag=0;//»ç°ú¸Ô¾ú´ÂÁö Ã¼Å©ÇÏ´Â flag
			ArrayList<SPoint> list = new ArrayList<>();
			if(hash.containsKey(result)) {
				//				p.dir = rotate(p.dir, hash.get(result));
			}
			for(int k=0;k<size;k++) {
				SPoint p = q.poll();
				int tx = p.x+dx[p.dir];
				int ty = p.y+dy[p.dir];
				if(tx<1 || ty<1 || tx>=N+1 || ty>=N+1 || arr[tx][ty]==1) {
					break outer;
				}
				if(arr[tx][ty]==2) {
					flag=1;
				}
				arr[tx][ty]=1;
			}
			result++;
		}
		System.out.println(result);
	}
	private static int rotate(int d, String s) {
		if(s.equals("L")) {//¿ÞÂÊÀ¸·Î 90µµ
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