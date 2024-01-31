import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
	static int N;
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb;

		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append("\n");

			N = sc.nextInt();
			int[][] graph = new int[N][N]; // 숫자를 저장할 그래프

			int y = 0, x = 0; // 숫자를 입력할 좌표값
			int number = 1;	  // 그래프에 넣을 현재 숫자
			int type = 0; 	  // 방향

			// Initial
			graph[y][x] = number++;

            while (number <= N * N) {
                y += dir[type][0];
                x += dir[type][1];

                if (isOutOfBound(y, x) || graph[y][x] != 0) {
					y -= dir[type][0];
					x -= dir[type][1];
                    type = (type + 1) % 4;
                    continue;
                }
                graph[y][x] = number++;
            }

			// 출력
			for (int[] ints : graph) {
				for (int anInt : ints) {
					sb.append(anInt).append(" ");
				}
				sb.append("\n");
			}

			System.out.print(sb);
		}
	}

	static boolean isOutOfBound(int y, int x) {
		return y < 0 || x < 0 || y >= N || x >= N;
	}
}