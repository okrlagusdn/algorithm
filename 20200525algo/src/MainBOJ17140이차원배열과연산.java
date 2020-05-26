import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class MainBOJ17140이차원배열과연산 {
	static int r,c,k;
	static int[][] arr;
	static int[][] resulta;
	static int[] sorta;
	static public class SPoint implements Comparable<SPoint>{
		int num1;
		int num2;
		public SPoint(int num1, int num2) {
			super();
			this.num1 = num1;
			this.num2 = num2;
		}
		@Override
		public int compareTo(SPoint o) {
			if(this.num2<o.num2) {
				return -1;
			}else if(this.num2==o.num2){
				if(this.num1>o.num1) {
					return 1;
				}else {
					return -1;
				}
			}
			return 0;
		}
	}
	static ArrayList<SPoint> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[101][101];
		sorta = new int[101];
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for(int i=1;i<=3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=3;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result=0;
//		System.out.println(arr[r][c]+" "+k);
		while(arr[r][c]!=k) {
			if(result>100) {
				System.out.println(-1);
				System.exit(0);
			}
			int row = 0;
			int col = 0;
			outer : for(int i=100;i>=1;i--) {
				for(int j=1;j<101;j++) {
					if(arr[j][i]!=0) {
						col = i;
						break outer;
					}
				}
			}
			outer : for(int i=100;i>=1;i--) {
				for(int j=0;j<=100;j++) {
					if(arr[i][j]!=0) {
						row = i;
						break outer;
					}
				}
			}
			if(row>=col) {//모든 행에대해서 정렬수행
				resulta = new int[101][101];
				for(int i=1;i<101;i++) {
					sorta = new int[101];
					list = new ArrayList<>();
					for(int j=1;j<101;j++) {
						if(arr[i][j]==0) {
							continue;
						}
						sorta[arr[i][j]]++;
					}
					for(int j=1;j<101;j++) {
						if(sorta[j]!=0) {
							list.add(new SPoint(j, sorta[j]));
						}
					}
					Collections.sort(list);
					for(int j=0;j<list.size();j++) {
						int idx = j*2;
						if(idx==100) {
							break;
						}
						resulta[i][idx+1] = list.get(j).num1;
						resulta[i][idx+2] = list.get(j).num2;
					}
				}
				arr = new int[101][101];
				for(int i=1;i<101;i++) {
					for(int j=1;j<101;j++) {
						arr[i][j] = resulta[i][j];
					}
				}
//				for(int i=1;i<101;i++) {
//					for(int j=1;j<101;j++) {
//						System.out.print(arr[i][j]+" ");
//					}System.out.println();
//				}
//				System.exit(0);
			}else {//모든 열에 대해서 정렬 수행
				resulta = new int[101][101];
				for(int i=1;i<101;i++) {
					sorta = new int[101];
					list = new ArrayList<>();
					for(int j=1;j<101;j++) {
						if(arr[j][i]==0) {
							continue;
						}
						sorta[arr[j][i]]++;
					}
					for(int j=1;j<101;j++) {
						if(sorta[j]!=0) {
							list.add(new SPoint(j, sorta[j]));
						}
					}
					Collections.sort(list);
					for(int j=0;j<list.size();j++) {
						int idx = j*2;
						if(idx==100) {
							break;
						}
						resulta[idx+1][i] = list.get(j).num1;
						resulta[idx+2][i] = list.get(j).num2;
					}
				}
				arr = new int[101][101];
				for(int i=1;i<101;i++) {
					for(int j=1;j<101;j++) {
						arr[i][j] = resulta[i][j];
					}
				}
//				for(int i=1;i<101;i++) {
//					for(int j=1;j<101;j++) {
//						System.out.print(arr[i][j]+" ");
//					}System.out.println();
//				}
//				System.exit(0);
			}
			result++;
		}
		System.out.println(result);
	}
}
