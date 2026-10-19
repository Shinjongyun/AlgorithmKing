import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int h = health;
        int count = 0;
        int time = 0;
        int idx = 0;
        int hTime = 0;

        while(true){
            
            boolean flag = false;
            int [] cur = new int [2];
            for(int i=idx; i<attacks.length; i++){
                if(time >= attacks[i][0]){
                    flag = true;
                    cur[0] = attacks[i][0]; 
                    cur[1] = attacks[i][1]; 
                    idx = i+1;
                    break;
                }
            }
            
            // 공격 당함
            if(flag){
                
                // 만약에 죽음
                if(h - cur[1] <= 0) return -1;
                
                h -= cur[1];
                hTime = 0;
                count++;
                
                if(count == attacks.length) break;
                
                time++;
                continue;
            }
            
            // 회복할 게 있다면
            if(h < health){
                
                h = Math.min(health, h + bandage[1]); 
                
                
            }

            if(!flag) hTime++;
            if(hTime == bandage[0]){
                h = Math.min(health, h + bandage[2]);   
                hTime = 0;
            }
            time++;
        }
        
        answer = h;
        return answer;
    }
}