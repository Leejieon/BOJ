import static java.util.Comparator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int INF = (int)1e8;

	static int N, E;
	static ArrayList<Edge>[] graph;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[n1].add(new Edge(n2, w));
			graph[n2].add(new Edge(n1, w));
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		int sum1 = 0;
		dijkstra(1);
		sum1 += dist[v1];
		dijkstra(v1);
		sum1 += dist[v2];
		dijkstra(v2);
		sum1 += dist[N];

		int sum2 = 0;
		dijkstra(1);
		sum2 += dist[v2];
		dijkstra(v2);
		sum2 += dist[v1];
		dijkstra(v1);
		sum2 += dist[N];

		if (sum1 >= INF && sum2 >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(sum1, sum2));
		}
	}

	static void dijkstra(int start) {
		dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>(comparingInt(o -> o.weight));
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (dist[edge.node] < edge.weight)
				continue;

			for (Edge next : graph[edge.node]) {
				if (dist[next.node] > dist[edge.node] + next.weight) {
					dist[next.node] = dist[edge.node] + next.weight;
					pq.offer(new Edge(next.node, dist[next.node]));
				}
			}
		}
	}

	static class Edge {
		int node, weight;

		Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
}