import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int[] operators, numbers;
	static int N, min, max;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");

			N = Integer.parseInt(br.readLine());

			operators = new int[4]; // 0 : +, 1 : -, 2 : *, 3 : /
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operators[i] = Integer.parseInt(st.nextToken());
			}

			numbers = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			recur(1, numbers[0]);
			sb.append(max - min).append("\n");
		}
		System.out.println(sb);
	}

	static void recur(int depth, int sum) {
		// Base Case
		if (depth == N) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}

		// Recursive Case
		for (int i = 0; i < 4; i++) {
			if (operators[i] > 0) {
				operators[i]--;
				recur(depth + 1, calculate(sum, numbers[depth], i));
				operators[i]++;
			}
		}
	}

	static int calculate(int operand1, int operand2, int operator) {
		switch (operator) {
			case 0:
				return operand1 + operand2;
			case 1:
				return operand1 - operand2;
			case 2:
				return operand1 * operand2;
			case 3:
				return operand1 / operand2;
		}
		return -1;
	}
}