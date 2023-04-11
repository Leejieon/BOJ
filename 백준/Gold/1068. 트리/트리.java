import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<ArrayList<Integer>> child = new ArrayList<>();
    static int[] leaf;
    static int root, erased;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        leaf = new int[N];
        for (int i = 0; i < N; i++) {
            child.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            int parent = sc.nextInt();
            if (parent == -1) {
                root = i;
                continue;
            }
            child.get(parent).add(i);
        }

        erased = sc.nextInt();

        for (int i = 0; i < N; i++) {
            if (child.get(i).contains(erased)) {
                child.get(i).remove(child.get(i).indexOf(erased));
            }
        }

        if(root != erased)
            DFS(root);

        System.out.println(leaf[root]);

    }

    static void DFS(int x) {
        // x가 리프노드일 경우
        if (child.get(x).isEmpty()) {
            leaf[x] = 1;
        }

        for (int y : child.get(x)) {
            DFS(y);
            // leaf[y] 가 계산된 상태
            leaf[x] += leaf[y];
        }
    }
}
