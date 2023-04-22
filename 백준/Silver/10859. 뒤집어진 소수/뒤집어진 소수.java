import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        long n = Long.parseLong(String.valueOf(input));
        if (isPrime(n)) {
            System.out.println("no");
        } else {
            if (!check(input)) System.out.println("no");
            else {
                long after = change(input);
                if (isPrime(after)) System.out.println("no");
                else System.out.println("yes");
            }
        }
    }

    public static boolean isPrime(long num) {
        if (num == 1) return true;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return true;
        }
        return false;
    }

    public static boolean check(String s) {
        long num = Long.parseLong(s);
        while (num > 0) {
            if (num % 10 == 3 || num % 10 == 4 || num % 10 == 7) return false;
            num /= 10;
        }
        return true;
    }

    public static long change(String s) {
        long num = Long.parseLong(s);
        long result = 0;
        while (num > 0) {
            if (num % 10 == 6) result = result * 10 + 9;
            else if (num % 10 == 9) result = result * 10 + 6;
            else
                result = result * 10 + num % 10;
            num /= 10;
        }
        return result;
    }
}