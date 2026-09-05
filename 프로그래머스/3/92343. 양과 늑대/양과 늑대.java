import java.util.*;

class Solution {
    
    static List<Edge>[] graph;
    static int answer = 0;
    static boolean[] isWolf;
    static int n;
    static boolean[] visited;
    
    class Edge{
        int to;
        Edge(int to){
            this.to = to;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        
        n = info.length;
        isWolf = new boolean [n];
        visited = new boolean[n];
        
        graph = new List[n];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }
        
        int idx = 0;
        for(int i : info){
            if(i == 1) isWolf[idx] = true;
            idx++;
        }
        
        for(int[] e : edges){
            graph[e[0]].add(new Edge(e[1]));
        }
        
        List<Integer> candidate = new ArrayList<>();
        dfs(0, candidate, 1, 0);
        
        return answer;
    }
    
    public static void dfs(int cur, List<Integer> candidate, int sheep, int wolf){
        
        answer = Math.max(answer, sheep);
        
        List<Integer> candi = new ArrayList<>(candidate);
        
        for(Edge e : graph[cur]){
            if(visited[e.to]) continue;
            candi.add(e.to);
        }
        
        for(int i=0; i<candi.size(); i++){
            int next = candi.get(i);
            if(visited[next]) continue;
            
            visited[next]= true;
            if(isWolf[next]){
                if(sheep > wolf + 1){
                    dfs(next, candi, sheep, wolf + 1);
                }
            }
            else{
                dfs(next, candi, sheep + 1, wolf);
            }
            visited[next] = false; 
        }
    }
}