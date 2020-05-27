import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ15685µå·¡°ïÄ¿ºê {
	static int N, result;
	static int[][] arr;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,0};
	static class SPoint {
		int x;
		int y;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		arr = new int[101][101];
		ArrayList<SPoint> list;
		for(int i=0;i<N;i++) {
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int gen = Integer.parseInt(st.nextToken());
			if(gen==0) {
				list.add(new SPoint(sy, sx));
				int tx = sy+dx[dir];
				int ty = sx+dy[dir];
				list.add(new SPoint(tx, ty));
			}else {
				list.add(new SPoint(sy, sx));
				int tx = sy+dx[dir];
				int ty = sx+dy[dir];
				list.add(new SPoint(tx, ty));
				for(int j=0;j<gen;j++) {
					int size = list.size();
					for(int k=size-1;k>=1;k--) {
						int xr = list.get(k-1).x-list.get(k).x;
						int yr = list.get(k-1).y-list.get(k).y;
						if(xr==0) {
							int temp = xr;
							xr = yr;
							yr = temp;
						}else {
							int temp = xr;
							xr = yr;
							yr = temp;
							yr*=-1;
						}
						tx = list.get(list.size()-1).x+xr;
						ty = list.get(list.size()-1).y+yr;
						list.add(new SPoint(tx, ty));
					}
				}
			}
			for(int j=0;j<list.size();j++) {
				int xx = list.get(j).x;
				int yy = list.get(j).y;
				arr[xx][yy] = 1;
			}
//			list.clear();
		}
		result = 0;
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(arr[i][j]==1 && arr[i][j+1]==1 && arr[i+1][j]==1 && arr[i+1][j+1]==1) {
					result++;
				}
				
			}
		}
		System.out.println(result);
	}
}