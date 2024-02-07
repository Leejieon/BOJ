import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int total = 0; 
        int N = Integer.parseInt(br.readLine());
        
        boolean[][] graph = new boolean[101][101];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            for (int j = x; j < x+10; j++) {
                for (int k = y; k < y+10; k++) {
                    if (!graph[j][k]) {
                        graph[j][k] = true;
                        total++;
                    }
                }
            }
        }
        System.out.print(total);
    }
}