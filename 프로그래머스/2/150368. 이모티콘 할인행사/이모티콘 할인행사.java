import java.util.*;

class Solution {
    
    static int[] answer = new int[2];
    static int[] rate = {10, 20, 30, 40};
    static int[] choice;
    static int n; // 이코티콘 수
    static int[][] users;
    static int[] emoticons;
    
    
    public int[] solution(int[][] users, int[] emoticons) {
    
        this.emoticons = emoticons;
        this.users = users;
        n = emoticons.length;
        choice = new int [n+1];
        
        back(1);
        
        return answer;
    }
    
    public static void back(int depth){
        
        if(depth == n+1){
            cal();
            return;
        }
        
        
        for(int i=0; i<4; i++){
            choice[depth] = rate[i];
            back(depth + 1);
            choice[depth] = 0;
            
        }
    }
    
    public static void cal(){
        
        int totalS = 0;
        int total = 0;
        
        for(int[] u : users){
            
            int yRate = u[0];
            int yLimit = u[1];
            
            int count = 0;
            for(int i=0; i<n; i++){
                // 내가 원하는 게 더 크면 스킵
                if(yRate > choice[i+1]){
                    
                } else{
                    count += emoticons[i] * (100 - choice[i+1]) / 100;
                }
            }
            
            if(count >= yLimit){
                totalS++;
                continue;
            }
            total += count;
        }
        
        if(answer[0] < totalS){
            answer[0] = totalS;
            answer[1] = total;
            return;
        }
        
        if(answer[0] == totalS){
            answer[1] = Math.max(answer[1], total);
            return;
        }
    }
        
}