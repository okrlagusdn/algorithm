import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionSWEA4014활주로건설 {
	static int T,N,X;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int result = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			boolean[][] visited = new boolean[N][N];
			for(int i=0;i<N;i++) {
//				System.out.println("가로 : "+i);
				outer : for(int j=0;j<N-1;j++) {
					if(Math.abs(arr[i][j]-arr[i][j+1])>1) {
						break;
					}
					if(arr[i][j]==arr[i][j+1]) {
						if((j+1)==(N-1)) {
							result++;
//							System.out.println("성공!");
							break outer;
						}
						continue;
					}
					if((arr[i][j]-1)==arr[i][j+1]) {
						int num = arr[i][j+1];
						for(int k=j+1;k<j+1+X;k++) {
							if(k>N-1) {
								break outer;
							}
							if(visited[i][k]) {
								break outer;
							}
							if(arr[i][k]!=num) {
								break outer;
							}
							visited[i][k] = true;
						}
						j+=(X-1);
						if(j==N-1 || j+1==N-1) {
							result++;
//							System.out.println("성공!");
							break outer;
						}
						continue;
					}
					if(arr[i][j]==(arr[i][j+1]-1)) {
						int num = arr[i][j];
						for(int k=j;k>j-X;k--) {
							if(k<0) {
								break outer;
							}
							if(visited[i][k]) {
								break outer;
							}
							if(arr[i][k]!=num) {
								break outer;
							}
							visited[i][k] = true;
						}
						if(j+1==N-1) {
							result++;
							break outer;
						}
						continue;
					}
				}
			}

			visited = new boolean[N][N];
			for(int j=0;j<N;j++) {
//				System.out.println("세로 : "+j);
				outer : for(int i=0;i<N-1;i++) {
					if(Math.abs(arr[i][j]-arr[i+1][j])>1) {
						break;
					}

					if(arr[i][j]==arr[i+1][j]) {
						if((i+1)==(N-1)) {
							result++;
//							System.out.println("성공!");
							break outer;
						}
						continue;
					}
					if((arr[i][j]-1)==arr[i+1][j]) {
						int num = arr[i+1][j];
						for(int k=i+1;k<i+1+X;k++) {
							if(k>N-1) {
								break outer;
							}
							if(visited[k][j]) {
								break outer;
							}
							if(arr[k][j]!=num) {
								break outer;
							}
							visited[k][j] = true;
						}
						i+=(X-1);
						if(i==N-1 || i+1==N-1) {
							result++;
//							System.out.println("성공!");
							break outer;
						}
						continue;
					}
					if(arr[i][j]==(arr[i+1][j]-1)) {
						int num = arr[i][j];
						for(int k=i;k>i-X;k--) {
							if(k<0) {
								break outer;
							}
							if(visited[k][j]) {
								break outer;
							}
							if(arr[k][j]!=num) {
								break outer;
							}
							visited[k][j] = true;
						}
						if(i+1==N-1) {
							result++;
							break outer;
						}
						continue;
					}
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}
}