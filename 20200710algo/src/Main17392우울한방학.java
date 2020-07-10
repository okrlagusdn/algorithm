import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17392우울한방학 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			sum+=Integer.parseInt(st.nextToken())+1;
		}
		int num = M-sum;
		int result = 0;
		if(num>0) {
			int a = 1;
			while(num/(N+1)!=0) {
				result+=Math.pow(a, 2)*(N+1);
				a++;
				num-=(N+1);
			}
			result+=Math.pow(a, 2)*(num%(N+1));
		}
		System.out.println(result);
	}
}