import java.util.*;

class Solution {
    
    static boolean[] isGate;
    static boolean[] isSummit;
    static int n;
    static List<Edge>[] graph;
    static int maxDist= 0;
    static int minDist = Integer.MAX_VALUE;
    static int[] answer = new int [2];
    static int[] gates;
    static int[] summits;
    
    static class Edge{
        int to;
        int dist;
        public Edge(int t, int d){
            this.to = t;
            this.dist = d;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        Arrays.sort(summits);
        
        this.n = n;
        this.gates = gates;
        this.summits = summits;
        
        isGate = new boolean[n+1];
        isSummit = new boolean [n+1];
        
        for(int i=0; i<gates.length; i++){
            isGate[gates[i]] = true;
        }
        
        for(int i=0; i<summits.length; i++){
            isSummit[summits[i]] = true;
        }
        
        graph = new List[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int [] p : paths){
            
            int from = p[0];
            int to = p[1];
            int dist = p[2];
            
            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
            maxDist = Math.max(dist, maxDist);
            minDist = Math.min(dist, minDist);
        }
        
        int low = minDist;
        int high = maxDist;
        while(low <= high){
            
            int mid = (low + high) / 2;
            
            if(canPlace(mid)) high = mid - 1;
            else low = mid + 1;
        }
        
        return answer;
    }
    
    public static boolean canPlace(int mid){
    
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        for(int gate : gates){
            q.add(gate);
            visited[gate] = true;
        }

        int bestSummit = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            int cur = q.poll();

            for(Edge e : graph[cur]){

                // mid보다 큰 간선은 못 지나감
                if(e.dist > mid) continue;

                // 이미 방문한 곳이면 스킵
                if(visited[e.to]) continue;

                // 다른 출입구로 들어가면 안 됨
                if(isGate[e.to]) continue;

                // 산봉우리에 도착한 경우
                if(isSummit[e.to]){
                    bestSummit = Math.min(bestSummit, e.to);
                    visited[e.to] = true;
                    continue;
                }

                visited[e.to] = true;
                q.add(e.to);
            }
        }

        if(bestSummit != Integer.MAX_VALUE){
            answer[0] = bestSummit;
            answer[1] = mid;
            return true;
        }

        return false;
    }
}