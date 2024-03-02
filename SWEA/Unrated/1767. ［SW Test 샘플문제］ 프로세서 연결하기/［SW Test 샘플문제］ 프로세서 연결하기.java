import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N,max,totalCnt,min,map[][]; // 멕시노스 크기, 최대코어수, 비가장자리코어수, 최소전선길이합, 멕시노스셀정보
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static ArrayList<int[]> list; // 비가장자리 코어 리스트
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			max = 0;
			totalCnt = 0;
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 비가장자리코어 리스트에 담기
					if(i>0 && i<N-1 && j>0 && j<N-1 && map[i][j]==1) {
						list.add(new int[] {i,j});
						totalCnt++;
					}
				}
			} // 멕시노스 셀 정보 입력 및 비가장자리 코어리스트 생성
			
			go(0, 0, 0);
			System.out.println("#"+tc+" "+min);
		}
		
	}
	
	static void go(int index, int cCnt, int lCnt) { // 현재 코어로 전선처리 시도, cCnt:연결코어갯수, lCnt:전선길이의합
		
		if(cCnt+(totalCnt-index)<max) return; //가지치기
		
		if(index == totalCnt) {
			if(max < cCnt) {
				max = cCnt;
				min = lCnt;
			}else if(max == cCnt) {
				if(min > lCnt) {
					min = lCnt;
				}
			}
			return;
		}
		
		int[] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		
		// 4방으로 시도
		for (int d = 0; d < 4; d++) {
			if(isAvailable(r, c, d)) {
				int len = setStatus(r, c, d, 2); // 전선 놓기
				go(index+1, cCnt+1, lCnt+len); // 다음 코어 가기
				setStatus(r, c, d, 0); // 전선 지우기
			}
		}
		// 전선 놓지 않기
		go(index+1, cCnt, lCnt);
	}
	
	
	static boolean isAvailable(int r,int c, int d) { // r,c 위치에서 d방향으로 전선놓기가 가능한지 체크
		int nr = r;
		int nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) return true;
			if(map[nr][nc] > 0) return false;
		}
	}
	
	static int setStatus(int r,int c, int d, int s) { // r,c 위치(코어위치)에서 d방향으로 s(2:전선,0:빈칸)로 상태 set
		int nr = r;
		int nc = c;
		int cnt = 0;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break;
			map[nr][nc] = s;
			cnt++;// 처리한 빈칸의 개수
		}
		return cnt;
	}
	
}