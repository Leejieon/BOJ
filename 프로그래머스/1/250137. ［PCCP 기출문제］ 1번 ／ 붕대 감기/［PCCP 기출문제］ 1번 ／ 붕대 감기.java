import java.util.*;

class Solution {
    static HashMap<Integer, Integer> map = new HashMap<>();
    
    public int solution(int[] bandage, int health, int[][] attacks) {      
        for(int i = 0; i < attacks.length; i++) {
            map.put(attacks[i][0], attacks[i][1]);
        }
        
        int time = 0;
        int continueSuccess = 0;
        int curHealth = health;
        int count = 0;
        while(true) {
            if(curHealth <= 0) {
                curHealth = -1;
                break;
            }
            
            if(count == attacks.length) {
                break;
            }
            
            // 해당 시간에 공격이 있는지 확인
            if(map.containsKey(time)) {
                curHealth -= map.get(time);
                continueSuccess = 0; 
                count++;
                time++;
                continue;
            }
            
            continueSuccess++;
            
            if(continueSuccess == bandage[0]) {
                curHealth = curHealth + bandage[2] > health ? health : curHealth + bandage[2];
                continueSuccess = 0;
            }
            curHealth = curHealth + bandage[1] > health ? health : curHealth + bandage[1];
            time++;
        }
        
        return curHealth;
    }
}