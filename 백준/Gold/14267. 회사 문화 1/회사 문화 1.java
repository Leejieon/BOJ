import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<ArrayList<Integer>> child = new ArrayList<>();
    static int N, M, root;
    static int[] praise;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        praise = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            child.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            int parent = sc.nextInt();
            if (parent == -1) {
                root = i;
                continue;
            }
            child.get(parent).add(i);
        }

        for (int i = 0; i < M; i++) {
            int person = sc.nextInt();
            int w = sc.nextInt();

            praise[person] += w;
        }

        DFS(root);

        for (int i = 1; i <= N; i++) {
            System.out.print(praise[i] + " ");
        }

    }

    static void DFS(int x) {
        if (child.get(x).isEmpty()) {
            return;
        }
        for (int cand = 0; cand < child.get(x).size(); cand++) {
            int next = child.get(x).get(cand);

            praise[next] += praise[x];
            DFS(next);
        }
    }
}
