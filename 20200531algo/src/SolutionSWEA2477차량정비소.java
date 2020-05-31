import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionSWEA2477차량정비소 {
	static int T,N,M,K,A,B,result;
	static Queue<SPoint> waitq1;
	static Queue<SPoint> waitq2;
	static Queue<SPoint> complet;
	static int[] a, b;
	static class SPoint{
		int cn;
		int recnum;
		int repnum;
		int time;
		public SPoint(int cn, int recnum, int repnum, int time) {
			super();
			this.cn = cn;
			this.recnum = recnum;
			this.repnum = repnum;
			this.time = time;
		}
	}
	static SPoint[] rece;
	static SPoint[] repa;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			result = 0;
			complet = new LinkedList<>();
			waitq1 = new LinkedList<>();
			waitq2 = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			a = new int[N+1];
			b = new int[M+1];
			rece = new SPoint[N+1];
			repa = new SPoint[N+1];
			arr = new int[K+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=M;i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=K;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int comp = 0;
			int time = 0;
			while(true) {
				if(time<K) {
					for(int i=1;i<=K;i++) {
						if(arr[i]==0) {
							waitq1.add(new SPoint(i, 0, 0, 0));		
							time++;
						}
						arr[i]--;
					}
				}
				for(int i=1;i<=M;i++) {
					if(repa[i]!=null) {
						repa[i].time--;
						if(repa[i].time==0) {
							complet.add(repa[i]);
							repa[i]=null;
							comp++;
						}
					}
				}
				for(int i=1;i<=N;i++) {
					if(rece[i]!=null) {
						rece[i].time--;
						if(rece[i].time==0) {
							waitq2.add(rece[i]);
							rece[i] = null;
						}
					}
				}
				//고객 도착 순으로 빈 접수 창구로 보내주기
				for(int j=1;j<=N;j++) {
					if(rece[j]==null && !waitq1.isEmpty()) {
						rece[j] = waitq1.poll();
						rece[j].recnum = j;
						rece[j].time = a[j];
					}
				}
				//정비하기 위해 기다리는 고객 정비소로 보내주기
				for(int j=1;j<=M;j++) {
					if(repa[j]==null && !waitq2.isEmpty()) {
						repa[j] = waitq2.poll();
						repa[j].repnum = j;
						repa[j].time = b[j];
					}
				}
				if(comp==K) {
					break;
				}
			}
			int size = complet.size();
			for(int i=0;i<size;i++) {
				SPoint p = complet.poll();
				if(p.recnum==A && p.repnum==B) {
					result+=p.cn;
				}
			}
			if(result==0) {
				System.out.println("#"+tc+" "+-1);
			}else {
				System.out.println("#"+tc+" "+result);
			}
		}
	}
}