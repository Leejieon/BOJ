import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] indeg;
    static ArrayList<ArrayList<Integer>> child = new ArrayList<>();
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 문제의 수
        M = sc.nextInt(); // 정보의 수

        indeg = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            child.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            child.get(x).add(y);
            indeg[y]++;
        }

        solve();

        for (Integer an : ans) {
            System.out.print(an + " ");
        }


    }

    static void solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if(indeg[i] == 0)
                pq.add(i);
        }

        while (!pq.isEmpty()) {
            int x = pq.poll();
            ans.add(x);

            for (int y : child.get(x)) {
                indeg[y]--;
                if(indeg[y] == 0)
                    pq.add(y);
            }
        }
    }
}
