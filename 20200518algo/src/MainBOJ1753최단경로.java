import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainBOJ1753�ִܰ�� {
	static int V, E, K;
	static int[][] map;
	static boolean[] visited;
	static int[] dist;
	static public class Info implements Comparable<Info>{
		int vertex;
		int dist;
		public Info(int vertex, int dist) {
			super();
			this.vertex = vertex;
			this.dist = dist;
		}
		@Override
		public int compareTo(Info o) {
			return this.dist - o.dist;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());//������ ����
		E = Integer.parseInt(st.nextToken());//������ ����
		K = Integer.parseInt(br.readLine());//���� ����
		PriorityQueue<Info> pq = new PriorityQueue<>();
		ArrayList<Info>[] list = new ArrayList[V+1];
		visited = new boolean[V+1];//������ �湮���θ� üũ�� �迭
		dist = new int[V+1];//�� ������ �ּ� �Ÿ��� ������ �迭
		for(int i=1;i<=V;i++) {
			dist[i] = Integer.MAX_VALUE;
			list[i] = new ArrayList<>();
		}
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			list[x].add(new Info(y, dist));
		}
		dist[K] = 0;
//		System.out.println(list[1].get(0).vertex);
		pq.add(new Info(K, 0));
		while(!pq.isEmpty()) {
			Info info = pq.poll();
			if(visited[info.vertex]) {
				continue;
			}
			visited[info.vertex] = true;
			for(Info i : list[info.vertex]) {
				if(!visited[i.vertex]) {
					dist[i.vertex] = Math.min(dist[i.vertex], dist[info.vertex]+i.dist);
					pq.add(new Info(i.vertex, dist[i.vertex]));
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