import java.util.Scanner;

public class Solution {
	
	static int T, H, W, x, y, d;
	static char[][] map;
	static int[][] dir = {{0,0}, {0,1}, {0,-1}, {1,0}, {-1,0}}; // 동서남북
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			H = sc.nextInt();
			W = sc.nextInt();
			map = new char[H][W];
			
			x = 0;
			y = 0;
			d = 0; 
			
			for(int i=0; i<H; i++) {
				String st = sc.next();
				for(int j=0; j<W; j++) {
					map[i][j] = st.charAt(j);
					
					if(map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						if(map[i][j] == '^') d = 4;
						else if(map[i][j] == 'v') d = 3;
						else if(map[i][j] == '<') d = 2;
						else if(map[i][j] == '>') d = 1;
						
						x = i;
						y = j;
					}
				}
			}
		
			int N = sc.nextInt();
			
			String str = sc.next();
			
			for(int i=0; i<N; i++) {
				char cur = str.charAt(i);
				int nx, ny;
				
				if(cur == 'U' || cur == 'D' || cur == 'L' || cur =='R') {
					if(cur == 'U') {
						d = 4;
						map[x][y] = '^';
					} else if(cur == 'D') {
						d = 3;
						map[x][y] = 'v';
					} else if(cur == 'L') {
						d = 2;
						map[x][y] = '<';
					} else if(cur == 'R') {
						d = 1;
						map[x][y] = '>';
					}
					
					nx = x + dir[d][0];
					ny = y + dir[d][1];
					
					if(isInside(nx, ny)) {
						if(map[nx][ny] == '.') {
							map[nx][ny] = map[x][y];
							map[x][y] = '.';
							x = nx; y = ny;
						}
					}
				} else if(cur == 'S') {
					nx = x;
					ny = y;
					
					while(true) {
						nx += dir[d][0];
						ny += dir[d][1];
						if(!isInside(nx, ny) || map[nx][ny] == '#') break;
						else if(map[nx][ny] == '*') {
							map[nx][ny] = '.';
							break;
						}
					}
				}
			}
			
			System.out.print("#" + tc + " ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
	
	public static boolean isInside(int x, int y) {
		return x>=0 && x<H && y>=0 && y<W; 
	}
}