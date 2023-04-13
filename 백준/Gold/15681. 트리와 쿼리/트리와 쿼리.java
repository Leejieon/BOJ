import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, R, Q;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt(); // root
        Q = sc.nextInt();
        count = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        DFS(R, -1);

        for (int i = 0; i < Q; i++) {
            int q = sc.nextInt();
            System.out.println(count[q]);
        }

    }

    static void DFS(int x, int parent) {
        count[x] = 1;
        for (int y : graph.get(x)) {
            if(y == parent) continue;
            DFS(y, x);
            count[x] += count[y];
        }
    }

}
