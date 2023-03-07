import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max, min;
    static int[] nums, operators, orders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        nums = new int[N];
        orders = new int[N-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        operators = new int[5];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < 5; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        rec_func(0, nums[0]);

        System.out.println(max + "\n" + min);
    }

    static void rec_func(int k, int value) {
        if (k + 1 == N) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        } else {
            for (int cand = 1; cand < 5; cand++) {
                if (operators[cand] >= 1) {
                    operators[cand]--;
                    orders[k] = cand;

                    rec_func(k+1, calculator(value, cand, nums[k + 1]));

                    operators[cand]++;
                    orders[k] = 0;
                }
            }
        }
    }

    static int calculator(int operand1, int operator, int operand2) {
        switch (operator) {
            case 1:
                return operand1 + operand2;
            case 2:
                return operand1 - operand2;
            case 3:
                return operand1 * operand2;
            case 4:
                return operand1 / operand2;
        }

        return -1;
    }
}
