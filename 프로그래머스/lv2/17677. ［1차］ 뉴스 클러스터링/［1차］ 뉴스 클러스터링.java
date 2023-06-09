import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        float similarity = 0;
        
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
 
        for(int i=1;i<str1.length();i++){
            String temp = str1.substring(i-1, i+1);
            temp = temp.replaceAll("[^a-zA-Z]","");
            if(temp.length() != 2)
                continue;
            
            list1.add(temp); 
        }
        for(int i=1;i<str2.length();i++){
            String temp = str2.substring(i-1, i+1);
            temp = temp.replaceAll("[^a-zA-Z]","");
            if(temp.length() != 2)
                continue;
            
            list2.add(temp);      
        } 
        
        if(list1.size() == 0 && list2.size() == 0){
            return 65536;
        }
        
        
        ArrayList<String> check = new ArrayList<>();
        ArrayList<String> inter = new ArrayList<>();
        
        for(int i=0;i<list1.size();i++){
            String str = list1.get(i);
            if(check.contains(str)){
                continue;
            }
            else{
                check.add(str);
                int num1 = findNumber(list1, str);
                int num2 = findNumber(list2, str);
                
                if(num2 == 0){
                    continue;
                }
                else{
                    int min = Math.min(num1, num2);
                    for(int j=0;j<min;j++){
                        inter.add(str);
                    }
                }
            }
        }    
        
        list1.addAll(list2);
        for(String s : inter){
            if(list1.contains(s))
                list1.remove(s);
        }
        
        float u_num = list1.size();
        float i_num = inter.size();
        
        similarity = i_num/u_num;
        answer = (int)(similarity * 65536);
        
        return answer;
    }
    
    public int findNumber(ArrayList<String> list, String str){
        int count = 0;
        
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals(str))
                count++;
        }
        return count;
    }
}