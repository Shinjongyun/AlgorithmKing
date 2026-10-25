import java.util.*;

class Solution {
    
    class Edge{
        int to;
        int weight; 
        int type;
        
        Edge(int t, int w, int ty){
            this.to =t ;
            this.weight = w;
            this.type = ty; 
        }
    }
    
    static List<Edge>[] graph;
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = Integer.MAX_VALUE;
        
        graph = new List[n+1]; for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
        
        for(int [] r : roads){
            
            graph[r[0]].add(new Edge(r[1], r[2], 0)); 
            graph[r[1]].add(new Edge(r[0], r[2], 1)); 
        }
        int[] trap = new int[n+1]; 
        for(int i =0; i< traps.length; i++){
            trap[traps[i]] = 1 << i;
        }
        
        PriorityQueue<int []> q = new PriorityQueue<>((a, b) -> a[1] - b[1]); 
        q.add(new int [] {start, 0, 0}); 
        
        int l = 1 << traps.length;
        int[][] dist = new int [n+1][l];
        
        for(int i=1; i<=n; i++){
            for(int j=0; j<l; j++){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[start][0] = 0;
        
        while(!q.isEmpty()){
            
            int[] cur = q.poll();
            int mask = cur[2];
            
            if(cur[1] > dist[cur[0]][mask]) continue; 
            
            for(Edge e : graph[cur[0]]){
                
                int next = e.to; 
                
                boolean a = (trap[cur[0]] & mask) != 0;
                boolean b = (trap[next] & mask) != 0;
                
                int result;
                if(a != b) result = 1;
                else result = 0;
                
                if(e.type != result) continue; 
                
                int nextBit = mask ^ trap[next];
                
                if(dist[next][nextBit] > dist[cur[0]][mask] + e.weight){
                    dist[next][nextBit] = dist[cur[0]][mask] + e.weight;
                    q.add(new int [] {next, dist[next][nextBit], nextBit});
                }
            }
        }
        
        for(int i=0; i<l; i++){
            answer = Math.min(answer, dist[end][i]);
        }
        
        return answer;
    }
}