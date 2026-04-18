import java.util.*;

class Solution {
    
    static int m, n, h, w;
    static int[] answer;
    static int INF = Integer.MAX_VALUE;
    static boolean imDone;
    static int[][] grid;
    
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        
        this.m= m;
        this.n=n;
        this.h=h;
        this.w=w;
        
        answer = new int[2];
        grid = new int [m][n];
        
        for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    grid[i][j] = INF;
                }
            }
        
        // 일단 빗방울 gird에 적용
        for(int i =0; i<drops.length; i++){
            int x = drops[i][0];
            int y = drops[i][1];
            
            grid[x][y] = i+1;
        }
        
        // 이분탐색
        int left = 0;
        int right = drops.length;
        while(left <= right){
            int mid = (left + right) /  2;
            int[][] sum = new int[m+1][n+1];
            
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    int val = 0;
                    if (grid[i][j] <= mid) val = 1;
                    sum[i + 1][j + 1] = val + sum[i][j + 1] + sum[i + 1][j] - sum[i][j];
                }
            }
            
            boolean flag = findSafeZone(sum);
            
            if(flag){
                left = mid +1;
            } else{
                right = mid-1;
            }
        }
        
        return answer;
    }
    
 
    
    public static boolean findSafeZone(int[][] sum){
        for(int i=h; i<=m ; i++){
            for(int j=w; j<=n ; j++){
                int area = sum[i][j] - sum[i-h][j] - sum[i][j-w] + sum[i-h][j-w];
                if(area == 0){
                    answer = new int[]{i-h, j-w};
                    return true;
                }
            }
        }
        return false;
    }
}