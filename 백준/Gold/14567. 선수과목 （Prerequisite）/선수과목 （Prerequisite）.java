import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> child = new ArrayList<>();
    static int[] indeg, time;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i <= N ; i++) {
            child.add(new ArrayList<>());
        }

        indeg = new int[N + 1];
        time = new int[N + 1];
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            child.get(x).add(y);
            indeg[y]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                queue.add(i);
                time[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int x = queue.poll();

            for (int y : child.get(x)) {
                indeg[y]--;
                time[y] = time[x] + 1;
                if (indeg[y] == 0) {
                    queue.add(y);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(time[i] + " ");
        }

    }
}
