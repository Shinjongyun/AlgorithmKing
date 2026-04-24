import java.util.*;

class Solution {
    
    static int[] answer = new int[4];
    static int n;
    static List<Edge>[] graph;
    static boolean[] isYou;
    static int me;
    
    static class Edge {
        int to;
        
        public Edge(int to) {
            this.to = to;
        }
    }
        
    public int[] solution(int[][] edges) {
        answer = new int[4];
        n = 0;
        
        for (int i = 0; i < edges.length; i++) {
            int num = Math.max(edges[i][0], edges[i][1]);
            n = Math.max(n, num);
        }
        
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        isYou = new boolean[n + 1];
        
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            graph[from].add(new Edge(to));
            isYou[to] = true;
        }
        
        // 생성 노드 찾기
        for (int i = 1; i <= n; i++) {
            if (!isYou[i] && graph[i].size() >= 2) {
                me = i;
                break;
            }
        }
        
        answer[0] = me;
        
        // 핵심 수정: start는 me가 아니라 e.to
        for (Edge e : graph[me]) {
            dfs(e.to, e.to, false);
        }
        
        return answer;
    }
    
    public static void dfs(int from, int start, boolean isStart) {
        
        // 8자 그래프
        if (graph[from].size() >= 2) {
            answer[3]++;
            return;
        }

        // 막대 그래프
        if (graph[from].size() == 0) {
            answer[2]++;
            return;
        }

        // 도넛 그래프
        if (from == start && isStart) {
            answer[1]++;
            return;
        }

        // 나가는 간선이 1개인 경우 계속 이동
        Edge next = graph[from].get(0);
        dfs(next.to, start, true);
    }
}