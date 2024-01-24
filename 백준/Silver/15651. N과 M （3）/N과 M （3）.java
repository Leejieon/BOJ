import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[M];

        permutation(0);
        System.out.print(sb);
    }

    static void permutation(int depth) {
        // Base Case
        if (depth == M) {
            for (int number : numbers) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
            return;
        }

        // Recursive Case
        for (int cand = 0; cand < N; cand++) {
            numbers[depth] = cand + 1;
            permutation(depth + 1);
        }
    }
}