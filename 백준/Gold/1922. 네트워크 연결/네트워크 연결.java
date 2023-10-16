import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        initParent(N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int com1 = Integer.parseInt(st.nextToken());
            int com2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(com1, com2, weight));
        }

        System.out.println(makeMst());
    }

    static void initParent(int N) {
        parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }

    }

    static int makeMst() {
        int result = 0;

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

        if(node1 != node2)
            parents[node2] = node1;
    }

    static int find(int node) {
        if(parents[node] == node)
            return node;

        return parents[node] = find(parents[node]);
    }

    static boolean isSameParent(int node1, int node2) {
        return find(node1) == find(node2);
    }

    static class Edge implements Comparable<Edge>{
        int node1;
        int node2;
        int weight;

        public Edge(int node1, int node2, int weight) {
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
