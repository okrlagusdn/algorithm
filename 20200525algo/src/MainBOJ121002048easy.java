//arraylist 문제는 아니고요.
//
//Integer가 내부적으로 127까지 캐싱을 해놓고 동일한 객체를 줍니다.
//
//128부터는 캐싱을 안하고요.
//
//int가 아니라 Integer라서 equals로 비교해주는게 맞습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainBOJ121002048easy {
	static int N, max = Integer.MIN_VALUE;
	static int[][] arr;
	static int[][] carr;
	static int[][] resultarr;
	static ArrayList<Integer> list = new ArrayList<>();
	static ArrayList<Integer> list2 = new ArrayList<>();
	static ArrayList<Integer> list3 = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		carr = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(5);
		System.out.println(max);
	}
	private static void dfs(int r) {
		if(r==0) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					carr[i][j] = arr[i][j];
				}
			}
			for(int k=0;k<list.size();k++) {
				resultarr = new int[N][N];
				if(list.get(k)==0) {
					for(int i=0;i<N;i++) {
						list2 = new ArrayList<>();
						list3 = new ArrayList<>();
						for(int j=0;j<N;j++) {
							if(carr[i][j]==0) {
								continue;
							}else {
								list2.add(carr[i][j]);
							}
						}
						if(list2.size()==1) {
							list3.add(list2.get(0));
						}else {
							for(int j=0;j<list2.size()-1;j++) {
								if(list2.get(j).equals(list2.get(j+1))) {
									list3.add(list2.get(j) + list2.get(j+1));
									j++;
								}else {
									list3.add(list2.get(j));
								}
								if(j==list2.size()-2) {
									list3.add(list2.get(list2.size()-1));
								}
							}
						}
						int idx=0;
						int idx2=0;
						while(idx2<list3.size()) {
							resultarr[i][idx] = list3.get(idx2);
							idx++;
							idx2++;
						}
					}
				}else if(list.get(k)==1) {
					for(int i=0;i<N;i++) {
						list2 = new ArrayList<>();
						list3 = new ArrayList<>();
						for(int j=N-1;j>=0;j--) {
							if(carr[i][j]==0) {
								continue;
							}else {
								list2.add(carr[i][j]);
							}
						}
						if(list2.size()==1) {
							list3.add(list2.get(0));
						}else {
							for(int j=0;j<list2.size()-1;j++) {
								if(list2.get(j).equals(list2.get(j+1))) {
									list3.add(list2.get(j) + list2.get(j+1));
									j++;
								}else {
									list3.add(list2.get(j));
								}
								if(j==list2.size()-2) {
									list3.add(list2.get(list2.size()-1));
								}
							}							
						}
						int idx=N-1;
						int idx2=0;
						while(idx2<list3.size()) {
							resultarr[i][idx] = list3.get(idx2);
							idx--;
							idx2++;
						}
					}
				}else if(list.get(k)==2) {
					for(int j=0;j<N;j++) {
						list2 = new ArrayList<>();
						list3 = new ArrayList<>();
						for(int i=0;i<N;i++) {
							if(carr[i][j]==0) {
								continue;
							}else {
								list2.add(carr[i][j]);
							}
						}
						if(list2.size()==1) {
							list3.add(list2.get(0));
						}else {
							for(int i=0;i<list2.size()-1;i++) {
								if(list2.get(i).equals(list2.get(i+1))) {
									list3.add(list2.get(i) + list2.get(i+1));
									i++;
								}else {
									list3.add(list2.get(i));
								}
								if(i==list2.size()-2) {
									list3.add(list2.get(list2.size()-1));
								}
							}							
						}
						int idx=0;
						int idx2=0;
						while(idx2<list3.size()) {
							resultarr[idx][j] = list3.get(idx2);
							idx++;
							idx2++;
						}
					}
				}else if(list.get(k)==3) {
					for(int j=N-1;j>=0;j--) {
						list2 = new ArrayList<>();
						list3 = new ArrayList<>();
						for(int i=N-1;i>=0;i--) {
							if(carr[i][j]==0) {
								continue;
							}else {
								list2.add(carr[i][j]);
							}
						}
						if(list2.size()==1) {
							list3.add(list2.get(0));
						}else {
							for(int i=0;i<list2.size()-1;i++) {
//								System.out.println(list2.get(i)+" "+list2.get(i+1));
								if(list2.get(i).equals(list2.get(i+1))) {
//									System.out.println(list2.get(i));
//									System.out.println("dsdsdsdsd");
									list3.add(list2.get(i) + list2.get(i+1));
									i++;
								}else {
									list3.add(list2.get(i));
								}
								if(i==list2.size()-2) {
									list3.add(list2.get(list2.size()-1));
								}
							}
						}
						int idx=N-1;
						int idx2=0;
						while(idx2<list3.size()) {
							resultarr[idx][j] = list3.get(idx2);
							idx--;
							idx2++;
						}
					}
				}

				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						carr[i][j] = resultarr[i][j];
					}
				}
//				for(int i=0;i<N;i++) {
//					for(int j=0;j<N;j++) {
//						System.out.print(carr[i][j]+" ");
//					}System.out.println();
//				}
//				System.out.println("------------------------------------");
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(max<carr[i][j]) {
						max = carr[i][j];
					}
				}
			}
			return;
		}
		for(int i=0;i<4;i++) {
			list.add(i);
			dfs(r-1);
			list.remove(list.size()-1);
		}
	}
}