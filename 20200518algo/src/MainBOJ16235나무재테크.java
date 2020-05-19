//ArrayList 다 비울 때, removeAll()할 때는 터졌는데, clear()하니까 안터짐.. ArrayList에 넣는 부분은 바로 넣는게 더 빠름
//LinkedList는 삽입, 삭제 할때 유리, ArrayList는 검색
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MainBOJ16235나무재테크 {
	static int N, M, K;
	static int[][] A, nutri;
	static int[] dx = {-1,1,0,0,-1,1,-1,1};
	static int[] dy = {0,0,-1,1,-1,1,1,-1};
	//	static ArrayList<TPoint> tlist = new ArrayList<>();
	static LinkedList<TPoint> tlist = new LinkedList<>();
	static ArrayList<TPoint> tlist2 = new ArrayList<>();
	static ArrayList<TPoint> dlist = new ArrayList<>();
	static public class TPoint implements Comparable<TPoint>{
		int x;
		int y;
		int age;
		public TPoint(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}
		@Override
		public int compareTo(TPoint o) {
			return this.age - o.age;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//N*N 밭
		M = Integer.parseInt(st.nextToken());//처음 나무 개수
		K = Integer.parseInt(st.nextToken());//K년
		A = new int[N+1][N+1];
		nutri = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				nutri[i][j] = 5;
			}
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			tlist.add(new TPoint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		int ycnt=0;
		Collections.sort(tlist);
		while(ycnt<K) {
			Iterator<TPoint> it = tlist.iterator();
			//봄
			while(it.hasNext()) {
				TPoint tree = it.next();
				if(tree.age <= nutri[tree.x][tree.y]) {
					nutri[tree.x][tree.y] -= tree.age;
					++tree.age;
				}else {
					dlist.add(new TPoint(tree.x, tree.y, tree.age/2));
					//					dlist.add(t);
					it.remove();
				}
			}
//			for(int i=0;i<tlist.size();i++) {
//				if(tlist.get(i).age <= nutri[tlist.get(i).x][tlist.get(i).y]) {
//					//나이만큼 양분 못먹어서 죽음
//					nutri[tlist.get(i).x][tlist.get(i).y] -= tlist.get(i).age;
//					tlist.get(i).age++;
//				}else {
//					dlist.add(tlist.get(i));
//					tlist.remove(i);
//					i--;
//				}
//			}

			//여름
			for(int i=0;i<dlist.size();i++) {
				TPoint dtree = dlist.get(i);
				nutri[dtree.x][dtree.y] += dtree.age;
			}
			dlist.clear();

			//가을
			it = tlist.iterator();
			while(it.hasNext()) {
				TPoint tree = it.next();
				if(tree.age%5==0) {
					for(int j=0;j<8;j++) {
						int tx = tree.x+dx[j];
						int ty = tree.y+dy[j];
						if(tx<1 || ty<1 || tx>N || ty>N) {
							continue;
						}
//						TPoint t = new TPoint(tx, ty, 1);
//						tlist2.add(t);
						tlist2.add(new TPoint(tx, ty, 1));
					}
				}
			}
			if(!tlist2.isEmpty()) {
				for(int i=0;i<tlist2.size();i++) {
					tlist.addFirst(tlist2.get(i));
				}
			}
			tlist2.clear();
			//			for(int i=0;i<tlist.size();i++) {
			//				if(tlist.get(i).age%5==0) {
			//					int sx = tlist.get(i).x;
			//					int sy = tlist.get(i).y;
			//					for(int j=0;j<8;j++) {
			//						int tx = sx+dx[j];
			//						int ty = sy+dy[j];
			//						if(tx<1 || ty<1 || tx>N || ty>N) {
			//							continue;
			//						}
			//						tlist.add(new TPoint(tx, ty, 1));
			//					}
			//				}
			//			}

			//겨울
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					nutri[i][j] += A[i][j];
				}
			}
			ycnt++;
		}
		System.out.println(tlist.size());
	}
}
