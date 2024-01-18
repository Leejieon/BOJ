import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int number = 1; number <= N; number++) {
            if (checkNumber(number + "")) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static boolean checkNumber(String number) {
        char[] numbers = number.toCharArray();

        // 한 자리 수인 경우
        if (numbers.length == 1) {
            return true;
        }

        int diff = numbers[1] - numbers[0];
        for (int idx = 2; idx < numbers.length; idx++) {
            if (diff != (numbers[idx] - numbers[idx - 1])) {
                return false;
            }
        }

        return true;
    }
}