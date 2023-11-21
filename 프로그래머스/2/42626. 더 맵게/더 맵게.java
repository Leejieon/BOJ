import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        
        boolean canMake = false;
        int min = pq.peek();
        while(pq.size() > 1) {
            if(min >= K) {
                canMake = true;
                break;
            }
            else {
                pq.offer(pq.poll() + pq.poll() * 2);
                min = pq.peek();
                answer++;
            }
        }
        
        if(!canMake && min < K)
            return -1;
        
        return answer;
    }
}