import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] fees, String[] records) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        Map<String, Integer> car = new HashMap<>();
        Map<String, Integer> result = new HashMap<>();
        Map<String, Integer> car_fee = new TreeMap<>();
        
        for(int i=0;i<records.length;i++){
            int time = getTime(records[i].split(" ")[0]);
            String car_num = records[i].split(" ")[1];
            String history = records[i].split(" ")[2];
            
            if(history.equals("IN")){
                car.put(car_num, time);
            }
            else{
                car.put(car_num, time - car.get(car_num));
                
                if(result.containsKey(car_num)){
                    result.put(car_num, result.get(car_num) + car.get(car_num));
                }
                else{
                    result.put(car_num, car.get(car_num));
                }
                car.remove(car_num);
            }
        }
        
        // 남은 자동차 23:59으로 계산
        for(String car_num : car.keySet()){
            int time = getTime("23:59");
            car.put(car_num, time - car.get(car_num));
            if(result.containsKey(car_num)){
                    result.put(car_num, result.get(car_num) + car.get(car_num));
                }
            else{
                result.put(car_num, car.get(car_num));
            }
        }
        
        for(String car_num : result.keySet()){
            car_fee.put(car_num, getFee(fees, result.get(car_num)));
        }
        
        for(String key : car_fee.keySet()){
            System.out.println(key);
            answer.add(car_fee.get(key));
        }
        
        return answer;
    }
    
    public int getTime(String time){
        int hour = Integer.parseInt(time.split(":")[0]);
        int min = Integer.parseInt(time.split(":")[1]);
        
        return hour * 60 + min;
    }
    
    public int getFee(int[] fees, int time){
        int base_time = fees[0];
        int base_fee = fees[1];
        int unit_time = fees[2];
        int unit_fee = fees[3];
        
        int result = base_fee;
        
        if(time > base_time){
            result += Math.ceil((time - base_time)/(double)unit_time) * unit_fee;
        }
        
        return result;
    }
    
    
}