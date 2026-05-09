import java.util.*;

class Solution {
    
    static int answer = 0;
    static int[][] board;
    static int[][] sum;
    static int n, m;
    
    public int solution(int[][] board, int[][] skill) {
        n = board.length;
        m = board[0].length;
        this.board = board; 
        sum = new int [n+1][m+1];
        
        for(int[] p : skill){
            
            int r1 = p[1];
            int c1 = p[2];
            int r2 = p[3];
            int c2 = p[4];
            int type = p[0];
            int degree = p[5]; 
             
            if(type == 1) degree *= -1;
            
            sum[r1][c1] += degree;
            sum[r2 + 1][c1] -= degree;
            sum[r1][c2 + 1] -= degree;
            sum[r2 + 1][c2 + 1] += degree;
            
        }
        
        for(int i=0; i<=n; i++){
            for(int j=1; j<=m; j++){
                sum[i][j] += sum[i][j-1];
            }
        }
        
        for(int j=0; j<=m; j++){
            for(int i=1; i<=n; i++){ 
                sum[i][j] += sum[i-1][j];
            }
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(sum[i][j] + board[i][j] > 0) answer++; 
            }
        }
        return answer; 
    }
}