import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] solutions = new int[N];
        for (int i = 0; i < N ; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solutions); // 용액들을 정렬

        int L = 0;
        int R = solutions.length - 1;
        int temp_solution = solutions[L] + solutions[R];
        int result_L = L, result_R = R;
        while (L != R) {
            if (check(temp_solution, solutions[L] + solutions[R])) {
                temp_solution = solutions[L] + solutions[R];
                result_L = L;
                result_R = R;
            }
            if (solutions[L] + solutions[R] == 0) {
                break;
            } else if (solutions[L] + solutions[R] > 0) {
                R--;
            } else {
                L++;
            }
        }

        System.out.println(solutions[result_L] + " " + solutions[result_R]);

    }

    static boolean check(int prev, int cur){
        if(Math.abs(prev - 0) > Math.abs(cur - 0))
            return true;
        return false;
    }

}
