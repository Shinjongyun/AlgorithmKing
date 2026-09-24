class Solution {
    
    static int[][] dun;
    static int answer = 0;
    static int n;
    static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        dun = dungeons;
        n = dun.length; 
        visited = new boolean[n]; 
        
        
        dfs(0, k, 0);
        return answer;
    }
    
    public static void dfs(int depth, int remain, int dist){
        
        if(depth == n){
            answer = Math.max(answer, dist);
            return; 
        }
        
        for(int i=0; i<n; i++){
            if(visited[i] || remain < dun[i][0]) continue; 
            visited[i] = true; 
            dfs(depth + 1, remain - dun[i][1], dist + 1); 
            visited[i] = false; 
        }
        dfs(depth + 1, remain, dist);
    }
}