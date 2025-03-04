class Solution {
    public int[] solution(int n, int s) {
        if(n > s) {
            return new int[]{-1};
        }
        
        int[] answer = new int[n];
        
        int quot = s / n;
        int mod = s % n;
        for(int i = 0; i < n; i++) {
            answer[i] = quot;
        }
        for(int m = 0; m < mod; m++) {
            answer[n - 1 - m]++; 
        }
        
        return answer; 
    }
}