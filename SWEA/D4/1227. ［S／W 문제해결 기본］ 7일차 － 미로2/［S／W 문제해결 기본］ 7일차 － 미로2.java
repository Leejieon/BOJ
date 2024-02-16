import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static int answer;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int tNum = Integer.parseInt(br.readLine());
            sb.append("#").append(tNum).append(" ");

            int sY = 0;
            int sX = 0;
            int eY = 0;
            int eX = 0;
            map = new int[100][100];
            visited = new boolean[100][100];
            for (int y = 0; y < 100; y++) {
                String line = br.readLine();
                for (int x = 0; x < 100; x++) {
                    map[y][x] = line.charAt(x) - '0';
                    if (map[y][x] == 2) {
                        sY = y;
                        sX = x;
                    }
                    if (map[y][x] == 3) {
                        eY = y;
                        eX = x;
                    }
                }
            }
            
            answer = 0;
            bfs(sY, sX);
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int y, int x) {
        Queue<int[]> queue = new ArrayDeque<>();
        
        visited[y][x] = true;
        queue.offer(new int[]{y, x});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] d : dir) {
                int dy = point[0] + d[0];
                int dx = point[1] + d[1];
                
                if(isOutOfBound(dy, dx)) continue;
                if(visited[dy][dx]) continue;
                if(map[dy][dx] == 1) continue;

                if (map[dy][dx] == 3) {
                    answer = 1;
                    return;
                }
                
                visited[dy][dx] = true;
                queue.offer(new int[]{dy, dx});
            }
        }
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= 100 || x >= 100;
    }
}