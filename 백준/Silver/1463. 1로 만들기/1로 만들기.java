import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // DP 배열과 초기값 초기화
        dp = new int[N + 1];
        for (int number = 2; number < N + 1; number++) {
            if (number % 6 == 0) {
                dp[number] = Math.min(Math.min(dp[number / 2], dp[number / 3]), dp[number - 1]) + 1;
            } else if (number % 3 == 0) {
                dp[number] = Math.min(dp[number / 3], dp[number - 1]) + 1;
            } else if (number % 2 == 0) {
                dp[number] = Math.min(dp[number / 2], dp[number - 1]) + 1;
            } else {
                dp[number] = dp[number - 1] + 1;
            }
        }
        System.out.println(dp[N]);
    }


}