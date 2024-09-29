class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int videoSec = convertToSec(video_len);
        int curSec = convertToSec(pos);
        int opStartSec = convertToSec(op_start);
        int opEndSec = convertToSec(op_end);
        
        for(String command : commands) {
            // 현재 위치가 오프닝 중인 경우
            if(opStartSec <= curSec && curSec <= opEndSec) {
                curSec = opEndSec;
            }
            
            if(command.equals("prev")) {
                curSec = curSec < 10 ? 0 : curSec - 10;
            } else {
                curSec = curSec > videoSec - 10 ? videoSec : curSec + 10;
            }
        }
        if(opStartSec <= curSec && curSec <= opEndSec) {
            curSec = opEndSec;
        }
        
        return convertToTimeFormat(curSec);
    }
    
    int convertToSec(String timeStr) {
        return Integer.parseInt(timeStr.split(":")[0]) * 60 
            + Integer.parseInt(timeStr.split(":")[1]);
    }
    
    String convertToTimeFormat(int sec) {
        String hour = sec / 60 >= 10 ? "" + sec/60 : "0" + sec/60;
        String min = sec % 60 >= 10 ? "" + sec%60 : "0" + sec%60;
        return hour + ":" + min;
    }
}