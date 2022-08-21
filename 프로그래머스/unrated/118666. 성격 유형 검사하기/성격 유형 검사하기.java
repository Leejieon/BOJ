import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Map<Character, Integer> result = new HashMap<>();
        result.put('R',0);
        result.put('T',0);
        result.put('C',0);
        result.put('F',0);
        result.put('J',0);
        result.put('M',0);
        result.put('A',0);
        result.put('N',0);
        
        for(int i=0;i<survey.length;++i){
            int score = choices[i]-4;
            if(score==0) continue;
            else if(score>4){//agree
                result.put(survey[i].charAt(1), result.get(survey[i].charAt(1))+score);
            }
            else{//disagree
                result.put(survey[i].charAt(0), result.get(survey[i].charAt(0))-score);
            }
        }
        
        if(result.get('R')>=result.get('T'))
            answer+='R';
        else
            answer+='T';
        if(result.get('C')>=result.get('F'))
            answer+='C';
        else
            answer+='F';
        if(result.get('J')>=result.get('M'))
            answer+='J';
        else
            answer+='M';
        if(result.get('A')>=result.get('N'))
            answer+='A';
        else
            answer+='N';
        
        
        return answer;
    }
}