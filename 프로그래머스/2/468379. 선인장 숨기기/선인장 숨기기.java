import java.util.*;

class Solution {
    
    static int m, n, h, w;
    static int[] answer;
    static int INF = Integer.MAX_VALUE;
    static int[][] grid;
    static int[][] drops;
    
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        
        answer = new int [2];
        grid = new int [m][n];
        this.n = n;
        this.h = h;
        this.m = m;
        this.w = w;
        this.drops = drops;
        
        for(int i = 0; i<m; i++){
            for(int j =0; j<n; j++){
                grid[i][j] = INF;
            }
        }
        
        for(int i =0; i<drops.length; i++){
            int x = drops[i][0];
            int y = drops[i][1];
            grid[x][y] = i + 1;
        }
        
        binary();
        
        return answer;
    }
    
    public static void binary(){
        
        int low = 1;
        int high = drops.length;
        
        
        while(low <= high){
            
            int mid = (low + high) / 2;
            int[][] sum = new int [m+1][n+1];
            for(int i = 0; i<m; i++){
                for(int j=0; j<n; j++){
                    int val = 0;
                    if(grid[i][j] <= mid) val = 1;
                    sum[i+1][j+1] = val + sum[i+1][j] + sum[i][j+1] - sum[i][j];
                }
            }
            
            if (cal(sum)) low = mid + 1;
            else high = mid - 1;
        }
    }
    
    public static boolean cal(int[][] sum) {

        for (int i = 0; i + h - 1 < m; i++) {
            for (int j = 0; j + w - 1 < n; j++) {

                int area = sum[i + h][j + w]
                         - sum[i][j + w]
                         - sum[i + h][j]
                         + sum[i][j];

                if (area == 0) {
                    answer[0] = i;
                    answer[1] = j;
                    return true;
                }
            }
        }

        return false;
    }
}