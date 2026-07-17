import java.util.*;

class Solution {
    
    static int answer = Integer.MAX_VALUE;
    static int[][] board;
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int n;
    static boolean[][] visited;
    static int[][][] dist;
    static int INF = Integer.MAX_VALUE;
    
    public int solution(int[][] board) {
        
        this.board = board;
        n = board.length;
        visited = new boolean [n][n];
        dist = new int [n][n][2];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                dist[i][j][0] = INF;
                dist[i][j][1] = INF;
            }
        }
        
        visited[0][0] = true;
        back(0, 0, 0, 0);
        
        return answer;
    }
    
    public static void back(int type, int x, int y, int count){
        
        dist[x][y][type] = count;
        
        if(x == n-1 && y == n-1){
            answer = Math.min(answer, count); 
            return;
        }
        
        for(int i=0; i<4; i++){
            int nx = x + dir[i][0]; 
            int ny = y + dir[i][1];
            
            if(nx<0 || ny<0 || nx>=n || ny>=n || visited[nx][ny] || board[nx][ny] == 1) continue;
            
            int newCount = count + 100;
            
            if(x!=0 || y!=0){
            
                if(type==1 && i <2) newCount += 500;
                if(type==0 && i>=2) newCount += 500;
            }
            
            if(newCount > answer|| dist[nx][ny][type] < newCount) continue; 
            
            visited[nx][ny] = true;
            if(i<2) back(0, nx, ny, newCount);
            else back(1, nx, ny, newCount);            
            visited[nx][ny] = false;           
        }
    }
}