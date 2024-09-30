import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int left = 1;
        int right = getMaxValue(diffs.clone());
        
        while(left < right) {
            int mid = (left + right) / 2;

            if(!isValid(diffs, times, limit, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return right;
    }
    
    boolean isValid(int[] diffs, int[] times, long limit, int level) {
        long total = times[0];
        for(int i = 1; i < diffs.length; i++) {
            if(diffs[i] <= level) {
                total += times[i];
            } else {
                total += (times[i - 1] + times[i]) * (diffs[i] - level) + times[i];
            }
        }

        return total <= limit;
    }
    
    int getMaxValue(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }
}