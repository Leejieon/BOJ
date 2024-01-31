import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int[] numbers;
    static int[] select;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            if (K == 0) break;

            sb = new StringBuilder();
            numbers = new int[K];
            for (int i = 0; i < K; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            select = new int[6];

            // kC6 구하기
            combination(0, 0);
            System.out.println(sb);
        }
    }

    static void combination(int start, int depth) {
        if (depth == 6) {
            for (int i : select) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < K; i++) {
            select[depth] = numbers[i];
            combination(i + 1, depth + 1);
        }
    }
}
