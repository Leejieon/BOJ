import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String s) {        
        ArrayList<Integer> result = new ArrayList<>();
        
        String[] list = s.split("}");
        for(int i=0;i<list.length;i++){
            list[i] = list[i].replaceAll("[^0-9,]", "");
        }
        
        // list의 문자열의 길이로 정렬
        Arrays.sort(list, (String s1, String s2) -> s1.length() - s2.length());
        
        for(int i=0;i<list.length;i++){
            String[] numbers = list[i].split(",");
            
            for(int j=0;j<numbers.length;j++){
                if(numbers[j].equals(""))
                    continue;
                
                int num = Integer.parseInt(numbers[j]);
                if(!result.contains(num)){
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}