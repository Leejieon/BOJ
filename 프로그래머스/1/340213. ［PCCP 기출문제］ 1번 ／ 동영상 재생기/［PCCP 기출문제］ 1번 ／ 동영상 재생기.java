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
        String[] times = timeStr.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }
    
    String convertToTimeFormat(int time) {
        int min = time/60;
        int sec = time%60;
        return String.format("%02d:%02d", min, sec);
    }
}