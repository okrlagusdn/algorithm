import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ17825주사위윷놀이 {
	static int[] arr;
	static boolean[] visited1;
	static boolean[] visited3;
	static boolean[][] visited2;
	static int result, max = Integer.MIN_VALUE;
	static public class SPoint{
		int x;
		int y;
		public SPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<SPoint> horse[];
	static ArrayList<Integer> list = new ArrayList<>();
	static int[][] rr = {{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,0},//22
			{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20},
			{0,2,4,6,8,10,13,16,19,25,30,35,40,0},//14
			{0,1,2,3,4,5,21,22,23,24,25,26,20,0},
			{0,2,4,6,8,10,12,14,16,18,20,22,24,25,30,35,40,0},//18,
			{0,1,2,3,4,5,6,7,8,9,10,27,28,24,25,26,20,0},
			{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,28,27,26,25,30,35,40,0},
			{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,29,30,31,24,25,26,20,0}};//24
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[10];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<10;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(10);
		System.out.println(max);
	}
	private static void dfs(int r) {
		if(r==0) {
			visited1 = new boolean[4];
			visited2 = new boolean[4][24];
			visited3 = new boolean[32];
			result=0;
			horse = new ArrayList[4];
			for(int i=0;i<4;i++) {
				horse[i] = new ArrayList<>();
				horse[i].add(new SPoint(0, 0));
			}
			for(int i=0;i<list.size();i++) {
				if(!visited1[list.get(i)]) {
					int flag=0;
					int px = horse[list.get(i)].get(0).x;
					int py = horse[list.get(i)].get(0).y;
					if(px==0 && py==5) {
						flag=1;
						px = 2;
					}
					if(px==0 && py==10) {
						flag=1;
						px = 4;
					}
					if(px==0 && py == 15) {
						flag=1;
						px = 6;
					}
					int ty = py+arr[i];
//					System.out.println(rr[px].length);
					if(ty>(rr[px].length-2)) {//도착한 경우
						visited1[list.get(i)] = true;
//						visited2[px][py]=false;
						visited3[rr[px+1][py]]=false;
						continue;
					}
//					if(visited2[px][ty]) {//도착할 위치에 이미 말이 있는 경우
//						break;
//					}
					if(visited3[rr[px+1][ty]]) {
						break;
					}
//					visited2[px][ty] = true;//도착위치에 도착
					visited3[rr[px+1][ty]] = true;
					if(flag==1) {//원래 있었던곳 방문체크 해제
//						visited2[0][py] = false;
						visited3[rr[px+1][py]] = false;
					}else {
//						visited2[px][py] = false;
						visited3[rr[px+1][py]] = false;
					}
					result += rr[px][ty];//도착할 위치의 값 더해주기
					horse[list.get(i)].get(0).x = px;
					horse[list.get(i)].get(0).y = ty;
				}
			}
			if(max<result) {
				max = result;
			}
			return;
		}
		for(int i=0;i<4;i++) {
			list.add(i);
			dfs(r-1);
			list.remove(list.size()-1);
		}
	}
}