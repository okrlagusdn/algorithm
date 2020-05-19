import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ16236�Ʊ��� {
	static int N, sec=0, result=0, eat=0;
	static int[][] arr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	static public class SPoint implements Comparable<SPoint>{
		int x;
		int y;
		int size;
		public SPoint(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}
		@Override
		public int compareTo(SPoint o) {
			if(this.x==o.x) {
				return this.y-o.y;
			}else {
				return this.x-o.x;				
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		arr = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j]==9) {
					arr[i][j]=0;
					bfs(i,j);
				}
			}
		}
		System.out.println(result);
	}
	private static void bfs(int sx, int sy) {
		Queue<SPoint> q = new LinkedList<>();
		q.add(new SPoint(sx, sy, 2));
		while(!q.isEmpty()) {
			visited[sx][sy]=true;
			int size = q.size();
			PriorityQueue<SPoint> pq = new PriorityQueue<>();
			int flag=0;
			for(int k=0;k<size;k++) {
				SPoint p = q.poll();
				//������ �ִ� ���� ���� ���� size for�� ������ q����... -> �켱����ť�� ������ �ִ� ����� �ְ� �����ϰ� peek�̰�, ���ڸ��� ��� ��ġ ��Ű�� �ٽ� ����
				for(int i=0;i<4;i++) {
					int tx = p.x+dx[i];
					int ty = p.y+dy[i];
					if(tx<0 || ty<0 || tx>=N || ty>=N) {
						continue;
					}
					if(visited[tx][ty]) {
						continue;
					}
					if(arr[tx][ty]>p.size) {
						continue;
					}
					if(arr[tx][ty]!=0 && arr[tx][ty]<p.size) {
						pq.add(new SPoint(tx, ty, p.size));
						flag=1;
					}
					q.add(new SPoint(tx, ty, p.size));
					visited[tx][ty] = true;
				}
			}
			sec++;
			if(flag==1) {
				visited = new boolean[N][N];
				q.clear();
				SPoint p2 = pq.poll();
				eat++;
				if(eat==p2.size) {
					p2.size++;
					eat=0;
				}
				arr[p2.x][p2.y] = 0;
				q.add(new SPoint(p2.x, p2.y, p2.size));
				System.out.println(p2.x+" "+p2.y +" " +sec+"��");
				result+=sec;
				sec=0;
			}
//			sec++;
		}
	}
}