import java.util.*;

class Solution {
    
    static int[][] puddle;
    static int mod = 1000000007;
    static boolean[][] isPuddle;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m+1][n+1];
        isPuddle = new boolean[m+1][n+1];
        
        for (int[] p : puddles) {
            isPuddle[p[0]][p[1]] = true;
        }
        
        dp[1][1] = 1;

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(canPlace(i, j)){    // 내가 웅덩이가 아닌지
                    if(i+1<m+1 && canPlace(i+1, j)){ // 아래 쪽이 열려있는 지
                        dp[i+1][j] = (dp[i][j] + dp[i+1][j]) % mod;
                    }

                    if(j+1<n+1 && canPlace(i, j+1)){ // 아래 쪽이 열려있는 지
                        dp[i][j+1] = (dp[i][j] + dp[i][j+1]) % mod;
                    }
                }
            }
        }
        int answer = dp[m][n];
        return answer;
    }
    
    
    public static boolean canPlace(int i, int j){
        if(isPuddle[i][j]){
            return false;
        }
        return true;
    }
}
