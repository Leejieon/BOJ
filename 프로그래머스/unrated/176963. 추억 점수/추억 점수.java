import java.util.HashMap;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        HashMap<String, Integer> score = new HashMap<>();
        
        for(int i=0; i<name.length;i++){
            score.put(name[i], yearning[i]);
        }
                
        for(int row=0;row<photo.length;row++){
            int sum = 0;
            
            for(int col=0;col<photo[row].length;col++){
                String person = photo[row][col];
                
                if(score.containsKey(person)){
                    sum += score.get(person);
                }
            }
            
            answer[row] = sum;
        }
        
        return answer;
    }
}