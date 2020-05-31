import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SolutionSWEA5658보물상자비밀번호 {
	static int T,N,K;
	static char[] carr;
	static HashSet<Integer> hash;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			hash = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			carr = new char[N+1];
			String s = br.readLine();
			for(int i=1;i<=N;i++) {
				carr[i] = s.charAt(i-1);
			}
			for(int si=0;si<N/4;si++) {
				char temp = carr[N];
				for(int i=N;i>1;i--) {
					carr[i] = carr[i-1];
				}
				carr[1] = temp;
				String string = "";
				for(int i=1;i<=N;i++) {
					string+=carr[i];
					if(i%(N/4)==0){
						hash.add(Integer.parseInt(string, 16));
						string = "";
					}
				}
			}
			ArrayList<Integer> list = new ArrayList<>(hash);
			Collections.sort(list);
			System.out.println("#"+tc+" "+list.get(list.size()-K));
		}
	}
}