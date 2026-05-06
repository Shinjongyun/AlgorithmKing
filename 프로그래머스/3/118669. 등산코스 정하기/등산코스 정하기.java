import java.util.*;

class Solution {
    
    static int[] d;
    static int[] answer = new int[2];
    static List<Edge>[] graph;
    static int INF = Integer.MAX_VALUE;
    static int n;
    static int[] summits;
    static int[] gates;

    static boolean[] isGate;
    static boolean[] isSummit;
    static boolean[] visited;
    
    static class Edge {
        int to;
        int w;

        public Edge(int t, int w) {
            this.to = t;
            this.w = w;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        Arrays.sort(summits);
        
        this.n = n;
        this.summits = summits;
        this.gates = gates;

        graph = new ArrayList[n + 1];
        isGate = new boolean[n + 1];
        isSummit = new boolean[n + 1];
        
        for(int i=0; i<gates.length; i++){
            isGate[gates[i]] = true;
        }
        
        for(int i=0; i<summits.length; i++){
            isSummit[summits[i]] = true;
        }

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        int left = INF;
        int right = 0;
        for (int i = 0; i < paths.length; i++) {
            int from = paths[i][0];
            int to = paths[i][1];
            int w = paths[i][2];
            
            graph[from].add(new Edge(to, w));
            graph[to].add(new Edge(from, w));
            right = Math.max(w, right);
            left = Math.min(w, left);
        }

        while(left <= right){
            visited = new boolean [n+1];
            int mid = (left + right) / 2;
            
            if(canPlace(mid)) right = mid -1;
            else left = mid + 1;
        }

       return answer;
    }
    
    public static boolean canPlace(int limit){
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<gates.length; i++){
            q.add(gates[i]);
            visited[gates[i]] = true;
        }
        
        while(!q.isEmpty()){
            
            int cur = q.poll();
            
            for(Edge e : graph[cur]){
                if(isGate[e.to] || visited[e.to]) continue;
                if(e.w > limit) continue;
                visited[e.to] = true;
                
                // 산봉우리에 도착하면 더 이상 그 뒤로 탐색하지 않음
                if (isSummit[e.to]) {
                    continue;
                }
                
                q.add(e.to);
            }
        }
        
        for(int i=0; i<summits.length; i++){
            if(visited[summits[i]]) {
                answer[0] = summits[i];
                answer[1] = limit;
                return true;
            }
        }
        return false;
    }
}