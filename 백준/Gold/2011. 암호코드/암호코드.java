import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.next();

        if (number.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        long[] dp = new long[number.length() + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= number.length(); i++) {
            char prev = number.charAt(i-2);
            if (number.charAt(i-1) == '0') {
                if (prev == '1' || prev == '2') {
                    dp[i] = dp[i - 2] % 1000000;
                }
                else
                    break;
            }
            else{
                if (prev == '0') {
                    dp[i] = dp[i - 1] % 1000000;
                }
                else{
                    int num = Integer.parseInt(number.substring(i - 2, i));
                    if (num >= 10 && num <= 26) {
                        dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000;
                    }
                    else
                        dp[i] = dp[i - 1] % 1000000;
                }
            }
        }

        System.out.println(dp[number.length()] % 1000000);

    }


}
