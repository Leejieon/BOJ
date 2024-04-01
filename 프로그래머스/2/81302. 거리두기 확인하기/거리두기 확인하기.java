class Solution {
    final int DEPTH = 2;
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean[][] visited;
    boolean isPossible, isFirst;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
                
        for(int i = 0; i < places.length; i++) {
            String[] place = places[i];
            isPossible = true;
            
            loop : for(int y = 0; y < 5; y++) {
                for(int x = 0; x < 5; x++) {
                    if(!isPossible) {
                        break loop;
                    }
                    
                    if(place[y].charAt(x) == 'P') {
                        visited = new boolean[5][5];
                        visited[y][x] = true;
                        isFirst = true;
                        dfs(place, y, x, 0);
                    }
                }
            }
            answer[i] = isPossible ? 1 : 0;
        }
        
        return answer;
    }
    
    void dfs(String[] place, int y, int x, int count) {
        // Base Case
        if(place[y].charAt(x) == 'P') {
            if(isFirst) {
                isFirst = false;
            } else {
                isPossible = false;
                return;
            }
        }
        
        if(count == DEPTH) {
            return;
        }
        
        // Recursive Case
        for(int[] d : dir) {
            int dy = y + d[0];
            int dx = x + d[1];
            
            if(isOutOfBound(dy, dx)) continue;
            if(visited[dy][dx]) continue;
            if(place[dy].charAt(dx) == 'X') continue;
            
            if(!isPossible) return;
            visited[dy][dx] = true;
            dfs(place, dy, dx, count + 1);
        }
    }
    
    boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= 5 || x >= 5;
    }
}