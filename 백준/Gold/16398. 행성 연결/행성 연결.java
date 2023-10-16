import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        initInput();

        System.out.println(makeFlow());
    }

    static void initInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        initParent(N);

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                int weight = Integer.parseInt(st.nextToken());
                if(weight != 0)
                    pq.offer(new Edge(y, x, weight));
            }
        }
    }

    static void initParent(int N) {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    static long makeFlow() {
        long result = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (!isSameParent(find(edge.node1), find(edge.node2))) {
                result += edge.weight;
                union(edge.node1, edge.node2);
            }
        }

        return result;
    }

    static void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);

        if (node1 != node2) {
            parents[node2] = node1;
        }
    }

    static int find(int node) {
        if(parents[node] == node)
            return node;

        return parents[node] = find(parents[node]);
    }

    static boolean isSameParent(int node1, int node2) {
        return find(node1) == find(node2);
    }

    static class Edge implements Comparable<Edge> {
        int node1;
        int node2;
        int weight;

        Edge(int node1, int node2, int weight) {
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
