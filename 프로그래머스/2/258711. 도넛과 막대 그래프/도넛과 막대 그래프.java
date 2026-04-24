import java.util.*;

class Solution {
    
    static int[] answer = new int[4];
    static int n;
    static List<Edge>[] graph;
    static boolean[] isYou;
    static int me;
    static int[] donutArr;
    static int[] degree;
    
    static class Edge {
        int to;
        boolean visited;
        
        public Edge(int to) {
            this.to = to;
        }
    }
        
    public int[] solution(int[][] edges) {
        answer = new int[4];
        n = 0;
        
        // n 추출
        for (int i = 0; i < edges.length; i++) {
            int num = Math.max(edges[i][0], edges[i][1]);
            n = Math.max(n, num);
        }
        
        degree = new int[n + 1];
        
        // 그래프 세팅
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        isYou = new boolean[n + 1];
        
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            graph[from].add(new Edge(to));
            
            // 들어오는 간선이 있는 노드 체크
            isYou[to] = true;
            
            // 나가는 간선 개수
            degree[from]++;
        }
        
        donutArr = new int[n + 1];
        
        // 생성 노드 찾기
        // 기존: !isYou[i]만 봄
        // 수정: 생성 노드는 들어오는 간선이 없고, 나가는 간선이 2개 이상
        for (int i = 1; i <= n; i++) {
            if (!isYou[i] && degree[i] >= 2) {
                me = i;
                break;
            }
        }
        
        answer[0] = me;
        
        // 생성 노드에서 연결된 그래프 하나씩 탐색
        for (Edge e : graph[me]) {
            e.visited = true;
            dfs(e.to, e.to, false, false);
        }
        
        return answer;
    }
    
    public static void dfs(int from, int start, boolean isStart, boolean hasBranch) {
        
        // DFS 도중 나가는 간선이 2개 이상인 노드를 만나면 8자 가능성 있음
        if (degree[from] >= 2) {
            hasBranch = true;
        }
        
        // 다시 시작점으로 돌아온 경우
        if (from == start && isStart) {
            
            // 중간에 분기점이 있었다면 8자
            if (hasBranch) {
                donutArr[start]++;
                
                // 시작점 자체가 8자 중심인 경우
                // 두 바퀴를 다 돌아야 8자로 카운트
                if (degree[start] >= 2) {
                    if (donutArr[start] == degree[start]) {
                        answer[3]++;
                    }
                } 
                // 시작점은 중심이 아니지만, 중간에 8자 중심을 만난 경우
                else {
                    answer[3]++;
                }
            } 
            // 분기점 없이 시작점으로 돌아오면 도넛
            else {
                answer[1]++;
            }
            
            return;
        }
        
        // 막대인 경우
        if (graph[from].size() == 0) {
            answer[2]++;
            return;
        }
        
        for (Edge e : graph[from]) {
            if (!e.visited) {
                e.visited = true;
                dfs(e.to, start, true, hasBranch);
            }
        }
    }
}