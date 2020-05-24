import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainBOJ14890°æ»ç·Î {
static int N, L, result=0;
static int[][] arr;
static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			int num = arr[i][0];
			outer : for(int j=1;j<N;j++) {
//				System.out.println(j);
				if(num==arr[i][j]) {
					if(j==N-1) {
						result++;
						break outer;
					}
					continue;
				}
				if(Math.abs(num-arr[i][j])>1) {
					break;
				}
				if(Math.abs(num-arr[i][j])==1) {
					if(num>arr[i][j]) {
						num=arr[i][j];
						for(int idx=j;idx<j+L;idx++) {
							if(idx>=N) {
								break outer;
							}
							if(visited[i][idx]) {
								break outer;
							}
							if(num!=arr[i][idx]) {
								break outer;
							}
							visited[i][idx]=true;
//							System.out.println(i+", "+idx);
						}
						num = arr[i][j+L-1];
						j+=(L-1);
						if(j==N-1) {
							result++;
							break outer;
						}
						j--;
					}else {
						for(int idx=j-1;idx>=j-L;idx--) {
							if(idx<0) {
								break outer;
							}
							if(num!=arr[i][idx]) {
								break outer;
							}
							if(visited[i][idx]) {
								break outer;
							}
							visited[i][idx]=true;
//							System.out.println(i+", "+idx);
						}
						num=arr[i][j];
						j--;
					}
				}
			}
		}
//		System.out.println("--------------------------------");
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			int num = arr[0][i];
			outer : for(int j=1;j<N;j++) {
//				System.out.println(j);
				if(num==arr[j][i]) {
					if(j==N-1) {
						result++;
						break outer;
					}
					continue;
				}
				if(Math.abs(num-arr[j][i])>1) {
					break;
				}
				if(Math.abs(num-arr[j][i])==1) {
					if(num>arr[j][i]) {
						num=arr[j][i];
						for(int idx=j;idx<j+L;idx++) {
							if(idx>=N) {
								break outer;
							}
							if(num!=arr[idx][i]) {
								break outer;
							}
							if(visited[idx][i]) {
								break outer;
							}
							visited[idx][i]=true;
//							System.out.println(idx+", "+i);
						}		
						num = arr[j+L-1][i];
						j+=(L-1);
						if(j==N-1) {
							result++;
							break outer;
						}
						j--;
					}else {
						for(int idx=j-1;idx>=j-L;idx--) {
							if(idx<0) {
								break outer;
							}
							if(num!=arr[idx][i]) {
								break outer;
							}
							if(visited[idx][i]) {
								break outer;
							}
							visited[idx][i]=true;
//							System.out.println(idx+", "+i);
						}
						num=arr[j][i];
						j--;
					}
				}
			}
		}
		System.out.println(result);
	}
}
