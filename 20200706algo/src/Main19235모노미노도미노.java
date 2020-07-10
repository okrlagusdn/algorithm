import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main19235모노미노도미노 {	
	static int score = 0;
	static int bcnt = 0;
	static class SPoint {
		int bl;//벽돌유무 0:빈칸 1:벽돌
		int no;//벽돌 번호
		public SPoint(int bl, int no) {
			super();
			this.bl = bl;
			this.no = no;
		}
	}
	static SPoint[][] bl = new SPoint[4][10];
	static SPoint[][] gr = new SPoint[10][4];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<4;i++) {
			for(int j=0;j<10;j++) {
				bl[i][j] = new SPoint(0,0);
			}
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<4;j++) {
				gr[i][j] = new SPoint(0,0);
			}
		}
		int num=1;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			//파란 보드
			if(t==1) {
				int tx = sx;
				int ty = sy;
				while(true) {
					ty++;
					if(ty==10 || bl[tx][ty].bl>0) {
						ty--;
						bl[tx][ty] = new SPoint(num, 1);
						break;
					}
				}
			}else if(t==2) {
				int tx = sx;
				int ty = sy+1;
				while(true) {
					ty++;
					if(ty==10 || bl[tx][ty].bl>0) {
						ty--;
						bl[tx][ty] = new SPoint(num, 2);
						bl[tx][ty-1] = new SPoint(num, 2);
						break;
					}
				}
			}else if(t==3) {
				int tx1 = sx;
				int tx2 = sx+1;
				int ty = sy;
				while(true) {
					ty++;
					if(ty==10 || bl[tx1][ty].bl>0 || bl[tx2][ty].bl>0) {
						ty--;
						bl[tx1][ty] = new SPoint(num, 3);
						bl[tx2][ty] = new SPoint(num, 3);
						break;
					}
				}
			}
			//초록색 보드
			if(t==1) {
				int tx = sx;
				int ty = sy;
				while(true) {
					tx++;
					if(tx==10 || gr[tx][ty].bl>0) {
						tx--;
						gr[tx][ty] = new SPoint(num, 1);
						break;
					}
				}
			}else if(t==2) {
				int tx = sx;
				int ty1 = sy;
				int ty2 = sy+1;
				while(true) {
					tx++;
					if(tx==10 || gr[tx][ty1].bl>0 || gr[tx][ty2].bl>0) {
						tx--;
						gr[tx][ty1] = new SPoint(num, 2);
						gr[tx][ty2] = new SPoint(num, 2);
						break;
					}
				}
			}else if(t==3) {
				int tx = sx+1;
				int ty = sy;
				while(true) {
					tx++;
					if(tx==10 || gr[tx][ty].bl>0) {
						tx--;
						gr[tx][ty] = new SPoint(num, 3);
						gr[tx-1][ty] = new SPoint(num, 3);
						break;
					}
				}
			}
			
			outer2 : while(true) {//파랑
				int flag=0;
				int flag3=0;
				int col = 0;
				outer : for(int j=9;j>3;j--) {
					int cnt = 0;
					for(int k=0;k<4;k++) {
						col = j;
						if(bl[k][j].bl>0) {
							cnt++;
						}
					}
					if(cnt==4) {
//						System.out.println(col+"열 삭제");
						score++;
						flag=1;
						flag3=1;
						for(int l=0;l<4;l++) {
							bl[l][col] = new SPoint(0,0);
						}
						continue outer;
					}
					if(flag3==1) {
						//지우고 점수 획득하는 로직(한칸씩 다 당기기)
						for(int l=9;l>3;l--) {
							for(int k=3;k>=0;k--) {
								if(bl[k][l].no==1) {
									int no1 = bl[k][l].bl;
									bl[k][l] = new SPoint(0, 0);
//									System.out.println(no1);
									int tx = k;
									int ty = l;
									while(true) {
										ty++;
										if(ty==10 || bl[tx][ty].bl>0) {
//											System.out.println(ty);
											ty--;
											bl[tx][ty] = new SPoint(no1, 1);
											break;
										}
									}
								}else if(bl[k][l].no==2) {
									int no2 = bl[k][l].bl;
									int flag2=0;
									int tx = k;
									int ty = l;
									if(bl[tx][ty].bl == bl[tx][ty-1].bl) {
										flag2=1;
									}
									if(flag2==0) {
										bl[k][l] = new SPoint(0, 0);											
									}else {
										bl[k][l] = new SPoint(0, 0);
										bl[k][l-1] = new SPoint(0, 0);											
									}
									while(true) {
										ty++;
										if(ty==10 || bl[tx][ty].bl>0) {
											ty--;
											if(flag2==1) {
												bl[tx][ty] = new SPoint(no2, 2);
												bl[tx][ty-1] = new SPoint(no2, 2);
											}else {
												bl[tx][ty] = new SPoint(no2, 2);
											}
											break;
										}
									}
								}else if(bl[k][l].no==3) {
									int no3 = bl[k][l].bl;
									bl[k][l] = new SPoint(0, 0);
									bl[k-1][l] = new SPoint(0, 0);
									int tx1 = k;
									int tx2 = k-1;
									int ty = l;
									while(true) {
										ty++;
										if(ty==10 || bl[tx1][ty].bl>0 || bl[tx2][ty].bl>0) {
											ty--;
											bl[tx1][ty] = new SPoint(no3, 3);
											bl[tx2][ty] = new SPoint(no3, 3);
											break;
										}
									}
									k--;
								}
							}
						}
						continue outer2;
					}
				}
				if(flag3==0) {
					break;
				}
			}
			outer2 : while(true) {//초록
				int flag=0;
				int row = 0;
				outer : for(int j=9;j>3;j--) {
					int cnt = 0;
					for(int k=0;k<4;k++) {
						row = j;
						if(gr[j][k].bl>0) {
							cnt++;
						}
					}
					if(cnt==4) {
						score++;
						flag=1;
						for(int k=0;k<4;k++) {
							gr[row][k] = new SPoint(0, 0);
						}			
						continue outer;
					}
					if(flag==1) {
						//지우고 점수 획득하는 로직(한칸씩 다 당기기)
						for(int l=9;l>3;l--) {
							for(int k=0;k<4;k++) {
								if(gr[l][k].no==1) {
									int no1 = gr[l][k].bl;
									gr[l][k] = new SPoint(0, 0);
									int tx = l;
									int ty = k;
									while(true) {
										tx++;
										if(tx==10 || gr[tx][ty].bl>0) {
											tx--;
											gr[tx][ty] = new SPoint(no1, 1);
											break;
										}
									}
								}else if(gr[l][k].no==2) {
									int no2 = gr[l][k].bl;
									gr[l][k] = new SPoint(0, 0);
									gr[l][k+1] = new SPoint(0, 0);		
									int tx = l;
									int ty1 = k;
									int ty2 = k+1;
									while(true) {
										tx++;
										if(tx==10 || gr[tx][ty1].bl>0 || gr[tx][ty2].bl>0) {
											tx--;
											gr[tx][ty1] = new SPoint(no2, 2);
											gr[tx][ty2] = new SPoint(no2, 2);
											break;
										}
									}
									k++;
								}else if(gr[l][k].no==3) {
									int no3 = gr[l][k].bl;
									int flag2=0;
									int tx = l;
									int ty = k;
									if(gr[tx][ty].bl == gr[tx-1][ty].bl) {
										flag2=1;
									}
									if(flag2==0) {
										gr[l][k] = new SPoint(0, 0);											
									}else {
										gr[l][k] = new SPoint(0, 0);
										gr[l-1][k] = new SPoint(0, 0);											
									}
									while(true) {
										tx++;
										if(tx==10 || gr[tx][ty].bl>0) {
											tx--;
											if(flag2==1) {
												gr[tx][ty] = new SPoint(no3, 3);
												gr[tx-1][ty] = new SPoint(no3, 3);
											}else {
												gr[tx][ty] = new SPoint(no3, 3);
											}
											break;
										}
									}
								}
							}
						}
						continue outer2;
					}
				}
				if(flag==0) {
					break;
				}
			}
			//연한칸 처리
			int cnt2 = 0;
			for(int j=4;j<6;j++) {
				for(int k=0;k<4;k++) {
					if(bl[k][j].bl>0) {
						cnt2++;
						break;
					}
				}
			}
			//			System.out.println(cnt2);
			if(cnt2>0) {
				for(int m=0;m<cnt2;m++) {
					for(int k=0;k<4;k++) {
						bl[k][9] = new SPoint(0, 0);
					}
					//					for(int z=0;z<4;z++) {
					//						for(int x=4;x<10;x++) {
					//							System.out.print(bl[z][x].bl+" ");
					//						}System.out.println();
					//					}
					for(int l=9;l>3;l--) {
						for(int k=3;k>=0;k--) {
							if(bl[k][l].no==1) {
								int no1 = bl[k][l].bl;
								int tx = k;
								int ty = l;
								while(true) {
									ty++;
									if(ty==10 || bl[tx][ty].bl>0) {
										ty--;
										bl[tx][ty] = new SPoint(no1, 1);
										break;
									}
								}
								bl[k][l] = new SPoint(0, 0);
							}else if(bl[k][l].no==2) {
								int no2 = bl[k][l].bl;
								int flag=0;
								int tx = k;
								int ty = l;
								if(bl[tx][ty].bl == bl[tx][ty-1].bl) {
									//									System.out.println("같음");
									flag=1;
								}
								if(flag==0) {
									bl[k][l] = new SPoint(0, 0);											
								}else {
									bl[k][l] = new SPoint(0, 0);
									bl[k][l-1] = new SPoint(0, 0);											
								}
								while(true) {
									ty++;
									if(ty==10 || bl[tx][ty].bl>0) {
										ty--;
										if(flag==1) {
											bl[tx][ty] = new SPoint(no2, 2);
											bl[tx][ty-1] = new SPoint(no2, 2);
										}else {
											bl[tx][ty] = new SPoint(no2, 2);
										}
										break;
									}
								}
							}else if(bl[k][l].no==3) {
								int no3 = bl[k][l].bl;
								bl[k][l] = new SPoint(0, 0);
								bl[k-1][l] = new SPoint(0, 0);
								int tx1 = k;
								int tx2 = k-1;
								int ty = l;
								while(true) {
									ty++;
									if(ty==10 || bl[tx1][ty].bl>0 || bl[tx2][ty].bl>0) {
										ty--;
										bl[tx1][ty] = new SPoint(no3, 3);
										bl[tx2][ty] = new SPoint(no3, 3);
										break;
									}
								}
								k--;
							}
						}
					}
				}
			}

			cnt2 = 0;
			for(int j=4;j<6;j++) {
				for(int k=0;k<4;k++) {
					if(gr[j][k].bl>0) {
						cnt2++;
						break;
					}
				}
			}
			if(cnt2>0) {
				for(int m=0;m<cnt2;m++) {
					for(int k=0;k<4;k++) {
						gr[9][k] = new SPoint(0, 0);
					}
					for(int l=9;l>3;l--) {
						for(int k=0;k<4;k++) {
							if(gr[l][k].no==1) {
								int no1 = gr[l][k].bl;
								int tx = l;
								int ty = k;
								while(true) {
									tx++;
									if(tx==10 || gr[tx][ty].bl>0) {
										tx--;
										gr[tx][ty] = new SPoint(no1, 1);
										break;
									}
								}
								gr[l][k] = new SPoint(0, 0);
							}else if(gr[l][k].no==2) {
								int no2 = gr[l][k].bl;
								gr[l][k] = new SPoint(0, 0);
								gr[l][k+1] = new SPoint(0, 0);
								int tx = l;
								int ty1 = k;
								int ty2 = k+1;
								while(true) {
									tx++;
									if(tx==10 || gr[tx][ty1].bl>0 || gr[tx][ty2].bl>0) {
										tx--;
										gr[tx][ty1] = new SPoint(no2, 2);
										gr[tx][ty2] = new SPoint(no2, 2);
										break;
									}
								}
								k++;
							}else if(gr[l][k].no==3) {
								int no3 = gr[l][k].bl;
								int flag=0;
								int tx = l;
								int ty = k;
								if(gr[tx][ty].bl == gr[tx-1][ty].bl) {
									flag=1;
								}
								if(flag==0) {
									gr[l][k] = new SPoint(0, 0);											
								}else {
									gr[l][k] = new SPoint(0, 0);
									gr[l-1][k] = new SPoint(0, 0);											
								}
								while(true) {
									tx++;
									if(tx==10 || gr[tx][ty].bl>0) {
										tx--;
										if(flag==1) {
											gr[tx][ty] = new SPoint(no3, 3);
											gr[tx-1][ty] = new SPoint(no3, 3);
										}else {
											gr[tx][ty] = new SPoint(no3, 3);
										}
										break;
									}
								}
							}
						}
					}
				}
			}
//			System.out.println("파란블록2");
//			for(int j=0;j<4;j++) {
//				for(int k=4;k<10;k++) {
//					System.out.print(bl[j][k].bl+"\t");
//				}System.out.println();
//			}
//			System.out.println("초록블록2");
//			for(int j=4;j<10;j++) {
//				for(int k=0;k<4;k++) {
//					System.out.print(gr[j][k].bl+"\t");
//				}System.out.println();
//			}
//			System.out.println(score);
			num++;
		}
		for(int i=0;i<4;i++) {
			for(int j=4;j<10;j++) {
				if(bl[i][j].bl>0) {
					bcnt++;
				}
			}
		}
		for(int i=4;i<10;i++) {
			for(int j=0;j<4;j++) {
				if(gr[i][j].bl>0) {
					bcnt++;
				}
			}
		}
		System.out.println(score);
		System.out.println(bcnt);
	}
}