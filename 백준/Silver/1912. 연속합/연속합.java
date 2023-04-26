import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");

        int[] numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(str[i - 1]);
        }

        int[] dp = new int[N + 1];
        dp[1] = numbers[1];
        int ans = dp[1];

        for (int i = 2; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1] + numbers[i], numbers[i]);
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}
