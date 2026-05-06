import java.util.*;

class Solution {

    static int[][] dp;
    static int maxA = 0;
    static int maxC = 0;
    static int INF = Integer.MAX_VALUE;
    
    public int solution(int alp, int cop, int[][] problems) {
        
        for(int[] p : problems){
            int a = p[0];
            int b = p[1];
            maxA = Math.max(maxA, a);
            maxC = Math.max(maxC, b);
        }
        dp = new int[maxA+1][maxC+1];
    
        alp = Math.min(alp, maxA);
        cop = Math.min(cop, maxC);
        
         for (int i = 0; i <= maxA; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[alp][cop] = 0;
        
        for(int i=alp; i<=maxA; i++){
            for(int j=cop; j<=maxC; j++){
                
                if(i + 1<= maxA) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                }
                
                if(j + 1 <= maxC) {
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                }
                
                for(int [] cur : problems){
                    
                    int reqAlp = cur[0];
                    int reqCop = cur[1];
                    int rwAlp = cur[2];
                    int rwCop = cur[3]; 
                    int cost = cur[4];
                    
                    // 풀기 가늘?
                    if (i >= reqAlp && j >= reqCop) {
                        int nextAlp = Math.min(maxA, i + rwAlp);
                        int nextCop = Math.min(maxC, j + rwCop);

                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + cost);
                    }
                }
            }
        }
        
        return dp[maxA][maxC];
    }
}