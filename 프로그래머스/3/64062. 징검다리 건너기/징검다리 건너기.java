import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 1;
        int left = 0;
        int right = Arrays.stream(stones).max().orElse(0);
        int[] newStones = new int[stones.length];
        
        while(left <= right) {
            int mid = (left + right) / 2;
            newStones = crossStones(stones, mid);
            
            if(check(newStones, k)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        
        return answer + 1;
    }
    
    int[] crossStones(int[] stones, int person) {
        return Arrays.stream(stones)
            .map(value -> value - person)
            .toArray();
    }
    
    boolean check(int[] stones, int k) {
        int jump = 0;
        int[] checkArr = new int[stones.length];
        
        for(int index = 0; index < stones.length; index++) {
            if(stones[index] <= 0) {
                jump++;
                checkArr[index] = jump;
            } else {
                jump = 0;
            }
        }
        
        int maxJump = Arrays.stream(checkArr).max().orElse(0);
        
        return k > maxJump;
    }
}