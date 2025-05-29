import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			dp[i][1] = 1;
		}
		for (int i = 0; i <= K; i++) {
			dp[1][i] = i;
		}

		for (int i = 2; i <= N; i++) {
			for (int k = 2; k <= K; k++) {
				dp[i][k] = (dp[i - 1][k] + dp[i][k - 1]) % 1_000_000_000;
			}
		}

		System.out.println(dp[N][K] % 1_000_000_000);
	}
}
