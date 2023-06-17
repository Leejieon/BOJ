import java.util.*;

class Solution {
    public ArrayList<String> solution(String[] record) {
        ArrayList<String> answer = new ArrayList<>();
        
        ArrayList<History> result = new ArrayList<>();
        Map<String, String> name = new HashMap<>();
    
        for(int i = 0;i<record.length;i++){
            String[] list = record[i].split(" ");
            
            if(list[0].equals("Enter")){
                // 이미 등록된 id가 있다면,
                if(name.containsKey(list[1])){
                    // 이름을 변경하지 않고 들어왔다면,
                    if(!name.get(list[1]).equals(list[2]))
                        name.replace(list[1], list[2]);
                }
                else{
                    name.put(list[1], list[2]);
                }
                
                result.add(new History(list[1], "Enter"));
            }
            else if(list[0].equals("Leave")){
                result.add(new History(list[1], "Leave"));
            }
            else{
                name.replace(list[1], list[2]);
            }
        }
        
        for(int i = 0;i<result.size();i++){
            String mode = result.get(i).mode;
            String user_name = name.get(result.get(i).user_id);
            
            if(mode.equals("Enter")){
                answer.add(user_name + "님이 들어왔습니다.");
            }
            else{
                answer.add(user_name + "님이 나갔습니다.");
            }
        }
        
        return answer;
    }
    
    class History{
        String user_id;
        String mode;
        
        History(String user_id, String mode){
            this.user_id = user_id;
            this.mode = mode;
        }
    }
}