import java.util.HashMap;

class Solution {
    public String[] solution(String[] players, String[] callings) {   
        HashMap<String, Integer> rank = new HashMap<>();
        
        for(int i = 0; i < players.length; i++){
            rank.put(players[i], i);
        }
        
        for(int i=0;i<callings.length;i++){
            // 이름이 불린 사람의 현재 등수
            int index = rank.get(callings[i]);
            // 불린 사람의 앞 사람
            String temp = players[index - 1];
            players[index - 1] = players[index];
            players[index] = temp;
            
            rank.put(callings[i], rank.get(callings[i]) - 1);
            rank.put(players[index], rank.get(players[index]) + 1);
        }
        
        return players;
    }
}
