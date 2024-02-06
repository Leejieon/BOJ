import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int[] snacks;

	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 과자 봉지 개수
			int M = Integer.parseInt(st.nextToken()); // 무게 합 제한

			snacks = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}

			int answer = -1;
			loop : for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					int sum = snacks[i] + snacks[j];
					if (sum == M) {
						answer = sum;
						break loop;
					} else if (sum < M) {
						answer = Math.max(answer, sum);
					}
				}
			}

			sb.append(answer).append("\n");
		}
		System.out.print(sb);
	}
}