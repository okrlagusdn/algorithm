import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainBOJ1753�ִܰ�θ޸��ʰ� {
static int V, E, K;
static int[][] map;
static boolean[] visited;
static int[] dist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());//������ ����
		E = Integer.parseInt(st.nextToken());//������ ����
		K = Integer.parseInt(br.readLine());//���� ����
		visited = new boolean[V+1];//������ �湮���θ� üũ�� �迭
		map = new int[V+1][V+1];//�� ���� ���� �Ÿ��� ���� �迭
		dist = new int[V+1];//�� ������ �ּ� �Ÿ��� ������ �迭
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			map[x][y] = dist;
//			map[y][x] = dist;
		}
		for(int i=1;i<=V;i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		visited[K] = true;
		dist[K] = 0;
		
		for(int i=1;i<=V;i++) {
			if(map[K][i]!=0) {
				dist[i] = map[K][i];//���������� ������ ������ �Ÿ� ����
			}
		}
		
		for(int k=0;k<V-1;k++) {
			int min = Integer.MAX_VALUE;
			int min_idx = -1;
			for(int i=1;i<=V;i++) {
				if(!visited[i] && dist[i]!=Integer.MAX_VALUE) {
					if(min>dist[i]) {
						min = dist[i];
						min_idx = i;
						visited[min_idx] = true;
					}
				}
			}
			if(min_idx!=-1) {
				for(int i=1;i<=V;i++) {
					if(!visited[i] && map[min_idx][i]!=0) {
						if(dist[i]>dist[min_idx]+map[min_idx][i]) {
							dist[i] = dist[min_idx]+map[min_idx][i];
						}
					}
				}
			}
		}
		for(int i=1;i<=V;i++) {
			if(dist[i]==Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(dist[i]);				
			}
		}
	}
}
