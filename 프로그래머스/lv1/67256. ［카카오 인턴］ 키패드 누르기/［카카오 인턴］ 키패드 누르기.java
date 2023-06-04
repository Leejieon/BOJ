class Solution {
    public static int[][] keypad = {{1,2,3},{4,5,6},{7,8,9},{-1,0,-1}};
    
    public String solution(int[] numbers, String hand) {
        String answer = "";
     
        Point left = new Point(3, 0);
        Point right = new Point(3, 2);
        
        for(int i=0;i<numbers.length;i++){
            Point p = findPoint(numbers[i]);
            
            
            int left_dist = Math.abs(left.y - p.y) + Math.abs(left.x - p.x);
            int right_dist = Math.abs(right.y - p.y) + Math.abs(right.x - p.x);
            
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7){
                answer += "L";
                left.x = 0;
                left.y = numbers[i]/3;
            }
            else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){
                answer += "R";
                right.x = 2;
                right.y = numbers[i]/3 - 1;
            }
            else{
                if(left_dist < right_dist){
                left.y = p.y;
                left.x = p.x;
                answer += "L";
                }
                else if(left_dist > right_dist){
                    right.y = p.y;
                    right.x = p.x;
                    answer += "R";
                }
                else if(left_dist == right_dist){
                    if(hand.equals("right")){
                        right.y = p.y;
                        right.x = p.x;
                        answer += "R";
                    }
                    else{
                        left.y = p.y;
                        left.x = p.x;
                        answer += "L";
                    }
                }
            }
            
        }
        
        return answer;
    }
    
    public Point findPoint(int number){
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                if(keypad[i][j] == number)
                    return new Point(i, j);
            }
        }
        
        return new Point(-1, -1);
    }
    
    public static class Point{
        int y, x;
        
        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}