import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainBOJ14891톱니바퀴 {
	static public class Info{
		int nb;
		int di;
		public Info(int nb, int di) {
			super();
			this.nb = nb;
			this.di = di;
		}
	}
	static int[][] arr;
	static int K;
	static Queue<Info> q;
	static ArrayList<Info> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		arr = new int[5][9];
		for(int i=1;i<=4;i++) {
//			st = new StringTokenizer(br.readLine());
			String s = br.readLine();
			for(int j=1;j<=8;j++) {
				arr[i][j] = s.charAt(j-1)-'0';
			}
		}
		K = Integer.parseInt(br.readLine());
		for(int i=0;i<K;i++) {
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			q = new LinkedList<>();
			if(num!=4) {
				q.add(new Info(num, dir));
				for(int j=num;j<4;j++) {
					Info info = q.poll();
					if(info.di==0) {
						break;
					}
					if(arr[j][3] == arr[j+1][7]) {
						q.add(new Info(j+1, info.di*0));
						list.add(new Info(j+1, info.di*0));
					}else {
						q.add(new Info(j+1, info.di*-1));
						list.add(new Info(j+1, info.di*-1));
					}
				}				
			}
			q = new LinkedList<>();
			if(num!=1) {
				q.add(new Info(num, dir));
				for(int j=num;j>1;j--) {
					Info info = q.poll();
					if(info.di==0) {
						break;
					}
					if(arr[j][7] == arr[j-1][3]) {
						q.add(new Info(j-1, info.di*0));
						list.add(new Info(j-1, info.di*0));
					}else {
						q.add(new Info(j-1, info.di*-1));
						list.add(new Info(j-1, info.di*-1));
					}
				}
			}
			list.add(new Info(num, dir));
//			for(int k=0;k<list.size();k++) {
//				System.out.println(list.get(k).nb+" "+list.get(k).di);
//			}
			rotate(list);
//			for(int k=1;k<=4;k++) {
//				for(int l=1;l<=8;l++) {
//					System.out.print(arr[k][l]+" ");
//				}System.out.println();
//			}
		}
		int result=0;
		for(int i=1;i<=4;i++) {//n=0, s=1
			if(arr[i][1]==1) {
				if(i==1) {
					result+=1;
				}
				if(i==2) {
					result+=2;
				}
				if(i==3) {
					result+=4;
				}
				if(i==4) {
					result+=8;
				}
			}
		}
		System.out.println(result);
	}
	private static void rotate(ArrayList<Info> list2) {
		for(int i=0;i<list2.size();i++) {
			int num = list2.get(i).nb;
			int dir = list2.get(i).di;
			if(dir==0) {
				continue;
			}
			if(dir==1) {//시계방향
				int temp = arr[num][8];
				for(int j=8;j>1;j--) {
					arr[num][j] = arr[num][j-1];
				}
				arr[num][1] = temp;
			}else if(dir==-1) {//반시계방향
				int temp = arr[num][1];
				for(int j=1;j<=7;j++) {
					arr[num][j] = arr[num][j+1];
				}
				arr[num][8] = temp;
			}
		}
	}
}