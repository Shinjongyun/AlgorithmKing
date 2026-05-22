import java.util.*;

class Solution {

    static int answer = Integer.MAX_VALUE;
    static List<Edge>[] graph; 
    static int[] dist1;
    static int[] dist2;
    static int n; 
    
    static class Edge{
        int to;
        int weight;
        public Edge(int t, int w){
            this.to = t;
            this.weight = w;
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        this.n = n;
        
        graph = new List[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for (int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int w = fare[2];

            graph[c].add(new Edge(d, w));
            graph[d].add(new Edge(c, w));
        }
        
        dij1(s);
        int[] dist = new int[n+1];
        for(int i=1; i<=n; i++){
            dij2(i);
            int total = dist1[i] + dist2[b] + dist2[a];
            answer = Math.min(total, answer);
        }
        return answer; 
    } 
    
    public static void dij1(int start){
        
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dist1 = new int[n+1];
        
        for(int i=1; i<=n; i++){
            dist1[i] = Integer.MAX_VALUE;
        }
        
        pq.add(new int[]{start, 0});
        dist1[start] = 0;
        
        while(!pq.isEmpty()){
            
            int[] p = pq.poll();
            int cur = p[0];
            int weight = p[1];
            
            if(weight > dist1[cur]) continue;
            
            for(Edge e : graph[cur]){
                
                if(dist1[e.to] > dist1[cur] + e.weight){
                    dist1[e.to] = dist1[cur] + e.weight;
                    pq.add(new int[] {e.to, dist1[e.to]});
                }
            }
        }
    }
    
    public static void dij2(int start){
        
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dist2 = new int[n+1];
        
        for(int i=1; i<=n; i++){
            dist2[i] = Integer.MAX_VALUE;
        }
        
        pq.add(new int[]{start, 0});
        dist2[start] = 0;
        
        while(!pq.isEmpty()){
            
            int[] p = pq.poll();
            int cur = p[0];
            int weight = p[1];
            
            if(weight > dist2[cur]) continue;
            
            for(Edge e : graph[cur]){
                
                if(dist2[e.to] > dist2[cur] + e.weight){
                    dist2[e.to] = dist2[cur] + e.weight;
                    pq.add(new int[] {e.to, dist2[e.to]});
                }
            }
        }
    }
}