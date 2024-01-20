import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        recur(list, 0);
        System.out.println(answer);

    }

    static void recur(List<Integer> list, int sum) {
        // Base Case
        if (list.size() <= 2) {
            answer = Math.max(answer, sum);
            return;
        }

        // Recursive case
        for (int i = 1; i < list.size() - 1; i++) {
            int cur = list.get(i);
            int energy = list.get(i - 1) * list.get(i + 1);
            list.remove(i);
            recur(list, sum + energy);
            list.add(i, cur);
        }
    }
}