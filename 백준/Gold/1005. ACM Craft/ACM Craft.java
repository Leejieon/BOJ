import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static ArrayList<ArrayList<Integer>> parent;
    static ArrayList<ArrayList<Integer>> child;
    static int[] weight, complete, indeg;
    static Queue<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            result = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 각 건물의 건설 시간 저장
            weight = new int[N + 1];
            // 각 건물의 건설 완료 시간 저장
            complete = new int[N + 1];
            // 각 건물의 in-degree 저장
            indeg = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }

            parent = new ArrayList<>();
            child = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                parent.add(new ArrayList<>());
                child.add(new ArrayList<>());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                parent.get(p).add(c);
                child.get(c).add(p);
                indeg[p]++;
            }

            // 최종적으로 건설할 건물 번호
            int building = Integer.parseInt(br.readLine());

            topologicalSort();

            while (!result.isEmpty()) {
                int x = result.poll();

                // 시작 지점일 경우
                if (parent.get(x).isEmpty()) {
                    complete[x] = weight[x];
                    continue;
                }

                // 한 곳에서만 오는 경우 = In-degree 가 1인 경우
                if (parent.get(x).size() == 1) {
                    complete[x] = weight[x] + complete[parent.get(x).get(0)];
                }
                // 여러 방향에서 오는 경우는 그 중 가장 오래 걸리는 것을 가져와야 한다.
                else {
                    int max_time = Integer.MIN_VALUE;
                    for (int i = 0; i < parent.get(x).size(); i++) {
                        max_time = Math.max(max_time, complete[parent.get(x).get(i)]);
                    }

                    complete[x] = weight[x] + max_time;
                }

                // 탐색 중 건설할 건물을 만나면 종료
                if (x == building) {
                    break;
                }
            }

            System.out.println(complete[building]);

        }
    }

    static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if(indeg[i] == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()) {
            int x = queue.poll();
            result.add(x);

            for (int y : child.get(x)) {
                indeg[y]--;
                if(indeg[y] == 0)
                    queue.add(y);
            }
        }
    }
}
