class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for(int i = 0; i < timelogs.length; i++) {
            int cuttime = getTime(schedules[i]) + 10;
            
            boolean success = true;
            int today = startday;
            for(int j = 0; j < 7; j++) {
                if(today == 0) {
                    today++;
                    j--;
                    continue;
                }
                if(today == 6 || today == 7) {
                    today = (today + 1) % 8;
                    continue;
                }
                if(getTime(timelogs[i][j]) > cuttime) {
                    success = false;
                    break;
                }
                today = (today + 1) % 8;
            }
            if(success) {
                answer++;
            }
        }
        return answer;
    }
    
    int getTime(int time) {
        return (time / 100) * 60 + time % 100; 
    }
}