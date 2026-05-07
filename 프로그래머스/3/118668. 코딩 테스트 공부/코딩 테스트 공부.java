import java.util.*;

class Solution {

    static int[][] dp;
    static int maxAlp = 0;
    static int maxCop = 0;
    static int INF = Integer.MAX_VALUE;
    
    public int solution(int alp, int cop, int[][] problems) {
        
        // dp를 쓰는 이유 -> 완탐이 되지 않음 비트마스킹 불가, dfs도 3^100이 되므로 불가
        
        for(int[] p : problems){
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }
        dp = new int [maxAlp+1][maxCop+1];
        for(int i=0; i<=maxAlp; i++){
            for(int j=0; j<=maxCop; j++){
                dp[i][j] = INF;
            }
        }
        
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        dp[alp][cop] = 0;
        for(int i=alp; i<=maxAlp; i++){
            for(int j=cop; j<=maxCop; j++){
                
                if(i+1 <= maxAlp){
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                }
                
                if(j+1 <= maxCop){
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                }
                
                for(int[] p : problems){
                    
                    int reqAl = p[0];
                    int reqCo = p[1];
                    int rwAl = p[2];
                    int rwCo = p[3];
                    int cost = p[4];
                    
                    if(reqAl <= i && reqCo <= j){
                        
                        int nextAl = Math.min(i+rwAl, maxAlp);
                        int nextCo = Math.min(j+rwCo, maxCop);
                        
                        dp[nextAl][nextCo] = Math.min(dp[nextAl][nextCo], dp[i][j] + cost);
                    }
                }
                
            }
        }
        return dp[maxAlp][maxCop];
        
    }
}