import java.util.*;

class Solution {
    
    static int n; 
    static int[][] dice;
    static int[] optimal;
    static int answer = -1;

    public int[] solution(int[][] dice) {
        
        this.dice = dice;
        n = dice.length;
        optimal = new int [n/2];
        
        int[] choose = new int[n/2];
        back(0, 0, choose);
        return optimal; 
    }
    
    // 순열
    public static void back (int depth, int idx, int[] choose){
        
        if(depth == n/2){
            int result = cal(choose);
            
            if(result > answer){
                answer = result;
                for(int i=0; i<choose.length; i++){
                    optimal[i] = choose[i] + 1;
                }
            }
            
            return; 
        }
        
        for(int i=idx; i<n; i++){
            
            choose[depth] = i;
            back(depth+1, i+1, choose);
        }
    }
    
    public static int cal (int[] choose){
        
        // 전처리
        boolean[] use = new boolean [n];
        for(int i=0; i<choose.length; i++){
            use[choose[i]] = true;
        }
        
        int[][] dp = new int [n/2+1][501];
        
        for(int d=0; d<choose.length; d++){
            int cur = choose[d];
            
            if(d == 0){
                
                for(int i=0; i<6; i++){
                    dp[1][dice[cur][i]]++;   
                }   
                continue;
            }
            
            for(int j=1; j<=400; j++){
                for(int i=0; i<6; i++){
                    dp[d+1][j + dice[cur][i]] += dp[d][j];
                }
            }
        }
        
        
        int[] choose1 = new int[n/2];
        int idx = 0;
        for(int i=0; i<use.length; i++){
            if(idx == n/2) break;
            
            if(!use[i]){
                choose1[idx] = i;
                idx++;
            }
        }
        
        int[][] dp1 = new int [n/2+1][501];
        
        for(int d=0; d<choose1.length; d++){
            int cur = choose1[d];
            
            if(d == 0){
                
                for(int i=0; i<6; i++){
                    dp1[1][dice[cur][i]]++;   
                }   
                continue;
            }
            
            for(int j=1; j<=400; j++){
                for(int i=0; i<6; i++){
                    dp1[d+1][j + dice[cur][i]] += dp1[d][j];
                }
            }
        }
        
        int count = 0;
        for(int i=1; i<=500; i++){
            int cur = dp[n/2][i];
            for(int j=1; j<i; j++){
                count += cur * dp1[n/2][j];
            }
        }
        return count;
    }
}