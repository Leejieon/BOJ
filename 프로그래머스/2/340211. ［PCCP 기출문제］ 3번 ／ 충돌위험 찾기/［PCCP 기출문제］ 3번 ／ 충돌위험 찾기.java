import java.util.*;

class Solution {
    int[][][] visited = new int[102][102][20_000];
    int time; // 움직인 시간
    int maxTime = Integer.MIN_VALUE; // 모든 경로 중 최대 소요 시간
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        for(int i = 0; i < routes.length; i++) {
            int[] route = routes[i]; // 경로
            time = 0; // 움직인 시간 초기화
            
            // 출발점
            int fy = points[route[0] - 1][0];
            int fx = points[route[0] - 1][1];
            visited[fy][fx][time++]++;
            
            // 도착점
            int ty = 0;
            int tx = 0;
            for(int j = 1; j < route.length; j++) {
                ty = points[route[j] - 1][0];
                tx = points[route[j] - 1][1];
                
                savePath(fy, fx, ty, tx);
                fy = ty;
                fx = tx;
            }
            maxTime = Math.max(maxTime, time);
        }
        
        // 시간 별 충돌 개수 확인
        for(int t = 0; t <= maxTime; t++) {
            for(int y = 1; y <= 101; y++) {
                for(int x = 1; x <= 101; x++) {
                    if(visited[y][x][t] >= 2) {
                        answer++;
                    }
                }
            }
        }
        
        return answer;
    }
    
    void savePath(int fy, int fx, int ty, int tx) {
        int y = fy;
        int x = fx;
        while(true) {
            // 목적지에 도달한 경우
            if(y == ty && x == tx) break;
            
            // y(r)좌표부터 같게 만들기
            if(y != ty) {
                y = y < ty ? y + 1 : y - 1;
            } else {
                x = x < tx ? x + 1 : x - 1;
            }
            visited[y][x][time++]++;
        }
    }
}