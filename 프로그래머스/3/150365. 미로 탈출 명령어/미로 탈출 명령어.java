import java.util.*;

class Solution {
    
    static String answer = "impossible";
    static boolean found = false;
    
    static int[][] dir = {
        {1, 0},   
        {0, -1},  
        {0, 1},   
        {-1, 0}   
    };
    
    static int N, M, R, C, K;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        N = n;
        M = m;
        R = r;
        C = c;
        K = k;
        
        int dist = Math.abs(x - R) + Math.abs(y - C);
        
        if (dist > K || (K - dist) % 2 != 0) {
            return "impossible";
        }
        
        dfs(x, y, 0, "");
        
        return answer;
    }
    
    public static void dfs(int x, int y, int depth, String route) {
        
        if (found) return;
        
        if (depth == K) {
            if (x == R && y == C) {
                answer = route;
                found = true;
            }
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            
            if (nx < 1 || ny < 1 || nx > N || ny > M) continue;
            
            int remain = K - (depth + 1);
            int dist = Math.abs(nx - R) + Math.abs(ny - C);
            
            if (dist > remain) continue;
            if ((remain - dist) % 2 != 0) continue;
            
            String nextRoute = route;
            
            if (i == 0) nextRoute += "d";
            if (i == 1) nextRoute += "l";
            if (i == 2) nextRoute += "r";
            if (i == 3) nextRoute += "u";
            
            dfs(nx, ny, depth + 1, nextRoute);
            
            if (found) return;
        }
    }
}
