import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainBOJ11723С§Че {
	static int M, val;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		M = Integer.parseInt(br.readLine());
		int data = 0;
		StringBuilder sb = new StringBuilder();
		while(M>0) {
			st = new StringTokenizer(br.readLine());
			
			String s = st.nextToken();
			if(!s.equals("all") && !s.equals("empty")) {
				val = Integer.parseInt(st.nextToken());				
			}
			val--;
			if(s.equals("add")) {
				data|=(1<<val);
			}
			if(s.equals("remove")) {
				data&=~(1<<val);
			}
			if(s.equals("check")) {
				int result = data & (1<<val);
				if(result==0) {
					sb.append(0+"\n");
				}else {
					sb.append(1+"\n");
				}
			}
			if(s.equals("toggle")) {
				data^=(1<<val);
			}
			if(s.equals("all")) {
				data|=((1<<20)-1);
			}
			if(s.equals("empty")) {
				data=0;
			}
			M--;
		}
		System.out.println(sb);
	}
}