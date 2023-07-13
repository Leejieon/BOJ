class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        int lux = 100;
        int luy = 100;
        int rdx = -1;
        int rdy = -1;
        
        for(int y=0;y<wallpaper.length;y++){
            char[] files = wallpaper[y].toCharArray();
            for(int x=0;x<files.length;x++){
                if(files[x] == '#'){
                    lux = Math.min(lux, y);
                    luy = Math.min(luy, x);
                    rdx = Math.max(rdx, y);
                    rdy = Math.max(rdy, x);
                }
            }    
        }
        
        answer[0] = lux;
        answer[1] = luy;
        answer[2] = rdx + 1;
        answer[3] = rdy + 1;
        
        return answer;
    }
}