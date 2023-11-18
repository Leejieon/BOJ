import java.util.Scanner;
import java.util.*;
import java.io.FileInputStream;

class Solution
{
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int answer;
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            graph = new ArrayList<>();
            int N = sc.nextInt();
            int M = sc.nextInt();
            
            for(int i=0; i<=N; i++) {
            	graph.add(new ArrayList<>());
            }
            
            for(int i=0; i<M; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                
                graph.get(x).add(y);
                graph.get(y).add(x);
            }

            visited = new boolean[N + 1];
            answer = 0;
            for(int i=1; i<=N; i++) {
                visited[i] = true;
                dfs(i, 1);
                visited[i] = false;
            }
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
    
    static void dfs(int node, int distance) {
        if(distance > answer) {
        	answer = distance;
        }
        for(int i=0; i<graph.get(node).size(); i++) {
        	int cur = graph.get(node).get(i);
            if(!visited[cur]) {
                visited[cur] = true;
                dfs(cur, distance + 1);
                visited[cur] = false;
            }
        }
    }
}