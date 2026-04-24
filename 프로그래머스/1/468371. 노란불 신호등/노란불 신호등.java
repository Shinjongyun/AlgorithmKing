import java.util.*;

class Solution {

    static int answer = -1;
    static int[][] signals;
    static int n;
    
    public int solution(int[][] signals) {
        
        this.signals = signals;
        n = signals.length;
        
        int totalTime = 1;
        for(int i=0; i<n; i++){
            totalTime *=20;
        }
        
        for(int i=1; i<=totalTime; i++){
            boolean[] isYellow = new boolean[n];
            
            boolean flag = true;
            for(int j=0; j<n; j++){
                isYellow[j] = checkYellow(i, j);
                if(!isYellow[j]) flag =false;
            }
            
            if(flag) return i;
        }
        return answer;
    }
    
    public static boolean checkYellow(int now, int idx){
        int sec = signals[idx][0] + signals[idx][1] + signals[idx][2]; // 이게 주기
        int time = now % sec; // 나머지 시간
        
        if(time > signals[idx][0] && time <= signals[idx][0] + signals[idx][1]){
            return true;
        }
        return false; 
    }
}