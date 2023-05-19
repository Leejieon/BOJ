import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static ArrayList<Integer> node;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            if (N == 0 && K == 0) {
                break;
            }

            st = new StringTokenizer(br.readLine());
            node = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                node.add(Integer.parseInt(st.nextToken()));
            }

            // 트리 만들기
            int cur_root = 0; // 현재 연결하고자 하는 부모 노드
            int[] parent = new int[N + 1]; // 각 인덱스의 부모 노드
            int[] child = new int[N + 1]; // 인덱스의 자식의 개수
            parent[cur_root] = -1;
            for (int i = 1; i < N; i++) {
                int cur_node = node.get(i);
                int prev_node = node.get(i - 1);

                // 연속되지 않은 수열이 오면 새로운 부모 노드를 찾아 연결
                if (cur_node - prev_node != 1) {
                    cur_root = findNext(child);
                }
                parent[i] = cur_root;
                child[cur_root]++;
            }

            int cousin = findCousin(parent, child);
            System.out.println(cousin);

        }

    }

    static int findNext(int[] child) {
        // 자식이 없는 최소 노드 찾아 반환하기
        for (int i = 0; i <= N; i++) {
            if (child[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    static int findCousin(int[] parent, int[] child) {
        int index = node.indexOf(K);
        int K_parent = parent[index]; // K의 부모 와는 달라야하고,
        if (K_parent == -1) {
            return 0;
        }
        int K_parent_root = parent[K_parent]; // K의 부모의 부모와는 같아야 한다.
        if (K_parent_root == -1) {
            return 0;
        }

        int ans = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (parent[i] == K_parent_root) {
                list.add(i);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            int cur = list.get(i);
            if (cur != K_parent) {
                ans += child[cur];
            }
        }

        return ans;
    }
}
