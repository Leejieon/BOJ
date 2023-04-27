import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> child = new ArrayList<>();
    static ArrayList<Integer> tmp = new ArrayList<>();
    static ArrayList<Integer> ans = new ArrayList<>();
    static int[] indeg;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indeg = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            child.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                tmp.add(Integer.parseInt(st.nextToken()));
            }

            for (int j = 1; j < tmp.size(); j++) {
                child.get(tmp.get(j-1)).add(tmp.get(j));
                indeg[tmp.get(j)]++;
            }

            tmp.clear();
        }

        topological_sort();

        if (ans.size() == N) {
            for (Integer an : ans) {
                System.out.println(an);
            }
        } else {
            System.out.println(0);
        }

    }

    static void topological_sort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if(indeg[i]==0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int x = queue.poll();
            ans.add(x);

            for (int y : child.get(x)) {
                indeg[y]--;
                if (indeg[y] == 0) {
                    queue.add(y);
                }
            }
        }
    }
}
