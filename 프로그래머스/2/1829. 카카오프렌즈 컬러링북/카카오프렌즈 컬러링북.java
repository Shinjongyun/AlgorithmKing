import java.util.*;

class Solution {
    
    static int m, n;
    static boolean[][] visited;
    static int[][] picture;
    
    static int[][] dir = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    
    public int[] solution(int m, int n, int[][] picture) {
        
        Solution.m = m;
        Solution.n = n;
        Solution.picture = picture;
        
        visited = new boolean[m][n];
        
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                if (!visited[i][j] && picture[i][j] != 0) {
                    int areaSize = bfs(i, j);
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, areaSize);
                }
            }
        }
        
        return new int[] {numberOfArea, maxSizeOfOneArea};
    }
    
    public static int bfs(int startX, int startY) {
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {startX, startY});
        visited[startX][startY] = true;
        
        int color = picture[startX][startY];
        int count = 1;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (picture[nx][ny] != color) continue;
                
                visited[nx][ny] = true;
                q.add(new int[] {nx, ny});
                count++;
            }
        }
        
        return count;
    }
}