import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int LEN = 19;
	static final int[][] dir = {
			{0, 1}, // 0 : 가로
			{1, 0}, // 1 : 세로
			{1, 1}, // 2 : 대각선 아래
			{-1, 1} // 3 : 대각선 위 
	};
	static int[][] graph = new int[LEN + 2][LEN + 2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int row = 1; row <= LEN; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 1; col <= LEN; col++) {
				graph[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		int win = 0;
		int resultY = 0;
		int resultX = 0;
		recur : for(int row = 1; row <= LEN; row++) {
			for(int col = 1; col <= LEN; col++) {
				if(graph[row][col] != 0) {
					for(int d = 0; d < dir.length; d++) {
						if(checkOmok(row, col, graph[row][col], d) == 5) {
							win = graph[row][col];
							resultY = row;
							resultX = col;
							break recur;
						}
					}
				}
			}
		}
		
		System.out.println(win);
		if(win != 0) {
			System.out.println(resultY + " " + resultX);
		}
		
	}
	
	static int checkOmok(int row, int col, int stone, int direction) {
		int result = 0;

		// 현재 돌 뒤 쪽 돌의 색깔과 같으면 안된다.
		if(graph[row - dir[direction][0]][col - dir[direction][1]] == stone) 
			return 0;
		
		while(true) {
			// Base Case
			// 범위를 벗어난 경우
			if(isOutOfBound(row, col)) break;
			
			// 같은 색상이 아닐 경우
			if(graph[row][col] != stone) break;
			
			// Recursive Case
			result++;
			row += dir[direction][0];
			col += dir[direction][1];
		}
		
		return result;
	}
	
	static boolean isOutOfBound(int row, int col) {
		return row <= 0 || col <= 0 || row >= LEN + 1 || col >= LEN + 1;
	}
}

