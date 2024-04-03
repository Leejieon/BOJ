import java.util.*;

class Solution {
    int N;
    int[][] cache, triangle;
    
    public int solution(int[][] triangle) {
        this.triangle = triangle;
        N = triangle.length;    
        
        cache = new int[501][501];
        for(int y = 0; y < 501; y++) {
            Arrays.fill(cache[y], -1);
        }
        
        return path(0, 0);
    }
    
    int path(int y, int x) {
        // Base Case
        if(y >= N - 1 || x > y) {
            return triangle[y][x];
        }
        
        // Recursive Case
        if(cache[y][x] != -1) {
            return cache[y][x];
        }
        return cache[y][x] = Math.max(path(y + 1, x), path(y + 1, x + 1)) + triangle[y][x];
    }
}