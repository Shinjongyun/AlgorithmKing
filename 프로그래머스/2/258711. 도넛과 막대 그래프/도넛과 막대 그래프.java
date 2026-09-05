import java.util.*;

class Solution {
    
    static List<Edge>[] graph;
    static int[] answer = new int [4];
    
    class Edge{
        int to;
        boolean visited;
        Edge(int to){
            this.to = to;
            visited = false;
        }
    }
    
    public int[] solution(int[][] edges) {
        
        int n = 0;
        for(int[] e : edges){
            n = Math.max(n, e[0]);
            n = Math.max(n, e[1]);
        }
        
        graph = new List[n+1];
        for(int i=1 ; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for (int[] e : edges){
            graph[e[0]].add(new Edge(e[1]));
        }
        
        int start = 0;
        boolean[] check = new boolean[n+1];
        for(int i=0; i<edges.length; i++){
            int you = edges[i][1];
            check[you] = true;
        }
        
        for(int i=1; i<=n; i++){
            if(!check[i] && graph[i].size() >= 2){
                start = i;
                answer[0] = start; 
                break; 
            }
        }
        
        for(Edge e : graph[start]){
            bfs(e.to);
        }
        
        return answer;
    }
    
    public static void bfs (int start){
        
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        
        boolean isPal = false;
        while(!q.isEmpty()){
            
            int cur = q.poll();
            
            if(graph[cur].size() >= 2){
                answer[3]++;
                return;
            }
            
            if(graph[cur].size() == 0){
                answer[2]++;
                return; 
            }
            
            for(Edge e : graph[cur]){
                
                if(e.visited) continue;
                e.visited = true;
                q.add(e.to);
            }
        }
        
        answer[1]++;
    }
}