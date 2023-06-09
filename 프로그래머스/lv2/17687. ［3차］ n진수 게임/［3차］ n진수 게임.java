class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = ""; 
        
        int number = 0; // 숫자
        int count = 0; // 진행된 횟수
        int order = 0; // 순서
        while(true){
            String number_s = makeNumber(n, number);
            for(int i=0;i<number_s.length();i++){
                order++;
                // 인원 수만큼 돌면 첫 번째 차례로 돌아가기
                if(order > m){
                    order = 1;
                }
                
                if(order == p){
                    answer += number_s.charAt(i);
                }
                if(answer.length() == t)
                    break;
            }
            
            if(answer.length() == t)
                break;
            
            number++;
        }
                
        return answer;
    }
    
    public String makeNumber(int n, int number){
        if(number == 0)
            return "0";
        
        String result = "";
        
        int mod = 0;
        while(true){
            if(number == 0)
                break;
            
            mod = number % n;
            
            String mod_s = "";
            if(mod >= 10)
                mod_s = String.valueOf((char)(65 + (mod - 10))); 
            else
                mod_s = mod + "";
                                       
            result += mod_s;
            number /= n;
        }
                
        StringBuffer sb = new StringBuffer(result);
        result = sb.reverse().toString();
        return result;
    }
    
}