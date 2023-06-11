import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        ArrayList<String> dic = new ArrayList<>(Arrays.asList(
            "-","A","B","C","D","E","F","G","H",
            "I","J","K","L","M","N","O","P","Q",
            "R","S","T","U","V","W","X","Y","Z"));

        for(int i = 0;i<msg.length();i++){
            int dic_count = dic.size();            
            String w = "";
            String c = "";
            int answer_count = answer.size();
            for(int j=i+1;j<msg.length()+1;j++){
                w = msg.substring(i, j);
                
                if(j == msg.length()){
                    answer.add(dic.indexOf(w));
                    i = j - 1;
                    break;
                }
                
                c = msg.substring(i, j+1);

                if(dic.contains(c)){
                    continue;
                }
                else{
                    answer.add(dic.indexOf(w));
                    dic.add(c);
                    i = j - 1;
                    break;
                }
            }
        }
        
        return answer;
    }
}