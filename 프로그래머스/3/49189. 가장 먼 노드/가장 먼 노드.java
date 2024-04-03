import java.util.*;

class Solution {
    final int INF = (int)1e9;
    int[] distance;
    ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    
    public int solution(int n, int[][] edge) {
        // init graph
        for(int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] e : edge) {
            int from = e[0];
            int to = e[1];
            graph.get(from).add(new Edge(to, 1));
            graph.get(to).add(new Edge(from, 1));
        }
        
        // init distance
        distance = new int[n + 1];
        for(int i = 1; i < n + 1; i++) {
            distance[i] = INF;
        }
        
        dijkstra(1);
        
        int max = Arrays.stream(distance).max().getAsInt();
        int answer = 0;
        for(int i = 1; i < n + 1; i++) {
            if(distance[i] == max) {
                answer++;
            }
        }
        return answer;
    }
    
    void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        distance[start] = 0;
        
        pq.offer(new Edge(start, 0));
        
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int dist = edge.cost;
            int cur = edge.to;
            
            if(distance[cur] < dist) continue;
            
            for(int i = 0; i < graph.get(cur).size(); i++) {
                int cost = distance[cur] + graph.get(cur).get(i).cost;
                if(cost < distance[graph.get(cur).get(i).to]) {
                    distance[graph.get(cur).get(i).to] = cost;
                    pq.offer(new Edge(graph.get(cur).get(i).to, cost));
                }
            }
        }
        
    }
    
    class Edge {
        int to;
        int cost;
        
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}