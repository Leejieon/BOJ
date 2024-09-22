import java.util.*;

class Solution {
    PriorityQueue<Integer> timeQ = new PriorityQueue<>();
    
    public String solution(int n, int t, int m, String[] timetable) {        
        for(String time : timetable) {
            timeQ.offer(convertTimeToInt(time));
        }
        
        int start = 9*60 - t;
        for(int i = 0; i < n - 1; i++) {
            // 셔틀의 도착 시간
            start += t;
            for(int j = 0; j < m; j++) {
                if(!timeQ.isEmpty() && timeQ.peek() <= start) {
                    timeQ.poll();
                } else {
                   break; 
                }
            }
        }
        
        // 마지막 셔틀 = 타야할 셔틀
        start += t;
        int resultTime = 0;
        // m번째 대기자가 없는 경우 : 셔틀 도착시간이 콘의 도착시간
        if(timeQ.size() < m) {
            resultTime = start;
        } 
        // m번째 대기자가 존재하는 경우
        else {
            // m번째 대기자 시간 확인
            for(int i = 0; i < m - 1; i++) {
                timeQ.poll();
            }
            int mth = timeQ.poll();

            // m번째 대기자가 도착 시간보다 늦은 경우
            if(mth > start) {
                resultTime = start;
            } else {
                resultTime = mth - 1;
            }
        }
        
        return convertTimeToString(resultTime);
    }
    
    private int convertTimeToInt(String timeStr) {        
        int hour = Integer.parseInt(timeStr.substring(0, 2));
        int min = Integer.parseInt(timeStr.substring(3, 5));

        return hour * 60 + min;
    }
    
    private String convertTimeToString(int time) {
        int hour = time / 60;
        int min = time % 60;
        
        String hourStr = hour < 10 ? "0" + hour : hour + "";
        String minStr = min < 10 ? "0" + min : min + "";
        
        return hourStr + ":" + minStr;
    }
}