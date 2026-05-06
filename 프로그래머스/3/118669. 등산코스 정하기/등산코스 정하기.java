import java.util.*;

class Solution {
    
    static int[] d;
    static int[] answer = new int[2];
    static List<Edge>[] graph;
    static int INF = Integer.MAX_VALUE;
    static int n;

    static boolean[] isGate;
    static boolean[] isSummit;
    
    static class Edge {
        int to;
        int w;

        public Edge(int t, int w) {
            this.to = t;
            this.w = w;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        this.n = n;

        graph = new ArrayList[n + 1];
        isGate = new boolean[n + 1];
        isSummit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < paths.length; i++) {
            int from = paths[i][0];
            int to = paths[i][1];
            int w = paths[i][2];
            
            graph[from].add(new Edge(to, w));
            graph[to].add(new Edge(from, w));
        }

        for (int gate : gates) {
            isGate[gate] = true;
        }

        for (int summit : summits) {
            isSummit[summit] = true;
        }

        // 산봉우리 번호 작은 순서 우선
        Arrays.sort(summits);

        // 모든 gate에서 동시에 출발
        dij(gates);

        int minIntensity = INF;
        int bestSummit = 0;

        for (int summit : summits) {
            if (d[summit] < minIntensity) {
                minIntensity = d[summit];
                bestSummit = summit;
            }
        }

        answer[0] = bestSummit;
        answer[1] = minIntensity;
 
        return answer;
    }
    
    public static void dij(int[] gates) {
    
        PriorityQueue<int[]> q = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        d = new int[n + 1];
        Arrays.fill(d, INF);

        // 모든 출입구를 시작점으로 넣기
        for (int gate : gates) {
            d[gate] = 0;
            q.add(new int[]{gate, 0});
        }

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int now = cur[0];
            int cost = cur[1];

            if (cost > d[now]) continue;

            // 산봉우리에 도착하면 스킵
            if (isSummit[now]) continue;

            for (Edge e : graph[now]) {
                int next = e.to;

                // 다른 출입구로 들어가면 스킵
                if (isGate[next]) continue;

                // 경로 중 가장 큰 간선 비용을 저장해야 함
                int nextC = Math.max(d[now], e.w);

                if (d[next] > nextC) {
                    d[next] = nextC;
                    q.add(new int[]{next, d[next]});
                }
            }
        }
    }
}