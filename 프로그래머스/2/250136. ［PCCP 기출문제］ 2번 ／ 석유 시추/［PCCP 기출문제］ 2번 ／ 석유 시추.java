import java.util.*;

class Solution {
    static int N, M, size;
    static int[][] land;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    static HashMap<Integer, Integer> group = new HashMap<>();
    
    public int solution(int[][] land) {
        int answer = Integer.MIN_VALUE;
        
        N = land.length;
        M = land[0].length;
        this.land = land;
        visited = new boolean[N][M];
        
        int groupNum = 1;
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(visited[y][x]) continue;
                if(land[y][x] == 0) continue;
                 
                visited[y][x] = true;
                land[y][x] = groupNum;
                size = 1; // 해당 그룹의 크기
                dfs(groupNum, y, x);
                group.put(groupNum, size);
                groupNum++;
            }
        }
        
        for(int x = 0; x < M; x++) {
            Set<Integer> set = new HashSet<>();
            
            int result = 0;
            for(int y = 0; y < N; y++) {
                set.add(land[y][x]);
            }
            
            for(int num : set) {
                if(num != 0) {
                    result += group.get(num);
                }
            }
            answer = Math.max(answer, result);
        }
        
        return answer;
    }
    
    static void dfs(int groupNum, int y, int x) {
        for(int[] d : dir) {
            int dy = y + d[0];
            int dx = x + d[1];
            
            if(isOutOfBound(dy, dx)) continue;
            if(visited[dy][dx]) continue;
            if(land[dy][dx] == 0) continue;
            
            visited[dy][dx] = true;
            land[dy][dx] = groupNum;
            size++;
            dfs(groupNum, dy, dx);
        }
    }
    
    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }
}