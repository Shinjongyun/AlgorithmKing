import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        // Id, 현재 x, 현재 y, 지금까지 온 거리, targetId
        Queue<int []> q = new LinkedList<>();
        for(int i=0; i<routes.length; i++){
            q.add(new int[] {i, points[routes[i][0]-1][0], points[routes[i][0]-1][1], 0, 0});
        }
        
        int done = 0;
        int count = 0;
        while(done < routes.length){
            
            Queue<int []> nq = new LinkedList<>();
            int[][] grid = new int [101][101];
            
            while(!q.isEmpty()){
                
                int[] c = q.poll();
                int cur = c[0]; 
                int x = c[1];
                int y = c[2];
                int d = c[3]; 
                int t = c[4];
                
                grid[x][y]++;
                
                if(x == points[routes[cur][t]-1][0] && y == points[routes[cur][t]-1][1]){
                    if(routes[cur].length - 1 == t){
                        done++;
                        continue; 
                    }
                    
                    t++; 
                }
                
                if(x < points[routes[cur][t]-1][0] || x > points[routes[cur][t]-1][0]){
                    
                    int nx;
                    int tx = points[routes[cur][t]-1][0];
                    if(x > tx) nx = x + dir[1][0];
                    else nx = x + dir[0][0];
                    
                    nq.add(new int[] {cur, nx, y, d+1, t});
                }
                else if(x == points[routes[cur][t]-1][0]){
                    int ny; 
                    int ty = points[routes[cur][t]-1][1];
                    
                    if(y > ty) ny = y + dir[3][1];
                    else ny = y + dir[2][1];
                    
                    nq.add(new int[] {cur, x, ny, d+1, t});
                }
                
            }
            
            for(int i=1; i<=100; i++){
                for(int j=1; j<=100; j++){
                    if(grid[i][j] >= 2) count++;
                }
            }
            q = new LinkedList<>(nq);
        }

        return count;
    }
}