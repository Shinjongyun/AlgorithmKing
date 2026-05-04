import java.util.*;

class Solution {
    
    static int[] answer = new int[4];
    static int n = 0;
    static List<Edge>[] graph;
    static int start;
    
    static class Edge{
        int to;
        boolean visited;
        
        public Edge(int t){
            this.to = t;
            this.visited = false;
        }
    }
        
    public int[] solution(int[][] edges) {
        
        
        for(int i=0; i<edges.length; i++){
           int from = edges[i][0];
           int to = edges[i][1];
            int one = Math.max(from, to);
            n =Math.max(n, one);
       }
        
        boolean[] find = new boolean[n+1];
        int[] out = new int[n + 1];          // 나가는 간선 개수
        
        for(int i=0; i<edges.length; i++){
             int from = edges[i][0];
           int to = edges[i][1];
            find[to] = true;
            out[from]++;
       }
        
        for(int i=1; i<=n; i++){
            if(!find[i] && out[i] >= 2){
                start = i;
                break;
            }
        }
        answer[0] = start;
        
        graph = new List[n + 1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        
       for(int i=0; i<edges.length; i++){
           int from = edges[i][0];
           int to = edges[i][1];
           graph[from].add(new Edge(to));
       }
        
        int[] time = new int[n+1];
        dfs(start, true, 0, time);
        return answer;
    }
    
    public static void dfs(int cur, boolean isDonut, int cycle, int[] time){

        for(Edge e : graph[cur]){
            
            boolean nextIsDonut = isDonut;

            if (graph[e.to].size() != 1) {
                nextIsDonut = false;
            }
            
            if(!e.visited){
                if(cur == start){
                    cycle = e.to;
                }
                
                if(graph[e.to].size() == 0){
                    answer[2]++;
                    continue;
                }
                
                if(cycle == e.to && isDonut && time[e.to] == graph[e.to].size()){
                    answer[1]++;
                }
                
                if(cycle == e.to && !isDonut && time[e.to] == graph[e.to].size()){
                    answer[3]++;
                }
                
                e.visited  = true;
                time[e.to]++;
                dfs(e.to, nextIsDonut, cycle, time);
            }
        }
    }
}