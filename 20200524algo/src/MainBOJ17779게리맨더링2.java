import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainBOJ17779게리맨더링2 {
	static int N, x, y, d1, d2;
	static int min=Integer.MAX_VALUE;
	static int[][] arr, arr2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		arr2 = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());			
			for(int j=1;j<=N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1;i<N-1;i++) {
			for(int j=2;j<N;j++) {
				x = i;
				y = j;
				for(int o=1;o<N;o++) {
					for(int p=1;p<N;p++) {
						d1 = o;
						d2 = p;
						if(1<=x+d1+d2 && x+d1+d2<=N && 1<=y-d1 && y-d1<y && y<y+d2 && y+d2<=N) {
							int[] sum = new int[6];
							arr2 = new int[N+1][N+1];
							for(int k=0;k<=d1;k++) {
								arr2[x+k][y-k] = 5;
							}
							for(int k=0;k<=d2;k++) {
								arr2[x+k][y+k] = 5;
							}
							for(int k=0;k<=d2;k++) {
								arr2[x+d1+k][y-d1+k] = 5;
							}
							for(int k=0;k<=d1;k++) {
								arr2[x+d2+k][y+d2-k] = 5;
							}
							for(int k=x+1;k<x+d1+d2;k++) {
								int fcnt = 0;
								for(int q=1;q<N+1;q++) {
									if(arr2[k][q]==5) {
										fcnt++;
									}
									if(fcnt==2) {
										break;
									}
									if(fcnt==1 && arr2[k][q]==0) {
										arr2[k][q]=5;
									}
								}
							}
							for(int k=1;k<x+d1;k++) {
								for(int q=1;q<=y;q++) {
									if(arr2[k][q]!=0) {
										continue;
									}
									arr2[k][q]=1;
								}
							}
							
							for(int k=1;k<=x+d2;k++) {
								for(int q=y+1;q<=N;q++) {
									if(arr2[k][q]!=0) {
										continue;
									}
									arr2[k][q]=2;
								}
							}
							
							for(int k=x+d1;k<=N;k++) {
								for(int q=1;q<y-d1+d2;q++) {
									if(arr2[k][q]!=0) {
										continue;
									}
									arr2[k][q]=3;
								}
							}
							
							for(int k=x+d2+1;k<=N;k++) {
								for(int q=y-d1+d2;q<=N;q++) {
									if(arr2[k][q]!=0) {
										continue;
									}
									arr2[k][q]=4;
								}
							}
							
							
							for(int k=1;k<=N;k++) {
								for(int q=1;q<=N;q++) {
									sum[arr2[k][q]] += arr[k][q];
								}
							}
							Arrays.sort(sum);
							int result = Math.abs(sum[1]-sum[5]);
							if(min>result) {
								min= result;
							}
						}
					}
				}
			}
		}
		System.out.println(min);
	}
}
