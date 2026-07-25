import java.util.*;

class Solution {
    
    static int[] answer = new int [4];
    static int n;
    static List<Edge>[] graph; 
    static int[] count;
    
    class Edge{
        int to;
        boolean visited; 
        Edge(int to){
            this.to = to;
            this.visited = false;
        }
    }
    
    public int[] solution(int[][] edges) {
       
        n = 0;
        for(int[] e : edges){
            n = Math.max(n, e[0]);
            n = Math.max(n, e[1]);
        }
        
        graph = new List[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        count = new int[n+1];
        
        for(int[] e : edges){
            graph[e[0]].add(new Edge(e[1]));
        }
        
        boolean[] visited = new boolean[n+1];
        for(int[] e : edges){
            visited[e[1]] = true;
        }
        for(int i=1; i<=n; i++){
            if(!visited[i] && graph[i].size() >= 2){
                answer[0] = i;
                break;
            }
        }
        
        for(Edge e : graph[answer[0]]){
            bfs(e.to);
        }
        return answer;
    }
    
    public static void bfs(int start){
        
        
        
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        count[start]++;
        boolean isPal = false; 
        
        while(!q.isEmpty()){
            
            int cur = q.poll();
            
            // System.out.print(cur + " ");
            
            if(graph[cur].size() == 2) isPal = true;
            
            if(graph[cur].size() == 0){
                answer[2]++;
                return;
            }
            
            for(Edge e : graph[cur]){
                
                if(e.to == start && isPal){
                    answer[3]++;
                    return;
                }
                
                if(e.to == start && !isPal){
                    answer[1]++;
                    return; 
                }
                
                if(e.visited) continue;
                e.visited = true;
                q.add(e.to);
            }
        }
    }
}