import java.util.*;

class Solution {
    
    static String answer = "impossible";
    static boolean found = false;
    static int n, m, x, y, r, c, k;
    
    static int[][] dir = {
        {1, 0},   // d
        {0, -1},  // l
        {0, 1},   // r
        {-1, 0}   // u
    };
    
    static char[] dirChar = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        this.x = x;
        this.y = y;
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        
        answer = "impossible";
        found = false;
        
        int dist = Math.abs(x - r) + Math.abs(y - c);
        
        if (dist > k || (k - dist) % 2 == 1) {
            return "impossible";
        }
        
        back(0, x, y, new StringBuilder());
        
        return answer;
    }
    
    public static void back(int depth, int curX, int curY, StringBuilder sb) {
        
        if (found) return;
        
        if (depth == k) {
            if (curX == r && curY == c) {
                answer = sb.toString();
                found = true;
            }
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int nx = curX + dir[i][0];
            int ny = curY + dir[i][1];
            
            if (nx < 1 || ny < 1 || nx > n || ny > m) {
                continue;
            }
            
            int remain = k - depth - 1;
            int need = Math.abs(nx - r) + Math.abs(ny - c);
            
            if (need > remain) {
                continue;
            }
            
            if ((remain - need) % 2 == 1) {
                continue;
            }
            
            sb.append(dirChar[i]);
            back(depth + 1, nx, ny, sb);
            sb.deleteCharAt(sb.length() - 1);
            
            if (found) return;
        }
    }
}