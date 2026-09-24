import java.util.*;

class Solution {
    
    class Edge{
        int to;
        int cost;
        Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        List<Edge>[] graph = new List[n]; for(int i=0; i<n; i++) graph[i] = new ArrayList<>(); 
        for(int[] c : costs){
            graph[c[0]].add(new Edge(c[1], c[2]));
            graph[c[1]].add(new Edge(c[0], c[2]));
        }
         
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> q = new PriorityQueue<>((a, b) -> a.cost - b.cost); 
        q.add(new Edge(0, 0));
        
        int cnt = 0;
        int mst = 0;
        while(!q.isEmpty()){
            
            Edge cur = q.poll();
            
            if(visited[cur.to]) continue;
            
            mst += cur.cost;
            visited[cur.to] = true; 
            if(cnt++ == n - 1) break; 
            
            for(Edge e : graph[cur.to]){
                if(visited[e.to]) continue; 
                q.add(new Edge(e.to, e.cost));
            }
        }
        return mst; 
    }
}