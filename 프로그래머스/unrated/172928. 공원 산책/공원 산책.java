import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        // answer[0] = y, answer[1] = x
        int H = park.length;
        int W = park[0].length();
        
        int[] answer = findStartIndex(park);
        
        for(int i=0;i<routes.length;i++){
            char dir = routes[i].charAt(0);
            int count = routes[i].charAt(2) - '0';
            
            int y = answer[0];
            int x = answer[1];
            
            if(dir == 'N'){
                if(y - count < 0)
                    continue;
                
                String road = "";
                for(int k = y - count; k < y; k++){
                    road += park[k].charAt(x);
                }
            
                if(!road.contains("X")){
                    answer[0] = y - count;
                    answer[1] = x;
                }
            }
            else if(dir == 'S'){
                if(y + count >= H)
                    continue;
                
                String road = "";
                for(int k = y + 1; k <= y + count; k++){
                    road += park[k].charAt(x);
                }
            
                if(!road.contains("X")){
                    answer[0] = y + count;
                    answer[1] = x;
                }
            }
            else if(dir == 'W'){
                if(x - count < 0)
                    continue;
                
                String road = "";
                for(int k = x - count; k < x; k++)
                    road += park[y].charAt(k);
                
                if(!road.contains("X")){
                    answer[0] = y;
                    answer[1] = x - count;
                }
            }
            else{
                if(x + count >= W)
                    continue;
                
                String road = "";
                for(int k = x + 1; k <= x + count; k++)
                    road += park[y].charAt(k);
                
                if(!road.contains("X")){
                    answer[0] = y;
                    answer[1] = x + count;
                }
            }
        }
        
        return answer;
    }
    
    public int[] findStartIndex(String[] park){
        int index[] = new int[2];
        
        for(int i = 0; i < park.length; i++){
            for(int j = 0; j < park[i].length(); j++){
                if(park[i].charAt(j) == 'S'){
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
        }
        
        return index;
    }
    
}