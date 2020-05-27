import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MainBOJ17143³¬½Ã¿Õ {
	static int R,C,M,r,c,s,d,z,result;
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,1,-1};
	static SPoint[][] arr;
	static class SPoint implements Comparable<SPoint>{
		int x;
		int y;
		public SPoint(int x, int y, int s, int d, int z) {
			super();
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		int s;
		int d;
		int z;

		public SPoint(int s, int d, int z) {
			super();
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(SPoint o) {
			if(this.y==o.y) {
				if(this.x == o.x) {
					return this.z<o.z? 1:-1;
				}else {
					return this.x < o.x ? -1:1;
				}
			}
			return this.y < o.y ? -1:1;
		}
	}
	static ArrayList<SPoint> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			list.add(new SPoint(r, c, s, d, z));
		}
		result=0;
		int cnt=1;
		while(cnt<C+1) {
			arr = new SPoint[R+1][C+1];
			Collections.sort(list);
			for(int j=0;j<list.size();j++) {
				if(list.get(j).y==cnt) {
					result+=list.get(j).z;
					list.remove(j);
					break;
				}
			}
			for(int j=0;j<list.size();j++) {
				int px = list.get(j).x;
				int py = list.get(j).y;
				int sp = list.get(j).s;
				int dir = list.get(j).d;
				while(sp>0) {
					px+=dx[dir];
					py+=dy[dir];
					if(px<1 || py<1 || px>R || py>C) {
						px-=dx[dir];
						py-=dy[dir];
						if(dir==1) {
							dir = 2;
						}else if(dir==2) {
							dir = 1;
						}else if(dir==3) {
							dir = 4;
						}else {
							dir = 3;
						}
						continue;
					}
					sp--;
				}
				if(arr[px][py]==null) {
					arr[px][py] = new SPoint(list.get(j).s, dir, list.get(j).z);					
				}else {
					if(arr[px][py].z<list.get(j).z) {
						arr[px][py] = new SPoint(list.get(j).s, dir, list.get(j).z);
					}
				}
			}
			list.clear();
			for(int j=1;j<=R;j++) {
				for(int k=1;k<=C;k++) {
					if(arr[j][k]!=null) {
						list.add(new SPoint(j, k, arr[j][k].s, arr[j][k].d, arr[j][k].z));
					}
				}
			}
			cnt++;
		}
		System.out.println(result);
	}
}