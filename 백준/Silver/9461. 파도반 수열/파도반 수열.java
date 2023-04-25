import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            long[] dp = new long[101];
            
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 2;

            if (N <= 4) {
                System.out.println(dp[N]);
                continue;
            }

            for (int j = 5; j <= N; j++) {
                dp[j] = dp[j - 1] + dp[j - 5];
            }

            System.out.println(dp[N]);
        }
    }
}
