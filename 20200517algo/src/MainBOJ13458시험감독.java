//최대값이 21억개가 넘으므로 결과 변수 타입을 long으로 해줘야됨....
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainBOJ13458시험감독 {
	static int N, B, C;
	static long result=0;
	static int[] A;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());//총감독이 감시할수 있는 사람수
		C = Integer.parseInt(st.nextToken());//부감독이 감시할수 있는 사람수
		for(int i=0;i<N;i++) {
			A[i]-=B;
			result++;
			if(A[i]<=0) {
				continue;
			}
			if(A[i]<=C) {
				A[i]-=C;
				result++;
			}
			if(A[i]>C) {
				result+=(A[i]/C);
				if((A[i]%C)>0) {
					result++;
				}
			}
		}
		System.out.println(result);
	}
}