import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static int N, answer;
	static boolean[] selected;
	static int[] numbers;
	static int[][] distance;
	static ArrayList<Point> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");

			N = Integer.parseInt(br.readLine()) + 2;

			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				list.add(new Point(y, x));
			}

			distance = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) {
						distance[i][j] = 0;
					} else {
						distance[i][j] = distance[j][i] =
								Math.abs(list.get(i).y - list.get(j).y) +
								Math.abs(list.get(i).x - list.get(j).x);
					}
				}
			}
            
			answer = Integer.MAX_VALUE;
			selected = new boolean[N];
			numbers = new int[N - 2]; // 회사와 집 빼고.
			permutation(0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	static void permutation(int depth) {
		// Base Case
		if (depth > N - 3) {
			answer = Math.min(answer, calculateDistance());
			return;
		}

		// Recursive Case
		for (int i = 2; i < N; i++) {
			if (!selected[i]) {
				selected[i] = true;
				numbers[depth] = i;
				permutation(depth + 1);
				selected[i] = false;
			}
		}
	}

	static int calculateDistance() {
		int result = 0;

		result += distance[0][numbers[0]];
		for (int i = 1; i < numbers.length; i++) {
			result += distance[numbers[i - 1]][numbers[i]];
		}
		result += distance[numbers[numbers.length - 1]][1];

		return result;
	}

	static class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}