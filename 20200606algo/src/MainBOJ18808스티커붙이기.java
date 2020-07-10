import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainBOJ18808스티커붙이기 {
	static int N,M,K,r,c,flag, cnt=0;
	static int[][] arr;
	static int[][] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		result = new int[N][M];
		for(int s=0;s<K;s++) {
			flag = -1;
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
//			System.out.println(r+" "+c);
			arr = new int[r][c];
			for(int i=0;i<r;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<c;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			outer : while(true) {
				int rr = r;
				int cc = c;
				if(flag==3) {
					break;
				}
				flag++;
				if(flag%2!=0) {
					rr = c;
					cc = r;
				}
//				System.out.println("씨발"+r+" "+c);
//				System.out.println(s+"  "+flag);
				for(int i=0;i<N;i++) {
					outer2 : for(int j=0;j<M;j++) {
						if(i+rr<=N && j+cc<=M) {
//							System.out.println(i+" "+j);
							int a = i;
							int b = j;
							if(flag==0) {
								for(int n=0;n<r;n++) {
									for(int m=0;m<c;m++) {
										if(result[a][b]+arr[n][m]>1) {
											continue outer2;
										}
										b++;
									}
									b=j;
									a++;
								}
								//붙힐수 있음.
								a = i;
								b = j;
								for(int n=0;n<r;n++) {
									for(int m=0;m<c;m++) {
										result[a][b]+=arr[n][m];
										b++;
									}
									b=j;
									a++;
								}
								break outer;
							}else if(flag==1) {//90도 회전
								for(int m=0;m<c;m++) {
									for(int n=r-1;n>=0;n--) {
										if(result[a][b]+arr[n][m]>1) {
											continue outer2;
										}
										b++;
									}
									b=j;
									a++;
								}
								//붙힐수 있음.
								a = i;
								b = j;
								for(int m=0;m<c;m++) {
									for(int n=r-1;n>=0;n--) {
										result[a][b]+=arr[n][m];
										b++;
									}
									b=j;
									a++;
								}
								break outer;
							}else if(flag==2) {//180도 회전
								for(int n=r-1;n>=0;n--) {
									for(int m=c-1;m>=0;m--) {
										if(result[a][b]+arr[n][m]>1) {
											continue outer2;
										}
										b++;
									}
									b=j;
									a++;
								}
								//붙힐수 있음.
								a = i;
								b = j;
								for(int n=r-1;n>=0;n--) {
									for(int m=c-1;m>=0;m--) {
										result[a][b]+=arr[n][m];
										b++;
									}
									b=j;
									a++;
								}
								break outer;
							}else if(flag==3) {//270도 회전
								for(int m=c-1;m>=0;m--) {
									for(int n=0;n<r;n++) {
										if(result[a][b]+arr[n][m]>1) {
											continue outer2;
										}
										b++;
									}
									b=j;
									a++;
								}
								//붙힐수 있음.
								a = i;
								b = j;
								for(int m=c-1;m>=0;m--) {
									for(int n=0;n<r;n++) {
										result[a][b]+=arr[n][m];
										b++;
									}
									b=j;
									a++;
								}
								break outer;
							}
						}
					}
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(result[i][j]==1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}